package brv.notifier.packt.properties;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@ConfigurationProperties(prefix="endpoint")
public class EndpointProperties implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3361786595828620277L;
	
	private String offers;
	private String summary;
	
	public void setOffers(String offers) {
		this.offers = offers;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String buildOffersEndpoint(Date startDate) {
		return "";
	}
	
	public String buildOffersEndpoint(Date startDate, Date endDate) {
		
		return MessageFormat.format(offers, startDate, endDate);
	}
	
	public String buildSummaryEndpoint(Long productId) {
		return MessageFormat.format(summary, productId);
	}
	
}
