package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;

public class Address extends BaseEntity implements IAddress {

	private String postcode;

	private ILocality locality;

	private String exactAddress;

	private String note;

	@Override
	public String getPostcode() {
		return postcode;
	}

	@Override
	public void setPostcode(final String postcode) {
		this.postcode = postcode;
	}

	@Override
	public ILocality getLocality() {
		return locality;
	}

	@Override
	public void setLocality(final ILocality locality) {
		this.locality = locality;
	}

	@Override
	public String getExactAddress() {
		return exactAddress;
	}

	@Override
	public void setExactAddress(final String exactAddress) {
		this.exactAddress = exactAddress;
	}

	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(final String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Address [postcode=" + postcode + "дocality=" + locality + "exactAddress=" + exactAddress
				+ "note=" + note + ", getId()=" + getId() + "]";
	}

}
