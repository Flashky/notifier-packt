package brv.notifier.packt.services.mailing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.exceptions.TemplateInputException;

import brv.test.util.FileUtil;

@SpringBootTest 
@RunWith(SpringRunner.class)
//@TestPropertySource(properties = {"MAIL_PASSWORD = anything"})
@ActiveProfiles("test")
public class ThymeleafTemplateServiceTest {

	@Autowired
	private TemplateService templateService;
	
	private FileUtil fileUtil = new FileUtil();
	private static final String FILE_SIMPLE_MAIL = "mail-test";
	private static final String FILE_VARIABLES_MAIL = "mail-test-variables";
	private static final String FILE_VARIABLES_UNSET_MAIL = "mail-test-variables-unset";
	private static final String RESULT_PATH = "results/";
	private static final String HTML_EXTENSION = ".html";
	
	@Test
	public void testProcess() throws Exception {
		
		String html = templateService.process(FILE_SIMPLE_MAIL);
		String expectedHtml = fileUtil.getFile(RESULT_PATH + FILE_SIMPLE_MAIL + HTML_EXTENSION);
		
		assertEquals(expectedHtml, html);
	}
	
	
	@Test
	public void testProcessNonExistingTemplate() {
		try {
			templateService.process("non-exist");
			fail("expected exception");
		} catch(TemplateInputException e) {
			
		}
	}
	
	@Test
	public void testProcessVariables() throws URISyntaxException, IOException {

		Map<String, Object> variables = new HashMap<>();
		variables.put("sampleHeaderValue", "Sample header value");
		
		String html = templateService.process(FILE_VARIABLES_MAIL, variables);
		String expectedHtml = fileUtil.getFile(RESULT_PATH + FILE_VARIABLES_MAIL + HTML_EXTENSION);
		
		assertEquals(expectedHtml, html);
	}
	
	@Test
	public void testProcessVariablesEmpty() throws URISyntaxException, IOException {
		
		String html = templateService.process(FILE_VARIABLES_MAIL, new HashMap<>());
		String expectedHtml = fileUtil.getFile(RESULT_PATH + FILE_VARIABLES_UNSET_MAIL + HTML_EXTENSION);
		
		assertEquals(expectedHtml, html);
		
	}
	
	@Test
	public void testProcessVariablesNull() throws URISyntaxException, IOException {
		
		String html = templateService.process(FILE_VARIABLES_MAIL, null);
		String expectedHtml = fileUtil.getFile(RESULT_PATH + FILE_VARIABLES_UNSET_MAIL + HTML_EXTENSION);
		
		assertEquals(expectedHtml, html);
		
	}
	
	@Test
	public void testProcessVariablesNonExistingTemplate() {
		
		try {
			templateService.process("non-exist", new HashMap<>());
			fail("expected exception");
		} catch(TemplateInputException e) {
			
		}
		
	}

}
