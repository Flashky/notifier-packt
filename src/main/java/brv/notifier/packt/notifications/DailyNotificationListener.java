package brv.notifier.packt.notifications;

import brv.notifier.packt.services.offers.dto.PacktFreeOffer;

public interface DailyNotificationListener {

	void notify(PacktFreeOffer offerData);
}
