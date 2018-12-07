package co.grandcircus.RideHard.GeoCodeAPI;

import java.util.List;

public class GeoCodeResponse {
	
	List<Result> results ;
	
	public GeoCodeResponse() {
		super();
	}

	public GeoCodeResponse(List<Result> results) {
		super();
		this.results = results;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	

}
