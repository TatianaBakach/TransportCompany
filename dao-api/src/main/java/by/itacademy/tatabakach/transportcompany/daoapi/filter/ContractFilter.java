package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class ContractFilter extends AbstractFilter{
	
	private boolean fetchCompany;
	private boolean fetchOurCompany;

    public boolean getFetchCompany() {
        return fetchCompany;
    }

    public void setFetchCompany(final boolean fetchCompany) {
        this.fetchCompany = fetchCompany;
    }

	public boolean getFetchOurCompany() {
		return fetchOurCompany;
	}

	public void setFetchOurCompany(boolean fetchOurCompany) {
		this.fetchOurCompany = fetchOurCompany;
	}

}
