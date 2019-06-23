package brv.notifier.packt.services.offers.dto;

import java.io.Serializable;
import java.util.Objects;

public class PacktFreeOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3253201491308498766L;
	
	private String title;
	private String coverImage;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(about, coverImage, features, learn, oneLiner, readUrl, shopUrl, title, type);
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
		return Objects.equals(about, other.about) && Objects.equals(coverImage, other.coverImage)
				&& Objects.equals(features, other.features) && Objects.equals(learn, other.learn)
				&& Objects.equals(oneLiner, other.oneLiner) && Objects.equals(readUrl, other.readUrl)
				&& Objects.equals(shopUrl, other.shopUrl) && Objects.equals(title, other.title)
				&& Objects.equals(type, other.type);
	}
	

	
}
