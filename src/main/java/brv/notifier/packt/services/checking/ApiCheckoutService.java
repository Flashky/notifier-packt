package brv.notifier.packt.services.checking;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.dto.PacktFreeOffer;
import brv.notifier.packt.mappers.PacktFreeOfferMapper;
import brv.notifier.packt.model.json.JsonOffers;
import brv.notifier.packt.model.json.JsonSummary;
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
	
	@Autowired
	private EndpointManager endpointManager;
	
	@Autowired
	private PacktFreeOfferMapper mapper;
	
	@Override
	public PacktFreeOffer getPacktOffer() {

		PacktFreeOffer offer = null;
		
		// Obtain the list of offers for today so we can obtain the productId
		String endpointUrl = endpointManager.getOfferListEndpoint(LocalDate.now());
		JsonOffers offerList = restTemplate.getForObject(endpointUrl, JsonOffers.class);
		
		if((offerList != null) && (!offerList.getData().isEmpty())) {
			
			// Obtain the productId and retrieve the data for that ebook
			Long productId = offerList.getData().get(0).getProductId();
			
			endpointUrl = endpointManager.getSummaryEndpoint(productId);
			JsonSummary offerSummary = restTemplate.getForObject(endpointUrl, JsonSummary.class);
			
			offer = mapper.jsonToModel(offerSummary);
		}

		return offer;
	}

}
