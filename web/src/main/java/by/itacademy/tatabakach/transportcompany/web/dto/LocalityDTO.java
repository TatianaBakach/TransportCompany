package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.Size;

public class LocalityDTO {

	private Integer id;

	@Size(min = 1, max = 250)
	private String name;
	
	private Integer regionId;
	private String regionName;


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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

}
