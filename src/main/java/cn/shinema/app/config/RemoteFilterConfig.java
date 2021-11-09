package cn.shinema.app.config;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiAclProperties.class)
public class RemoteFilterConfig {

	@Autowired
	ApiAclProperties apiAclProperties;

	@Bean
	public FilterRegistrationBean<RemoteAddrFilter> remoteAddressFilter() {
		FilterRegistrationBean<RemoteAddrFilter> filterRegistrationBean = new FilterRegistrationBean<RemoteAddrFilter>();

		RemoteAddrFilter filter = new RemoteAddrFilter();
//		filter.setAllow("127.0.0.1");
//		filter.setAllow("0:0:0:0:0:0:0:1");
		filter.setAllow(apiAclProperties.getIpAddressPatterns());
		filterRegistrationBean.setFilter(filter);

//		filterRegistrationBean.addUrlPatterns("/gs/serving-web-content/testParameters");
//		filterRegistrationBean.addUrlPatterns("/gs/serving-web-content/testHeaders");
		apiAclProperties.getUrlPatterns().forEach(urlPattern -> filterRegistrationBean.addUrlPatterns(urlPattern));

		filterRegistrationBean.setName("RemoteAddressFilter");
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setEnabled(false);

		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<RemoteIpFilter> remoteIPFilter() {
		FilterRegistrationBean<RemoteIpFilter> filterRegistrationBean = new FilterRegistrationBean<RemoteIpFilter>();
		RemoteIpFilter filter = new RemoteIpFilter();
		
//		filter.setRemoteIpHeader("x-forwarded-for");
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("RemoteIPFilter");
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		return filterRegistrationBean;
	}
}
