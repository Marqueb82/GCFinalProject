package co.grandcircus.RideHard.TicketMaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketmaster.discovery.model.Date;

public class Event {
	
	private String id; 
	private String name; 
	@JsonProperty("_embedded")
	private Embedded1 _embedded;
	private Date dates;
	
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
	public Embedded1 get_embedded() {
		return _embedded;
	}
	public void set_embedded(Embedded1 _embedded) {
		this._embedded = _embedded;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date date) {
		this.dates = date;
	}
	public Event() {}
	public Event(String id, String name, Embedded1 _embedded) {
		super();
		this.id = id;
		this.name = name;
		this._embedded = _embedded;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", _embedded=" + _embedded + "]";
	}
	
	
	
	

}
