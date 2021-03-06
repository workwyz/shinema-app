package cn.shinema.app.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.shinema.core.domain.ConcurrencySafeEntity;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user_statistics", uniqueConstraints = { @UniqueConstraint(columnNames = { "stat_date", "account_id" }) })
@NamedQuery(query = "select u from UserStatistics u", name = "query_find_all_user_statistics")
@DynamicInsert
@DynamicUpdate
public class UserStatistics extends ConcurrencySafeEntity {
	private static final long serialVersionUID = -3385483052003669854L;

	@Column(name = "account_id", nullable = false, length = 64)
	private String accountId;

	@Column(name = "stat_date", nullable = false, length = 10, columnDefinition = "DATE")
	private Date statDate;

	@Column(name = "reg_day")
	private boolean regDay = false;

	@Column(name = "modify_num")
	private int modifyNum = 0;

	@Column(name = "login_num")
	private int loginNum = 0;

	public UserStatistics(String accountId) {
		super();
		this.statDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.accountId = accountId;
	}

	public UserStatistics() {
		super();
	}

	public void finishRegisted() {
		this.regDay = true;
	}

	public void loginAdd() {
		synchronized (this) {
			this.loginNum++;
		}
	}

	public void modifyAdd() {
		synchronized (this) {
			this.modifyNum++;
		}
	}

	public static Date today() {
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static void main(String[] args) {
		Date today = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		System.out.println("???:" + cal.get(Calendar.YEAR));
		System.out.println("???:" + (cal.get(Calendar.MONTH) + 1));
		System.out.println("???:" + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("???:" + cal.get(Calendar.HOUR_OF_DAY));
		System.out.println("???:" + cal.get(Calendar.MINUTE));
		System.out.println("???:" + cal.get(Calendar.SECOND));

		LocalDate nowDate = LocalDate.now();
		System.out.println("??????????????????" + nowDate);// ??????????????????2018-09-06
		int year = nowDate.getYear();// ????????????????????????????????????
		System.out.println("year???" + year);// year???2018
		int month = nowDate.getMonthValue();// ????????????????????????????????????
		System.out.println("month???" + month);// month???9
		int day = nowDate.getDayOfMonth();// ?????????????????????????????????????????????????????????
		System.out.println("day???" + day);// day???6

		int dayOfYear = nowDate.getDayOfYear();// ????????????????????????
		System.out.println("dayOfYear???" + dayOfYear);// dayOfYear???249

		// ??????
		System.out.println(nowDate.getDayOfWeek());// THURSDAY
		System.out.println(nowDate.getDayOfWeek().getValue());// 4
		// ??????
		System.out.println(nowDate.getMonth());// SEPTEMBER
		System.out.println(nowDate.getMonth().getValue());// 9

		System.out.println(nowDate);

//		ZonedDateTime zonedDateTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault());

		System.out.println(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

	}
}
