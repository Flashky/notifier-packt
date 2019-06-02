package brv.test.util.dummies;

import brv.notifier.packt.notifications.DailyNotificationListener;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;

public class DummyDailyNotificationListener implements DailyNotificationListener {

	@Override
	public void notify(PacktFreeOffer offerData) {
		
		// Do nothing, just for testing purposes
	}

}
