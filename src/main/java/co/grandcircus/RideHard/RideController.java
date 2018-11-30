package co.grandcircus.RideHard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Events;

import co.grandcircus.RideHard.ParkWhizApi.Park;
import co.grandcircus.RideHard.ParkWhizApi.ParkWhizAPIService;
import co.grandcircus.RideHard.TicketMaster.TicketMasterAPIService;
import co.grandcircus.RideHard.entity.EnumsAreFun;

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
	public ModelAndView tmAPI() throws IOException {
		ModelAndView mv = new ModelAndView("tmAPI");
		SearchEventsOperation seo = new SearchEventsOperation();
		seo.keyword("justin timberlake");
		System.out.println("check");
		PagedResponse<Events> pr = tmAPI.searchEvents(seo);
		System.out.println("checktwo");
		String test = pr.getJsonPayload();
		mv.addObject("pagedresponse", test);
		return mv;
	}

	@RequestMapping("/park")
	public ModelAndView getPark() {
		ModelAndView mv = new ModelAndView("park");
		Park park = pwas.getPark();
		mv.addObject("park", park);
		return mv;
	}

	// ditto CoOrd API
	@RequestMapping("/CoOrdAPI")
	public ModelAndView coOrdAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("coOrdAPI");

		return mv;
	}

	// Same, Uber
	@RequestMapping("/uberAPI")
	public ModelAndView ubAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("ubAPI");

		return mv;
	}

	// etc.
	@RequestMapping("/lyftAPI")
	public ModelAndView lyAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("lyAPI");

		return mv;
	}

	@RequestMapping("/spotHeroAPI")
	public ModelAndView shAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("shAPI");

		return mv;
	}

	// I forget if we landed on actually using this or how, but seems good.
	@RequestMapping("/mapsAPI")
	public ModelAndView gmAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("gmAPI");

		return mv;
	}

	// Demo of list display in JSP
	@RequestMapping("/list")
	public ModelAndView lists() {
		ModelAndView mv = new ModelAndView("listexample");
		List<Integer> numbersList = new ArrayList<Integer>();
		// list items are displayed in the order they were place in the list.
		numbersList.add(4);
		numbersList.add(5);
		numbersList.add(8);
		numbersList.add(34);
		numbersList.add(9);
		numbersList.add(2);
		mv.addObject("numbers", numbersList);

		Map<String, Integer> hm = new HashMap<>();
		// Don't know what determines what order the HashMap displays in the JSP. Seems
		// to do whatever it feels like, unless the key is in numerical order? Will
		// probably lost several hours sleep on this this weekend, in the meantime,
		// would could use HashMaps because HashMaps are neat.
		hm.put("What", 100);
		hm.put("order", 99);
		hm.put("are", 98);
		hm.put("these", 999);
		hm.put("in", 97);
		mv.addObject("Dec6th", hm);

		// In fact, let's make a HashMap of Enums! Obviously, the most superior object
		// in all of OOP.
		Map<Enum, Character> hm2 = new HashMap<>();
		hm2.put(EnumsAreFun.WHAT, 'a');
		hm2.put(EnumsAreFun.YOU, 'c');
		hm2.put(EnumsAreFun.THOUGHT, 'e');
		hm2.put(EnumsAreFun.I, 'g');
		hm2.put(EnumsAreFun.WAS, 'b');
		hm2.put(EnumsAreFun.JOKING, 'c');
		hm2.put(EnumsAreFun.ABOUT, 'e');
		hm2.put(EnumsAreFun.THE, 'f');
		hm2.put(EnumsAreFun.ENUMS, 'h');
		mv.addObject("SecondOne", hm2);

		return mv;

		// This is what it's like being in a group with me. Hope you're having fun!
	}

}
