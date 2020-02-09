package brv.notifier.packt.services.hashtags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import brv.notifier.packt.restclients.HashtagRestClient;
import brv.notifier.packt.restclients.model.AutoHashtag;

@Service
public class RiteKitHashtagService implements HashtagService {

	@Autowired
	private HashtagRestClient hashtagRestClient;
	
	@Override
	public String getTextHashtag(String text, Integer maxHashtags) {
		
		if(maxHashtags < 0) {
			throw new IllegalArgumentException("Select at leat one hashtag");
		}
		
		AutoHashtag apiResult= hashtagRestClient.getHashtaggedText(text);
		
		String result = null;
	
		if(HttpStatus.valueOf(apiResult.getCode()).is2xxSuccessful()) {
			result = apiResult.getPost();
		} else {
			result = text;
		}
		
		return result;
	}

}
