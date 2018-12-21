package brv.notifier.packt.model.enums;

public enum WebPath {

	/**
	 * /packt/offers/free-learning
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
