package brv.notifier.packt.services.mailing;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.services.NotificationListener;
import brv.notifier.packt.util.MessageHelper;

@Service
@Import(SimpleJavaMailSpringSupport.class)
public class EmailService implements NotificationListener {

	@Autowired 
	private Mailer mailer;
	
	@Autowired
	private TemplateEngine template;
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	private static final Logger LOGGER = LogManager.getLogger(EmailService.class.getName());
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		
		String emailBody = applyTemplate(offerData);
		
		LOGGER.info(messageHelper.getMessage("info.sending.mail"));
		
		// Create a new email. 
		// Most of the configuration is obtained through the application.yml
		Email email = EmailBuilder.startingBlank()
			    .withSubject(messageHelper.getMessage("mail.subject", new Object[] {offerData.getTitle()}))
			    .withHTMLText(emailBody)
			    .buildEmail();
		
		
		mailer.sendMail(email);
		
		LOGGER.info(messageHelper.getMessage("info.successful.send"));
	}
	
	/**
	 * Substitutes the input data into an html template.
	 * @param offerData - the data to be substituted
	 * @return html string with any variables substituted.
	 */
	private String applyTemplate(PacktFreeOffer offerData) {
	
		String html = "";
		
		Context ctx = new Context(new Locale("es","ES"));
		ctx.setVariable("offer",offerData);
		
		html = template.process("mail.html", ctx);
		
		//System.out.println(html);
		return html;
	}

}
