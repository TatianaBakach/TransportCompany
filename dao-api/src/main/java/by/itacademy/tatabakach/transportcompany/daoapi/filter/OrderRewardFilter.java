package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class OrderRewardFilter extends AbstractFilter{
	
	private boolean fetchOrder;
	private boolean fetchEmployee;
	private boolean fetchOrderRewardRercent;
	
	public boolean getFetchOrder() {
		return fetchOrder;
	}
	public void setFetchOrder(boolean fetchOrder) {
		this.fetchOrder = fetchOrder;
	}
	public boolean getFetchEmployee() {
		return fetchEmployee;
	}
	public void setFetchEmployee(boolean fetchEmployee) {
		this.fetchEmployee = fetchEmployee;
	}
	public boolean getFetchOrderRewardRercent() {
		return fetchOrderRewardRercent;
	}
	public void setFetchOrderRewardRercent(boolean fetchOrderRewardRercent) {
		this.fetchOrderRewardRercent = fetchOrderRewardRercent;
	}


}
