package brv.notifier.packt.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum Url {

	SHOP(Protocol.HTTPS,		"www.packtpub.com"),
	SUBSCRIBE(Protocol.HTTPS,	"subscribe.packtpub.com"),
	SERVICES(Protocol.HTTPS,	"services.packtpub.com"),
	STATIC(Protocol.HTTPS,		"static.packt-cdn.com");
	
	private Protocol protocol;
	private String host;

	private Url(Protocol protocol, String host) {
		this.protocol = protocol;
		this.host = host;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}
	
	public String getBasepath() {
		return UriComponentsBuilder.newInstance().scheme(protocol.getScheme()).host(host).toString();
	}

	
	
	
	
}
