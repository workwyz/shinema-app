package cn.shinema.app.domain;

import java.util.Date;

import cn.shinema.core.AssertionConcern;
import cn.shinema.core.domain.DomainEvent;
import cn.shinema.core.event.EventPublisher;
import cn.shinema.core.notification.NotificationGateway;

@EventPublisher(name = "j_event_user_regist_finish", quantity = 5, notifyTypes = { NotificationGateway.KAFKA })
public class UserRegisterFinished extends AssertionConcern implements DomainEvent {

	private String accountId;

	private Date registDate;

	public UserRegisterFinished() {
		super();
	}

	public UserRegisterFinished(String accountId, Date registDate) {
		super();
		this.accountId = accountId;
		this.registDate = registDate;
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

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getRegistDate() {
		return registDate;
	}

}
