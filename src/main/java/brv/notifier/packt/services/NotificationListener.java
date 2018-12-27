package brv.notifier.packt.services;

import brv.notifier.packt.model.PacktFreeOffer;

public interface NotificationListener {

	void notify(PacktFreeOffer offerData);
}
