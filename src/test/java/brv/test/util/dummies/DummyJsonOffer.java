package brv.test.util.dummies;

import java.time.LocalDate;

import brv.notifier.packt.model.offers.JsonOffer;

public class DummyJsonOffer {

	private DummyJsonOffer() { }
	
	public static JsonOffer get() {
		
		JsonOffer offer = new JsonOffer();
		
		LocalDate today = LocalDate.now();
		offer.setProductId(5L);
		offer.setAvailableFrom(today);
		offer.setExpiresAt(today.plusDays(1));
	
		return offer;
	}
	

}
