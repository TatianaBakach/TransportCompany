package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;

public interface IPayment extends IBaseEntity {

	Date getDate();

	void setDate(Date date);

	IOrder getOrder();

	void setOrder(IOrder order);

	ICompany getCompany();

	void setCompany(ICompany company);
	
	Currency getCurrency();

	void setCurrency(Currency currency);
	
	BigDecimal getRate();

	void setRate(BigDecimal rate);
	
	BigDecimal getAmount();

	void setAmount(BigDecimal amount);


}
