package brv.notifier.packt.services.offers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.notifier.packt.repositories.ImageRepository;
import brv.notifier.packt.repositories.OffersRepository;
import brv.notifier.packt.repositories.SummaryRepository;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.services.offers.mappers.PacktFreeOfferMapperImpl;
import brv.test.util.dummies.DummyJsonOffer;
import brv.test.util.dummies.DummyJsonSummary;

@RunWith(MockitoJUnitRunner.class)
public class ApiOffersServiceTest {

	@InjectMocks
	private ApiOffersService offersService = new ApiOffersService();
	
	@Mock
	private OffersRepository offersDao;
	
	@Mock
	private SummaryRepository summaryDao;
	
	@Mock
	private PacktFreeOfferMapperImpl mapper;
	
	@Mock
	private ImageRepository imageDao;
	
	@Test
	public void testGetPacktOffer() {
		
		LocalDate today = LocalDate.now();
		
		// There is a JsonOffer response
		Mockito.doReturn(Optional.of(DummyJsonOffer.get()))
				.when(offersDao)
				.getOffer(ArgumentMatchers.any());

		// There is a JsonSummaryResponse
		Mockito.doReturn(Optional.of(DummyJsonSummary.get()))
		.when(summaryDao)
		.findById(ArgumentMatchers.any());
		
		// Execute real mapping
		Mockito.doCallRealMethod()
		.when(mapper)
		.jsonToModel(ArgumentMatchers.any());
		
		Mockito.doReturn(Optional.empty()) 
		.when(imageDao)
		.getFromUrl(ArgumentMatchers.any());
		
		PacktFreeOffer offer = offersService.getPacktOffer(today);
		
		// Verify there is a non null offer. 
		// Content of the result is not verified, as it can be anything.
		assertNotNull(offer);
	}
	
	@Test
	public void testGetPacktOfferNullWhenNoOffer() {
		
		LocalDate today = LocalDate.now();
		
		// There is no JsonOffer response
		Mockito.doReturn(Optional.empty())
				.when(offersDao)
				.getOffer(ArgumentMatchers.any());

		PacktFreeOffer offer = offersService.getPacktOffer(today);	
		assertNull(offer);
	}
	
	@Test
	public void testGetPacktOfferNullWhenNoSummary() {
		
		LocalDate today = LocalDate.now();
		
		// There is a JsonOffer response
		Mockito.doReturn(Optional.of(DummyJsonOffer.get()))
			.when(offersDao)
			.getOffer(ArgumentMatchers.any());

		// There is no JsonSummary response
		Mockito.doReturn(Optional.empty())
			.when(summaryDao)
			.findById(ArgumentMatchers.any());
		
		PacktFreeOffer offer = offersService.getPacktOffer(today);
		assertNull(offer);
	}

}
