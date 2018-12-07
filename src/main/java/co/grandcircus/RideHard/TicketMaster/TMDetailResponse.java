package co.grandcircus.RideHard.TicketMaster;

import com.ticketmaster.discovery.model.Classification;
import com.ticketmaster.discovery.model.Event.PriceRange;

public class TMDetailResponse {
	
	private Classification[] classifications;
	private PriceRange[] priceRanges;
	
	

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

}
