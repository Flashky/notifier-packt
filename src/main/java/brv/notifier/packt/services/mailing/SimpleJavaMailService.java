package brv.notifier.packt.services.mailing;

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

import brv.notifier.packt.util.MessageHelper;

@Service
@Import(SimpleJavaMailSpringSupport.class)
public class SimpleJavaMailService implements EmailService {

	@Autowired 
	private Mailer mailer;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	private static final Logger LOGGER = LogManager.getLogger(SimpleJavaMailService.class.getName());
	
	@Override
	public void send(EmailData emailData) {

		
		LOGGER.info(messageHelper.getMessage("mail.sending.start"));
		
		String html = templateService.process(emailData.getTemplate(), emailData.getVariables());
		
		// Create a new email. 
		// Most of the configuration is obtained through the application.yml
		Email email = EmailBuilder.startingBlank()
				.withSubject(emailData.getSubject())
			    .withHTMLText(html)
			    .buildEmail();
		
		
		mailer.sendMail(email);
		
		LOGGER.info(messageHelper.getMessage("mail.sending.sucessful"));

	}


}
