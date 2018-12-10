package co.grandcircus.RideHard.entity;
 import java.util.Comparator;

import co.grandcircus.RideHard.ParkWhizApi.Park;
 public class SortByDistance implements Comparator<Park> {
 	public int compare(Park a, Park b) {
		return (int) (a.getDistanceInFeet() - b.getDistanceInFeet());
	}
 } 