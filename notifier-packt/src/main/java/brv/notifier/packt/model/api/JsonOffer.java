package brv.notifier.packt.model.api;

import java.io.Serializable;
import java.time.LocalDate;

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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
		result = prime * result + ((expiresAt == null) ? 0 : expiresAt.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		JsonOffer other = (JsonOffer) obj;
		if (availableFrom == null) {
			if (other.availableFrom != null)
				return false;
		} else if (!availableFrom.equals(other.availableFrom))
			return false;
		if (expiresAt == null) {
			if (other.expiresAt != null)
				return false;
		} else if (!expiresAt.equals(other.expiresAt))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PacktOffer [productId=" + productId + ", availableFrom=" + availableFrom + ", expiresAt=" + expiresAt
				+ "]";
	}
	
	
	
	
}
