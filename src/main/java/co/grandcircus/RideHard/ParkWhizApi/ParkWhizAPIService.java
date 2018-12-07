package co.grandcircus.RideHard.ParkWhizApi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkWhizAPIService {

	@Value("${parkWhizKey}")
	private String apiKey;

	RestTemplate restTemplate = new RestTemplate();

	public Park[] getPark(String latitude, String longitude, String date, String time, Double howFar) {

		// Map<String, String> request = new HashMap<>();
		// request.put("q=",

		String url = "http://api.parkwhiz.com/v4/quotes/?q=coordinates:" + latitude + "," + longitude +" distance="+howFar+"&start_time=" + date + "T" + time + "&end_time=" + date + "T23:59&api_key="
				+ apiKey;
		System.out.println(url);
		Park[] response = restTemplate.getForObject(url, Park[].class);
		System.out.println(Arrays.toString(response));
		return response;
	}
}
