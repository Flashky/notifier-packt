package brv.notifier.packt.services.mailing;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ThymeleafTemplateService implements TemplateService {

	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public String process(String template) {
		
		Context ctx = new Context(new Locale("es","ES"));

		return templateEngine.process(template, ctx);
	}

	@Override
	public String process(String template, Map<String, Object> variables) {
		
		Context ctx = new Context(new Locale("es","ES"));
		
		if(!CollectionUtils.isEmpty(variables)) {
			ctx.setVariables(variables);
		}
		
		return templateEngine.process(template, ctx);
	}
	
	

}
