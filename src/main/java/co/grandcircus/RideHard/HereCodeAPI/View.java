package co.grandcircus.RideHard.HereCodeAPI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class View {
	
	@JsonProperty("Result")
	private List<Result> result;

	public View() {
		super();
	}

	public View(List<Result> result) {
		super();
		this.result = result;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "View [result=" + result + "]";
	}

	
}
