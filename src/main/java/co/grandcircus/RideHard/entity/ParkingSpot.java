package co.grandcircus.RideHard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParkingSpot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String purchasingprice;
	private String name;
	private String address;
	private String city;
	private Double latitude;
	private Double longitude;

	public ParkingSpot() {
	}

	public ParkingSpot(Long id, String purchasingprice, String name, String address, String city, Double latitude,
			Double longitude) {

		this.id = id;
		this.purchasingprice = purchasingprice;
		this.name = name;
		this.address = address;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurchasingprice() {
		return purchasingprice;
	}

	public void setPurchasingprice(String purchasingprice) {
		this.purchasingprice = purchasingprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	@Override
	public String toString() {
		return "ParkingSpot [id=" + id + ", purchasingprice=" + purchasingprice + ", name=" + name + ", address="
				+ address + ", city=" + city + "]";
	}

	public void setLatLong(Double[] latLong) {
		this.latitude = latLong[0];
		
		
	}

}
