package co.grandcircus.RideHard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "rider")
public class Rider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String name;
	public String phone;
	public String email;
	@ManyToOne
	public Car car;
	public Boolean driver;
	public String eventId;
	
	
	public Rider() {}
	
	
	
	public Rider(Long id, String name, String phone, String email, Car car, Boolean driver, String eventId) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.car = car;
		this.driver = driver;
		this.eventId = eventId;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Boolean getDriver() {
		return driver;
	}
	public void setDriver(Boolean driver) {
		this.driver = driver;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}



	@Override
	public String toString() {
		return "Rider [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", car=" + car
				+ ", driver=" + driver + ", eventId=" + eventId + "]";
	}
	
	
	
	
	
	
}
