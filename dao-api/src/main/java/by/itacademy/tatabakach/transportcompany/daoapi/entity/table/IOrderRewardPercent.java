package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;

public interface IOrderRewardPercent extends IBaseEntity{
	
	String getName();

	void setName(String name);
	
	BigDecimal getPercent();

	void setPercent(BigDecimal percent);

}
