package brv.notifier.packt.services;

import brv.notifier.packt.model.PacktSummary;

public interface NotificationListener {

	void notify(PacktSummary offerData);
}
