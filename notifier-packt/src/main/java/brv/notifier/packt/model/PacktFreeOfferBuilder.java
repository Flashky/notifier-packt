package brv.notifier.packt.model;

public class PacktFreeOfferBuilder {

	protected String title;
	protected String coverImage = "";
	protected String offerUrl = "";
	protected String shopUrl = "";
	protected String readUrl = "";
	
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

	public PacktFreeOffer build() {
		
		return new PacktFreeOffer(this);
	}
}
