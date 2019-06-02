package brv.notifier.packt.notifications;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import brv.notifier.packt.services.mailing.EmailService;
import brv.notifier.packt.services.offers.dto.PacktFreeOffer;
import brv.notifier.packt.util.MessageHelper;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotificationListenerTest {
	
	@InjectMocks
	private EmailNotificationListener notificationListener;
	
	@Mock
	private EmailService emailService;
	
	@Mock
	private MessageHelper messageHelper;
	
	@Test
	public void testNotify() {
		
		try {
			
			// There is PacktFreeOffer input data
			Mockito.doNothing()
					.when(emailService)
					.send(ArgumentMatchers.any());
			
			notificationListener.notify(new PacktFreeOffer());
		
		} catch(Exception e) {
			fail("Unexpected exception");
		}
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testNotifyNull() {
		
		notificationListener.notify(null);
	}

}
