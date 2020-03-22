package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.Size;

public class CountryDTO {

	private Integer id;

	@Size(min = 1, max = 250)
	private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
