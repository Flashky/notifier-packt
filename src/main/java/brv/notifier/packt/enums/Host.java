package brv.notifier.packt.enums;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * Enumeration of Packt site URLs.
 * <p>
 * Does not contain any relative paths, but can be concatenated using the provided functions.
 * </p>
 * @author Flashky
 *
 */
public enum Host {

	/** Url:  <pre>https://www.packtpub.com</pre> 
	 * <p>Packt main Website.</p>
	 */
	SHOP(Protocol.HTTPS,		"www.packtpub.com"),
	
	/** Url: <pre>https://subscription.packtpub.com</pre> 
	 * <p>Packt subscription and ebook reading Website.</p>
	 */
	SUBSCRIBE(Protocol.HTTPS,	"subscription.packtpub.com"),
	
	/** Url: <pre>https://services.packtpub.com</pre> 
	 * <p>Packt Services API.</p>
	 * */
	SERVICES(Protocol.HTTPS,	"services.packtpub.com"),
	
	/** Url: <pre>https://static.packt-cdn.com</pre> 
	 * <p>Packt static content API.</p>
	 */
	STATIC(Protocol.HTTPS,		"static.packt-cdn.com");
	
	private Protocol protocol;
	private String host;

	private Host(Protocol protocol, String host) {
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
	 * @see Host#path(String)
	 * @see Host#getUriComponentsBuilder()
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
	 * @see Host#build()
	 * @see Host#getUriComponentsBuilder()
	 */
	public String path(String path) {
		return getUriComponentsBuilder()
				.path(path)
				.build().toString();
	}

	
	/**
	 * Initializes a {@link UriComponentsBuilder} using the enum values to build complexes URLs.
	 * <p>
	 * Use this method if you need to do anything not covered by {@link Host#build()} or {@link Host#path(String)}.
	 * </p>
	 * @return UriComponentsBuilder
	 * @see Host#build()
	 * @see Host#path(String)
	 */
	public UriComponentsBuilder getUriComponentsBuilder() {
		return UriComponentsBuilder
				.newInstance()
				.encode()
				.scheme(protocol.getScheme())
				.host(host);
	}
	
	
	
	
	
	
}
