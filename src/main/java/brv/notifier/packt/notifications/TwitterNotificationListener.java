package brv.notifier.packt.notifications;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterNotificationListener implements DailyNotificationListener {

	private static final Logger LOGGER = LogManager.getLogger(TwitterNotificationListener.class.getName());
	
	private static final String NEWLINE = "\n";
	private static final String HASHTAG = "#";
	
	@Autowired
	@Qualifier("messages-mail")
	private MessageHelper messageHelper;
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		

		LOGGER.info("Sending tweet...");
		InputStream in = null;
		Twitter twitter = TwitterFactory.getSingleton();
	    try {
	    	
	    	StringBuilder tweet = new StringBuilder();
	    	tweet.append(formatOneliner(offerData));
	    	tweet.append(NEWLINE);
	    	tweet.append(NEWLINE);
	    	tweet.append("Grab it only today! ");
	    	tweet.append("https://www.packtpub.com/free-learning");
	    	tweet.append(NEWLINE);
	    	tweet.append(NEWLINE);
	    	tweet.append("#packt #free #ebook");
	    	
	    	StatusUpdate status = new StatusUpdate(tweet.toString());
	    	System.out.println(offerData.getCoverImage());
	    	
	    	in = new URL(offerData.getCoverImage()).openStream();
	    	status.setMedia("Pactk Free Learn - Cover", in);

	    	twitter.updateStatus(status);
	    	
		} catch (TwitterException | IOException e) {
			LOGGER.error("There was an error on Twitter4JService", e);
		} finally {
			close(in);
		}
		LOGGER.info("Twitter4J sent");
		
	}

	private String formatOneliner(PacktFreeOffer offerData) {
		
		String title = offerData.getTitle();
		String[] titleWords = title.split("\\s+");
		String oneliner = offerData.getOneLiner();
		
		Set<String> matches = new HashSet<>();
		
		for(String word : titleWords) {
			if((!matches.contains(word)) && (word.length() > 2) && (oneliner.contains(word))) {
				matches.add(word);
				oneliner = oneliner.replaceFirst(word, HASHTAG+word);
			}
		}
		
		return oneliner;
	}
	
	
	private void close(InputStream input) {
		if(input != null) {
			try {
				
				LOGGER.debug("Closing input stream");
				input.close();
				LOGGER.debug("Input stream has been closed.");
				
			} catch (IOException e) {
				LOGGER.error("Couldn't close input stream.", e);
			}
		}
	}
}
