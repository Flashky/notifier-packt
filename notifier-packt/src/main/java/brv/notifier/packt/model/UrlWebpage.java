package brv.notifier.packt.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum UrlWebpage {

	FREE_OFFER(Url.SHOP, "/packt/offers/free-learning");
	
	private Url url;
	private String path;
	
	private UrlWebpage(Url url, String path) {
		this.url = url;
		this.path = path;
	}
	
	public UriComponentsBuilder getUriComponentsBuilder() {
		return UriComponentsBuilder
				.newInstance()
				.scheme(url.getProtocol().getScheme())
				.host(url.getHost());
	}
	
	public String getAbsolutePath() {
		return getUriComponentsBuilder().build().toUriString();
	}
	
	
}
