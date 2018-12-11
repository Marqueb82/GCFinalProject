package co.grandcircus.RideHard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.RideHard.HereCodeAPI.HereCodeAPIService;
import co.grandcircus.RideHard.ParkDao.ParkDao;
import co.grandcircus.RideHard.ParkWhizApi.Park;
import co.grandcircus.RideHard.ParkWhizApi.ParkWhizAPIService;
import co.grandcircus.RideHard.TicketMaster.Event;
import co.grandcircus.RideHard.TicketMaster.TMDetailResponse;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIResponse;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIService;
import co.grandcircus.RideHard.entity.SortByDistance;

@Controller
public class RideController {

	@Autowired
	private ParkDao pd;
	@Autowired
	private TicketMasterAPIService tmAPI;
	@Autowired
	private ParkWhizAPIService pwas;
	@Autowired
	private HereCodeAPIService geo;

	@RequestMapping("/")
	public ModelAndView tmAPI(@RequestParam(name = "Search", required = false) String searchTerm,
			@RequestParam(name = "City", required = false) String searchCity, HttpSession session,
			RedirectAttributes redir) throws IOException {
		ModelAndView mv = new ModelAndView("tmAPI");
		session.removeAttribute("Events"); // clear previous search results

		TicketMasterAPIResponse pr;
		if (searchTerm == null || searchCity == null) {
			// No form submission
			return new ModelAndView("tmAPI");
		} else if (searchTerm.isEmpty() && searchCity.isEmpty()) {
			// form submitted empty
			return new ModelAndView("tmAPI", "EventMessage", "Please enter either an event or city to search.");
		} else if (searchTerm.isEmpty()) {
			// city only
			pr = tmAPI.citySearchEvents(searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "CityMessage", "Please enter a valid city name.");
			}
		} else if (searchCity.isEmpty()) {
			// keyword only
			pr = tmAPI.searchEvents(searchTerm);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "EventMessage", "Sorry, we can't find that event!");
			}
		} else {
			// keyword and city
			pr = tmAPI.searchEvents(searchTerm, searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "EventMessage", "Sorry, we can't find that event!");
			}
		}

		List<Event> events = pr.get_embedded().getEvents();

		session.setAttribute("Events", events);
		return mv;
	}

	@RequestMapping("/howFar/{eventId}")
	public ModelAndView distance(@PathVariable("eventId") String eventId, HttpSession session,
			RedirectAttributes redir) {
		ModelAndView mv3 = new ModelAndView("howFar");
		Event event = selectedEvent(eventId, session);

		// mv3.addObject("event", event);
		session.setAttribute("Event", event);
		return mv3;
	}

	@RequestMapping("/park")
	public ModelAndView getPark(@RequestParam(name = "howFar", required = false) Double howFar,
			@RequestParam(name = "vSize", required = false) Integer vSize,
			@RequestParam(name = "DrivingDistance", required = false) Double drivingDistance, HttpSession session,
			RedirectAttributes redir) {

		if (howFar != null) {
			session.setAttribute("howFar", howFar);
		}
		if (vSize != null) {
			session.setAttribute("vSize", vSize);
		}
		if (drivingDistance != null) {
			session.setAttribute("DriveD", drivingDistance);
		}

		System.out.println(drivingDistance);
		System.out.println(session.getAttribute("DriveD"));

		ModelAndView mv = new ModelAndView("park");
		Event event = (Event) session.getAttribute("Event");

		List<Park> dbParking = findParkingFromDatabase(session);
		List<Park> apiParking = findParkingFromApi(session);

		List<Park> allParking = new ArrayList<>();
		allParking.addAll(dbParking);
		allParking.addAll(apiParking);

		orderList(allParking, session);

		TMDetailResponse detail = tmAPI.eventDetails(event.getId());
		Double ticketPrice = detail.getPriceRanges()[0].getMin();
		String range = "$" + detail.getPriceRanges()[0].getMin() + " - $" + detail.getPriceRanges()[0].getMax();

		session.setAttribute("TicketPrice", ticketPrice);
		session.setAttribute("TicketRange", range);

		Double driveDistance = (Double) session.getAttribute("DriveD");
		Double gasCost = gasCalc(driveDistance);
		session.setAttribute("GasCost", gasCost);

		Double totalCost;
		if (session.getAttribute("ParkPrice") != null) {
			totalCost = gasCost + (Double) session.getAttribute("ParkPrice") + ticketPrice;
		} else {
			totalCost = gasCost + ticketPrice;
		}
		session.setAttribute("TotalCost", totalCost);

		// Park valuePark = bestValue(allParking);

		mv.addObject("event", event);
		mv.addObject("allParking", allParking);
		// mv.addObject("ValuePark", valuePark);
		return mv;
	}

	@RequestMapping("/parkingspot")
	public ModelAndView parkingSpot() {
		return new ModelAndView("parkingspot");
	}

	@RequestMapping("/add/parkingspot")
	public ModelAndView addPark(Park parkingSpot, HttpSession session, RedirectAttributes redir) {
		if ((parkingSpot.getLatitude() == null) || (parkingSpot.getLongitude() == null)) {
			parkingSpot.setLatLong(geo.getLatLong(parkingSpot));
		}
		pd.create(parkingSpot);
		return new ModelAndView("redirect:/park");
	}

	@RequestMapping("/park/choose/")
	private ModelAndView choose(@RequestParam(name = "Name", required = false) String name,
			@RequestParam(name = "Price", required = false) Double parkPrice, HttpSession session,
			RedirectAttributes redir) {
		session.setAttribute("ParkPrice", parkPrice);
		session.setAttribute("Name", name);

		return new ModelAndView("redirect:/park");
	}

	/**
	 * @param eventId
	 * @param session
	 * @return selected event
	 */
	@SuppressWarnings("unchecked")
	private Event selectedEvent(String eventId, HttpSession session) {
		List<Event> events = (List<Event>) session.getAttribute("Events");
		Event event = new Event();
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getId().equals(eventId)) {
				event = events.get(i);
			}
		}
		return event;
	}

	private List<Park> findParkingFromApi(HttpSession session) {
		double howFar = (double) session.getAttribute("howFar");
		Event event = (Event) session.getAttribute("Event");

		Park[] response = pwas.getPark(event.get_embedded().getVenues().get(0).getLocation().getLatitude(),
				event.get_embedded().getVenues().get(0).getLocation().getLongitude(),
				event.getDates().getStart().getLocalDate(), event.getDates().getStart().getLocalTime(), howFar);

		ArrayList<Park> currentParks = new ArrayList<>();
		for (Park park : response) {
			if (park.getPrice() != null) {
				currentParks.add(park);
			}
		}
		return currentParks;
	}

	private List<Park> findParkingFromDatabase(HttpSession session) {
		double howFarMiles = (double) session.getAttribute("howFar");
		double howFarFeet = howFarMiles * 5280;
		Event event = (Event) session.getAttribute("Event");

		List<Park> psList = new ArrayList<Park>();
		List<Park> fullList = pd.findall();
		for (Park park : fullList) {
			if (event.get_embedded().getVenues().get(0).getLocation().distanceFrom(park) <= howFarFeet) {
				psList.add(park);
			}
		}
		psList = orderList(psList, session);
		return psList;
	}

	public Double gasCalc(Double drivingDistance) {
		Double gasPrice = drivingDistance * 0.525;
		return gasPrice;
	}

	@RequestMapping("/back")
	public ModelAndView back(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	public List<Park> orderList(List<Park> parks, HttpSession session) {
		Event event = (Event) session.getAttribute("Event");
		for (Park park : parks) {
			if ((park.getLatitude() == null) || (park.getLongitude() == null)) {
				park.setLatLong(geo.getLatLong(park));
			}
			park.setDistanceInFeet(event.get_embedded().getVenues().get(0).getLocation().distanceFrom(park));
		}
		Collections.sort(parks, new SortByDistance());
		return parks;
	}

	public Park bestValue(List<Park> allParking) {
		Park temp = new Park();
		Double tempValue = null;
		for (int i = 0; i < allParking.size(); i++) {

			Double value = (allParking.get(i).getPrice() * allParking.get(i).getPrice())
					+ (allParking.get(i).getDistanceInFeet());
			if ((tempValue == null) || (value < tempValue)) {
				tempValue = value;
				temp = allParking.get(i);
			}
		}
		return temp;
	}
}