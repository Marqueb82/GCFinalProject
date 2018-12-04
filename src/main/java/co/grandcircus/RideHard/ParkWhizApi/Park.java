package co.grandcircus.RideHard.ParkWhizApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Park {

	@JsonProperty("location_id")
	private Long locationId;
	@JsonProperty("start_time")
	private String starttime;
	@JsonProperty("end_time")
	private String endtime;
	@JsonProperty("min_start")
	private String minstart;
	@JsonProperty("max_end")
	private String maxend;
	@JsonProperty("purchase_options")
	// Why is the list plural and the class singular? -ED
	// the parkwhiz API begins in an array from the beginning,
	// via David he said it would be best to put this in a list because it would be
	// easier to manage
	// ED stay out--😡😡😡😡😡
	private List<PurchaseOptions> purchaseoption;
	@JsonProperty("distance")
	private Distance distance;

	public Park() {
	}

	public Park(Long locationId, String starttime, String endtime, String minstart, String maxend,
			List<PurchaseOptions> purchaseOption, Distance distance) {
		super();
		this.locationId = locationId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.minstart = minstart;
		this.maxend = maxend;
		this.purchaseoption = purchaseOption;
		this.distance = distance;
	}

	public String getMinStart() {
		return minstart;
	}

	public void setMinStart(String minStart) {
		this.minstart = minStart;
	}

	public String getMaxEnd() {
		return maxend;
	}

	public void setMaxEnd(String maxEnd) {
		this.maxend = maxEnd;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public List<PurchaseOptions> getPurchaseOption() {
		return purchaseoption;
	}

	public void setPurchaseOption(List<PurchaseOptions> purchaseOption) {
		this.purchaseoption = purchaseOption;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getStartTime() {
		return starttime;
	}

	public void setStartTime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndTime() {
		return endtime;
	}

	public void setEndTime(String endTime) {
		this.endtime = endTime;
	}

	@Override
	public String toString() {
		return "Park [locationId=" + locationId + ", startTime=" + starttime + ", endTime=" + endtime + ", minStart="
				+ minstart + ", maxEnd=" + maxend + ", purchaseOption=" + purchaseoption + ", distance=" + distance
				+ "]";
	}

}
