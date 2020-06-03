package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class CorrespondenceFilter extends AbstractFilter {
	
	private boolean fetchCompany;
	
	private boolean fetchOrder;
	
    public boolean getFetchCompany() {
        return fetchCompany;
    }

    public void setFetchCompany(final boolean fetchCompany) {
        this.fetchCompany = fetchCompany;
    }
    
    public boolean getFetchOrder() {
        return fetchOrder;
    }

    public void setFetchOrder(final boolean fetchOrder) {
        this.fetchOrder = fetchOrder;
    }

}
