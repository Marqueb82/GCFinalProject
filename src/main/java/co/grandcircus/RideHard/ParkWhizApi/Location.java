package co.grandcircus.RideHard.ParkWhizApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("address1")
	private String address1;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", address1=" + address1 + ", city=" + city + ", state="
				+ state + "]";
	}

}
