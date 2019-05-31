package brv.notifier.packt.services.mailing;

import java.util.HashMap;
import java.util.Map;

public class EmailData {

	private String subject = "";
	
	private String template = "";
	private Map<String, Object> variables = new HashMap<>();

	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	

	

	
	
	
}