package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Distance {

	@JsonProperty("straight_line")
	private StraightLine straightline;

	public StraightLine getStraightline() {
		return straightline;
	}

	public void setStraightline(StraightLine straightline) {
		this.straightline = straightline;
	}

	@Override
	public String toString() {
		return "Distance [straightline=" + straightline + "]";
	}

}
