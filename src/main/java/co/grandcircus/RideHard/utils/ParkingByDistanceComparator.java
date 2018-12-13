package co.grandcircus.RideHard.utils;

import java.util.Comparator;

import co.grandcircus.RideHard.ParkWhizApi.Park;

public class ParkingByDistanceComparator implements Comparator<Park> {

	//Method to sort Park objects by distance. Called as part of comparator.
	public int compare(Park a, Park b) {
		return (int) (a.getDistanceInFeet() - b.getDistanceInFeet());
	}
}
