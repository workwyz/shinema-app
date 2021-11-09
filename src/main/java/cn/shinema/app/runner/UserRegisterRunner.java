package cn.shinema.app.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.application.command.RegisterCommand;
import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.exception.NegativeException;

@Order(100)
//@Component
public class UserRegisterRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterRunner.class);

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) {

		String cellpone = "13022221111";
		String password = "test";
		String nickname = "test";
		Integer age = 10;
		String province = "gd";
		String city = "sz";
		String area = "ba";

		RegisterCommand command = new RegisterCommand(cellpone, password, nickname, age, province, city, area);
		try {
			UserAccount userAccount = userAccountApplication.register(command);
		} catch (NegativeException e) {
			LOGGER.error(e.getMessage());
		}
//		UserInfoRepresentation activityListRepresentation = new UserInfoRepresentation(userAccount);
//		LOGGER.info("result => {}", JsonUtils.toStr(activityListRepresentation));
	}

}
