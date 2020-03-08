package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class LocalityFilter extends AbstractFilter{
	
	private boolean fetchDistrict;

    public boolean getFetchDistrict() {
        return fetchDistrict;
    }

    public void setFetchDistrict(final boolean fetchDistrict) {
        this.fetchDistrict = fetchDistrict;
    }

}
