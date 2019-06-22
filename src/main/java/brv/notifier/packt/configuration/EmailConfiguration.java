package brv.notifier.packt.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brv.notifier.packt.constants.PropertyName;
import brv.notifier.packt.notifications.DailyNotificationListener;
import brv.notifier.packt.notifications.EmailNotificationListener;
import brv.notifier.packt.services.PacktCheckTask;

@Configuration
@ConditionalOnProperty(value = PropertyName.EMAIL_ENABLED, havingValue = "true", matchIfMissing = true)
public class EmailConfiguration {
	
	
	@Bean 	
	public EmailNotificationListener getEmailService(PacktCheckTask service) {
		
		DailyNotificationListener listener = new EmailNotificationListener();
		service.addNotificationListener(listener);
		
		return (EmailNotificationListener) listener;
	}
}
