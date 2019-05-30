package brv.notifier.packt.services.checking;

import java.time.LocalDate;

import org.junit.Test;

import brv.notifier.packt.repositories.rest.EndpointManager;

public class EndpointManagerTest {
 
	@Test
	public void test() {
		EndpointManager manager = new EndpointManager();
		String endpoint = manager.getOffersEndpoint(LocalDate.now());
		
		System.out.println(endpoint);
	}
	
	@Test
	public void testBetween() {
		EndpointManager manager = new EndpointManager();
		String endpoint = manager.getOffersEndpoint(LocalDate.now(), LocalDate.now().plusDays(1));
		
		System.out.println(endpoint);
	}

}
