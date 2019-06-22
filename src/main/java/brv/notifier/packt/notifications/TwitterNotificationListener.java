package brv.notifier.packt.notifications;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import brv.notifier.packt.enums.Host;
import brv.notifier.packt.enums.WebPath;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

public class TwitterNotificationListener implements DailyNotificationListener {

	private static final Logger LOGGER = LogManager.getLogger(TwitterNotificationListener.class.getName());
	
	private static final String EMOJI_BOOK = "open_book";
	
	// Oneliner formatting
	private static final String HASHTAG = "#";
	private static final int MINIMUM_LENGTH = 2;
	private static final int NO_MATCH = -1;
	private static final String REGEX_WHITESPACES = "\\s+";
	
	@Autowired
	private Twitter twitter;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		

		LOGGER.info(messageHelper.getMessage("twitter.sending.start"));
		InputStream in = null;

	    try {
	    	
	    	Emoji emoji = EmojiManager.getForAlias(EMOJI_BOOK);
	    	String tweet = messageHelper.getMessage("twitter.status.template",
	    											emoji.getUnicode(),
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
		
		String[] titleWords = offerData.getTitle().split(REGEX_WHITESPACES);
		Set<String> matches = new HashSet<>();
		String oneliner = offerData.getOneLiner();
		
		StringBuilder onelinerBuilder = new StringBuilder(oneliner);
		
		for(String word : titleWords) {
			if((!matches.contains(word)) && (word.length() > MINIMUM_LENGTH)) {
				
				int position = StringUtils.indexOfIgnoreCase(oneliner, word);
				if(position != NO_MATCH) {
					
					// Append the hashtag before the character
					onelinerBuilder.insert(position, HASHTAG);
					oneliner = onelinerBuilder.toString();
					
					// Add the word to the set so I don't repeat hashtags
					matches.add(word);
				}
				
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
