package brv.notifier.packt.model;

import java.io.Serializable;

public class PacktOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2608866765763128611L;
	
	/**
	 * Book title.
	 * <p>IE: Spring Framework Cookbook</p>
	 */
	private String title;
	
	/** 
	 * Book url.
	 * <p>Url which points to the book details page</p>
	 */
	private String bookUrl;
	
	
	/**
	 * Image url.
	 * <p>Url which contains the book cover image.</p>
	 */
	private String imageUrl;
	
	/**
	 * Offer url.
	 * <p>Url which points to the free packt offer page to claim the book.</p>
	 */
	private String offerUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOfferUrl() {
		return offerUrl;
	}
	
	public void setOfferUrl(String offerUrl) {
		this.offerUrl = offerUrl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookUrl == null) ? 0 : bookUrl.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((offerUrl == null) ? 0 : offerUrl.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		PacktOffer other = (PacktOffer) obj;
		if (bookUrl == null) {
			if (other.bookUrl != null)
				return false;
		} else if (!bookUrl.equals(other.bookUrl))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (offerUrl == null) {
			if (other.offerUrl != null)
				return false;
		} else if (!offerUrl.equals(other.offerUrl))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	
	

	
}
