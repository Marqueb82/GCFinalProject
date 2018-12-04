package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Embedded {

	@JsonProperty("pw:location")
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Embedded [location=" + location + "]";
	}

}
