package cn.shinema.app.port.adapter.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.domain.UserRegisterFinished;
import cn.shinema.core.event.ConsumedEventRepository;
import cn.shinema.core.event.KafkaConsumer;
import cn.shinema.core.port.adapter.util.JsonUtils;

@Order(50)
@Component
public class UserRegistStatisticsListener implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistStatisticsListener.class);

	@Autowired
	ConsumedEventRepository consumedEventRepository;

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) throws Exception {
		LOGGER.info("====================UserRegistListener=================start");
	}

	@KafkaConsumer(topic = "j_event_user_regist_finish", groupId = "test", enableDLQ = false)
	public void listen(String message) throws Exception {
		LOGGER.info("Receive Message:: Message=>{}", message);

		UserRegisterFinished userRegisterFinished = JsonUtils.toBean(message, UserRegisterFinished.class);
		String accountId = userRegisterFinished.accountId();
//		Date loginDate = userRegisterFinished.getRegistDate();

		userAccountApplication.registStatistics(accountId);

		Thread.sleep(500);
	}

}
