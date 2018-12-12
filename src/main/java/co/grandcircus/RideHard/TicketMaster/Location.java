package co.grandcircus.RideHard.TicketMaster;

import co.grandcircus.RideHard.ParkWhizApi.Park;

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

	public double distanceFrom(Park park) {
		final double EARTH_RADIUS_FEET = 20902230.9711;
		
		double latOne = Double.parseDouble(latitude);
		double longOne = Double.parseDouble(longitude);
		
		double lat1 = Math.toRadians(latOne);
		double long1 = Math.toRadians(longOne);
		double lat2 = Math.toRadians(park.getLatitude());
		double long2 = Math.toRadians(park.getLongitude());
		// apply the spherical law of cosines with a triangle composed of the
		// two locations and the north pole
		double theCos = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2);
		double arcLength = Math.acos(theCos);
		return arcLength * EARTH_RADIUS_FEET;
	}

}
