package brv.notifier.packt.properties;

import java.io.Serializable;

public class ProxyProperty implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020307668656121299L;
	
	private String host;
	private Integer port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
	
}
