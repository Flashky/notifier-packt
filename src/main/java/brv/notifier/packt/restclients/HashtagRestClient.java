package brv.notifier.packt.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import brv.notifier.packt.restclients.model.AutoHashtag;

@FeignClient(name = "${api.hashtags.name}", url = "${api.hashtags.url}")
public interface HashtagRestClient {

	
	@GetMapping("/auto-hashtag?client_id=${api.hashtags.clientid}")
	AutoHashtag getHashtaggedText(@RequestParam String post);
	
	
	@GetMapping("/auto-hashtag?client_id=${api.hashtags.clientid}")
	AutoHashtag getHashtaggedText(@RequestParam String post, @RequestParam Integer maxHashtags);
	
}
