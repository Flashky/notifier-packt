package brv.notifier.packt.properties;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class HashtagProperties {

	private Set<String> hashtags;

	public Set<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}
	
	
}
