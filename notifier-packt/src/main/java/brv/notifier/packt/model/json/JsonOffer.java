package brv.notifier.packt.model.json;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JsonOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6929492344668185298L;
	
	private Long productId;
	private LocalDate availableFrom;
	private LocalDate expiresAt;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}
	public LocalDate getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDate expiresAt) {
		this.expiresAt = expiresAt;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(productId)
		.append(availableFrom)
		.append(expiresAt);
		
		return builder.toHashCode();
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
		
		final JsonOffer other = (JsonOffer) obj;
		EqualsBuilder builder = new EqualsBuilder();
		
		builder.append(productId, other.productId)
		.append(availableFrom, other.availableFrom)
		.append(expiresAt, other.expiresAt);
		
		return builder.isEquals();
	}
	
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		
		builder.append(productId)
		.append(availableFrom)
		.append(expiresAt);
		
		return builder.toString();
	}
	
	
	
	
}
