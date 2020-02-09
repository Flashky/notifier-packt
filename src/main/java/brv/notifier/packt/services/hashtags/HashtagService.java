package brv.notifier.packt.services.hashtags;

public interface HashtagService {

	/**
	 * Applies hashtags to the input text
	 * @param text The text to add hashtags in
	 * @param maxHashtags Maximum number of hashtags (recommended: 2-3)
	 * @return The text with hashtags
	 */
	String getTextHashtag(String text, Integer maxHashtags);
}
