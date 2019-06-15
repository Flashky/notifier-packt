package brv.notifier.packt.notifications;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import brv.notifier.packt.enums.Host;
import brv.notifier.packt.enums.WebPath;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

public class TwitterNotificationListener implements DailyNotificationListener {

	private static final Logger LOGGER = LogManager.getLogger(TwitterNotificationListener.class.getName());
	
	private static final String NEWLINE = "\n";
	private static final String PARAGRAPH = NEWLINE + NEWLINE;
	private static final String HASHTAG = "#";
	
	@Autowired
	private Twitter twitter;
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		

		LOGGER.info("Sending tweet...");
		InputStream in = null;

	    try {
	    	
	    	StringBuilder tweet = new StringBuilder()
	    			.append("Free ebook: ")
	    			.append(offerData.getTitle())
	    			.append(PARAGRAPH)
	    			.append(formatOneliner(offerData))
	    			.append(PARAGRAPH)
	    			.append("Grab it only today! ")
	    			.append(Host.SHOP.path(WebPath.FREE_OFFER.getPath()))
	    			.append(PARAGRAPH)
	    			.append("#packt #free #ebook");
	    	
	    	StatusUpdate status = new StatusUpdate(tweet.toString());
	    	
	    	in = new URL(offerData.getCoverImage()).openStream();
	    	status.setMedia("Cover - " + offerData.getTitle(), in);

	    	twitter.updateStatus(status);
	    	LOGGER.info("Tweet has been sent.");
	    	
		} catch (Exception e) {
			LOGGER.error("Tweet sending has failed.");
			LOGGER.error(e.getMessage());
		} finally {
			close(in);
		}
		
		
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
