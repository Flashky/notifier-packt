package brv.notifier.packt;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import brv.notifier.packt.model.PacktFreeOffer;
import brv.notifier.packt.properties.ProxyProperties;
import brv.notifier.packt.services.NotificationListener;
import brv.notifier.packt.services.notifiers.DailyOfferEmailNotifier;
import brv.notifier.packt.services.notifiers.WeeklyOffersEmailNotifier;
import brv.notifier.packt.tasks.CheckDailyOfferTask;
import brv.notifier.packt.tasks.CheckWeeklyOffersTask;
import brv.notifier.packt.util.MessageHelper;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
@EnableConfigurationProperties(ProxyProperties.class)
public class NotifierPacktApplication {


	@Autowired
	private ProxyProperties proxyProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(NotifierPacktApplication.class, args);
	}
	
	@Bean 
	public DailyOfferEmailNotifier getDailyOfferEmailNotifier(CheckDailyOfferTask service) {
		
		NotificationListener<PacktFreeOffer> listener = new DailyOfferEmailNotifier();
		service.addNotificationListener(listener);
		
		return (DailyOfferEmailNotifier) listener;
	}
	
	@Bean 
	public WeeklyOffersEmailNotifier getWeeklyOffersEmailNotifier(CheckWeeklyOffersTask service) {
		
		NotificationListener<List<PacktFreeOffer>> listener = new WeeklyOffersEmailNotifier();
		service.addNotificationListener(listener);
		
		return (WeeklyOffersEmailNotifier) listener;
	}
	
	@Bean
	public Locale getLocale() {
		return new Locale("es","ES");
	}
	
	@Bean("messageSourceApp")
	public MessageSource getAppMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
	    return messageSource;
	}
	
	/**
	 * Obtains a MessageSource for mail template properties.
	 * <p>
	 * The bean needs to be qualified as "messageSource" in order to be able to be autowired by {@link org.thymeleaf.TemplateEngine TemplateEngine}.
	 * </p>
	 * @return
	 */
	@Bean("messageSource")
	public MessageSource getMailMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("templates/mail");
        return messageSource;
	}
	
	@Bean("messages-app")
	public MessageHelper getAppMessageHelper(@Qualifier("messageSourceApp") MessageSource messageSource, Locale locale) {
		return new MessageHelper(messageSource, locale);
	}
	
	@Bean("messages-mail")
	public MessageHelper getMailMessageHelper(@Qualifier("messageSource") MessageSource messageSource, Locale locale) {
		return new MessageHelper(messageSource, locale);
	}
	

	@Bean
	public RestTemplate getRestTemplate() {
	
		RestTemplate restTemplate = new RestTemplate();

		String host = proxyProperties.getHost();
		Integer port = proxyProperties.getPort();
		
		if((host != null) && (port != null)) {
			
			// Behind a proxy - Adapt RestTemplate configuration to support this.
			
			if(port < 1)
				throw new IllegalArgumentException("Invalid property: 'proxy.port' must be an integer greater than 0.");
			
			SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
			
	        InetSocketAddress address = new InetSocketAddress(host,port);
	        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
	        factory.setProxy(proxy);
	        
	        restTemplate.setRequestFactory(factory);
		}
		
		return restTemplate;
	}
}