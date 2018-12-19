package brv.notifier.packt.model.api;

import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.model.Url;

public enum UrlEndpoint {
	
	OFFERS(Url.SERVICES, "/free-learning-v1/offers?dateFrom={dateFrom}&dateTo={dateTo}"),
	SUMMARY(Url.STATIC, "/products/{productId}/summary");
	
	private Url url;
	private String path;
	
	private UrlEndpoint(Url url, String path) {
		this.url = url;
		this.path = path;
	}
	
	public UriComponentsBuilder getUriComponentsBuilder() {
		
		return UriComponentsBuilder
				.newInstance()
				.scheme(url.getProtocol().getScheme())
				.host(url.getHost())
				.path(path);
		
	}
}
