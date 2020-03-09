package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class ContractFilter extends AbstractFilter{
	
	private boolean fetchCompany;

    public boolean getFetchCompany() {
        return fetchCompany;
    }

    public void setFetchCompany(final boolean fetchCompany) {
        this.fetchCompany = fetchCompany;
    }

}
