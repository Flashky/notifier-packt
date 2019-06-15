package brv.notifier.packt;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import brv.notifier.packt.notifications.DailyNotificationListener;
import brv.notifier.packt.notifications.EmailNotificationListener;
import brv.notifier.packt.properties.PacktProperties;
import brv.notifier.packt.properties.ProxyProperty;
import brv.notifier.packt.services.PacktCheckTask;
import brv.notifier.packt.util.MessageHelper;

//@EnableTwitter
@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
@EnableConfigurationProperties({PacktProperties.class})
@Import(TwitterConfiguration.class)
public class NotifierPacktApplication {


	@Autowired
	private PacktProperties packtProperties;

	
	public static void main(String[] args) {
		SpringApplication.run(NotifierPacktApplication.class, args);
	}
	
	
	@Bean 	
	@ConditionalOnProperty(value="notifications.email.enabled", havingValue = "true", matchIfMissing = true)
	public EmailNotificationListener getEmailService(PacktCheckTask service) {
		
		DailyNotificationListener listener = new EmailNotificationListener();
		service.addNotificationListener(listener);
		
		return (EmailNotificationListener) listener;
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

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);
		
		ProxyProperty proxyProps = packtProperties.getProxy();
		
		if(proxyProps != null) {
			
			// Behind a proxy
			String host = proxyProps.getHost();
			Integer port = proxyProps.getPort();
			
			if((host == null) || (port == null))
				throw new IllegalArgumentException("Invalid property: Please initialize 'proxy.host' and 'proxy.port' properties.");

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
