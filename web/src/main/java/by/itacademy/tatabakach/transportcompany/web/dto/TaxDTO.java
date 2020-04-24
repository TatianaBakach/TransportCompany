package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class TaxDTO {
	
	private Integer id;
	
	@Size(min = 1, max = 250)
	private String name;
	
	@Max(value = 100)
	private BigDecimal rate;

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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


}