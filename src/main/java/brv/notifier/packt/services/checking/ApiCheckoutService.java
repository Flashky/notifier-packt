package brv.notifier.packt.services.checking;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brv.notifier.packt.dto.PacktFreeOffer;
import brv.notifier.packt.mappers.PacktFreeOfferMapper;
import brv.notifier.packt.model.json.JsonOffer;
import brv.notifier.packt.model.json.JsonSummary;
import brv.notifier.packt.repositories.OffersRepository;
import brv.notifier.packt.repositories.SummaryRepository;
import brv.notifier.packt.services.CheckoutService;

/**
 * Implementation of CheckoutService which obtains the Packt offer data through an API.
 * @author Flashky
 *
 */
@Service
public class ApiCheckoutService implements CheckoutService {
	
	@Autowired
	private OffersRepository offersDao;
	
	@Autowired
	private SummaryRepository summaryDao;
	
	@Autowired
	private PacktFreeOfferMapper mapper;
	
	@Override
	public PacktFreeOffer getPacktOffer() {

		PacktFreeOffer result = null;
		
		Optional<JsonOffer> offer = offersDao.getTodayOffer();
		
		if(offer.isPresent()) {
			
			// Obtain the productId and retrieve the data for that ebook
			Optional<JsonSummary> summary = summaryDao.findById(offer.get().getProductId());
					
			if(summary.isPresent()) {
				result = mapper.jsonToModel(summary.get());
			}
		}

		return result;
	}


}
