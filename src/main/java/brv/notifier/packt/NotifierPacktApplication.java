package brv.notifier.packt;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import brv.notifier.packt.configuration.EmailConfiguration;
import brv.notifier.packt.configuration.TwitterConfiguration;
import brv.notifier.packt.constants.PlaceholderValue;
import brv.notifier.packt.util.MessageHelper;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
@Import({TwitterConfiguration.class, EmailConfiguration.class})
public class NotifierPacktApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(NotifierPacktApplication.class, args);
	}
	
	@Bean
	public Locale getLocale(@Value(PlaceholderValue.LANG) String lang) {
		return new Locale(lang);
	}
	
	
	@Bean(name = "messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
	    return messageSource;
	}
	
	@Bean
	public MessageHelper getAppMessageHelper(@Qualifier("messageSource") MessageSource messageSource, Locale locale) {
		return new MessageHelper(messageSource, locale);
	}
	
	 
	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
		
	}
	
}
