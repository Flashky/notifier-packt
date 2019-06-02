package brv.notifier.packt.services;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.notifier.packt.services.offers.OffersService;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;
import brv.test.util.dummies.DummyDailyNotificationListener;

@RunWith(MockitoJUnitRunner.class)
public class PacktCheckTaskTest {

	@InjectMocks
	private static PacktCheckTask service;
	
	@Mock
	private OffersService offersService;
	
	@Mock
	private MessageHelper messageHelper;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		service = new PacktCheckTask();
		
		// Add a dummy listener
		service.addNotificationListener(new DummyDailyNotificationListener());
		
	}
	@Test
	public void testNewOffer() {
	
		try {
			
			// There is a PacktFreeOffer response
			Mockito.doReturn(new PacktFreeOffer())
					.when(offersService)
					.getPacktOffer(ArgumentMatchers.any());
			
			// Two loops, to check first and repeated offer
			service.checkPacktDailyOffer();
			service.checkPacktDailyOffer();
			
		} catch(Exception e) {
			fail("Unexpected exception");
		}
	}


	@Test
	public void testNullOffer() {
		try {
			
			// There is no PacktFreeOffer response
			Mockito.doReturn(null)
					.when(offersService)
					.getPacktOffer(ArgumentMatchers.any());
			
			service.checkPacktDailyOffer();
			
		} catch(Exception e) {
			fail("Unexpected exception");
		}
	}
}
