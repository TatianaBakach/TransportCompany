package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;

public interface IOrderReward extends IBaseEntity {

	IOrder getOrder();

	void setOrder(IOrder order);

	IEmployee getEmployee();

	void setEmployee(IEmployee employee);

	RewardType getRewardType();

	void setRewardType(RewardType rewardType);

	IOrderRewardPercent getOrderRewardPercent();

	void setOrderRewardPercent(IOrderRewardPercent orderRewardPercent);

	BigDecimal getAdditionalExpenses();

	void setAdditionalExpenses(BigDecimal additionalExpenses);
	
	BigDecimal getAmount();

	void setAmount(BigDecimal amount);
	
	Date getPaymentDate();

	void setPaymentDate(Date paymentDate);
	
	Boolean getRewardIssued();

	void setRewardIssued(Boolean rewardIssued);
	
	String getNote();

	void setNote(String note);

}
