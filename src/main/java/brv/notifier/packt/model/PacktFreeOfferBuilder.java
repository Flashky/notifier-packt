package brv.notifier.packt.model;

public class PacktFreeOfferBuilder {

	protected String title;
	protected String coverImage = "";
	protected String offerUrl = "";
	protected String shopUrl = "";
	protected String readUrl = "";
	protected String oneLiner;

	
	/**
	 * Book full description.
	 */
	protected String about;
	
	/**
	 * Skills you will learn when reading this book.
	 */
	protected String learn;
	
	
	/**
	 * Main topics related to the book.
	 */
	protected String features;
	
	public PacktFreeOfferBuilder(String title) {
		this.title = title;
	}

	public PacktFreeOfferBuilder withCoverImage(String coverImage) {
		
		if(coverImage != null)			
			this.coverImage = coverImage;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withOfferUrl(String url) {
		
		if(url != null)
			this.offerUrl = url;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withShopUrl(String url) {
		
		if(url != null)
			this.shopUrl = url;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withReadUrl(String url) {
		
		if(url != null)
			this.readUrl = url;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withOneLiner(String oneLiner) {
		if(oneLiner != null)
			this.oneLiner = oneLiner;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withAbout(String about) {
		if(about != null)
			this.about = about;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withLearn(String learn) {
		if(learn != null)
			this.learn = learn;
		
		return this;
	}
	
	
	public PacktFreeOfferBuilder withFeatures(String features) {
		if(features != null)
			this.features = features;
		
		return this;
	}
	
	public PacktFreeOffer build() {
		
		return new PacktFreeOffer(this);
	}
}
