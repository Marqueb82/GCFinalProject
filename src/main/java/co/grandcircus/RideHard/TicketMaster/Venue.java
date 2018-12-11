package co.grandcircus.RideHard.TicketMaster;

import com.ticketmaster.discovery.model.Venue.City;

public class Venue {
    
    private String id;
    private String name;
    private Location location;
    private City city;
    
    public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
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
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Venue [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
    public Venue(String id, String name, Location location) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
    }
    public Venue() {}

}