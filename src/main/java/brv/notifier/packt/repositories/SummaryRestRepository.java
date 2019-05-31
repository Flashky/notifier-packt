package brv.notifier.packt.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.model.offers.JsonSummary;

@Repository
public class SummaryRestRepository implements SummaryRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EndpointManager endpointManager;
	
	@Override
	public Optional<JsonSummary> findById(Long productId) {
		
		String endpoint = endpointManager.getSummaryEndpoint(productId);
		JsonSummary offerSummary = restTemplate.getForObject(endpoint, JsonSummary.class);
		
		return Optional.ofNullable(offerSummary);
	}

}
