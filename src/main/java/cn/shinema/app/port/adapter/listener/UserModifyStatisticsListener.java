package cn.shinema.app.port.adapter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.domain.UserModifySuccess;
import cn.shinema.core.event.KafkaConsumer;
import cn.shinema.core.port.adapter.util.JsonUtils;

@Component
public class UserModifyStatisticsListener implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserModifyStatisticsListener.class);

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) throws Exception {
		LOGGER.info("=====================================start");
	}

	@KafkaConsumer(topic = "cn.shinema.app.domain.UserModifySuccess", groupId = "test")
	public void listen(Long notifyId, String message) throws Exception {
		LOGGER.info("Receive Message::  NotifyId=>{}; Message=>{}", notifyId, message);

		UserModifySuccess userRegisterFinished = JsonUtils.toBean(message, UserModifySuccess.class);
		String accountId = userRegisterFinished.accountId();
//		Date loginDate = userRegisterFinished.getRegistDate();

		userAccountApplication.modifyStatistics(accountId);
	}

}
