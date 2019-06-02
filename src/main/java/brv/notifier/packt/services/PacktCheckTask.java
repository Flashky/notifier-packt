package brv.notifier.packt.services;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import brv.notifier.packt.notifications.DailyNotificationListener;
import brv.notifier.packt.services.offers.OffersService;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;

@Service
public class PacktCheckTask {

	@Autowired
	private OffersService checkoutService;

	@Autowired
	@Qualifier("messages-app")
	private MessageHelper  messageHelper;

	private static final Logger LOGGER = LogManager.getLogger(PacktCheckTask.class.getName());
	
	/** 
	 * Previous found offer.
	 * <p>
	 * Used to prevent duplicated notifications, any offer should be notified just once.
	 * </p>
	 */

	private PacktFreeOffer previousOffer;
	
	private List<DailyNotificationListener> listeners = new LinkedList<>();
	
	@Scheduled(cron = "${service.cron}")
	//@Scheduled(fixedRateString = "5000")
	public void checkPacktDailyOffer() {

		PacktFreeOffer offer = checkoutService.getPacktOffer(LocalDate.now());
		
		if(offer == null) {
			LOGGER.info(messageHelper.getMessage("offer.missing"));
		} else if (offer.equals(previousOffer)){
			LOGGER.info(messageHelper.getMessage("offer.repeated"));
		}
		else {
			notifyListeners(offer);
			previousOffer = offer;
		} 

	}
	
	/**
	 * Notifies every listener that a new Packt offer has been detected.
	 * @param offer - the data to be notified.
	 */

	private void notifyListeners(PacktFreeOffer offer) {
		
		Object[] messageParameters = new Object[] { offer.getTitle() };	
		LOGGER.info(messageHelper.getMessage("offer.detected", messageParameters));
		for(DailyNotificationListener listener : listeners) {
			listener.notify(offer);
		}
	}
	
	/**
	 * Adds a listener to be notified when the task detects an offer.
	 * <p>
	 * A <code>null</code> listener will be ignored if added.
	 * </p>
	 * @param listener - the listener to register.
	 */
	public void addNotificationListener(DailyNotificationListener listener) {
		if(listener != null) {
			this.listeners.add(listener);
		}
	}

} 
