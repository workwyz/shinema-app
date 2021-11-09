package cn.shinema.app.domain;

import java.util.Date;

import cn.shinema.core.AssertionConcern;
import cn.shinema.core.domain.DomainEvent;
import cn.shinema.core.event.EventPublisher;
import cn.shinema.core.notification.NotificationGateway;

@EventPublisher(quantity = 10, notifyTypes = { NotificationGateway.ALL })
public class UserLoginSuccessed extends AssertionConcern implements DomainEvent {

	private String accountId;
	private Date loginDate;

	public UserLoginSuccessed() {
		super();
	}

	public UserLoginSuccessed(String accountId, Date loginDate) {
		super();
		this.accountId = accountId;
		this.loginDate = loginDate;
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

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

}
