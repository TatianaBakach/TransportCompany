package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;

public class Payment extends BaseEntity implements IPayment {
	
	private Date date;
	
	private IOrder order;
	
	private ICompany company;
	
	private Currency currency;
	
	private BigDecimal rate;
	
	private BigDecimal amount;
	
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

}
