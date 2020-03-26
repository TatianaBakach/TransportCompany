package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class RouteItemFilter extends AbstractFilter {
	
	private boolean fetchOrder;
	private boolean fetchAddress;
	private boolean fetchCustom;
	
	public boolean getFetchOrder() {
		return fetchOrder;
	}
	public void setFetchOrder(boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}
	public boolean getFetchAddress() {
		return fetchAddress;
	}
	public void setFetchAddress(boolean fetchAddress) {
		this.fetchAddress = fetchAddress;
	}
	public boolean getFetchCustom() {
		return fetchCustom;
	}
	public void setFetchCustom(boolean fetchCustom) {
		this.fetchCustom = fetchCustom;
	}

}
