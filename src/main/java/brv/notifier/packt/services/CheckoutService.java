package brv.notifier.packt.services;

import brv.notifier.packt.dto.PacktFreeOffer;

public interface CheckoutService {

	/**
	 * Obtains the summary for the free offer of the day.
	 * @return A PacktFreeOffer containing the free offer data or <code>null</code> if there are no free offers at the moment.
	 */
	PacktFreeOffer getPacktOffer();
	
}
