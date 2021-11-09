package cn.shinema.app.application.representation;

import java.io.Serializable;

import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.exception.NegativeCode;
import cn.shinema.app.exception.NegativeException;
import lombok.Data;

@Data
public class UserInfoRepresentation implements Serializable {
	private static final long serialVersionUID = 2899304320063865318L;

	private String cellphone;

	private String email;

	private String nickname;

	private Integer age;

	public UserInfoRepresentation(UserAccount userAccount) throws NegativeException {
		super();
		initializeFrom(userAccount);
	}

	private void initializeFrom(UserAccount userAccount) throws NegativeException {

		try {
			this.cellphone = userAccount.cellphone();
			this.email = userAccount.email();
			this.nickname = userAccount.user().getNickName();
			this.age = userAccount.user().getAge();
		} catch (Exception e) {
			throw new NegativeException(NegativeCode.LOGIN_USERINFO_INIT_ERROR,
					"initializeFrom user account info is Exception", e);
		}

	}

}
