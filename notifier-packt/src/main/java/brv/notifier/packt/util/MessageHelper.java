package brv.notifier.packt.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageHelper {

	private MessageSource messages;
	private Locale locale;
	
	public MessageHelper(MessageSource messageSource, Locale locale) {
	    this.messages = messageSource;
	    this.locale = locale;
	}
	
	public String getMessage(String code) {
		return messages.getMessage(code, null, locale);		
	}
	
	public String getMessage(String code, Object[] messageParameters) {
		return messages.getMessage(code, messageParameters, locale);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
}
