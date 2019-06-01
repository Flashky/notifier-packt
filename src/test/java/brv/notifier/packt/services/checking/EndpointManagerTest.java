package brv.notifier.packt.services.checking;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import brv.notifier.packt.repositories.EndpointManager;

public class EndpointManagerTest {
 
	private EndpointManager manager = new EndpointManager();
	private LocalDate testDate = LocalDate.of(2019, Month.JUNE, 01);
	
	@Test
	public void testEndpointDateFrom() {

		String endpoint = manager.getOffersEndpoint(testDate);
		assertEquals("https://services.packtpub.com/free-learning-v1/offers?dateFrom=2019-06-01", endpoint);
		
	}
	
	@Test
	public void testEndpointDateFromDateTo() {

		String endpoint = manager.getOffersEndpoint(testDate, testDate.plusDays(1));
		assertEquals("https://services.packtpub.com/free-learning-v1/offers?dateFrom=2019-06-01&dateTo=2019-06-02", endpoint);

	}

}
