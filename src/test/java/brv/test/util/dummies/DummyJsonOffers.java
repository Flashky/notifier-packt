package brv.test.util.dummies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import brv.notifier.packt.model.offers.JsonOffer;
import brv.notifier.packt.model.offers.JsonOffers;

public class DummyJsonOffers {
	private DummyJsonOffers() { }
	
	public static JsonOffers get() {
		
		JsonOffers offers = new JsonOffers();
		
		List<JsonOffer> data = new ArrayList<>();
		data.add(DummyJsonOffer.get());
		offers.setData(data);
		
		return offers;
		
	}
}
