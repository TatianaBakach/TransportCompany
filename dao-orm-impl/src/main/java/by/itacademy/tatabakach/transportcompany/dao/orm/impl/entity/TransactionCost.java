package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;

@Entity
public class TransactionCost extends BaseEntity implements ITransactionCost {

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Currency currency;
	
	@Column
	private BigDecimal amount;
	
	@Column
	private BigDecimal rate;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Currency intermediateCurrency;
	
	@Column
	private BigDecimal intermediateCurrencyRate;
	
	@Column
	private Integer paymentPeriod;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private PaymentTermsType paymentTermsType;
	
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
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(final Currency currency) {
		this.currency = currency;
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
	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public void setRate(final BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public Currency getIntermediateCurrency() {
		return intermediateCurrency;
	}

	@Override
	public void setIntermediateCurrency(final Currency intermediateCurrency) {
		this.intermediateCurrency = intermediateCurrency;
	}

	@Override
	public BigDecimal getIntermediateCurrencyRate() {
		return intermediateCurrencyRate;
	}

	@Override
	public void setIntermediateCurrencyRate(final BigDecimal intermediateCurrencyRate) {
		this.intermediateCurrencyRate = intermediateCurrencyRate;
	}

	@Override
	public Integer getPaymentPeriod() {
		return paymentPeriod;
	}

	@Override
	public void setPaymentPeriod(final Integer paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	@Override
	public PaymentTermsType getPaymentTermsType() {
		return paymentTermsType;
	}

	@Override
	public void setPaymentTermsType(final PaymentTermsType paymentTermsType) {
		this.paymentTermsType = paymentTermsType;
	}

	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(final String note) {
		this.note = note;
	}

}
