package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class PaymentFilter extends AbstractFilter {
	
	private boolean fetchOrder;
	private boolean fetchCompany;
	
	public boolean getFetchOrder() {
		return fetchOrder;
	}
	public void setFetchOrder(boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}
	public boolean getFetchCompany() {
		return fetchCompany;
	}
	public void setFetchCompany(boolean fetchCompany) {
		this.fetchCompany = fetchCompany;
	}

}
