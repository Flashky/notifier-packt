package brv.notifier.packt.model;

import java.io.Serializable;
import java.util.List;

public class PacktOfferList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605618016826327972L;
	
	private List<PacktOffer> data;

	public List<PacktOffer> getData() {
		return data;
	}

	public void setData(List<PacktOffer> data) {
		this.data = data;
	}
	
	
}
