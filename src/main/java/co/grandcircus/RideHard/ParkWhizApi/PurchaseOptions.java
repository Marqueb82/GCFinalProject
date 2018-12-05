package co.grandcircus.RideHard.ParkWhizApi;

import com.ticketmaster.discovery.model.Page.Link;

public class PurchaseOptions {

	private String id = "";
	private Price price;
	private Link link;

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Purchasing " + price + "pay at " + link;
	}

}
