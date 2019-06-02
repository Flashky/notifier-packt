package brv.notifier.packt.model.offers;

import java.io.Serializable;
import java.util.List;

public class JsonOffers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605618016826327972L;
	
	private List<JsonOffer> data;

	public List<JsonOffer> getData() {
		return data;
	}

	public void setData(List<JsonOffer> data) {
		this.data = data;
	}
	
}
