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

import co.grandcircus.RideHard.ParkWhizApi.Park;
import co.grandcircus.RideHard.ParkWhizApi.ParkWhizAPIService;
import co.grandcircus.RideHard.TicketMaster.Event;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIResponse;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIService;
import co.grandcircus.RideHard.TicketMaster.Venue;

@Controller
public class RideController {

	@Autowired
	private TicketMasterAPIService tmAPI;

	@Autowired
	private ParkWhizAPIService pwas;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		String aString = "I'm a String, about to be sent to a JSP!";
		mv.addObject("JSP_data", aString);
		return mv;
	}

	// None of these controller methods really matter at this point from here on.
	// These are to serve as placeholders only to prove the API calls are working.

	// potential controller to demonstrate Ticket Master API call
	@RequestMapping("/ticketmasterAPI")
	public ModelAndView tmAPI(@RequestParam(name = "Search", required = false) String searchTerm, HttpSession session,
			RedirectAttributes redir) throws IOException {
		ModelAndView mv = new ModelAndView("tmAPI");

		if (searchTerm == null) {
			searchTerm = "Justin Timberlake";
		}
		TicketMasterAPIResponse pr = tmAPI.searchEvents(searchTerm);

		List<Event> events = pr.get_embedded().getEvents();
		Venue venue = pr.get_embedded().getEvents().get(0).get_embedded().getVenues().get(0);
		mv.addObject("Events", events);
		session.setAttribute("Events", events);
		return mv;
	}

	@RequestMapping("/park/{eventId}")
	public ModelAndView getPark(@PathVariable("eventId") String eventId, HttpSession session,
			RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView("park");
		List<Event> events = (List<Event>) session.getAttribute("Events");
		Event event = new Event();
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getId().equals(eventId)) {
				event = events.get(i);
			}
		}

		Park[] response = pwas.getPark(event.get_embedded().getVenues().get(0).getLocation().getLatitude(),
				event.get_embedded().getVenues().get(0).getLocation().getLongitude(),
				event.getDates().getStart().getLocalDate(), event.getDates().getStart().getLocalTime());
//		System.out.println("test");
//		// String parking = parks.();
//	//	String parking = response[0].toString();
//		System.out.println(response[0].getPurchaseoption());
//		System.out.println(response);

		ArrayList<Park> currentParks = new ArrayList<>();
		for (Park park : response)
			if (!park.getPurchaseoption().isEmpty()) {
				currentParks.add(park);
			}
		System.out.println(currentParks);
		mv.addObject("Parks", currentParks);
		return mv;
	}
	@RequestMapping("/howFar")
	public ModelAndView distance() {
		ModelAndView mv3 = new ModelAndView("howFar");
		return mv3;
	}

	


}
