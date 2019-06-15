package brv.notifier.packt.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import brv.notifier.packt.enums.Host;
import brv.notifier.packt.enums.WebPath;
import brv.notifier.packt.services.mailing.EmailData;
import brv.notifier.packt.services.mailing.EmailService;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;

@Service
public class EmailNotificationListener implements DailyNotificationListener {

	@Autowired
	private EmailService service;
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	@Override
	public void notify(PacktFreeOffer offerData) {

		// Adapter pattern to communicate with the EmailService.
		// The input data is wrapped into an EmailData.
		EmailData emailData = new EmailData();
		emailData.setSubject(messageHelper.getMessage("mail.subject", new Object[] {offerData.getTitle()}));
		emailData.setTemplate("mail.html");
		emailData.getVariables().put("offer", offerData);		
		emailData.getVariables().put("freeLearningUrl", Host.SHOP.path(WebPath.FREE_OFFER.getPath()));
		
		service.send(emailData);

	}
}
