package co.grandcircus.RideHard.TicketMaster;

import co.grandcircus.RideHard.entity.ParkingSpot;

public class Location {

	private String longitude;
	private String latitude;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	public Location(String longitude, String latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Location() {
	}

	public double distanceFrom(ParkingSpot other) {
		
		double latOne = Double.parseDouble(latitude);
		double longOne = Double.parseDouble(longitude);
		
		double lat1 = Math.toRadians(latOne);
		double long1 = Math.toRadians(longOne);
		double lat2 = Math.toRadians(other.getLatitude());
		double long2 = Math.toRadians(other.getLongitude());
		// apply the spherical law of cosines with a triangle composed of the
		// two locations and the north pole
		double theCos = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2);
		double arcLength = Math.acos(theCos);
		return arcLength * 3959.0; //distance of radius in miles. The number stands for radius of the earth in miles. 
	}

}
