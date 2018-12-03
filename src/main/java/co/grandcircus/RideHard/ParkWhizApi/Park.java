package co.grandcircus.RideHard.ParkWhizApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Park {

	@JsonProperty("location_id")
	private Long locationId;
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("end_time")
	private String endTime;
	@JsonProperty("min_start")
	private String minStart;
	@JsonProperty("max_end")
	private String maxEnd;
	@JsonProperty("purchase_options")
	private List<PurchaseOptions> purchaseOption;
	@JsonProperty("distance")
	private Distance distance;

	public Park() {
	}

	public Park(Long locationId, String startTime, String endTime, String minStart, String maxEnd,
			List<PurchaseOptions> purchaseOption, Distance distance) {
		super();
		this.locationId = locationId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.minStart = minStart;
		this.maxEnd = maxEnd;
		this.purchaseOption = purchaseOption;
		this.distance = distance;
	}

	public String getMinStart() {
		return minStart;
	}

	public void setMinStart(String minStart) {
		this.minStart = minStart;
	}

	public String getMaxEnd() {
		return maxEnd;
	}

	public void setMaxEnd(String maxEnd) {
		this.maxEnd = maxEnd;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public List<PurchaseOptions> getPurchaseOption() {
		return purchaseOption;
	}

	public void setPurchaseOption(List<PurchaseOptions> purchaseOption) {
		this.purchaseOption = purchaseOption;
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
		return "Park [locationId=" + locationId + ", startTime=" + startTime + ", endTime=" + endTime + ", minStart="
				+ minStart + ", maxEnd=" + maxEnd + ", purchaseOption=" + purchaseOption + ", distance=" + distance
				+ "]";
	}

}
