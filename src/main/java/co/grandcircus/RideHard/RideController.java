package co.grandcircus.RideHard;

import java.io.IOException;
import java.util.ArrayList;
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
import co.grandcircus.RideHard.TicketMaster.Event;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIResponse;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIService;

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
	private ForMath math;	
	@Autowired
	private HereCodeAPIService geo;


	// Controller for index page. Accepts parameters from search fields, maintains
	// the session.
	@RequestMapping("/")
	public ModelAndView tmAPI(@RequestParam(name = "Search", required = false) String searchTerm,
			@RequestParam(name = "City", required = false) String searchCity, HttpSession session,
			RedirectAttributes redir) throws IOException {
		// Creates MAV object to hold the JSP page.
		ModelAndView mv = new ModelAndView("index");
		// Variable to hold the Ticket Master API response.
		TicketMasterAPIResponse pr;

		// Logic to determine display of index page.
		if (searchTerm == null || searchCity == null) {
			// No form submission. Only seen upon initial pageload.
			return new ModelAndView("index");
		} else if (searchTerm.isEmpty() && searchCity.isEmpty()) {
			// Form submitted empty.
			return new ModelAndView("index", "EventMessage", "Please enter either an event or city to search.");
		} else if (searchTerm.isEmpty()) {
			// City only is searched.
			pr = tmAPI.citySearchEvents(searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("index", "CityMessage", "Please enter a valid city name.");
			}
		} else if (searchCity.isEmpty()) {
			// Keyword only is searched.
			pr = tmAPI.searchEvents(searchTerm);
			if (pr.get_embedded() == null) {
				return new ModelAndView("index", "EventMessage", "Sorry, we can't find that event!");
			}
		} else {
			// keyword and city are searched.
			pr = tmAPI.searchEvents(searchTerm, searchCity);
			if (pr.get_embedded() == null) {
				return new ModelAndView("index", "EventMessage", "Sorry, we can't find that event!");
			}
		}

		// Generates list of events for display on search page, ignoring those without
		// start times.
		List<Event> events = math.filterTimeless(pr.get_embedded().getEvents());

		// Places the events list on the ModelAndView page.
		mv.addObject("Events", events);
		return mv;
	}

	// Controller for page for user to add their preferences.
	@RequestMapping("/howFar/{eventId}")
	public ModelAndView distance(@PathVariable("eventId") String eventId, HttpSession session,
			RedirectAttributes redir) {
		ModelAndView mv3 = new ModelAndView("howFar");
		// Creates an Event object to store the event details.
		Event event = tmAPI.eventDetails(eventId);

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
		// redirect back to park page without losing those variables.
		if (howFar != null) {
			session.setAttribute("howFar", howFar);
		}
		if (vSize != null) {
			session.setAttribute("vSize", vSize);
		}
		if (drivingDistance != null) {
			session.setAttribute("DriveD", drivingDistance);
		}

		ModelAndView mv = new ModelAndView("park");
		// Pulling event from the session for it's details.
		Event event = (Event) session.getAttribute("Event");

		// Lists to hold our Park objects, one from the DB and one from the API
		List<Park> dbParking = math.findParkingFromDatabase(session);
		List<Park> apiParking = math.findParkingFromApi(session);
		// List to contain both sets of Park objects.
		List<Park> allParking = new ArrayList<>();
		// Methods to Actually add both types of Park objects to same list.
		allParking.addAll(dbParking);
		allParking.addAll(apiParking);

		allParking.sort((h1, h2) -> h1.getPrice().compareTo(h2.getPrice()));
		if (allParking.isEmpty()) {
			return new ModelAndView("index", "EventMessage",
					"We're sorry, there isn't any parking for that location. Maybe you'd consider adding one.");
		}
		Park cheapestPark = allParking.get(0);

		// Calls the method to order the list.
		math.orderList(allParking, session);

		// Pull the price info from the event.
		Double ticketPrice = math.reasonablePrice(event.getPriceRanges()[0]);
		String range = "$" + event.getPriceRanges()[0].getMin() + " - $" + event.getPriceRanges()[0].getMax();

		session.setAttribute("TicketPrice", ticketPrice);
		session.setAttribute("TicketRange", range);

		// Determines gas costs based on driving distance. Calls method to apply gas
		// cost factor. Then stores to the session.
		Double driveDistance = (Double) session.getAttribute("DriveD");
		Double gasCost = math.gasCalc(driveDistance);
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
		Park valuePark = math.bestValue(allParking);
		Park closestPark = allParking.get(0);
		mv.addObject("allParking", allParking);

		// Adds remaining attributes to the JSP. Should refactor to add all allowable
		// objects to be placed this way, rather than adding them to the session.
		mv.addObject("event", event);
		mv.addObject("CheapPark", cheapestPark);
		mv.addObject("ClosePark", closestPark);
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
		parkingSpot.setLatLong(geo.getLatLong(parkingSpot));
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

	// Controller sends user back to the start of the starts and invalidate session.
	@RequestMapping("/back")
	public ModelAndView back(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}