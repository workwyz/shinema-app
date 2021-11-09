package cn.shinema.app.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.acl")
public class ApiAclProperties {

	private List<String> urlPatterns;
	private String ipAddressPatterns;

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}

	public String getIpAddressPatterns() {
		return ipAddressPatterns;
	}

	public void setIpAddressPatterns(String ipAddressPatterns) {
		this.ipAddressPatterns = ipAddressPatterns;
	}

	@Override
	public String toString() {
		return "ApiAclProperties{" + "urlPatterns=" + urlPatterns + ", ipAddressPatterns='" + ipAddressPatterns + '\''
				+ '}';
	}

}
