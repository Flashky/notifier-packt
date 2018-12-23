package brv.notifier.packt.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
	
	protected PacktFreeOffer(PacktFreeOfferBuilder builder) {
		
		this.title 		= builder.title;
		this.coverImage = builder.coverImage;
		this.offerUrl	= builder.offerUrl;
		this.shopUrl 	= builder.shopUrl;
		this.readUrl 	= builder.readUrl;
		this.oneLiner 	= builder.oneLiner;
		this.about		= builder.about;
		this.learn		= builder.learn;
		this.features	= builder.features;
		
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

	@Override
	public int hashCode() {
		
		HashCodeBuilder builder = new HashCodeBuilder();
		
		builder.append(coverImage)
		.append(title)
		.append(readUrl)
		.append(shopUrl)
		.append(readUrl)
		.append(oneLiner)
		.append(about)
		.append(learn)
		.append(features);
		
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
		
		final PacktFreeOffer other = (PacktFreeOffer) obj;
		EqualsBuilder builder = new EqualsBuilder();
		
		builder.append(coverImage, other.coverImage)
		.append(title, other.title)
		.append(readUrl, other.readUrl)
		.append(shopUrl,  other.shopUrl)
		.append(oneLiner, other.oneLiner)
		.append(about, other.about)
		.append(learn, other.learn)
		.append(features, other.features);
		
		return builder.isEquals();
	}

	@Override
	public String toString() {
		
		ToStringBuilder builder = new ToStringBuilder(this);
		
		builder.append(coverImage)
		.append(title)
		.append(readUrl)
		.append(shopUrl)
		.append(readUrl)
		.append(oneLiner)
		.append(about)
		.append(learn)
		.append(features);
		
		return builder.toString();
	}
	
	
	
}
