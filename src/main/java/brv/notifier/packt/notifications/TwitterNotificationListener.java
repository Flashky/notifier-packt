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
import brv.notifier.packt.util.MessageHelper;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

public class TwitterNotificationListener implements DailyNotificationListener {

	private static final Logger LOGGER = LogManager.getLogger(TwitterNotificationListener.class.getName());
	private static final String HASHTAG = "#";
	
	@Autowired
	private Twitter twitter;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		

		LOGGER.info(messageHelper.getMessage("twitter.sending.start"));
		InputStream in = null;

	    try {
	    	
	    	String tweet = messageHelper.getMessage("twitter.status.template",
	    											offerData.getTitle(),
	    											formatOneliner(offerData),
	    											Host.SHOP.path(WebPath.FREE_OFFER.getPath()));
	    	
	    	StatusUpdate status = new StatusUpdate(tweet);
	    	
	    	in = new URL(offerData.getCoverImage()).openStream();
	    	status.setMedia("Cover - " + offerData.getTitle(), in);

	    	twitter.updateStatus(status);
	    	LOGGER.info(messageHelper.getMessage("twitter.sending.sucessful"));
	    	
		} catch (Exception e) {
			LOGGER.error(messageHelper.getMessage("twitter.sending.unsucessful"));
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
				
				LOGGER.debug(messageHelper.getMessage("inputstream.closing.start"));
				input.close();
				LOGGER.debug(messageHelper.getMessage("inputstream.closing.sucessful"));
				
			} catch (IOException e) {
				LOGGER.error(messageHelper.getMessage("inputstream.closing.unsucessful"), e);
			}
		}
	}
}
