package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderRewardPercentDTO {
	
	private Integer id;
	
	@Size(min = 1, max = 250)
	private String name;
	
	@NotNull
	@Max(value = 100)
	private BigDecimal percent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}


}