package co.grandcircus.RideHard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Integer capacity;
	public String name;
	public Car(Long id, Integer capacity, String name) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.name = name;
	}
	public Car() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", capacity=" + capacity + ", name=" + name + "]";
	}
	
	
	

}
