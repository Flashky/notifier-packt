package brv.test.util.dummies;

import java.util.ArrayList;
import java.util.List;

import brv.notifier.packt.model.offers.JsonOffer;
import brv.notifier.packt.model.offers.JsonOffers;

public class DummyJsonOffers {
	private DummyJsonOffers() { }
	
	public static JsonOffers getJsonOffers() {
		JsonOffers jsonOffers = new JsonOffers();
		
		List<JsonOffer> list = new ArrayList<>();
		list.add(DummyJsonOffer.getJsonOffer());
		
		jsonOffers.setData(list);
		return jsonOffers;
	}

}
