package brv.notifier.packt.model.json;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonOffers other = (JsonOffers) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PacktOfferList [data=" + data + "]";
	}
	
	
	
	
}
