package cn.shinema.app.port.adapter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.domain.UserLoginSuccessed;
import cn.shinema.core.event.KafkaConsumer;
import cn.shinema.core.port.adapter.util.JsonUtils;

@Order(50)
@Component
public class UserLoginStatisticsListener implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginStatisticsListener.class);

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) throws Exception {
		LOGGER.info("========================UserLoginStatisticsListener=============start");
	}

	// @KafkaConsumer(topic = "j_event_user_login", groupId = "test")
	@KafkaConsumer(topic = "cn.shinema.app.domain.UserLoginSuccessed", groupId = "test2", retryCount = 1)
	public void listen(Long notifyId, String message) throws Exception {
		LOGGER.info("Receive Message2::  NotifyId=>{}; Message=>{}", notifyId, message);

//        int ri = RandomUtils.nextInt(1, 10);
//        if (ri == 5) {
		UserLoginSuccessed userLoginSuccessed = JsonUtils.toBean(message, UserLoginSuccessed.class);
		String username = userLoginSuccessed.accountId();
//		Date loginDate = userLoginSuccessed.getLoginDate();
		userAccountApplication.loginStatistics(username);
		Thread.sleep(500);
//        } else {
//            throw new Exception("=================================Error Test=================================");
//        }
	}

}
