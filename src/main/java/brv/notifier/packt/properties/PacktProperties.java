package brv.notifier.packt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="service")
public class PacktProperties {
	
	private String cron;
	private ProxyProperty proxy;
	
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}



	public ProxyProperty getProxy() {
		return proxy;
	}

	public void setProxy(ProxyProperty proxy) {
		this.proxy = proxy;
	}
	
}
