package co.grandcircus.RideHard.HereCodeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty("DisplayPosition")
	private DisplayPosition displayPosition;

	public Location() {
		super();
	}

	public Location(DisplayPosition displayPosition) {
		super();
		this.displayPosition = displayPosition;
	}

	public DisplayPosition getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(DisplayPosition displayPosition) {
		this.displayPosition = displayPosition;
	}

	@Override
	public String toString() {
		return "Location [displayPosition=" + displayPosition + "]";
	}
		
}
