package brv.notifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import brv.notifier.packt.NotifierPacktApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotifierPacktApplication.class)
public class NotifierPacktApplicationTests {
	
	@Test
	public void contextLoads() {
		// In order to make the test work, you will need to add the following environment variables:
		// - ENCRYPT_MASTER_PASSWORD
		// - MAIL_PASSWORD
	}

}
