package co.grandcircus.RideHard.ParkWhizApi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkWhizAPIService {

	RestTemplate restTemplate = new RestTemplate();

	public Park getPark() {

		String url = "https://api.parkwhiz.com/v4/";

		ParkWhizResponse response = restTemplate.getForObject(url, ParkWhizResponse.class);

		return response.getPark();
	}
}
