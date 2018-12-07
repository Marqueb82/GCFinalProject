package co.grandcircus.RideHard.HereCodeAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.RideHard.entity.ParkingSpot;

@Component
public class HereCodeAPIService {

	@Value("${hereId}")
	private String apId;
	@Value("${hereCode}")
	private String apiCode;

	RestTemplate restTemplate = new RestTemplate();

	public Double[] getLatLong(ParkingSpot park) {
		String addy = park.getAddress() + " " + park.getCity();
		System.out.println("" + addy);
		String url = "https://geocoder.api.here.com/6.2/geocode.json\r\n?app_id=" + apId + "&app_code=" + apiCode
				+ "&searchtext=" + addy;
//		String url = "https://geocoder.api.here.com/6.2/geocode.json?app_id=LUWetpk2tasboD76HVuG&app_code=rX04RFZsKvcq40wS_gkMXg&searchtext=1570%20woodward%20detroit";
		HereCodeResponse response = restTemplate.getForObject(url, HereCodeResponse.class);
		System.out.println(response.getResponse().getView().get(0).getResult().get(0).getLocation().getDisplayPosition().getLatitude());
		Double[] latLong = {
				Double.parseDouble(response.getResponse().getView().get(0).getResult().get(0).getLocation()
						.getDisplayPosition().getLatitude()),
				Double.parseDouble(response.getResponse().getView().get(0).getResult().get(0).getLocation()
						.getDisplayPosition().getLongitude()) };
		return latLong;
	}
}
