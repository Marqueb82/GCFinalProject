package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StraightLine {

	@JsonProperty("meters")
	private Double meters;
	@JsonProperty("feet")
	private Double feet;

	public Double getMeters() {
		return meters;
	}

	public void setMeters(Double meters) {
		this.meters = meters;
	}

	public Double getFeet() {
		return feet;
	}

	public void setFeet(Double feet) {
		this.feet = feet;
	}

	@Override
	public String toString() {
		return "StraightLine [meters=" + meters + ", feet=" + feet + "]";
	}

}
