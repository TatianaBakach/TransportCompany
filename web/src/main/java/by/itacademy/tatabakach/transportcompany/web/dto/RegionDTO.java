package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;

public class RegionDTO {

	private Integer id;

	@Size(min = 1, max = 250)
	private String name;

	@NotNull
	private ICountry country;

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

	public ICountry getCountry() {
		return country;
	}

	public void setCountry(ICountry country) {
		this.country = country;
	}



}
