package brv.notifier.packt.services.checking;

import java.time.LocalDate;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.dto.PacktFreeOffer;
import brv.notifier.packt.mappers.PacktFreeOfferMapper;
import brv.notifier.packt.model.json.JsonOffer;
import brv.notifier.packt.model.json.JsonOffers;
import brv.notifier.packt.model.json.JsonSummary;
import brv.notifier.packt.repositories.EndpointManager;
import brv.notifier.packt.repositories.OffersRepository;
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
	private OffersRepository offersDao;
	
	@Autowired
	private PacktFreeOfferMapper mapper;
	
	@Override
	public PacktFreeOffer getPacktOffer() {

		PacktFreeOffer offer = null;
		
		
		Optional<JsonOffer> jsonOffer = offersDao.getTodayOffer();
		
		if(jsonOffer.isPresent()) {
			
			// Obtain the productId and retrieve the data for that ebook
			Long productId = jsonOffer.get().getProductId();
			Optional<JsonSummary> offerSummary = getOfferSummary(productId);
			 
			if(offerSummary.isPresent()) {
				offer = mapper.jsonToModel(offerSummary.get());
			}
		}

		return offer;
	}
	
	private Optional<JsonSummary> getOfferSummary(Long productId) {
		
		String endpointUrl = endpointManager.getSummaryEndpoint(productId);
		JsonSummary offerSummary = restTemplate.getForObject(endpointUrl, JsonSummary.class);
		
		return Optional.ofNullable(offerSummary);
	}

}
