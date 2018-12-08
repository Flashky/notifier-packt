package brv.notifier.packt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import brv.notifier.packt.model.CssSelector;

@Component
@ConfigurationProperties(prefix="service")
public class PacktProperties {
	
	private String cron;
	private String offerUrl;
	private CssSelector cssSelector;
	
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
	public String getOfferUrl() {
		return offerUrl;
	}
	
	public void setOfferUrl(String offerUrl) {
		this.offerUrl = offerUrl;
	}
	
	public CssSelector getCssSelector() {
		return cssSelector;
	}
	
	public void setCssSelector(CssSelector cssSelector) {
		this.cssSelector = cssSelector;
	}
	
	
}
