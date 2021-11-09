package cn.shinema.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Immutable;

import cn.shinema.core.AssertionConcern;

@Embeddable
@Immutable
public class Address extends AssertionConcern implements Serializable {
	private static final long serialVersionUID = -941409229750582840L;

	@Column(length = 50)
	private String province;

	@Column(length = 100)
	private String city;

	@Column(length = 100)
	private String area;

	public Address(String province, String city, String area) {
		super();
		this.setArea(area);
		this.setCity(city);
		this.setProvince(province);
	}

	public Address() {
		super();
	}

	public String province() {
		return province;
	}

	public String city() {
		return city;
	}

	public String area() {
		return area;
	}

	private void setProvince(String province) {
		assertArgumentNotNull(province, "province not be null.");
		this.province = province;
	}

	private void setCity(String city) {
		assertArgumentNotNull(city, "city not be null.");
		this.city = city;
	}

	private void setArea(String area) {
		assertArgumentNotNull(area, "area not be null.");
		this.area = area;
	}

}
