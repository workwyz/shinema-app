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
public class UserLoginListener implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginListener.class);

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) throws Exception {
		LOGGER.info("====================UserLoginListener=================start");
	}

//	@KafkaConsumer(topic = "j_event_user_login", groupId = "test")
	@KafkaConsumer(topic = "cn.shinema.app.domain.UserLoginSuccessed", groupId = "test")
	public void listen(Long notifyId, String message) throws Exception {
		LOGGER.info("Receive Message::  NotifyId=>{}; Message=>{}", notifyId, message);

		UserLoginSuccessed userLoginSuccessed = JsonUtils.toBean(message, UserLoginSuccessed.class);
		String accountId = userLoginSuccessed.accountId();
//		Date loginDate = userLoginSuccessed.getLoginDate();

		userAccountApplication.loginForUser(accountId);

//		throw new Exception("test========");

		Thread.sleep(100);
	}

}
