package brv.notifier.packt.services.checking;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.enums.ApiPath;
import brv.notifier.packt.model.enums.Url;

@Service
public class EndpointManager {

	private static final int MIN_NUMBER_DAYS = 1;
	private static final String QUERY_DATE_FROM = "dateFrom";
	private static final String QUERY_DATE_TO = "dateTo";
	
	@Value("${api-config.max-number-days:3}")
	private int maxNumberOfDays;
	
	
	/**
	 * Generates and offer list endpoint.
	 * <p>
	 * Offer list endpoints are like this:
	 * </p>
	 * <pre>
	 * https://services.packtpub.com/free-learning-v1/offers?dateFrom={startDate}&dateTo={endDate}
	 * </pre>
	 * <p>
	 * This method retrieves only one result after the startDate by default. 
	 * <br>If you need more results, use {@link #getOfferListEndpoint(LocalDate, int)}.
	 * </p>
	 * @param startDate the starting date to obtain data.
	 * @param numberOfDays the number of days you want to retrieve data from.
	 * @return A formatted endpoint:
	 * <pre>
	 * https://services.packtpub.com/free-learning-v1/offers?dateFrom=2018-12-24&dateTo=2018-12-25
	 * </pre>
	 * 
	 */
	public String getOfferListEndpoint(LocalDate startDate) {
		
		LocalDate endDate = startDate.plusDays(MIN_NUMBER_DAYS);
		
		return getOfferListEndpoint(startDate, endDate);
	}
	
	/**
	 * Generates and offer list endpoint.
	 * <p>
	 * Offer list endpoints are like this:
	 * </p>
	 * <pre>
	 * https://services.packtpub.com/free-learning-v1/offers?dateFrom={startDate}&dateTo={endDate}
	 * </pre>
	 * <p>
	 * Currently the API only shows deals up to three days ahead of today, so you may notice that you don't get enough results even
	 * if your numberOfDays is greater than that.
	 * </p>
	 * @param startDate the starting date to obtain data.
	 * @param numberOfDays the number of days you want to retrieve data from.
	 * @return A formatted endpoint:
	 * <pre>
	 * https://services.packtpub.com/free-learning-v1/offers?dateFrom=2018-12-24&dateTo=2018-12-30
	 * </pre>
	 */
	public String getOfferListEndpoint(LocalDate startDate, int numberOfDays) {
		
		if(numberOfDays < MIN_NUMBER_DAYS)
			throw new IllegalArgumentException("Number of days should be at least "+MIN_NUMBER_DAYS+".");
		
		LocalDate endDate = startDate.plusDays(numberOfDays);
		
		return getOfferListEndpoint(startDate, endDate);
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
		
		return Url.STATIC.getUriComponentsBuilder()
				.path(ApiPath.SUMMARY.getPath())
				.buildAndExpand(productId).toString();
		
	}
	
	/**
	 * Private helper class to build an offer list endpoint.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private String getOfferListEndpoint(LocalDate startDate, LocalDate endDate) {
		
		return Url.SERVICES.getUriComponentsBuilder()
				.path(ApiPath.OFFERS.getPath())
				.queryParam(QUERY_DATE_FROM, startDate.toString())
				.queryParam(QUERY_DATE_TO, endDate.toString())
				.build().toString();
		
	}
	
}
