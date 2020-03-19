package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class LocalityFilter extends AbstractFilter{
	
	private boolean fetchRegion;
	private boolean fetchCountry;

    public boolean getFetchRegion() {
        return fetchRegion;
    }

    public void setFetchRegion(final boolean fetchRegion) {
        this.fetchRegion = fetchRegion;
    }
    
    public boolean getFetchCountry() {
        return fetchCountry;
    }

    public void setFetchCountry(final boolean fetchCountry) {
        this.fetchCountry = fetchCountry;
    }


}
