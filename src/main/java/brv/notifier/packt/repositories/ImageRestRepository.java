package brv.notifier.packt.repositories;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageRestRepository implements ImageRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger LOGGER = LogManager.getLogger(ImageRestRepository.class.getName());
	
	@Override
	public Optional<byte[]> getFromUrl(String imageUrl) {
		
		Optional<byte[]> result = Optional.empty();
		
		if(imageUrl != null) {
			
			HttpEntity<String> requestEntity = new HttpEntity<>(prepareRequestHeaders());
			
			ResponseEntity<byte[]> response = restTemplate.exchange(imageUrl, HttpMethod.GET, requestEntity, byte[].class);
			
			HttpStatus httpStatus = response.getStatusCode();

	    	if(httpStatus.is2xxSuccessful()) {
	    		result = Optional.ofNullable(response.getBody());
	    	} else {
	    		LOGGER.warn("No image couldn't be obtained from url: " + imageUrl + " ("+httpStatus.value()+")");
	    	}
	    	
		}

		return result;
	}
	

	private HttpHeaders prepareRequestHeaders() {
		
		HttpHeaders headers = new HttpHeaders();
		//headers.set(HttpHeaders.ACCEPT,	"*/*");
		headers.set(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
		headers.set(HttpHeaders.REFERER, "https://www.packtpub.com/free-learning");
		headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
		
		return headers;
	}
	

}
