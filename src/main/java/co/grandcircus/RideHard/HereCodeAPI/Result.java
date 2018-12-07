package co.grandcircus.RideHard.HereCodeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

	@JsonProperty("Location")
	private Location location;

	
	public Result(Location location) {
		super();
		this.location = location;
	}

	public Result() {
		super();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
		@Override
	public String toString() {
		return "Result [location=" + location + "]";
	}

}
