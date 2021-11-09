package cn.shinema.app.port.adapter.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.shinema.app.service.GreetingService;

@DubboService(version = "0.0.1")
public class GreetingServiceImpl implements GreetingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceImpl.class);

	public String greeting(String name) {
		LOGGER.info("provider received invoke of greeting: {}", name);
		sleepWhile();
		return "Annotation, greeting " + name;
	}

	public String replyGreeting(String name) {
		LOGGER.info("provider received invoke of replyGreeting:{} ", name);
		sleepWhile();
		return "Annotation, fine " + name;
	}

	private void sleepWhile() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
