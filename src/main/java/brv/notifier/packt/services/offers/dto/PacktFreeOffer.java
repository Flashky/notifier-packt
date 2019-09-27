package brv.notifier.packt.services.offers.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	

	

	
}
