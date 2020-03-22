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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;

@Entity
public class OrderReward extends BaseEntity implements IOrderReward {
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
	private IEmployee employee;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private RewardType rewardType;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderRewardPercent.class)
	private IOrderRewardPercent orderRewardPercent;
	
	@Column
	private BigDecimal additionalExpenses;
	
	@Column
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date paymentDate;
	
	@Column
	private Boolean rewardIssued;
	
	@Column
	private String note;

	@Override
	public IOrder getOrder() {
		return order;
	}

	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
	}

	@Override
	public IEmployee getEmployee() {
		return employee;
	}

	@Override
	public void setEmployee(final IEmployee employee) {
		this.employee = employee;
	}

	@Override
	public RewardType getRewardType() {
		return rewardType;
	}

	@Override
	public void setRewardType(final RewardType rewardType) {
		this.rewardType = rewardType;
	}

	@Override
	public IOrderRewardPercent getOrderRewardPercent() {
		return orderRewardPercent;
	}

	@Override
	public void setOrderRewardPercent(final IOrderRewardPercent orderRewardPercent) {
		this.orderRewardPercent = orderRewardPercent;
	}

	@Override
	public BigDecimal getAdditionalExpenses() {
		return additionalExpenses;
	}

	@Override
	public void setAdditionalExpenses(final BigDecimal additionalExpenses) {
		this.additionalExpenses = additionalExpenses;
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
	public Date getPaymentDate() {
		return paymentDate;
	}

	@Override
	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public Boolean getRewardIssued() {
		return rewardIssued;
	}

	@Override
	public void setRewardIssued(final Boolean rewardIssued) {
		this.rewardIssued = rewardIssued;
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
