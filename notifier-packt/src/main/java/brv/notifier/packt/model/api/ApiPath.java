package brv.notifier.packt.model.api;

import brv.notifier.packt.model.Url;

/**
 * Enumeration of useful API paths.
 * <p>
 * Does not contain any absolute paths. Use of {@link Url#getUrl(String)} enumeration is recommended to build the full qualified URL using the path.
 * </p>
 * @author Flashky
 * @see Url
 */
public enum ApiPath {
	
	/**
	 * Endpoint: <pre>/free-learning-v1/offers</pre>
	 * <p>
	 * This endpoint provides a list of free offers.
	 * </p>
	 * <p>
	 * Known optional parameters:
	 * </p>
	 * <ul>
	 * 	<li>dateFrom: The starting date from which to pick the offers. <pre>dateFrom=2018-12-20</pre></li>
	 * 	<li>dateTo: The ending date from which to pick the offers. <pre>dateTo=2018-12-21</pre></li>
	 * </ul>
	 */
	OFFERS("/free-learning-v1/offers"),
	
	/**
	 * Endpoint: <pre>/products/{productId}/summary</pre>
	 * <p>
	 * This endpoint provides information about a specific product (usually, a book)
	 * </p>
	 */
	SUMMARY("/products/{productId}/summary");
	
	private String path;
	
	private ApiPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
