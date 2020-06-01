package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class RegionFilter extends AbstractFilter {
	
	private boolean fetchCountry;
	
	private Integer countryId;

    public boolean getFetchCountry() {
        return fetchCountry;
    }

    public void setFetchCountry(final boolean fetchCountry) {
        this.fetchCountry = fetchCountry;
    }

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
