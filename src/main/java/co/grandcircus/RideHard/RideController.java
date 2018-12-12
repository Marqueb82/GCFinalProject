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

	// Class fields.
	// Database for user entered Park objects.
	@Autowired
	private ParkDao pd;
	// API service fields.
	@Autowired
	private TicketMasterAPIService tmAPI;
	@Autowired
	private ParkWhizAPIService pwas;
	@Autowired
	private HereCodeAPIService geo;

	// Controller for index page. Accepts parameters from search fields, maintains
	// the session.
	@RequestMapping("/")
	public ModelAndView tmAPI(@RequestParam(name = "Search", required = false) String searchTerm,
			@RequestParam(name = "City", required = false) String searchCity, HttpSession session,
			RedirectAttributes redir) throws IOException {
		// Creates MAV object to hold the JSP page.
		ModelAndView mv = new ModelAndView("tmAPI");
		// Clear previous search results to allow for events list to determine whether
		// the list displays on the page.
		session.removeAttribute("Events");
		// Variable to hold the Ticket Master API response.
		TicketMasterAPIResponse pr;

		// Logic to determine display of index page.
		if (searchTerm == null || searchCity == null) {
			// No form submission. Only seen upon initial pageload.
			return new ModelAndView("tmAPI");
		} else if (searchTerm.isEmpty() && searchCity.isEmpty()) {
			// Form submitted empty.
			return new ModelAndView("tmAPI", "EventMessage", "Please enter either an event or city to search.");
		} else if (searchTerm.isEmpty()) {
			// City only is searched.
			pr = tmAPI.citySearchEvents(searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "CityMessage", "Please enter a valid city name.");
			}
		} else if (searchCity.isEmpty()) {
			// Keyword only is searched.
			pr = tmAPI.searchEvents(searchTerm);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "EventMessage", "Sorry, we can't find that event!");
			}
		} else {
			// keyword and city.
			pr = tmAPI.searchEvents(searchTerm, searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("tmAPI", "EventMessage", "Sorry, we can't find that event!");
			}
		}

		// Generates list of events for display on search page.
		List<Event> events = pr.get_embedded().getEvents();
		// Sets list to the session to allow display on the search screen.
		session.setAttribute("Events", events);
		return mv;
	}

	// Controller for page for user to add their preferences.
	@RequestMapping("/howFar/{eventId}")
	public ModelAndView distance(@PathVariable("eventId") String eventId, HttpSession session,
			RedirectAttributes redir) {
		ModelAndView mv3 = new ModelAndView("howFar");
		// Creates an Event object to store the event details.
		Event event = selectedEvent(eventId, session);

		// mv3.addObject("event", event);
		session.setAttribute("Event", event);
		return mv3;
	}

	// Controller to process and display our results.
	@RequestMapping("/park")
	public ModelAndView getPark(@RequestParam(name = "howFar", required = false) Double howFar,
			@RequestParam(name = "vSize", required = false) Integer vSize,
			@RequestParam(name = "DrivingDistance", required = false) Double drivingDistance, HttpSession session,
			RedirectAttributes redir) {

		// Series of if statements set the user variables to the session and allow for
		// redicting back to tpark page without lsoing those variables.
		if (howFar != null) {
			session.setAttribute("howFar", howFar);
		}
		if (vSize != null) {
			session.setAttribute("vSize", vSize);
		}
		if (drivingDistance != null) {
			session.setAttribute("DriveD", drivingDistance);
		}

//		System.out.println(drivingDistance);
//		System.out.println(session.getAttribute("DriveD"));

		ModelAndView mv = new ModelAndView("park");
		// Pulling event from the session for it's details.
		Event event = (Event) session.getAttribute("Event");

		// Lists to hold our Park objects, one from the DB and one from the API
		List<Park> dbParking = findParkingFromDatabase(session);
		List<Park> apiParking = findParkingFromApi(session);
		// List to contain both sets of Park objects.
		List<Park> allParking = new ArrayList<>();
		// Methods to Actually add both types of Park objects to same list.
		allParking.addAll(dbParking);
		allParking.addAll(apiParking);

		// Calls the method to order the list.
		orderList(allParking, session);

		// Gives us additional details about the Event object. In particular, price.
		TMDetailResponse detail = tmAPI.eventDetails(event.getId());
		// Pull the price info from the new detail object.
		Double ticketPrice = detail.getPriceRanges()[0].getMin();
		String range = "$" + detail.getPriceRanges()[0].getMin() + " - $" + detail.getPriceRanges()[0].getMax();

		session.setAttribute("TicketPrice", ticketPrice);
		session.setAttribute("TicketRange", range);

		// Determines gas costs based on driving distance. Calls funtion to apply gas
		// cost factor. Then stores to the session.
		Double driveDistance = (Double) session.getAttribute("DriveD");
		Double gasCost = gasCalc(driveDistance);
		session.setAttribute("GasCost", gasCost);

		// Statements to produce a total cost, based on all other costs available. Then
		// stores to the session.
		Double totalCost;
		if (session.getAttribute("ParkPrice") != null) {
			totalCost = gasCost + (Double) session.getAttribute("ParkPrice") + ticketPrice;
		} else {
			totalCost = gasCost + ticketPrice;
		}
		session.setAttribute("TotalCost", totalCost);

		// Produces best value Park object based on cost and distance.
		Park valuePark = bestValue(allParking);

		// Adds remaining attributes to the JSP. Should refactor to add all allowable
		// objects to be placed this way, rather than adding them to the session.
		mv.addObject("event", event);
		mv.addObject("allParking", allParking);
		mv.addObject("ValuePark", valuePark);
		return mv;
	}

	// Controller direct to user Park object addition page.
	@RequestMapping("/parkingspot")
	public ModelAndView parkingSpot() {
		return new ModelAndView("parkingspot");
	}

	// Controller to direct back to main results page at park.jsp.
	@RequestMapping("/add/parkingspot")
	public ModelAndView addPark(Park parkingSpot, HttpSession session, RedirectAttributes redir) {
		// If statement will fill in the lat and long if they aren't added when the PArk
		// object is created.
		if ((parkingSpot.getLatitude() == null) || (parkingSpot.getLongitude() == null)) {
			parkingSpot.setLatLong(geo.getLatLong(parkingSpot));
		}
		// Adds parking spot object to the database.
		pd.create(parkingSpot);
		// Redirects us back to the results at /park.
		return new ModelAndView("redirect:/park");
	}

	// Controller to handle selecting a park object
	@RequestMapping("/park/choose/")
	private ModelAndView choose(@RequestParam(name = "Name", required = false) String name,
			@RequestParam(name = "Price", required = false) Double parkPrice, HttpSession session,
			RedirectAttributes redir) {
		// Set the chosen Park object's attributes to the session.
		session.setAttribute("ParkPrice", parkPrice);
		session.setAttribute("Name", name);

		return new ModelAndView("redirect:/park");
	}

	// Method pulls the event from the list of events based on the event ID.
	@SuppressWarnings("unchecked")
	private Event selectedEvent(String eventId, HttpSession session) {
		// Pulls list of events form the session.
		List<Event> events = (List<Event>) session.getAttribute("Events");
		// Creates a new event to hold the event.
		Event event = new Event();
		// Checks ids of event list objects to return the event object solected.
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getId().equals(eventId)) {
				event = events.get(i);
			}
		}
		return event;
	}

	// Calls on the Parkwhiz API to respond a list of park locations in the area of
	// the given event.
	private List<Park> findParkingFromApi(HttpSession session) {
		// Pulls useful variables from the session.
		double howFar = (double) session.getAttribute("howFar");
		Event event = (Event) session.getAttribute("Event");

		// Stores the response from the Parkwhiz API.
		Park[] response = pwas.getPark(event.get_embedded().getVenues().get(0).getLocation().getLatitude(),
				event.get_embedded().getVenues().get(0).getLocation().getLongitude(),
				event.getDates().getStart().getLocalDate(), event.getDates().getStart().getLocalTime(), howFar);

		// List to store to store the park objects from the API
		ArrayList<Park> currentParks = new ArrayList<>();
		// Checks to determine whether the parking objects have prices associated with
		// them.
		for (Park park : response) {
			if (park.getPrice() != null) {
				currentParks.add(park);
			}
		}
		return currentParks;
	}

	// Parses database for user entered park objects within the given radius from
	// the event.
	private List<Park> findParkingFromDatabase(HttpSession session) {
		// Pulls relevant data from the session.
		double howFarMiles = (double) session.getAttribute("howFar");
		// Converts to feet.
		double howFarFeet = howFarMiles * 5280;
		Event event = (Event) session.getAttribute("Event");

		// An empty list to store the Park objects from the database that fall within
		// the given radius.
		List<Park> psList = new ArrayList<Park>();
		// Stores all the park objects from the database.
		List<Park> fullList = pd.findall();
		// Fill the new list based on the datbase park objects' distance from the event.
		for (Park park : fullList) {
			if ((event.get_embedded().getVenues().get(0).getLocation().distanceFrom(park) <= howFarFeet)
					&& (park.getPrice() != null)) {
				psList.add(park);
			}
		}
		// Orders new list.
		psList = orderList(psList, session);
		return psList;
	}

	// Calculates the gas cost base on driving distance and IRS mileage factor for 2018.
	public Double gasCalc(Double drivingDistance) {
		Double gasPrice = drivingDistance * 0.525;
		return gasPrice;
	}

	//Controller sends user back to the start of the starta and invalidate session.
	@RequestMapping("/back")
	public ModelAndView back(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	// Method to order the park object lists.
	public List<Park> orderList(List<Park> parks, HttpSession session) {
		Event event = (Event) session.getAttribute("Event");
		// Converts distance from event to feet.
		for (Park park : parks) {
			// It verifies that each park object has a lat and long.
			if ((park.getLatitude() == null) || (park.getLongitude() == null)) {
				park.setLatLong(geo.getLatLong(park));
			}
			park.setDistanceInFeet(event.get_embedded().getVenues().get(0).getLocation().distanceFrom(park));
		}
		//Sorts the list based on distance from event location.
		Collections.sort(parks, new SortByDistance());
		return parks;
	}

	//Method to determine the park location with the best ratio of price to distance.
	public Park bestValue(List<Park> allParking) {
		Park temp = new Park();
		Double tempValue = null;
		
		// Loop to determine which Park object has the "best value"
		for (int i = 0; i < allParking.size(); i++) {
			//Variable stores the "value" factor of the park object, based on distance and weighted price.
			Double value = (allParking.get(i).getPrice() * allParking.get(i).getPrice())
					+ (allParking.get(i).getDistanceInFeet());
			//Determines if current park object has lower value than the previous.
			if ((tempValue == null) || (value < tempValue)) {
				tempValue = value;
				temp = allParking.get(i);
			}
		}
		return temp;
	}
}