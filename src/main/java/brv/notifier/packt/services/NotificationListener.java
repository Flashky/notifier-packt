package brv.notifier.packt.services;

import brv.notifier.packt.dto.PacktFreeOffer;

public interface NotificationListener {

	void notify(PacktFreeOffer offerData);
}
