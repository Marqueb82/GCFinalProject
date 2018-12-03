package co.grandcircus.RideHard.TicketMaster;

import java.util.List;

public class Embedded1 {
	
	private List<Venue> venues;
	
	public Embedded1() {}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public Embedded1(List<Venue> venues) {
		super();
		this.venues = venues;
	}

	@Override
	public String toString() {
		return "Embedded1 [venues=" + venues + "]";
	}
	
	

}
