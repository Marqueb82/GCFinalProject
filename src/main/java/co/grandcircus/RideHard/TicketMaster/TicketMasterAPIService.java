package co.grandcircus.RideHard.TicketMaster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TicketMasterAPIService {

	@Value("${apiKey}")
	private String apiKey;

	private RestTemplate restTemplate;

	{ // This configures the restTemplateWithUserAgent to include a User-Agent header
		// with every HTTP request.
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
		restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	//Method to search for events by keyword.
	public TicketMasterAPIResponse searchEvents(String keyword) {
		String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=15&apikey=" + apiKey + "&keyword="
				+ keyword + "";
		TicketMasterAPIResponse response = restTemplate.getForObject(url, TicketMasterAPIResponse.class);
		return response;
	}

	//Method to search events by city. Helpful for narrowing results.
	public TicketMasterAPIResponse citySearchEvents(String city) {
		String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=15&apikey=" + apiKey + "&city=" + city
				+ "";
		System.out.println("citySearchEvents: " + url);
		TicketMasterAPIResponse response = restTemplate.getForObject(url, TicketMasterAPIResponse.class);
		return response;
	}

	//Method to search events for both city and keyword. Overloads keyword search.
	public TicketMasterAPIResponse searchEvents(String keyword, String city) {
		String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=15&apikey=" + apiKey + "&keyword="
				+ keyword + "&city=" + city + "";
		System.out.println("searchEvents: " + url);
		TicketMasterAPIResponse response = restTemplate.getForObject(url, TicketMasterAPIResponse.class);
		return response;
	}

	// Method to acquire price information based on "eventId". Also event details
	// for header. Other info comes in, but those things are the point of this one
	public Event eventDetails(String eventId) {
		String url = "https://app.ticketmaster.com//discovery/v2/events/" + eventId + ".json?apikey=" + apiKey;
		Event response = restTemplate.getForObject(url, Event.class);
		return response;
	}

}
