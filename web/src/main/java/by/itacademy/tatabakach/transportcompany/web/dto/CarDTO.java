package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.Size;

public class CarDTO {
	private Integer id;
	
	private String model;
	
	@Size(min = 1, max = 250)
	private String number;

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*@Override
	public String toString() {
		return "Car [model=" + model + ", number=" + number + ", getId()=" + getId() + "]";
	}*/

}