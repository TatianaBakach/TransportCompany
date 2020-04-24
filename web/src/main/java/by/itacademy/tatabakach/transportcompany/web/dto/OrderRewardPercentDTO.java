package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class OrderRewardPercentDTO {
	
	private Integer id;
	
	@NotNull
	@Max(value = 100)
	private BigDecimal percent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	

}