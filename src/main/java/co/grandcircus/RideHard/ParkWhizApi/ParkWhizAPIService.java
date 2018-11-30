package co.grandcircus.RideHard.ParkWhizApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParkWhizAPIService {

	@Value("${parkapiKey}")
	private String apiKey;
	
	RestTemplate restTemplate = new RestTemplate();

	public Park[] getPark() {
		

	//	Map<String, String> request = new HashMap<>();
	//	request.put("q=", 
		
		
		String url = "http://api.parkwhiz.com/v4/quotes/?q=coordinates:41.8857256,-87.6369590&start_time=2017-12-23T12:00&end_time=2017-12-23T20:00&api_key=" + apiKey;

		Park[] response = restTemplate.getForObject(url, Park[].class);

		return response;
	}
}
