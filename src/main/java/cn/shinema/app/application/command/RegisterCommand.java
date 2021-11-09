package cn.shinema.app.application.command;

import java.io.Serializable;

import cn.shinema.core.AssertionConcern;

public class RegisterCommand extends AssertionConcern implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cellphone;

	private String email;

	private String password;

	private String nickname;

	private Integer age;

	private String province;

	private String city;

	private String area;

	public RegisterCommand(String cellphone, String password, String nickname, Integer age, String province, String city, String area) {
		super();
		assertArgumentNotNull(cellphone, "register cellphone is not be null.");
		assertArgumentNotNull(password, "register password is not be null.");
		assertArgumentNotNull(nickname, "register nickname is not be null.");
		assertArgumentNotNull(age, "register age is not be null.");

		this.cellphone = cellphone;
		this.password = password;
		this.nickname = nickname;
		this.age = age;
		this.province = province;
		this.city = city;
		this.area = area;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
