package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;

public class TransactionCostDTO {
	
	private Integer id;
	
	@NotNull
	private Date date;
	
	@NotNull
	private Currency currency;
	
	@NotNull
	private BigDecimal amount;
	
	private BigDecimal rate;
	
	private Currency intermediateCurrency;
	
	private BigDecimal intermediateCurrencyRate;
	
	@NotNull
	private Integer paymentPeriod;
	
	@NotNull
	private PaymentTermsType paymentTermsType;
	
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Currency getIntermediateCurrency() {
		return intermediateCurrency;
	}

	public void setIntermediateCurrency(Currency intermediateCurrency) {
		this.intermediateCurrency = intermediateCurrency;
	}

	public BigDecimal getIntermediateCurrencyRate() {
		return intermediateCurrencyRate;
	}

	public void setIntermediateCurrencyRate(BigDecimal intermediateCurrencyRate) {
		this.intermediateCurrencyRate = intermediateCurrencyRate;
	}

	public Integer getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(Integer paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public PaymentTermsType getPaymentTermsType() {
		return paymentTermsType;
	}

	public void setPaymentTermsType(PaymentTermsType paymentTermsType) {
		this.paymentTermsType = paymentTermsType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}