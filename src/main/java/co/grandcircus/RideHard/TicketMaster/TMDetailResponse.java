package co.grandcircus.RideHard.TicketMaster;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketmaster.discovery.model.Classification;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event.PriceRange;

public class TMDetailResponse {
	
	private Classification[] classifications;
	private PriceRange[] priceRanges;
	@Id
	@OneToOne
	private String id; 
	private String name; 
	@JsonProperty("_embedded")
	private Embedded1 _embedded;
	private Date dates;

	
	public TMDetailResponse(Classification[] classifications, PriceRange[] priceRanges) {
		super();
		this.classifications = classifications;
		this.priceRanges = priceRanges;
	}

	public PriceRange[] getPriceRanges() {
		return priceRanges;
	}

	public void setPriceRanges(PriceRange[] priceRanges) {
		this.priceRanges = priceRanges;
	}

	public TMDetailResponse() {
		super();
	}

	public Classification[] getClassifications() {
		return classifications;
	}

	public void setClassifications(Classification[] classifications) {
		this.classifications = classifications;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}
}
