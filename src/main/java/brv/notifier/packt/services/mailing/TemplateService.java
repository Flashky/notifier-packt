package brv.notifier.packt.services.mailing;

import java.util.Map;

public interface TemplateService {

	/**
	 * Processes the template specified by parameter.
	 * <p>This method performs no parameter substitution.</p>
	 * @param templateName the template name to instantiate
	 * @return
	 */
	String process(String template);
	
	/**
	 * Processes the template specified by parameter.
	 * <p>This method performs parameter substitution using the values contained in the input Map.</p>
	 * <p>If the map is <code>empty</code> or <code>null</code>, result will be the same as executing {@link #process(String)}
	 * @param template
	 * @param variables
	 * @return
	 */
	String process(String template, Map<String, Object> variables);
	
}
