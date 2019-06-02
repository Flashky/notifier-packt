package brv.notifier.packt.services.mailing;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.simplejavamail.mailer.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest 
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SimpleJavaMailServiceTest {

	@Autowired
	private SimpleJavaMailService mailService;
	
	@MockBean
	private Mailer mailer;
	
	private static final String FILE_SIMPLE_MAIL = "mail-test";
	
	@Test
	public void test() {
		
		EmailData emailData = new EmailData();
		emailData.setTemplate(FILE_SIMPLE_MAIL);
		emailData.setSubject("subject");
		emailData.setVariables(new HashMap<>());
		
		// NOTICE:
		// Void methods like "mailer.sendMail(Email)" do nothing for @MockBean annotated objects
		// Therefore, it is safe to use the following without having to mock any method.
		
		mailService.send(emailData);
	}

}
