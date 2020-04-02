package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegionDTO {

	private Integer id;

	@Size(min = 1, max = 250)
	private String name;
	
	@NotNull
	private Integer countryId;
	private String countryName;


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

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
