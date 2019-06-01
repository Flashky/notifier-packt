package brv.notifier.packt.services.offers;

import java.time.LocalDate;

import brv.notifier.packt.services.offers.dto.PacktFreeOffer;

public interface OffersService {

	/**
	 * Obtains the summary for the free offer of the selected date.
	 * @return A PacktFreeOffer containing the free offer data or <code>null</code> if there are no free offers at the moment.
	 */
	PacktFreeOffer getPacktOffer(LocalDate date);
	
}
