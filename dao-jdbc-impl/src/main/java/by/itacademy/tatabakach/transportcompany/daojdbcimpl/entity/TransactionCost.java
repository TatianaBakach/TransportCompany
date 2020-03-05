package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;

public class TransactionCost extends BaseEntity implements ITransactionCost {

	private Date date;
	private Currency currency;
	private BigDecimal amount;
	private BigDecimal rate;
	private Currency intermediateCurrency;
	private BigDecimal intermediateCurrencyRate;
	private Integer paymentPeriod;
	private PaymentTermsType paymentTermsType;
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
