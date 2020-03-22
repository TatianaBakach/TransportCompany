package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;

@Entity
public class Payment extends BaseEntity implements IPayment {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
	private ICompany company;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Currency currency;
	
	@Column
	private BigDecimal rate;
	
	@Column
	private BigDecimal amount;
	
	@Column
	private String note;
	
	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(final Date date) {
		this.date = date;
	}
	
	@Override
	public IOrder getOrder() {
		return order;
	}
	
	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
	}
	
	@Override
	public ICompany getCompany() {
		return company;
	}

	@Override
	public void setCompany(final ICompany company) {
		this.company = company;

	}
	
	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(final Currency currency) {
		this.currency = currency;
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
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(String note) {
		this.note = note;
	}

}
