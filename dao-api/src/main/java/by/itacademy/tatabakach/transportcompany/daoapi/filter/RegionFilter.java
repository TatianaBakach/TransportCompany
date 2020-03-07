package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class RegionFilter extends AbstractFilter {
	
	private boolean fetchCountry;

    public boolean getFetchCountry() {
        return fetchCountry;
    }

    public void setFetchBrand(final boolean fetchCountry) {
        this.fetchCountry = fetchCountry;
    }

}
