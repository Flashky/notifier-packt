package brv.notifier.packt.services.notifiers;

import brv.notifier.packt.model.PacktFreeOffer;

public interface NotificationListener {

	void notify(PacktFreeOffer offerData);
}
