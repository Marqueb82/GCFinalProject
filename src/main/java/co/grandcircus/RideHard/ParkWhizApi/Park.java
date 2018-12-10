package co.grandcircus.RideHard.ParkWhizApi;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.grandcircus.RideHard.HereCodeAPI.HereCodeAPIService;

@Entity
public class Park {

	@JsonProperty("location_id")
	@Transient // The @Transient annotation basically tells the Hibernate not to try to apply
				// this field to a table column.
	private Long locationId;
	@JsonProperty("start_time")
	@Transient
	private String starttime;
	@JsonProperty("end_time")
	@Transient
	private String endtime;
	@JsonProperty("min_start")
	@Transient
	private String minstart;
	@JsonProperty("max_end")
	@Transient
	private String maxend;
	// Why is the list singular and the class plural? -ED
	// the parkwhiz API begins in an array from the beginning,
	// via David he said it would be best to put this in a list because it would be
	// easier to manage
	// ED stay out--😡😡😡😡😡
	@JsonProperty("purchase_options")
	@Transient
	private List<PurchaseOptions> purchaseoption;
	@JsonProperty("distance")
	@Transient
	private Distance distance;
	@JsonProperty("pricing_segments")
	@Transient
	private PriceSegments priceSegments;
	@JsonProperty("space_availability")
	@Transient
	private Spaceavailability spaceavailabilty;
	@Transient
	@JsonProperty("_embedded")
	private Embedded embedded;
	private Double price; // testing
	private Double latitude; // should be
	private Double longitude; // should be
	private String name; // testing
	private String address; // testing
	private String city; // testing
	private Double purchasingprice;
	@Transient
	private Double distanceInFeet;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Autowired
	@Transient
	private HereCodeAPIService geo;

	public Park() {

	}

	public Park(Long id, Double purchasingprice, String name, String address, String city) {
		this.id = id;
		this.price = purchasingprice;
		this.name = name;
		this.address = address;
		this.city = city;
		this.setLatLong(geo.getLatLong(this));

	}

	public Park(Long id, Double purchasingprice, String name, String address, String city, Double latitude,
			Double longitude) {

		this.id = id;
		this.purchasingprice = purchasingprice;
		this.name = name;
		this.address = address;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setLatLong(Double[] latLong) {
		this.latitude = latLong[0];
		this.longitude = latLong[1];

	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPurchaseoption(List<PurchaseOptions> purchaseoption) {
		if (purchaseoption.size() == 0) {
			this.price = null;
		} else {
			this.purchaseoption = purchaseoption;
			this.price = purchaseoption.get(0).getPrice().getUsd();
		}
	}

	public String getCost() {
		return "Purchasing " + purchaseoption.get(0).getPrice();
	}

	// This is how the API represents distance
	public void setDistance(Distance distance) {
		this.distanceInFeet = distance.getStraightline().getFeet();
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
		this.name = embedded.getLocation().getName();
		this.address = embedded.getLocation().getAddress1();
		this.city = embedded.getLocation().getCity();
		this.embedded = embedded;
	}

	public Double getPurchasingprice() {
		return purchasingprice;
	}

	public void setPurchasingprice(Double purchasingprice) {
		this.purchasingprice = purchasingprice;
	}

	@Override
	public String toString() {
		return "Park locationId=" + locationId + ", starttime=" + starttime + ", endtime=" + endtime + ", minstart="
				+ minstart + ", maxend=" + maxend + ", purchaseoption=" + purchaseoption + ", distance=" + distance
				+ ", priceSegments=" + priceSegments + ", spaceavailabilty=" + spaceavailabilty + ", embedded="
				+ embedded;
	}

	public String getAddress() {

		return this.address;
	}

	// One unified property for distance
	public Double getDistanceInFeet() {
		return distanceInFeet;
	}

	public void setDistanceInFeet(Double distanceInFeet) {
		this.distanceInFeet = distanceInFeet;
	}

	public String getDistanceDescription() {
		if (distanceInFeet > 2000) {
			return String.format("%.1f mi", (distanceInFeet / 5420));
		} else {
			return String.format("%d ft", Math.round(distanceInFeet));
		}
	}

}