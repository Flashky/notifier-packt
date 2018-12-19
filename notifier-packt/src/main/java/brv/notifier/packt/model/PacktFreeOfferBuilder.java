package brv.notifier.packt.model;

import org.springframework.web.util.UriComponentsBuilder;

public class PacktFreeOfferBuilder {

	protected String title;
	protected String coverImage = "";
	protected String offerUrl = "";
	protected String shopUrl = "";
	protected String readUrl = "";
	
	private UriComponentsBuilder offerUrlBuilder 	= UriComponentsBuilder.newInstance();
	private UriComponentsBuilder shopUrlBuilder		= UriComponentsBuilder.newInstance();
	private UriComponentsBuilder readUrlBuilder		= UriComponentsBuilder.newInstance();
	
	public PacktFreeOfferBuilder(String title) {
		// TODO validar al menos title no null
		this.title = title;
	}

	public PacktFreeOfferBuilder withCoverImage(String coverImage) {
		
		if(coverImage != null)			
			this.coverImage = coverImage;
		
		return this;
	}
	
	public PacktFreeOfferBuilder withOfferUrl(String absolutePath) {
		
		if(absolutePath != null)
			this.offerUrlBuilder = UriComponentsBuilder.fromPath(absolutePath);
		
		return this;
	}
	
	public PacktFreeOfferBuilder withOfferUrl(String basePath, String offerPath) {
		
		if(basePath != null && offerPath != null)
			this.offerUrlBuilder = UriComponentsBuilder.fromPath(basePath).path(offerPath);
		
		return this;
	}
	
	
	public PacktFreeOfferBuilder withShopUrl(String absolutePath) {
		
		if(absolutePath != null)
			this.shopUrlBuilder = UriComponentsBuilder.fromPath(absolutePath);
		
		return this;
	}
	
	
	public PacktFreeOfferBuilder withShopUrl(String basePath, String relativeBookPath) {
		
		if(basePath != null && relativeBookPath != null)
			this.shopUrlBuilder = UriComponentsBuilder.fromPath(basePath).path(relativeBookPath);
		
		return this;
	}
	
	public PacktFreeOfferBuilder withReadUrl(String absolutePath) {
		
		if(absolutePath != null)
			this.readUrlBuilder = UriComponentsBuilder.fromPath(absolutePath);
		
		return this;
	}
	
	public PacktFreeOfferBuilder withReadUrl(String basePath, String relativeBookPath) {
		
		if(basePath != null && relativeBookPath != null)
			this.readUrlBuilder = UriComponentsBuilder.fromPath(basePath).path(relativeBookPath);
		
		return this;
	}

	public PacktFreeOffer build() {
		
		this.offerUrl = this.offerUrlBuilder.build().toUriString();
		this.shopUrl = this.shopUrlBuilder.build().toUriString();
		this.readUrl = this.readUrlBuilder.build().toUriString();
		return new PacktFreeOffer(this);
	}
}
