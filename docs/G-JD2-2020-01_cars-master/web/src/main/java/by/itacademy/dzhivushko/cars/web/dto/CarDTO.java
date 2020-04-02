package by.itacademy.dzhivushko.cars.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class CarDTO {

	private Integer id;

	@Size(min = 1, max = 20)
	private String vin;

	@NotNull
	private Integer modelId;

	private String modelName;

	@NotNull
	private Boolean sold;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date soldDate;

	@DateTimeFormat(pattern = "hh:mm a")
	private Date soldTime;

	private Date created;

	private Date updated;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(final String vin) {
		this.vin = vin;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(final Integer modelId) {
		this.modelId = modelId;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(final Boolean sold) {
		this.sold = sold;
	}

	public Date getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(final Date soldDate) {
		this.soldDate = soldDate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public Date getSoldTime() {
		return soldTime;
	}

	public void setSoldTime(final Date soldTime) {
		this.soldTime = soldTime;
	}

}
