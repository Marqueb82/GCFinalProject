package co.grandcircus.RideHard.TicketMaster;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketMasterAPIResponse {
	
	@JsonProperty("_embedded")
	private Embedded _embedded;
		
	public TicketMasterAPIResponse() {	
	}

	public TicketMasterAPIResponse(Embedded _embedded) {
		super();
		this._embedded = _embedded;
	}

	public Embedded get_embedded() {
		return _embedded;
	}

	public void set_embedded(Embedded _embedded) {
		this._embedded = _embedded;
	}

	@Override
	public String toString() {
		return "TicketMasterAPIResponse [_embedded=" + _embedded + "]";
	}

}
