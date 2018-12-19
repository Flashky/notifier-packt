package brv.notifier.packt.model;

import java.io.Serializable;

public class PacktFreeOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8878314968135118188L;
	
	private String title;
	private String coverImage;
	private String offerUrl;
	private String shopUrl;
	private String readUrl;
	
	protected PacktFreeOffer(PacktFreeOfferBuilder builder) {
		
		this.title 		= builder.title;
		this.coverImage = builder.coverImage;
		this.offerUrl	= builder.offerUrl;
		this.shopUrl 	= builder.shopUrl;
		this.readUrl 	= builder.readUrl;
		
	}
	
	public String getTitle() {
		return title;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public String getOfferUrl() {
		return offerUrl;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public String getReadUrl() {
		return readUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
		result = prime * result + ((offerUrl == null) ? 0 : offerUrl.hashCode());
		result = prime * result + ((readUrl == null) ? 0 : readUrl.hashCode());
		result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
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
		PacktFreeOffer other = (PacktFreeOffer) obj;
		if (coverImage == null) {
			if (other.coverImage != null)
				return false;
		} else if (!coverImage.equals(other.coverImage))
			return false;
		if (offerUrl == null) {
			if (other.offerUrl != null)
				return false;
		} else if (!offerUrl.equals(other.offerUrl))
			return false;
		if (readUrl == null) {
			if (other.readUrl != null)
				return false;
		} else if (!readUrl.equals(other.readUrl))
			return false;
		if (shopUrl == null) {
			if (other.shopUrl != null)
				return false;
		} else if (!shopUrl.equals(other.shopUrl))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PacktFreeOffer [title=" + title + ", coverImage=" + coverImage + ", offerUrl=" + offerUrl + ", shopUrl="
				+ shopUrl + ", readUrl=" + readUrl + "]";
	}
	
	
	
}
