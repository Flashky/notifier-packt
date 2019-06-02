package brv.notifier.packt.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import brv.notifier.packt.model.offers.JsonOffer;
import brv.notifier.packt.model.offers.JsonOffers;
import brv.test.util.dummies.DummyJsonOffers;

@RunWith(MockitoJUnitRunner.class)
public class OffersRestRepositoryTest {

	@InjectMocks
	private OffersRestRepository offersRepository;
	
	@Mock
	private EndpointManager endpointManager;
	
	@Mock
	private RestTemplate restTemplate;
	
	private LocalDate today = LocalDate.now();
	private LocalDate tomorrow = today.plusDays(1);
	
	@Before
	public void setUp() {
		
		Mockito.doCallRealMethod()
			.when(endpointManager)
			.getOffersEndpoint(ArgumentMatchers.any());

		Mockito.doCallRealMethod()
			.when(endpointManager)
			.getOffersEndpoint(ArgumentMatchers.any(), ArgumentMatchers.any());
		
	}
	
	@Test
	public void testGetOffer() {


		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonOffers>>any()))
				.thenReturn(DummyJsonOffers.get());
		
		Optional<JsonOffer> offer = offersRepository.getOffer(today);
		
		assertNotNull(offer);
		assertTrue(offer.isPresent());
		assertEquals(new Long(5), offer.get().getProductId()) ;
		assertEquals(today, offer.get().getAvailableFrom());
		assertEquals(tomorrow, offer.get().getExpiresAt());
	}
	
	@Test
	public void testGetOfferNull() {

		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonOffers>>any()))
				.thenReturn(null);
		
		Optional<JsonOffer> offer = offersRepository.getOffer(today);
		
		assertNotNull(offer);
		assertFalse(offer.isPresent());

	}
	
	@Test
	public void testGetOfferEmpty() {

		JsonOffers jsonOffers = DummyJsonOffers.get();
		jsonOffers.setData(new ArrayList<>());
		
		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonOffers>>any()))
				.thenReturn(jsonOffers);
		
		Optional<JsonOffer> offer = offersRepository.getOffer(today);
		
		assertNotNull(offer);
		assertFalse(offer.isPresent());

	}
	
	@Test
	public void testGetOffersStartDate() {
	
		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonOffers>>any()))
				.thenReturn(DummyJsonOffers.get());	
		
		List<JsonOffer> offers = offersRepository.getOffers(today);
		
		assertNotNull(offers);
		assertFalse(offers.isEmpty());
		assertEquals(new Long(5), offers.get(0).getProductId()) ;
		assertEquals(today, offers.get(0).getAvailableFrom());
		assertEquals(tomorrow, offers.get(0).getExpiresAt());
	}
	
	@Test
	public void testGetOffersStartEndDate() {
		
		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonOffers>>any()))
				.thenReturn(DummyJsonOffers.get());	
		
		List<JsonOffer> offers = offersRepository.getOffers(today,tomorrow);
		
		assertNotNull(offers);
		assertFalse(offers.isEmpty());
		assertEquals(new Long(5), offers.get(0).getProductId()) ;
		assertEquals(today, offers.get(0).getAvailableFrom());
		assertEquals(tomorrow, offers.get(0).getExpiresAt());
	}
}
