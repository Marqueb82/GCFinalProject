package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceSegments {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("end_time")
	private String endTime;
	@JsonProperty("event")
	private String event;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "PriceSegments [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", event=" + event
				+ "]";
	}

}
