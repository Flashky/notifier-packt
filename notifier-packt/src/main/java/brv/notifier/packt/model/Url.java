package brv.notifier.packt.model;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * Enumeration of Packt site URLs.
 * <p>
 * Does not contain any relative paths, but can be concatenated using the provided functions.
 * </p>
 * @author Flashky
 *
 */
public enum Url {

	/** Shop Url: <br> https://www.packtpub.com */
	SHOP(Protocol.HTTPS,		"www.packtpub.com"),
	
	/** Subscribe Url: <br> https://subscription.packtpub.com */
	SUBSCRIBE(Protocol.HTTPS,	"subscription.packtpub.com"),
	
	/** Services Url: <br> https://services.packtpub.com */
	SERVICES(Protocol.HTTPS,	"services.packtpub.com"),
	
	/** Static Url: <br> https://static.packt-cdn.com */
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
	
	/**
	 * Obtains the fully qualified url for the selected enum value.
	 * <p>Example:</p>
	 * <pre>
	 * String url = Url.SHOP.build(); 
	 * // returns: 'https://www.packtpub.com'
	 * </pre>
	 * <p>
	 * Summarizing this method works as a shorthand for the following:
	 * </p>
	 * <pre>
	 * Url.SHOP.getUriComponentsBuilder().build().toString();
	 * </pre>
	 * @return The url String representation of the selected enum value.
	 * @see Url#path(String)
	 * @see Url#getUriComponentsBuilder()
	 */
	public String build() {
		return getUriComponentsBuilder()
				.build().toString();
	}
	
	/**
	 * Obtains the fully qualified url for the selected enum value and provided path.
	 * <p>Example:</p>
	 * <pre>
	 * String url = Url.SHOP.path("/my-path/"); 
	 * // returns: 'https://www.packtpub.com/my-path/'
	 * </pre>
	 * <p>
	 * Summarizing this method works as a shorthand for the following:
	 * </p>
	 * <pre>
	 * Url.SHOP.getUriComponentsBuilder().path("/my-path/").build().toString();
	 * </pre>
	 * @param path - the path add to the url
	 * @return The url String representation of the selected enum value concatenated with the path.
	 * @see Url#build()
	 * @see Url#getUriComponentsBuilder()
	 */
	public String path(String path) {
		return getUriComponentsBuilder()
				.path(path)
				.build().toString();
	}
	
	/**
	 * Initializes a {@link UriComponentsBuilder} using the enum values to build complexes URLs.
	 * <p>
	 * Use this method if you need to do anything not covered by {@link Url#build()} or {@link Url#path(String)}.
	 * </p>
	 * @return UriComponentsBuilder
	 * @see Url#build()
	 * @see Url#path(String)
	 */
	public UriComponentsBuilder getUriComponentsBuilder() {
		return UriComponentsBuilder
				.newInstance()
				.scheme(protocol.getScheme())
				.host(host);
	}
	
	
	
	
	
	
}
