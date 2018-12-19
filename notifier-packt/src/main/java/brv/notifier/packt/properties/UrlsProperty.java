package brv.notifier.packt.properties;

import java.io.Serializable;

public class UrlsProperty implements Serializable{
	
	private String webOffers;
	private String webRead;
	private String apiOffers;
	private String apiSummary;
	
	public String getWebOffers() {
		return webOffers;
	}
	
	public void setWebOffers(String webOffers) {
		this.webOffers = webOffers;
	}
	
	public String getWebRead() {
		return webRead;
	}
	
	public void setWebRead(String webRead) {
		this.webRead = webRead;
	}
	
	public String getApiOffers() {
		return apiOffers;
	}
	
	public void setApiOffers(String apiOffers) {
		this.apiOffers = apiOffers;
	}
	
	public String getApiSummary() {
		return apiSummary;
	}
	
	public void setApiSummary(String apiSummary) {
		this.apiSummary = apiSummary;
	}
	
	
}
