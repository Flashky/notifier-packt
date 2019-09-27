package brv.notifier.packt.services.offers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.restclients.OffersRestClient;
import brv.notifier.packt.restclients.ProductsRestClient;
import brv.notifier.packt.restclients.model.JsonOffer;
import brv.notifier.packt.restclients.model.JsonOffers;
import brv.notifier.packt.restclients.model.JsonSummary;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapper;

/**
 * Implementation of CheckoutService which obtains the Packt offer data through an API.
 * @author Flashky
 *
 */
@Service
public class ApiOffersService implements OffersService {
	
	@Autowired
	private OffersRestClient offersRestClient;
	
	@Autowired
	private ProductsRestClient productsRestClient;
	
	@Autowired
	private PacktFreeOfferMapper mapper;
	
	@Value("${api.products.url}")
	private String productsApi;
	
	@Override
	public PacktFreeOffer getPacktOffer(LocalDate date) {

		PacktFreeOffer result = null;
		
		// Obtain the productId of the deal of the day
		JsonOffers jsonOffers = offersRestClient.getOffers(date.toString(), 
															date.plusDays(1).toString());
		
		if((jsonOffers != null) && !CollectionUtils.isEmpty(jsonOffers.getData())) {
			result = getSummary(jsonOffers.getData().get(0));
		}

		return result;
	}
	
	private PacktFreeOffer getSummary(JsonOffer offer) {
		
		PacktFreeOffer result = null;
		
		// Retrieve the data for the ebook offer
		JsonSummary jsonSummary = productsRestClient.getProductSummary(offer.getProductId());
		byte[] coverImage = productsRestClient.getProductImage(offer.getProductId());
		
		if(jsonSummary != null) {

			// Override default cover image url
			jsonSummary.setCoverImage(getCoverImageUrl(jsonSummary));
			result = mapper.jsonToModel(jsonSummary);
			result.setCoverImageBytes(coverImage);

		}
		
		return result;
				
	}
	
	private String getCoverImageUrl(JsonSummary jsonSummary) {
		return UriComponentsBuilder.fromHttpUrl(this.productsApi)
				.pathSegment("{productId}")
				.pathSegment("cover")
				.pathSegment("smaller").buildAndExpand(jsonSummary.getProductId()).toString();
	}



}
