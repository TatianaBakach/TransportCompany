package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class DistrictFilter extends AbstractFilter {
	
	private boolean fetchRegion;

    public boolean getFetchRegion() {
        return fetchRegion;
    }

    public void setFetchRegion(final boolean fetchRegion) {
        this.fetchRegion = fetchRegion;
    }

}
