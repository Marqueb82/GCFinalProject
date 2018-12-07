package co.grandcircus.RideHard.HereCodeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisplayPosition {

	@JsonProperty("Latitude")
	private String Latitude;
	@JsonProperty("Longitude")
	private String Longitude;
	
	public DisplayPosition() {
		super();
	}
	public DisplayPosition(String latitude, String longitude) {
		super();
		Latitude = latitude;
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	


}
