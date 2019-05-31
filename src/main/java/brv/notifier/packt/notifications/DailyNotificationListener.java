package brv.notifier.packt.notifications;

import brv.notifier.packt.dto.PacktFreeOffer;

public interface DailyNotificationListener {

	void notify(PacktFreeOffer offerData);
}
