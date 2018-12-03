package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StraightLine {

	@JsonProperty("meters")
	private Integer meters;
	@JsonProperty("feet")
	private Integer feet;

	public Integer getMeters() {
		return meters;
	}

	public void setMeters(Integer meters) {
		this.meters = meters;
	}

	public Integer getFeet() {
		return feet;
	}

	public void setFeet(Integer feet) {
		this.feet = feet;
	}

	@Override
	public String toString() {
		return "StraightLine [meters=" + meters + ", feet=" + feet + "]";
	}

}
