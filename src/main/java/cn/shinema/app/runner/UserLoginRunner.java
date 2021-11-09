package cn.shinema.app.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.application.command.LoginCommand;
import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.exception.NegativeException;

@Order(101)
//@Component
public class UserLoginRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginRunner.class);

	@Autowired
	private UserAccountApplication userAccountApplication;

	public void run(String... args) throws NegativeException {
		String username = "13022221111";
		String password = "test";

		LoginCommand command = new LoginCommand(username, password);
		for (int i = 0; i < 5; i++) {
			UserAccount userAccount = userAccountApplication.login(command);
		}
//		UserInfoRepresentation activityListRepresentation = new UserInfoRepresentation(userAccount);

//		LOGGER.info("result => {}", JsonUtils.toStr(activityListRepresentation));
	}

}
