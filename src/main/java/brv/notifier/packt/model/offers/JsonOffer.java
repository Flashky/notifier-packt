package brv.notifier.packt.model.offers;

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
	
}
