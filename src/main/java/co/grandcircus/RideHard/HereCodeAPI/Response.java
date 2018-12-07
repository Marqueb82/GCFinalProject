package co.grandcircus.RideHard.HereCodeAPI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("View")
	private List<View> view;

	public Response() {
		super();
	}

	public Response(List<View> view) {
		super();
		this.view = view;
	}

	public List<View> getView() {
		return view;
	}

	public void setView(List<View> view) {
		this.view = view;
	}
	
	

}
