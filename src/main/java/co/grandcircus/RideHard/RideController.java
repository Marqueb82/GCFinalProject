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

	// potential controller to demonstrate Ticket Master API call
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

		ArrayList<Park> currentParks = new ArrayList<>();
		for (Park park : response)
			if (!park.getPurchaseoption().isEmpty()) {
				currentParks.add(park);
			}
		System.out.println(currentParks);
		mv.addObject("Parks", currentParks);
		return mv;
	}

}
