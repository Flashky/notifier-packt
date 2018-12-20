package brv.notifier.packt.services.checking;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.PacktFreeOfferBuilder;
import brv.notifier.packt.model.enums.ApiPath;
import brv.notifier.packt.model.enums.Url;
import brv.notifier.packt.model.enums.WebPath;
import brv.notifier.packt.model.json.JsonOffers;
import brv.notifier.packt.model.json.JsonSummary;
import brv.notifier.packt.properties.PacktProperties;
import brv.notifier.packt.properties.ProxyProperty;
import brv.notifier.packt.services.CheckoutService;

/**
 * Implementation of CheckoutService which obtains the Packt offer data through an API.
 * @author Flashky
 *
 */
@Service
public class ApiCheckoutService implements CheckoutService {

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public PacktFreeOffer getPacktOffer() {

		PacktFreeOffer offer = null;
		
		String endpointUrl = getOfferListEndpoint();
		JsonOffers offerList = restTemplate.getForObject(endpointUrl, JsonOffers.class);
		
		if((offerList != null) && (!offerList.getData().isEmpty())) {
			
			Long productId = offerList.getData().get(0).getProductId();
			
			endpointUrl = getSummaryEndpoint(productId);
			JsonSummary offerSummary = restTemplate.getForObject(endpointUrl, JsonSummary.class);
			
			offer = mapToDto(offerSummary);
			System.out.println(offer);
		}

		return offer;
	}
	
	@Override
	public List<PacktFreeOffer> getPacktOfferList(LocalDate start, int numberOfDays) {
		
		
		// TODO hacer en un futuro
		return null;
	}
	
	private PacktFreeOffer mapToDto(JsonSummary offerSummary) {
		
		PacktFreeOfferBuilder builder = new PacktFreeOfferBuilder(offerSummary.getTitle())
				.withCoverImage(offerSummary.getCoverImage())
				.withOfferUrl(Url.SHOP.path(WebPath.FREE_OFFER.getPath()))
				.withShopUrl(Url.SHOP.path(offerSummary.getShopUrl()))
				.withReadUrl(Url.SUBSCRIBE.path(offerSummary.getReadUrl()));
		
		return builder.build();
	}

	// TODO move to properties / utility class
	private String getOfferListEndpoint() {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(1);
		
		return Url.SERVICES.getUriComponentsBuilder()
				.path(ApiPath.OFFERS.getPath())
				.queryParam("dateFrom", startDate.toString())
				.queryParam("dateTo", endDate.toString())
				.build().toString();
	}

	// TODO move to properties / utility class
	private String getSummaryEndpoint(Long productId) {
		
		return Url.STATIC.getUriComponentsBuilder()
				.path(ApiPath.SUMMARY.getPath())
				.buildAndExpand(productId).toString();
		
	}


}
