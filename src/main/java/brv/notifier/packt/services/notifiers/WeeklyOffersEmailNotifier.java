package brv.notifier.packt.services.notifiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.services.NotificationListener;
import brv.notifier.packt.services.mailing.EmailData;
import brv.notifier.packt.services.mailing.EmailService;
import brv.notifier.packt.util.MessageHelper;

@Service
public class WeeklyOffersEmailNotifier implements NotificationListener<List<PacktFreeOffer>> {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	@Override
	public void notify(List<PacktFreeOffer> data) {

		// Adapter pattern to communicate with the EmailService.
		// The input data is wrapped into an EmailData.
		EmailData emailData = new EmailData();
		emailData.setSubject(messageHelper.getMessage("mail.weekly-offer.subject"));
		emailData.setTemplate("mail_weekly_offer.html");
		emailData.getVariables().put("offers", data);		
		
		emailService.send(emailData);

	}

}
