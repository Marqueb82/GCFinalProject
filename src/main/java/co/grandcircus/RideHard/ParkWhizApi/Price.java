package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

	@JsonProperty("USD")
	private Double usd = 0.0;

	public Double getUsd() {
		return usd;
	}

	public void setUsd(Double usd) {
		this.usd = usd;
	}

	@Override
	public String toString() {
		return "Price = $" + usd;
	}

}
