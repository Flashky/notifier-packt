package brv.notifier.packt.services;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import brv.notifier.packt.model.PacktOffer;
import brv.notifier.packt.model.PacktOfferList;
import brv.notifier.packt.model.PacktSummary;
import brv.notifier.packt.properties.PacktProperties;
import brv.notifier.packt.properties.ProxyProperty;

/**
 * Implementation of CheckoutService which obtains the Packt offer data through an API.
 * @author Flashky
 *
 */
@Service
public class ApiCheckoutService implements CheckoutService {

	@Autowired
	private PacktProperties packtProperties;
	
	@Override
	public PacktSummary getPacktOffer() {
		
		PacktSummary offerSummary = null;
		RestTemplate restTemplate = prepareRestTemplate();
		
		PacktOfferList offerList = restTemplate.getForObject(getOfferListEndpoint(), PacktOfferList.class);
		
		if((offerList != null) && (!offerList.getData().isEmpty())) {
			
			Long productId = offerList.getData().get(0).getProductId();
			offerSummary = restTemplate.getForObject(getSummaryEndpoint(productId), PacktSummary.class);
		}

		return offerSummary;
	}
	
	@Override
	public List<PacktSummary> getPacktOfferList(LocalDate start, int numberOfDays) {
		
		RestTemplate restTemplate = prepareRestTemplate();
		
		// TODO hacer en un futuro
		return null;
	}
	
	// TODO move to Spring context?
	// Is it something that will only be executed once per day, does it worth the extra memory?
	private RestTemplate prepareRestTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		// Behind a proxy
		ProxyProperty proxyProps = packtProperties.getProxy();
		if(proxyProps != null) {
			System.out.println("Behind proxy");
			SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
			
			// TODO verify both 'host' and 'port' need to be initialized if 'proxy' property exist.
	        InetSocketAddress address = new InetSocketAddress(proxyProps.getHost(),proxyProps.getPort());
	        Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
	        factory.setProxy(proxy);
	        
	        restTemplate.setRequestFactory(factory);
		}
		
		return restTemplate;
	}

	// TODO move to properties / utility class
	private String getOfferListEndpoint() {

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(1);
		
		Map<String,String> queryValues = new HashMap<>();
		queryValues.put("dateFrom", startDate.toString());
		queryValues.put("dateTo", endDate.toString());
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("services.packtpub.com")
				.path("/free-learning-v1/offers")
				.query("dateFrom={dateFrom}")
				.query("dateTo={dateTo}")
				.buildAndExpand(queryValues);
		
		return uriComponents.toString();
	}

	// TODO move to properties / utility class
	private String getSummaryEndpoint(Long productId) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("static.packt-cdn.com")
				.path("/products/{productId}/summary")
				.buildAndExpand(productId);
		
		return uriComponents.toString();
		
	}


}
