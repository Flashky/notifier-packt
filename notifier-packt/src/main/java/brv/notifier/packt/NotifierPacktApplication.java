package brv.notifier.packt;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import brv.notifier.packt.properties.PacktProperties;
import brv.notifier.packt.properties.ProxyProperty;
import brv.notifier.packt.services.NotificationListener;
import brv.notifier.packt.services.PacktCheckTask;
import brv.notifier.packt.services.mailing.EmailService;
import brv.notifier.packt.util.MessageHelper;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
@EnableConfigurationProperties(PacktProperties.class)
public class NotifierPacktApplication {


	@Autowired
	private PacktProperties packtProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(NotifierPacktApplication.class, args);
	}
	
	@Bean 
	public EmailService getEmailService(PacktCheckTask service) {
		
		NotificationListener listener = new EmailService();
		service.addNotificationListener(listener);
		
		return (EmailService) listener;
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
		
		// Behind a proxy
		ProxyProperty proxyProps = packtProperties.getProxy();
		if(proxyProps != null) {
			SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
			
			// TODO verify both 'host' and 'port' need to be initialized if 'proxy' property exist.
	        InetSocketAddress address = new InetSocketAddress(proxyProps.getHost(),proxyProps.getPort());
	        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
	        factory.setProxy(proxy);
	        
	        restTemplate.setRequestFactory(factory);
		}
		
		return restTemplate;
	}
}
