package cn.shinema.app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user_info")
@NamedQuery(query = "select u from UserInfo u", name = "query_find_all_userinfos")
@DynamicInsert
@DynamicUpdate
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -3385483052003669854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 11)
	private long id;

	@Column(name = "nick_name", length = 100)
	private String nickName;

	@Column
	private String icon;

	@Column
	private Integer age;

	@Embedded
	private Address address;

	@Column(name = "login_num")
	private int loginNum;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date LastModifiedDate;

	public UserInfo() {
		super();
	}

	public UserInfo(String nickName, Integer age, Address address) {
		super();
		this.nickName = nickName;
		this.age = age;
		this.address = address;
	}

	public void loginAdd() {
		synchronized (this) {
			this.loginNum++;
		}
	}

}
