package co.grandcircus.RideHard.HereCodeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HereCodeResponse {

	@JsonProperty("Response")
	private Response response;

	public HereCodeResponse() {
		super();
	}

	public HereCodeResponse(Response response) {
		super();
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
