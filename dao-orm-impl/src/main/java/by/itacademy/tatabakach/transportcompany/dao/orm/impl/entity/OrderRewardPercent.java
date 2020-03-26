package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;

@Entity
public class OrderRewardPercent extends BaseEntity implements IOrderRewardPercent {

	@Column
	private BigDecimal percent;

	@Override
	public BigDecimal getPercent() {
		return percent;
	}

	@Override
	public void setPercent(final BigDecimal percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "OrderRewardPercent [percent=" + percent + ", getId()=" + getId() + "]";
	}

}
