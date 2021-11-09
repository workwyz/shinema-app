package cn.shinema.app.runner;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import cn.shinema.app.exception.NegativeException;

@Component
@Order(100)
public class RedisTestRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisTestRunner.class);

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void run(String... args) throws NegativeException {

		LOGGER.info("======================================================");
		for (int i = 0; i < 10; i++) {
			String randomKey = UUID.randomUUID().toString();
			redisTemplate.boundValueOps(randomKey).set(String.valueOf(i));

//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

			LOGGER.info("index => {} ;randomKey => {}", i, randomKey);
		}
		LOGGER.info("======================================================");

	}

}
