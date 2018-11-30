package co.grandcircus.RideHard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RideController {
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		String aString = "I'm a String, about to be sent to a JSP!";
		mv.addObject("JSP_data", aString);
		return mv;
	}

	//None of these controller methods really matter at this point from here on. These are to serve as placeholders only to prove the API calls are working.
	
	//potential controller to demonstrate Ticket Master API call
	@RequestMapping("/ticketmasterAPI")
	public ModelAndView tmAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("tmAPI");
		
		return mv;
	}
	
	//ditto CoOrd API
	@RequestMapping("/CoOrdAPI")
	public ModelAndView coOrdAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("coOrdAPI");
		
		return mv;
	}
	
	//Same, Uber
	@RequestMapping("/uberAPI")
	public ModelAndView ubAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("ubAPI");
		
		return mv;
	}
	
	//etc. 
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
	
	
	//I forget if we landed on actually using this or how, but seems good.
	@RequestMapping("/mapsAPI")
	public ModelAndView gmAPI(@RequestParam("somedata") String probably) {
		ModelAndView mv = new ModelAndView("gmAPI");
		
		return mv;
	}

	//Demo of list display in JSP
	@RequestMapping("/list")
	public ModelAndView lists() {
		ModelAndView mv = new ModelAndView("listexample");
		List<Integer> numbersList = new ArrayList<Integer>();
		numbersList.add(4);
		numbersList.add(5);
		numbersList.add(8);
		numbersList.add(34);
		numbersList.add(9);
		numbersList.add(2);
		mv.addObject("numbers", numbersList);
		
		Map<String, Integer> hm = new HashMap<>();
		//Don't know what determines what order the HashMap displays in the JSP.
		hm.put("What", 100);
		hm.put("order", 99);
		hm.put("are", 98);
		hm.put("these", 999);
		hm.put("in", 97);
		mv.addObject("Dec6th", hm);
				
		return mv;
	}
	
}
