package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDTO {

	private Integer id;

	@Size(min = 0, max = 10)
	private String postcode;
	
	@NotNull
	private Integer localityId;
	private String localityName;
	
	private String exactAddress;
	
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Integer getLocalityId() {
		return localityId;
	}

	public void setLocalityId(Integer localityId) {
		this.localityId = localityId;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public String getExactAddress() {
		return exactAddress;
	}

	public void setExactAddress(String exactAddress) {
		this.exactAddress = exactAddress;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



}
