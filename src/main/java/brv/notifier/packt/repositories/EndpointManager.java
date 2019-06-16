package brv.notifier.packt.repositories;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import brv.notifier.packt.enums.ApiPath;
import brv.notifier.packt.enums.Host;

@Component	
public class EndpointManager {

	private static final String QUERY_DATE_FROM = "dateFrom";
	private static final String QUERY_DATE_TO = "dateTo";

	
	public String getOffersEndpoint(LocalDate dateFrom) {
		
		return Host.SERVICES.getUriComponentsBuilder()
		.path(ApiPath.OFFERS.getPath())
		.queryParam(QUERY_DATE_FROM, dateFrom)
		.build().toString();
		
	}
	
	public String getOffersEndpoint(LocalDate dateFrom, LocalDate dateTo) {
		
		return Host.SERVICES.getUriComponentsBuilder()
		.path(ApiPath.OFFERS.getPath())
		.queryParam(QUERY_DATE_FROM, dateFrom)
		.queryParam(QUERY_DATE_TO, dateTo)
		.build().toString();

		
	}
	
	
	/**
	 * Generates a summary endpoint.
	 * <p>
	 * Summary endpoints have the following look:
	 * </p>
	 * <pre>
	 * https://static.packt-cdn.com/products/{productId}/summary
	 * </pre>
	 * @param productId the product id to substitute on the path. 
	 * @return A formatted endpoint:
	 * <pre>
	 * https://static.packt-cdn.com/products/9781786462923/summary
	 * </pre>
	 */
	public String getSummaryEndpoint(Long productId) {
		
		return Host.STATIC.getUriComponentsBuilder()
				.path(ApiPath.SUMMARY.getPath())
				.buildAndExpand(productId).toString();
		
	}

	
}
