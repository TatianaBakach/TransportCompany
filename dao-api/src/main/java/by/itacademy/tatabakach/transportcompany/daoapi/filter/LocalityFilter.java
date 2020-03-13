package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class LocalityFilter extends AbstractFilter{
	
	private boolean fetchDistrict;
	private boolean fetchCountry;

    public boolean getFetchDistrict() {
        return fetchDistrict;
    }

    public void setFetchDistrict(final boolean fetchDistrict) {
        this.fetchDistrict = fetchDistrict;
    }
    
    public boolean getFetchCountry() {
        return fetchCountry;
    }

    public void setFetchCountry(final boolean fetchCountry) {
        this.fetchCountry = fetchCountry;
    }


}
