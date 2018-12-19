package brv.notifier.packt.model.api;

import java.io.Serializable;

public class JsonSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2608866765763128611L;
	
	
	private Long productId;
	
	/**
	 * Book title.
	 * <p>IE: Spring Framework Cookbook</p>
	 */
	private String title;

	
	private String coverImage;

	/** 
	 * Book url which points to the book details page.
	 */
	private String shopUrl;
	
	/**
	 * Book url which points to the book read page.
	 */
	private String readUrl;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getReadUrl() {
		return readUrl;
	}

	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		JsonSummary other = (JsonSummary) obj;
		if (coverImage == null) {
			if (other.coverImage != null)
				return false;
		} else if (!coverImage.equals(other.coverImage))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
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
		return "JsonSummary [productId=" + productId + ", title=" + title + ", coverImage=" + coverImage + ", shopUrl="
				+ shopUrl + ", readUrl=" + readUrl + "]";
	}
	
	

	
	
	
	
	
}
