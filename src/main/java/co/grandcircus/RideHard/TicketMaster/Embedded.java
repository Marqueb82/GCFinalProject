package co.grandcircus.RideHard.TicketMaster;

import java.util.List;


public class Embedded {
	
	private List<Event> events;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Embedded(List<Event> events) {
		super();
		this.events = events;
	}
	public Embedded() {}

	@Override
	public String toString() {
		return "Embedded [events=" + events + "]";
	}
	
	
	
	

}
