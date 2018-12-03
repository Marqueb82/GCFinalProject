package co.grandcircus.RideHard.TicketMaster;

import java.util.Arrays;

import com.ticketmaster.discovery.model.Events;

public class TicketMasterAPIResponse {
	
	private Embedded _embedded;
	
	private Events[] events; 
	
	public TicketMasterAPIResponse() {	
	}

	public Events[] getEvents() {
		return events;
	}

	public void setEvents(Events[] events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "TicketMasterAPIResponse [events=" + Arrays.toString(events) + "]";
	}
	
	
	
	

}
