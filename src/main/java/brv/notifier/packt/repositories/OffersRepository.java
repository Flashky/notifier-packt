package brv.notifier.packt.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import brv.notifier.packt.model.offers.JsonOffer;

public interface OffersRepository {
	
	/**
	 * Obtains a single day free offer.
	 * @param date the date to lookup for a free offer.
	 * @return an object containing the date free offer.
	 */
	Optional<JsonOffer> getOffer(LocalDate date);
	
	/**
	 * Obtains all free offers from the selected date onwards.
	 * @param startDate starting date to look up for free offers.
	 * @return A list containing offers from today onwards.
	 */
	List<JsonOffer> getOffers(LocalDate startDate);
	
	/**
	 * Obtains all free offers between two dates.
	 * @param startDate starting date to look up for free offers.
	 * @param endDate ending date to look up for free offers.
	 * @return A list containing all free offers between both dates.
	 */
	List<JsonOffer> getOffers(LocalDate startDate, LocalDate endDate);
}
