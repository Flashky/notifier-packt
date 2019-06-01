package brv.notifier.packt.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import brv.notifier.packt.model.offers.JsonSummary;

@RunWith(MockitoJUnitRunner.class)
public class SummaryRestRepositoryTest {

	@InjectMocks
	private SummaryRestRepository summaryRestRepository;
	
	@Mock
	private EndpointManager endpointManager;
	
	@Mock
	private RestTemplate restTemplate;
	
	private final static Long PRODUCT_ID = 1234L;
	
	@Before
	public void setup() {
		
		Mockito.when(endpointManager.getSummaryEndpoint(ArgumentMatchers.any()))
		.thenReturn("http://test-url");
		
	}
	
	private JsonSummary getJsonSummary() {
		JsonSummary summary = new JsonSummary();
		
		summary.setAbout("about");
		summary.setCoverImage("http://image.png");
		summary.setFeatures("features");
		summary.setLearn("learn");
		summary.setOneLiner("oneLiner");
		summary.setProductId(PRODUCT_ID);
		summary.setReadUrl("http://read-url");
		summary.setShopUrl("http://shop-url");
		summary.setTitle("title");
		
		return summary;
	}
	
	@Test
	public void testFindById() {
		
		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonSummary>>any()))
		.thenReturn(getJsonSummary());
		
		Optional<JsonSummary> summary = summaryRestRepository.findById(PRODUCT_ID);
		
		assertNotNull(summary);
		assertTrue(summary.isPresent());
		
		assertEquals("about", summary.get().getAbout());
		assertEquals("http://image.png", summary.get().getCoverImage());
		assertEquals("features", summary.get().getFeatures());
		assertEquals("learn", summary.get().getLearn());
		assertEquals("oneLiner", summary.get().getOneLiner());
		assertEquals(PRODUCT_ID, summary.get().getProductId());
		assertEquals("http://read-url", summary.get().getReadUrl());
		assertEquals("http://shop-url", summary.get().getShopUrl());
		assertEquals("title", summary.get().getTitle());
		
	}
	
	@Test
	public void testFindByIdNull() {
		
		Mockito.when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.<Class<JsonSummary>>any()))
		.thenReturn(null);
		
		Optional<JsonSummary> summary = summaryRestRepository.findById(PRODUCT_ID);
		
		assertNotNull(summary);
		assertFalse(summary.isPresent());
		
	}

}
