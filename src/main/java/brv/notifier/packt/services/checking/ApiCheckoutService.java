package brv.notifier.packt.services.checking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.mappers.Mapper;
import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.json.JsonOffer;
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
			
			offer = Mapper.jsonToModel(offerSummary);
		}

		return offer;
	}
	
	
	@Override
	public List<PacktFreeOffer> getPacktOfferList(LocalDate start, int numberOfDays) {
		
		List<PacktFreeOffer> result = new ArrayList<>();
		
		// Obtain the list of upcoming offers
		String endpointUrl = endpointManager.getOfferListEndpoint(LocalDate.now(), numberOfDays);
		JsonOffers offerList = restTemplate.getForObject(endpointUrl, JsonOffers.class);
		
		if((offerList != null) && (!offerList.getData().isEmpty())) {
			
			for(JsonOffer jsonOffer : offerList.getData()) {
				
				endpointUrl = endpointManager.getSummaryEndpoint(jsonOffer.getProductId());
				JsonSummary offerSummary = restTemplate.getForObject(endpointUrl, JsonSummary.class);
				
				result.add(Mapper.jsonToModel(offerSummary));
			}
		}
		
		return result;
	}

}
