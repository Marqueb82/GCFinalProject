package co.grandcircus.RideHard.ParkWhizApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkWhizAPIService {

	@Value("${parkWhizKey}")
	private String apiKey;

	RestTemplate restTemplate = new RestTemplate();

	public Park[] getPark() {

		// Map<String, String> request = new HashMap<>();
		// request.put("q=",

		String url = "http://api.parkwhiz.com/v4/quotes/?q=coordinates:42.3368,-83.0500&start_time=2018-12-08T07:00&end_time=2018-12-08T13:00&api_key="
				+ apiKey;
		System.out.println(url);
		Park[] response = restTemplate.getForObject(url, Park[].class);

		return response;
	}
}
