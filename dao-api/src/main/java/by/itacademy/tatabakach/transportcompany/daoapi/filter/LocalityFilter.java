package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class LocalityFilter extends AbstractFilter{
	
	private boolean fetchRegion;
	
	private Integer regionId; 

    public boolean getFetchRegion() {
        return fetchRegion;
    }

    public void setFetchRegion(final boolean fetchRegion) {
        this.fetchRegion = fetchRegion;
    }

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
    
}
