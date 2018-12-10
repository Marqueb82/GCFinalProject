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
		TicketMasterAPIResponse pr;
		if (searchTerm == null && searchCity == null) {
			searchTerm = " ";
			searchCity = " ";
			pr = tmAPI.searchEvents(searchTerm, searchCity);
		} else if (searchTerm == null) {
			pr = tmAPI.citySearchEvents(searchCity);
		} else if (searchCity == null) {
			pr = tmAPI.searchEvents(searchTerm);
		} else {
			pr = tmAPI.searchEvents(searchTerm, searchCity);
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

		mv3.addObject("event", event);
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
		// String eventId = event.getId();
		// Event event = selectedEvent(eventId, session);
		
		// 1. Find parking
		List<Park> dbParking = findParkingFromDatabase(session);
		List<Park> apiParking = findParkingFromApi(session);
		
		// 2. Combine parking
		List<Park> allParking = new ArrayList<>();
		allParking.addAll(dbParking);
		allParking.addAll(apiParking);
		
		// 3. Resort the whole list
		// TODO
		

		TMDetailResponse detail = tmAPI.eventDetails(event.getId());
		Double ticketPrice = (detail.getPriceRanges()[0].getMax() + detail.getPriceRanges()[0].getMin()) / 2;
		String range = "$" + detail.getPriceRanges()[0].getMax() + " - $" + detail.getPriceRanges()[0].getMin();

		session.setAttribute("TicketPrice", ticketPrice);
		session.setAttribute("TicketRange", range);

		Double driveDistance = (Double) session.getAttribute("DriveD");
		Double gasCost = gasCalc(driveDistance);
		session.setAttribute("GasCost", gasCost);

		Double totalCost;
		if (session.getAttribute("Park") != null) {
			totalCost = gasCost + (Double) session.getAttribute("ParkPrice") + ticketPrice;
		} else {
			totalCost = gasCost;
		}
		session.setAttribute("TotalCost", totalCost);

		Double twoTotalCost = totalCost / 2;
		session.setAttribute("twoTotalCost", twoTotalCost);

		Double threeTotalCost = totalCost / 3;
		session.setAttribute("threeTotalCost", threeTotalCost);

		Double fourTotalCost = totalCost / 4;
		session.setAttribute("fourTotalCost", fourTotalCost);

		mv.addObject("event", event);
		mv.addObject("allParking", allParking);
		return mv;
	}

	@RequestMapping("/parkingspot")
	public ModelAndView parkingSpot() {
		return new ModelAndView("parkingspot");
	}

	@RequestMapping("/add/parkingspot")
	public ModelAndView addPark(Park parkingSpot, HttpSession session, RedirectAttributes redir) {
		Double howFar = (Double) session.getAttribute("howFar");
		ModelAndView mv = new ModelAndView("park");
		if ((parkingSpot.getLatitude() == null) || (parkingSpot.getLongitude() == null)) {
			parkingSpot.setLatLong(geo.getLatLong(parkingSpot));
		}
		pd.create(parkingSpot);

		Event event = (Event) session.getAttribute("Event");
		// String eventId = event.getId();
		// Event event = selectedEvent(eventId, session);
		List<Park> userparking = findParkingFromDatabase(session);

		Park[] response = pwas.getPark(event.get_embedded().getVenues().get(0).getLocation().getLatitude(),
				event.get_embedded().getVenues().get(0).getLocation().getLongitude(),
				event.getDates().getStart().getLocalDate(), event.getDates().getStart().getLocalTime(), howFar);

		ArrayList<Park> currentParks = new ArrayList<>();
		for (Park park : response)
			if (park.getPrice() != 99999.93) {
				currentParks.add(park);
			}
		System.out.println(currentParks);
		mv.addObject("event", event);
		mv.addObject("Parks", currentParks);
		mv.addObject("userparking", userparking);
		return mv;
	}

	@RequestMapping("/park/choose/")
	private ModelAndView choose(@RequestParam(name = "Name", required = false) String name,
			@RequestParam(name = "Price", required = false) Double parkPrice, HttpSession session, RedirectAttributes redir) {
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
			if (park.getPrice() != 99999.93) {
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
		for (Park park: parks) {
			park.setDistanceInFeet(event.get_embedded().getVenues().get(0).getLocation().distanceFrom(park));
		}
		Collections.sort(parks, new SortByDistance());
		return parks;
	}
}