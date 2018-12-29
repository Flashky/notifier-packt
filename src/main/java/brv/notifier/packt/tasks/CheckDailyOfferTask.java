package brv.notifier.packt.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.model.PacktFreeOfferBuilder;
import brv.notifier.packt.services.CheckoutService;
import brv.notifier.packt.util.MessageHelper;

@Service
public class CheckDailyOfferTask extends PacktCheckTask<PacktFreeOffer> {

	@Autowired
	private CheckoutService checkoutService;

	@Autowired
	@Qualifier("messages-app")
	private MessageHelper  messageHelper;

	private static final Logger LOGGER = LogManager.getLogger(CheckDailyOfferTask.class.getName());
	
	/** 
	 * Previous found offer.
	 * <p>
	 * Used to prevent duplicated notifications, any offer should be notified just once.
	 * </p>
	 */

	private PacktFreeOffer previousOffer;

	//@Scheduled(cron = "${task.cron.daily-offer}")
	@Scheduled(fixedRateString = "5000")
	public void check() {

		PacktFreeOffer offer = checkoutService.getPacktOffer();
		//PacktFreeOffer offer = new PacktFreeOfferBuilder("Test titulo").withAbout("texto ejemplo").build();
		
		if(offer == null) {
			LOGGER.info(messageHelper.getMessage("offer.missing"));
		} else if (offer.equals(previousOffer)){
			LOGGER.info(messageHelper.getMessage("offer.repeated"));
		}
		else {
			Object[] messageParameters = new Object[] { offer.getTitle() };	
			LOGGER.info(messageHelper.getMessage("offer.detected", messageParameters));
			
			this.notifyListeners(offer);
			previousOffer = offer;
		} 

	}
} 
