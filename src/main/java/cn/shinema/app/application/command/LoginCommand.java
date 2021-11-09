package cn.shinema.app.application.command;

import java.io.Serializable;

import cn.shinema.core.AssertionConcern;

public class LoginCommand extends AssertionConcern implements Serializable {
	private static final long serialVersionUID = 7729059130570349898L;

	private String username;
	private String password;

	public LoginCommand(String username, String password) {
		super();
		assertArgumentNotNull(username, "login accoutName is not be null.");
		assertArgumentNotNull(password, "login password is not be null.");
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
