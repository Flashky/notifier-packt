package brv.notifier.packt.notifications;

import java.io.ByteArrayInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import brv.notifier.packt.enums.Host;
import brv.notifier.packt.enums.WebPath;
import brv.notifier.packt.services.hashtags.HashtagService;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;
import twitter4j.RateLimitStatus;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.util.CharacterUtil;

public class TwitterNotificationListener implements DailyNotificationListener {

	private static final Logger LOGGER = LogManager.getLogger(TwitterNotificationListener.class.getName());
	
	private static final String EMOJI_BOOK = "open_book";
	private static final String EMOJI_VIDEO = "tv";
	
	// Type selecting
	private static final String VIDEO_OFFER = "videos";
	
	@Autowired
	private Twitter twitter;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@Autowired
	private HashtagService hashtagService;
	
	@Override
	public void notify(PacktFreeOffer offerData) {
		

		LOGGER.info(messageHelper.getMessage("twitter.sending.start"));

	    try {
	    	
	    	String tweet = prepareTweet(offerData);
	    	StatusUpdate status = new StatusUpdate(tweet);
	    	
	    	
	    	// Assign book cover image to the tweet
	    	if(offerData.getCoverImageBytes() != null) {
	    		status.setMedia("cover-image", new ByteArrayInputStream(offerData.getCoverImageBytes()));
	    	}
	    	
	    	twitter.updateStatus(status);
	    	LOGGER.info(messageHelper.getMessage("twitter.sending.sucessful"));
	    	
		} catch (TwitterException e){
			
			LOGGER.error(messageHelper.getMessage("twitter.sending.unsucessful"));
			LOGGER.error(e.getMessage());
			
			if(e.getRateLimitStatus() != null) {
				RateLimitStatus rateLimitStatus = e.getRateLimitStatus();
				LOGGER.error("Exceeded attempt number: " + rateLimitStatus.getRemaining());
			}
			
		}catch (Exception e) {
			LOGGER.error("Uncontrolled exception:");
			LOGGER.error(messageHelper.getMessage("twitter.sending.unsucessful"));
			LOGGER.error(e.getMessage());
		}
		
		
	}

	private String prepareTweet(PacktFreeOffer offerData) {
		
		// Calculate emoji for header line
    	Emoji emoji;
    	if(VIDEO_OFFER.equals(offerData.getType())) {
    		emoji = EmojiManager.getForAlias(EMOJI_VIDEO);
    	} else {
    		emoji = EmojiManager.getForAlias(EMOJI_BOOK);
    	}
    	
    	// Build the tweet status part by part
    	String title = messageHelper.getMessage("twitter.status.title", emoji.getUnicode(), offerData.getTitle());
    	
    	
    	String content = hashtagService.getTextHashtag(offerData.getOneLiner(), 2);
    	String footer = messageHelper.getMessage("twitter.status.footer", Host.SHOP.path(WebPath.FREE_OFFER.getPath()));
    	
    	
    	// Check tweet length and abbreviate if needed
    	int totalLength = title.length() + content.length() + footer.length();
    	
    	if(totalLength > CharacterUtil.MAX_TWEET_LENGTH) {
    		content = StringUtils.abbreviate(content, CharacterUtil.MAX_TWEET_LENGTH - title.length() - footer.length());
    	}
    	
    	StringBuilder tweetBuilder = new StringBuilder(title)
    			.append(content)
    			.append(footer);
    	
    	return tweetBuilder.toString();
    	
	}
	
	/*
	private String formatOneliner(PacktFreeOffer offerData) {
		
		String[] titleWords = offerData.getTitle().split(REGEX_WHITESPACES);
		Set<String> matches = new HashSet<>();
		String oneliner = offerData.getOneLiner();
		
		StringBuilder onelinerBuilder = new StringBuilder(oneliner);
		
		// Strategy 1
		for(String word : titleWords) {
			if((!matches.contains(word)) && (word.length() > MINIMUM_LENGTH)) {
				
				int position = StringUtils.indexOfIgnoreCase(oneliner, word);
				if(position != StringUtils.INDEX_NOT_FOUND) {
					
					// Append the hashtag before the character
					onelinerBuilder.insert(position, HASHTAG);
					oneliner = onelinerBuilder.toString();
					
					// Add the word to the set so I don't repeat hashtags
					matches.add(word);
				}
				
			}
		}
		
		// Strategy 2
		for(String word : hashtags.getHashtags()) {
			if(!matches.contains(word)) {
				
				int position = StringUtils.indexOfIgnoreCase(oneliner, word);
				if(position != StringUtils.INDEX_NOT_FOUND) {
					
					// Append the hashtag before the character
					onelinerBuilder.insert(position,  HASHTAG);
					oneliner = onelinerBuilder.toString();
					
					// Add the word to the set so I don't repeat hashtags
					matches.add(word);
				}
			}
		}
		
		
		return oneliner;
	} 
	*/
}
