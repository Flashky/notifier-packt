package brv.notifier.packt.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brv.notifier.packt.notifications.DailyNotificationListener;
import brv.notifier.packt.notifications.TwitterNotificationListener;
import brv.notifier.packt.properties.TwitterProperties;
import brv.notifier.packt.services.PacktCheckTask;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConditionalOnProperty(value="notifications.twitter.enabled", havingValue = "true", matchIfMissing = true)
public class TwitterConfiguration {
	
	@Bean
	public Twitter getTwitter(TwitterProperties twitterProperties) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(twitterProperties.getConsumerKey())
		  .setOAuthConsumerSecret(twitterProperties.getConsumerSecret())
		  .setOAuthAccessToken(twitterProperties.getAccessToken())
		  .setOAuthAccessTokenSecret(twitterProperties.getAccessSecret());
		
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		
		return twitterFactory.getInstance();
		
	}
	
	@Bean
	public TwitterNotificationListener getTwitterService(PacktCheckTask service) {
		DailyNotificationListener listener = new TwitterNotificationListener();
		service.addNotificationListener(listener);
		
		return (TwitterNotificationListener) listener;
	}
}
