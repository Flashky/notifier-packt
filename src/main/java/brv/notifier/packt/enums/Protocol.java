package brv.notifier.packt.enums;

public enum Protocol {

	HTTP("http"),
	HTTPS("https");
	
	private String scheme;
	
	private Protocol(String scheme) {
		this.scheme = scheme;
	}
	
	public String getScheme() {
		return scheme;
	}
}
