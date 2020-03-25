package by.itacademy.tatabakach.transportcompany.daoapi.filter;

public class OrderFilter extends AbstractFilter {
	
	private boolean fetchOurCompany;
	private boolean fetchCustomer;
	private boolean fetchCarrier;
	private boolean fetchCar;
	private boolean fetchDriver;
	private boolean fetchCustomerCost;
	private boolean fetchCarrierCost;
	private boolean fetchTax;
	private boolean fetchCreator;

	public boolean getFetchOurCompany() {
		return fetchOurCompany;
	}

	public void setFetchOurCompany(boolean fetchOurCompany) {
		this.fetchOurCompany = fetchOurCompany;
	}

	public boolean getFetchCustomer() {
		return fetchCustomer;
	}

	public void setFetchCustomer(boolean fetchCustomer) {
		this.fetchCustomer = fetchCustomer;
	}

	public boolean getFetchCarrier() {
		return fetchCarrier;
	}

	public void setFetchCarrier(boolean fetchCarrier) {
		this.fetchCarrier = fetchCarrier;
	}

	public boolean getFetchCar() {
		return fetchCar;
	}

	public void setFetchCar(boolean fetchCar) {
		this.fetchCar = fetchCar;
	}

	public boolean getFetchDriver() {
		return fetchDriver;
	}

	public void setFetchDriver(boolean fetchDriver) {
		this.fetchDriver = fetchDriver;
	}

	public boolean getFetchCustomerCost() {
		return fetchCustomerCost;
	}

	public void setFetchCustomerCost(boolean fetchCustomerCost) {
		this.fetchCustomerCost = fetchCustomerCost;
	}

	public boolean getFetchCarrierCost() {
		return fetchCarrierCost;
	}

	public void setFetchCarrierCost(boolean fetchCarrierCost) {
		this.fetchCarrierCost = fetchCarrierCost;
	}

	public boolean getFetchTax() {
		return fetchTax;
	}

	public void setFetchTax(boolean fetchTax) {
		this.fetchTax = fetchTax;
	}

	public boolean getFetchCreator() {
		return fetchCreator;
	}

	public void setFetchCreator(boolean fetchCreator) {
		this.fetchCreator = fetchCreator;
	}


}
