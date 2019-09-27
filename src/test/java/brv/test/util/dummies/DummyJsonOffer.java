package brv.test.util.dummies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import brv.notifier.packt.model.offers.JsonOffer;
import brv.notifier.packt.model.offers.JsonOffers;

public class DummyJsonOffer {

	private DummyJsonOffer() { }
	
	public static JsonOffer getJsonOffer() {
		
		JsonOffer offer = new JsonOffer();
		
		LocalDate today = LocalDate.now();
		offer.setProductId(5L);
		offer.setAvailableFrom(today);
		offer.setExpiresAt(today.plusDays(1));
	
		return offer;
	}
	

}
