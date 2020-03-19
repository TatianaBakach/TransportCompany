package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;

public interface ITax extends IBaseEntity{
	
	String getName();

	void setName(String name);

	BigDecimal getRate();

	void setRate(BigDecimal rate);

}
