package cn.shinema.app.runner;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.shinema.app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(300)
@Slf4j
public class DubboServiceRunner implements CommandLineRunner {

	@DubboReference(version = "0.0.1")
	private GreetingService greetingService;

	public void run(String... args) throws Exception {
		log.info("======================================================");
		String result = greetingService.greeting("jim");
		log.info(String.format("%s%s", "dubbo GreetingService =>", result));
		log.info("======================================================");
	}

}
