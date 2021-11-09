package cn.shinema.app.application;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.shinema.app.application.command.LoginCommand;
import cn.shinema.app.application.command.ModifyCommand;
import cn.shinema.app.application.command.RegisterCommand;
import cn.shinema.app.domain.Address;
import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.domain.UserAccountRepository;
import cn.shinema.app.domain.UserInfo;
import cn.shinema.app.domain.UserStatistics;
import cn.shinema.app.domain.UserStatisticsRepository;
import cn.shinema.app.exception.NegativeCode;
import cn.shinema.app.exception.NegativeException;
import cn.shinema.core.event.EventListener;

@Service
public class UserAccountApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountApplication.class);

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserStatisticsRepository userStatisticsRepository;

	@EventListener
//	@Transactional(rollbackFor = { Throwable.class })
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class, Error.class })
	public UserAccount register(RegisterCommand command) throws NegativeException {
		LOGGER.info("user register command >> {}", command);
		UserAccount account = userAccountRepository.findByCellphone(command.getCellphone());
		if (account == null) {
			UserInfo userinfo = new UserInfo(command.getNickname(), command.getAge(), new Address(command.getProvince(), command.getCity(), command.getArea()));

			account = new UserAccount(UUID.randomUUID().toString().replace("-", ""));
			String pwsalt = randomSalt();
			account.register(command.getCellphone(), securePasswd(command.getPassword(), pwsalt), pwsalt, userinfo);

			userAccountRepository.save(account);
		} else {
			throw new NegativeException(NegativeCode.REGISTER_USER_IS_EXIST, "注册账户已存在.");
		}
		return account;
	}

	@EventListener
	@Transactional(rollbackFor = { Throwable.class })
	public UserAccount login(LoginCommand command) throws NegativeException {
		LOGGER.info("user login command >> {}", command);

		UserAccount account = userAccountRepository.findByUserName(command.getUsername());

		if (account != null) {
			return account.login(securePasswd(command.getPassword(), account.pwsalt()));
		} else {
			throw new NegativeException(NegativeCode.LOGIN_USER_NOT_EXIST, "登录账户不存在.");
		}
	}

	@EventListener
	@Transactional(rollbackFor = { Throwable.class })
	public UserAccount modify(ModifyCommand command) throws NegativeException {
		LOGGER.info("user modify command >>{}", command);
		UserAccount account = userAccountRepository.findByCellphone(command.getCellphone());

		if (account != null) {

			account.modifyUser(command.getNickname(), command.getAge());

			userAccountRepository.save(account);

			return account;
		} else {
			throw new NegativeException(400, "账户不存在.");
		}

	}

	@Transactional(rollbackFor = { Throwable.class })
	public void loginForUser(String accountId) {
		LOGGER.info("user login statistics >> {}", accountId);
		UserAccount account = userAccountRepository.findByAccountId(accountId);
		if (account != null) {
			account.statistics();
			userAccountRepository.save(account);
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void registStatistics(String accountId) {
		LOGGER.info("user regist registStatistics Id >> {}", accountId);

		UserStatistics stat = getOne(accountId);
		if (stat != null) {
			stat.finishRegisted();
			userStatisticsRepository.save(stat);
		}
	}

	@Transactional(rollbackFor = { Throwable.class })
	public void loginStatistics(String accountId) {
		LOGGER.info("user regist loginStatistics Id >> {}", accountId);

		UserStatistics stat = getOne(accountId);
		if (stat != null) {
			stat.loginAdd();
			userStatisticsRepository.save(stat);
		}

	}

	@Transactional(readOnly = false, rollbackFor = { Throwable.class })
	public void modifyStatistics(String accountId) {
		LOGGER.info("user modify statistics Id >> {}", accountId);

		UserStatistics stat = getOne(accountId);
		if (stat != null) {
			stat.modifyAdd();
			userStatisticsRepository.save(stat);
		}

	}

	private synchronized UserStatistics getOne(String accountId) {
		UserStatistics stat = userStatisticsRepository.findByAccountIdAndStatDate(accountId, UserStatistics.today());
		if (stat == null) {
			return new UserStatistics(accountId);
		}
		return stat;
	}

	public Boolean passwdIsRight(String accNo, String pass) {
		return true;
	}

	private String securePasswd(String password, String salt) {
		return DigestUtils.sha256Hex(String.format("%s_%s", password, salt));
	}

	private String randomSalt() {
		return RandomStringUtils.randomAscii(10);
	}

}
