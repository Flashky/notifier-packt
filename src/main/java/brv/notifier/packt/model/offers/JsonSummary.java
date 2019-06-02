package brv.notifier.packt.model.offers;

import java.io.Serializable;

public class JsonSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2608866765763128611L;
	
	/**
	 * Unique product identifier.
	 */
	private Long productId;
	
	/**
	 * Book title.
	 * <p>IE: Spring Framework Cookbook</p>
	 */
	private String title;

	/**
	 * Cover image url for the book.
	 */
	private String coverImage;

	/** 
	 * Book url which points to the book details page.
	 */
	private String shopUrl;
	
	/**
	 * Book url which points to the book read page.
	 */
	private String readUrl;
	
	/**
	 * Book quick description in one line.
	 */
	private String oneLiner;
	
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

	
	public String getOneLiner() {
		return oneLiner;
	}

	public void setOneLiner(String oneLiner) {
		this.oneLiner = oneLiner;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLearn() {
		return learn;
	}

	public void setLearn(String learn) {
		this.learn = learn;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
}
