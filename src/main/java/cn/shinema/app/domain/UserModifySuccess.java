package cn.shinema.app.domain;

import java.util.Date;

import cn.shinema.core.AssertionConcern;
import cn.shinema.core.domain.DomainEvent;
import cn.shinema.core.event.EventPublisher;
import cn.shinema.core.notification.NotificationGateway;

@EventPublisher(quantity = 10, notifyTypes = { NotificationGateway.KAFKA })
public class UserModifySuccess extends AssertionConcern implements DomainEvent {

	private String accountId;

	private String nickname;

	private int age;

	private Date modifyDate;

	public UserModifySuccess() {
		super();
	}

	public UserModifySuccess(String accountId, Date modifyDate) {
		super();
		this.accountId = accountId;
		this.modifyDate = modifyDate;
	}

	public UserModifySuccess(String accountId, String nickname, int age, Date modifyDate) {
		super();
		this.accountId = accountId;
		this.nickname = nickname;
		this.age = age;
		this.modifyDate = modifyDate;
	}

	public Date occurredOn() {
		return new Date();
	}

	public String accountId() {
		return accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
