package cn.shinema.app.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.shinema.app.exception.NegativeException;
import cn.shinema.core.domain.ConcurrencySafeEntity;
import cn.shinema.core.domain.DomainEvent;
import cn.shinema.core.domain.DomainEventPublisher;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user_account", uniqueConstraints = { @UniqueConstraint(columnNames = { "account_id" }), @UniqueConstraint(columnNames = { "cellphone" }),
		@UniqueConstraint(columnNames = { "email" }) })
@NamedQuery(query = "select u from UserAccount u", name = "query_find_all_user_accounts")
@DynamicInsert
@DynamicUpdate
@Data
@Accessors(fluent = true)
public class UserAccount extends ConcurrencySafeEntity {
	private static final long serialVersionUID = -3385483052003669854L;

	@Column(name = "account_id", nullable = false, length = 64)
	private String accountId;

	@Column(length = 20)
	private String cellphone;

	@Column(length = 100)
	private String email;

	@Column(length = 100)
	private String password;

	@Column(length = 32)
	private String pwsalt;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private UserInfo user;

	public UserAccount() {
		super();
	}

	public UserAccount(String accountId) {
		super();
		this.accountId = accountId;
	}

	public UserAccount register(String cellphone, String password, String pwsalt, UserInfo user) throws NegativeException {
		this.cellphone = cellphone;
		this.password = password;
		this.pwsalt = pwsalt;
		this.user = user;
		DomainEvent registEvent = new UserRegisterFinished(this.accountId, new Date());
		DomainEventPublisher.instance().publish(registEvent);
		return this;
	}

	public UserAccount login(String password) throws NegativeException {
		assertArgumentNotEmpty(password, "login password is not be empty.");

		if (!this.password.equals(password)) {
			super.assertArgumentNotEquals(this.password, password, "登录密码错误！");
		}

		DomainEvent loginEvent = new UserLoginSuccessed(this.accountId, new Date());
		DomainEventPublisher.instance().publish(loginEvent);

		return this;
	}

	public UserAccount modifyUser(String nickname, int age) {
		user.setNickName(nickname);
		user.setAge(age);
		DomainEvent modifyEvent = new UserModifySuccess(this.accountId, nickname, age, new Date());
		DomainEventPublisher.instance().publish(modifyEvent);
		return this;
	}

	public void statistics() {
		user.loginAdd();
	}

}
