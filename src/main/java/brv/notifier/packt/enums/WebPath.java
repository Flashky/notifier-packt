package brv.notifier.packt.enums;

public enum WebPath {

	/**
	 * Path: <pre>/packt/offers/free-learning</pre>
	 * <p>
	 * Ebook free offers are published in this Website path.
	 * </p>
	 */
	FREE_OFFER("/packt/offers/free-learning");
	
	private String path;
	
	private WebPath(String path) {
		this.path = path;
	}
	
	
	public String getPath() {
		return path;
	}
	
	
}
