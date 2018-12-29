package brv.notifier.packt.services;

import java.time.LocalDate;
import java.util.List;

import brv.notifier.packt.model.PacktFreeOffer;

public interface CheckoutService {

	/**
	 * Obtains the summary for the free offer of the day.
	 * @return A PacktFreeOffer containing the free offer data or <code>null</code> if there are no free offers at the moment.
	 */
	PacktFreeOffer getPacktOffer();
	
	/**
	 * Obtains a list of offer summaries between the startDate and the specified number of days
	 * @param start - the start date.
	 * @param numberOfDays - the number of days after the first offer to retrieve any offers in that number of days.
	 * @return a list of packt offers that will be offered between both dates. If there are no free offers, then it will return an empty list.
	 */
	List<PacktFreeOffer> getPacktOfferList(LocalDate startDate, int numberOfDays);
	
}
