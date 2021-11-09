package cn.shinema.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.shinema.core.domain.ConcurrencySafeEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_student", uniqueConstraints = { @UniqueConstraint(columnNames = { "stu_id" }), @UniqueConstraint(columnNames = { "cellphone" }),
		@UniqueConstraint(columnNames = { "email" }) })
@NamedQuery(query = "select u from Student u", name = "query_find_all_student")
public class Student extends ConcurrencySafeEntity {
	private static final long serialVersionUID = -3108538215935882315L;

	@Column(name = "stu_id", nullable = false, length = 64)
	private String stuId;

	@Column(name = "stu_name", nullable = false, length = 255)
	private String stuName;

	@Column
	private int age;

	@Column(name = "cellphone", length = 20)
	private String cellphone;

	@Column(name = "email", length = 100)
	private String email;

	@Column(length = 255)
	private String hobby;

	@Column(length = 255)
	private String remark;

	public Student() {
		super();
	}

}
