package brv.notifier.packt.services.offers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import brv.notifier.packt.repositories.OffersRepository;
import brv.notifier.packt.repositories.SummaryRepository;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.test.util.dummies.DummyJsonOffer;
import brv.test.util.dummies.DummyJsonSummary;

@SpringBootTest 
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ApiOffersServiceTest {

	@Autowired
	private ApiOffersService offersService;
	
	@MockBean
	private OffersRepository offersDao;
	
	@MockBean
	private SummaryRepository summaryDao;
	
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
