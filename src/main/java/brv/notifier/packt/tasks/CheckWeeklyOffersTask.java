package brv.notifier.packt.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.services.CheckoutService;
import brv.notifier.packt.util.MessageHelper;

@Service
public class CheckWeeklyOffersTask extends PacktCheckTask<List<PacktFreeOffer>> {

	@Autowired
	private CheckoutService checkoutService;

	@Autowired
	@Qualifier("messages-app")
	private MessageHelper  messageHelper;

	private static final Logger LOGGER = LogManager.getLogger(CheckWeeklyOffersTask.class.getName());
	
	@Override
	//@Scheduled(cron = "${task.cron.weekly-offers}")
	@Scheduled(fixedRateString = "5000")
	public void check() {
		
		LocalDate startDate = LocalDate.of(2018, 12, 3); // TODO use LocalDate.now(), but for now we need this for testing.
		LocalDate endDate = startDate.plusDays(7); // TODO remove magic number
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Object[] messageParameters = new Object[] { startDate.format(formatter), endDate.format(formatter) };	
		
		List<PacktFreeOffer> offers = checkoutService.getPacktOfferList(startDate, 7);
		
		if(offers.isEmpty()) {
			LOGGER.info(messageHelper.getMessage("offers.missing", messageParameters));
			
		} else {
			
			// Write the offers in the log
			LOGGER.info(messageHelper.getMessage("offers.detected", messageParameters));
			
			for(PacktFreeOffer offer : offers) {
				LOGGER.info("- " + offer.getTitle());
			}
			
			// Notify any listeners to send additional notifications
			this.notifyListeners(offers);
		}
		
	}

}
