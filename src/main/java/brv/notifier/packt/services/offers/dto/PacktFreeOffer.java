package brv.notifier.packt.services.offers.dto;

import java.io.Serializable;
import java.util.Arrays;

public class PacktFreeOffer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7044969747287734366L;
	
	private String title;
	private String coverImage;
	private byte[] coverImageBytes;
	private String shopUrl;
	private String readUrl;
	private String oneLiner;
	private String type;
	
	
	/**
	 * Book full description.
	 */
	private String about;
	
	/**
	 * Skills you will learn when reading this book.
	 */
	private String learn;
	
	
	/**
	 * Main topics related to the book.
	 */
	private String features;
	
	public String getTitle() {
		return title;
	}
	public String getCoverImage() {
		return coverImage;
	}
	
	public String getShopUrl() {
		return shopUrl;
	}
	public String getReadUrl() {
		return readUrl;
	}

	public String getOneLiner() {
		return oneLiner;
	}

	public String getAbout() {
		return about;
	}

	public String getLearn() {
		return learn;
	}

	public String getFeatures() {
		return features;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}
	public void setOneLiner(String oneLiner) {
		this.oneLiner = oneLiner;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public void setLearn(String learn) {
		this.learn = learn;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getCoverImageBytes() {
		return coverImageBytes;
	}
	public void setCoverImageBytes(byte[] coverImageBytes) {
		this.coverImageBytes = coverImageBytes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((coverImage == null) ? 0 : coverImage.hashCode());
		result = prime * result + Arrays.hashCode(coverImageBytes);
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((learn == null) ? 0 : learn.hashCode());
		result = prime * result + ((oneLiner == null) ? 0 : oneLiner.hashCode());
		result = prime * result + ((readUrl == null) ? 0 : readUrl.hashCode());
		result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (coverImage == null) {
			if (other.coverImage != null)
				return false;
		} else if (!coverImage.equals(other.coverImage))
			return false;
		if (!Arrays.equals(coverImageBytes, other.coverImageBytes))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (learn == null) {
			if (other.learn != null)
				return false;
		} else if (!learn.equals(other.learn))
			return false;
		if (oneLiner == null) {
			if (other.oneLiner != null)
				return false;
		} else if (!oneLiner.equals(other.oneLiner))
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

	

	
}
