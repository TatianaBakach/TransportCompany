package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class AddressFilter extends AbstractFilter {
	
	private boolean fetchLocality;

    public boolean getFetchLocality() {
        return fetchLocality;
    }

    public void setFetchLocality(final boolean fetchLocality) {
        this.fetchLocality = fetchLocality;
    }
    
}
