package brv.notifier.packt.repositories.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.model.json.JsonOffer;
import brv.notifier.packt.model.json.JsonOffers;
import brv.notifier.packt.repositories.OffersRepository;

@Repository
public class OffersRestRepository implements OffersRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EndpointManager endpointManager;
	
	private static final Integer SINGLE_RESULT = 0;
	private static final int MIN_NUMBER_DAYS = 1;
	
	@Override
	public Optional<JsonOffer> getTodayOffer() {
		
		return getOffer(LocalDate.now());
		
	}
	

	@Override
	public Optional<JsonOffer> getOffer(LocalDate dateFrom) {
		
		LocalDate dateTo = dateFrom.plusDays(MIN_NUMBER_DAYS);
		
		String endpoint = endpointManager.getOffersEndpoint(dateFrom, dateTo);
		List<JsonOffer> offers = getOffers(endpoint);

		Optional<JsonOffer> result = Optional.empty();
		
		if(!offers.isEmpty()) {
			 result = Optional.ofNullable(offers.get(SINGLE_RESULT));
		}
		
		return result;
		
	}
	

	@Override
	public List<JsonOffer> getOffers(LocalDate dateFrom) {
		
		String endpoint = endpointManager.getOffersEndpoint(dateFrom);
		return getOffers(endpoint);
	}

	@Override
	public List<JsonOffer> getOffers(LocalDate dateFrom, LocalDate dateTo) {
		
		String endpoint = endpointManager.getOffersEndpoint(dateFrom, dateTo);
		return getOffers(endpoint);
	}

	
	/**
	 * Obtiene un listado de ofertas a partir del endpoint.
	 * @param endpoint La URI de la cual obtener las ofertas.
	 * @return Una lista con las ofertas obtenidas a partir del endpoint. Si no hay ofertas, devuelve una lista vacía.
	 */
	private List<JsonOffer> getOffers(String endpoint) {
		
		List<JsonOffer> result = new ArrayList<>();
		
		JsonOffers offers = restTemplate.getForObject(endpoint, JsonOffers.class);
		
		if( (offers != null) && (!CollectionUtils.isEmpty(offers.getData())) ) {
			result = offers.getData();
		}
			
		return result;
	}


}
