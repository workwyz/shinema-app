package cn.shinema.app.application.command;

import java.io.Serializable;

import cn.shinema.core.AssertionConcern;

public class ModifyCommand extends AssertionConcern implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cellphone;

	private String nickname;

	private Integer age;

	public ModifyCommand() {
		super();
	}

	public ModifyCommand(String cellphone, String nickname, Integer age) {
		super();
		assertArgumentNotNull(cellphone, "register cellphone is not be null.");

		this.cellphone = cellphone;
		this.nickname = nickname;
		this.age = age;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
