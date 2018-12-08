package brv.notifier.packt.services;

import brv.notifier.packt.model.PacktOffer;

public interface NotificationListener {

	void notify(PacktOffer offerData);
}
