package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;

public class Order extends BaseEntity implements IOrder {

	private String number;
	
	private Date date;

	private ICompany ourCompany;

	private ICompany customer;

	private ICompany carrier;

	private ICar car;

	private IDriver driver;

	private LoadingMethod loadingMethod;

	private String cargoType;

	private String cargoWeightVolume;

	private ITransactionCost customerCost;

	private Boolean paidCustomer;

	private ITransactionCost carrierCost;

	private Boolean paidCarrier;

	private ITax tax;

	private String additionalConditions;
	
	private IEmployee creator;
	
	private String note;

	private Set<IEmployee> employees = new HashSet<>();

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(final String number) {
		this.number = number;
	}
	
	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	@Override
	public ICompany getOurCompany() {
		return ourCompany;
	}

	@Override
	public void setOurCompany(final ICompany ourCompany) {
		this.ourCompany = ourCompany;
	}

	@Override
	public ICompany getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(final ICompany customer) {
		this.customer = customer;
	}

	@Override
	public ICompany getCarrier() {
		return carrier;
	}

	@Override
	public void setCarrier(final ICompany carrier) {
		this.carrier = carrier;
	}

	@Override
	public ICar getCar() {
		return car;
	}

	@Override
	public void setCar(final ICar car) {
		this.car = car;
	}

	@Override
	public IDriver getDriver() {
		return driver;
	}

	@Override
	public void setDriver(final IDriver driver) {
		this.driver = driver;
	}

	@Override
	public LoadingMethod getLoadingMethod() {
		return loadingMethod;
	}

	@Override
	public void setLoadingMethod(final LoadingMethod loadingMethod) {
		this.loadingMethod = loadingMethod;
	}

	@Override
	public String getCargoType() {
		return cargoType;
	}

	@Override
	public void setCargoType(final String cargoType) {
		this.cargoType = cargoType;
	}

	@Override
	public String getCargoWeightVolume() {
		return cargoWeightVolume;
	}

	@Override
	public void setCargoWeightVolume(final String cargoWeightVolume) {
		this.cargoWeightVolume = cargoWeightVolume;
	}

	@Override
	public ITransactionCost getCustomerCost() {
		return customerCost;
	}

	@Override
	public void setCustomerCost(final ITransactionCost customerCost) {
		this.customerCost = customerCost;
	}

	@Override
	public Boolean getPaidCustomer() {
		return paidCustomer;
	}

	@Override
	public void setPaidCustomer(final Boolean paidCustomer) {
		this.paidCustomer = paidCustomer;
	}

	@Override
	public ITransactionCost getCarrierCost() {
		return carrierCost;
	}

	@Override
	public void setCarrierCost(final ITransactionCost carrierCost) {
		this.carrierCost = carrierCost;
	}

	@Override
	public Boolean getPaidCarrier() {
		return paidCarrier;
	}

	@Override
	public void setPaidCarrier(final Boolean paidCarrier) {
		this.paidCarrier = paidCarrier;
	}

	@Override
	public ITax getTax() {
		return tax;
	}

	@Override
	public void setTax(final ITax tax) {
		this.tax = tax;
	}

	@Override
	public String getAdditionalConditions() {
		return additionalConditions;
	}

	@Override
	public void setAdditionalConditions(final String additionalConditions) {
		this.additionalConditions = additionalConditions;
	}
	
	@Override
	public IEmployee getCreator() {
		return creator;
	}

	@Override
	public void setCreator(final IEmployee creator) {
		this.creator = creator;
	}
	
	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(final String note) {
		this.note = note;
	}

	@Override
	public Set<IEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<IEmployee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Order [number=" + number + ", ourCompany=" + ourCompany + "customer=" + customer + "carrier=" + carrier
				+ "car=" + car + "driver=" + driver + "loadingMethod=" + loadingMethod + "cargoType=" + cargoType
				+ "cargoWeightVolume=" + cargoWeightVolume + "customerCost=" + customerCost + "paidCustomer="
				+ paidCustomer + "carrierCost=" + carrierCost + "paidCarrier=" + paidCarrier + "tax=" + tax
				+ "additionalConditions=" + additionalConditions + "creator=" + creator + ", getId()=" + getId() + "]";
	}

}
