package brv.notifier.packt.services.checking;

import java.time.LocalDate;
import java.util.List;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.api.JsonSummary;

public interface CheckoutService {

	/**
	 * Obtains the summary for the free offer of the day.
	 * @return
	 */
	PacktFreeOffer getPacktOffer();
	
	/**
	 * Obtains a list of offer summaries between the startDate and the specified number of days
	 * @param start - the start date.
	 * @param numberOfDays - the number of days after the first offer to retrieve any offers in that number of days.
	 * @return a list of packt offers that will be offered between both dates.
	 */
	List<PacktFreeOffer> getPacktOfferList(LocalDate start, int numberOfDays);
	
}