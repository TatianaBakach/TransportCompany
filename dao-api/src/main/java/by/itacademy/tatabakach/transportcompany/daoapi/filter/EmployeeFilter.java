package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class EmployeeFilter {
	
	private boolean fetchDepartment;
	private boolean fetchPosition;
	
	public boolean getFetchDepartment() {
		return fetchDepartment;
	}
	public void setFetchDepartment(boolean fetchDepartment) {
		this.fetchDepartment = fetchDepartment;
	}
	public boolean getFetchPosition() {
		return fetchPosition;
	}
	public void setFetchPosition(boolean fetchPosition) {
		this.fetchPosition = fetchPosition;
	}


}
