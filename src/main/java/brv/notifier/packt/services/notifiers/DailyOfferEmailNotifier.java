package brv.notifier.packt.services.notifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.services.NotificationListener;
import brv.notifier.packt.services.mailing.EmailData;
import brv.notifier.packt.services.mailing.EmailService;
import brv.notifier.packt.util.MessageHelper;

@Service
public class DailyOfferEmailNotifier implements NotificationListener<PacktFreeOffer> {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	@Override
	public void notify(PacktFreeOffer data) {

		// Adapter pattern to communicate with the EmailService.
		// The input data is wrapped into an EmailData.
		EmailData emailData = new EmailData();
		emailData.setSubject(messageHelper.getMessage("mail.daily-offer.subject", new Object[] {data.getTitle()}));
		emailData.setTemplate("mail_daily_offer.html");
		emailData.getVariables().put("offer", data);		
		
		emailService.send(emailData);

		
	}

}
