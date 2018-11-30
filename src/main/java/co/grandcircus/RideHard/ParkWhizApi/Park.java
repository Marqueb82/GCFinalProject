package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Park {

	
	@JsonProperty("location_id")
	private Long locationId;
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("end_time")
	private String endTime;

	public Park() {
	}

	public Park(Long locationId, String startTime, String endTime) {
		super();
		this.locationId = locationId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Park [locationId=" + locationId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
