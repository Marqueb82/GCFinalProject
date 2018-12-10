package co.grandcircus.RideHard.ParkWhizApi;

public class PurchaseOptions {

	private String id = "";
	private Price price;

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
		return "Purchasing " + price + "pay at ";
	}

}
