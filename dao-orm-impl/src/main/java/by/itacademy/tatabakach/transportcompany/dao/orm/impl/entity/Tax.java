package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;

@Entity
public class Tax extends BaseEntity implements ITax {
	
	@Column
	private String name;
	
	@Column
	private BigDecimal rate;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public void setRate(final BigDecimal rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "Tax [name=" + name + ", rate=" + rate + ", getId()=" + getId() + "]";
	}


}
