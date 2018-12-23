package brv.notifier.packt.model.json;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(data);
		
		return builder.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		// TODO this section of code could be moved to an utility class:
		// I could use instanceof, but this code fragment is much more generic
		// and reusable, as it is class agnostic.
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		final JsonOffers other = (JsonOffers) obj;
		EqualsBuilder builder = new EqualsBuilder();
		
		builder.append(data, other.data);
		
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		
		builder.append(data);
		
		return builder.toString();
	}
	
	
	
	
}
