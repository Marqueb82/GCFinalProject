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
	@JsonProperty("pricing_segments")
	private PriceSegments priceSegments;
	@JsonProperty("space_availability")
	private Spaceavailability spaceavailabilty;
	@JsonProperty("_embedded")
	private Embedded embedded;

	public Park() {

	}

	public Park(Long locationId, String starttime, String endtime, String minstart, String maxend,
			List<PurchaseOptions> purchaseoption, Distance distance, PriceSegments priceSegments,
			Spaceavailability spaceavailabilty, Embedded embedded) {
		super();
		this.locationId = locationId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.minstart = minstart;
		this.maxend = maxend;
		this.purchaseoption = purchaseoption;
		this.distance = distance;
		this.priceSegments = priceSegments;
		this.spaceavailabilty = spaceavailabilty;
		this.embedded = embedded;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getMinstart() {
		return minstart;
	}

	public void setMinstart(String minstart) {
		this.minstart = minstart;
	}

	public String getMaxend() {
		return maxend;
	}

	public void setMaxend(String maxend) {
		this.maxend = maxend;
	}

	public List<PurchaseOptions> getPurchaseoption() {
		return purchaseoption;
	}

	public void setPurchaseoption(List<PurchaseOptions> purchaseoption) {
		this.purchaseoption = purchaseoption;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public PriceSegments getPriceSegments() {
		return priceSegments;
	}

	public void setPriceSegments(PriceSegments priceSegments) {
		this.priceSegments = priceSegments;
	}

	public Spaceavailability getSpaceavailabilty() {
		return spaceavailabilty;
	}

	public void setSpaceavailabilty(Spaceavailability spaceavailabilty) {
		this.spaceavailabilty = spaceavailabilty;
	}

	public Embedded getEmbedded() {
		return embedded;
	}

	public void setEmbedded(Embedded embedded) {
		this.embedded = embedded;
	}

	@Override
	public String toString() {
		return "Park [locationId=" + locationId + ", starttime=" + starttime + ", endtime=" + endtime + ", minstart="
				+ minstart + ", maxend=" + maxend + ", purchaseoption=" + purchaseoption + ", distance=" + distance
				+ ", priceSegments=" + priceSegments + ", spaceavailabilty=" + spaceavailabilty + ", embedded="
				+ embedded + "]";
	}

}
