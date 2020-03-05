package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;

public interface ITransactionCost extends IBaseEntity {

	Date getDate();

	void setDate(Date date);

	Currency getCurrency();

	void setCurrency(Currency currency);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	BigDecimal getRate();

	void setRate(BigDecimal rate);

	Currency getIntermediateCurrency();

	void setIntermediateCurrency(Currency intermediateCurrency);

	BigDecimal getIntermediateCurrencyRate();

	void setIntermediateCurrencyRate(BigDecimal intermediateCurrencyRate);

	Integer getPaymentPeriod();

	void setPaymentPeriod(Integer paymentPeriod);

	PaymentTermsType getPaymentTermsType();

	void setPaymentTermsType(PaymentTermsType paymentTermsType);

	String getNote();

	void setNote(String note);

}
