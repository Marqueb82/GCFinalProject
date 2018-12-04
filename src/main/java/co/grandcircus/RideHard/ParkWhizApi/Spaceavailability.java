package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spaceavailability {

	@JsonProperty("status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Spaceavailability [status=" + status + "]";
	}

}
