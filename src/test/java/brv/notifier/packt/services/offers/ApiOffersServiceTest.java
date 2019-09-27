package brv.notifier.packt.services.offers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import brv.notifier.packt.restclients.OffersRestClient;
import brv.notifier.packt.restclients.ProductsRestClient;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapperImpl;
import brv.test.util.dummies.DummyJsonOffers;
import brv.test.util.dummies.DummyJsonSummary;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest 
@RunWith(SpringRunner.class)
public class ApiOffersServiceTest {

	@Autowired
	private ApiOffersService offersService;// = new ApiOffersService();
	
	@MockBean
	private OffersRestClient offersRestClient;
	
	@MockBean
	private ProductsRestClient productsRestClient;
	
	@Mock
	private PacktFreeOfferMapperImpl mapper;
	
	@Test
	public void testGetPacktOffer() {
		
		LocalDate today = LocalDate.now();
		
		// There is a JsonOffer response
		BDDMockito.given(offersRestClient.getOffers(BDDMockito.any(), BDDMockito.any()))
			.willReturn(DummyJsonOffers.getJsonOffers());
		
		// There is a JsonSummaryResponse
		BDDMockito.given(productsRestClient.getProductSummary(BDDMockito.any()))
			.willReturn(DummyJsonSummary.get());
		
		// Mock the image
		Mockito.doReturn(null)
			.when(productsRestClient)
			.getProductImage(ArgumentMatchers.any());

		
		// Execute real mapping
		Mockito.doCallRealMethod()
				.when(mapper)
				.jsonToModel(ArgumentMatchers.any());
		
		PacktFreeOffer offer = offersService.getPacktOffer(today);
		
		// Verify there is a non null offer. 
		// Content of the result is not verified, as it can be anything.
		assertNotNull(offer);
	}
	
	@Test
	public void testGetPacktOfferNullWhenNoOffer() {
		
		LocalDate today = LocalDate.now();
		
		// There is no JsonOffer response
		BDDMockito.given(offersRestClient.getOffers(BDDMockito.any(), BDDMockito.any()))
				.willReturn(null);

		PacktFreeOffer offer = offersService.getPacktOffer(today);	
		assertNull(offer);
	}
	
	@Test
	public void testGetPacktOfferNullWhenNoSummary() {
		
		LocalDate today = LocalDate.now();
		
		// There is a JsonOffer response
		BDDMockito.given(offersRestClient.getOffers(BDDMockito.any(), BDDMockito.any()))
					.willReturn(DummyJsonOffers.getJsonOffers());
		
		// There is no JsonSummary response
		BDDMockito.given(productsRestClient.getProductSummary(BDDMockito.any()))
					.willReturn(null);
		
		PacktFreeOffer offer = offersService.getPacktOffer(today);
		assertNull(offer);
	}

}
