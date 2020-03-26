package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class CompanyFilter extends AbstractFilter {
	
	private boolean fetchLegalAddress;
	private boolean fetchPostAddress;
	private boolean fetchCreator;
	
	public boolean getFetchLegalAddress() {
		return fetchLegalAddress;
	}
	public void setFetchLegalAddress(boolean fetchLegalAddress) {
		this.fetchLegalAddress = fetchLegalAddress;
	}
	public boolean getFetchPostAddress() {
		return fetchPostAddress;
	}
	public void setFetchPostAddress(boolean fetchPostAddress) {
		this.fetchPostAddress = fetchPostAddress;
	}
	public boolean getFetchCreator() {
		return fetchCreator;
	}
	public void setFetchCreator(boolean fetchCreator) {
		this.fetchCreator = fetchCreator;
	}

}
