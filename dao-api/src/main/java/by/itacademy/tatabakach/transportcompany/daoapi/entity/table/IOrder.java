package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;

public interface IOrder extends IBaseEntity {

	String getNumber();

	void setNumber(String number);

	ICompany getOurCompany();

	void setOurCompany(ICompany ourCompany);

	ICompany getCustomer();

	void setCustomer(ICompany customer);

	ICompany getCarrier();

	void setCarrier(ICompany carrier);

	ICar getCar();

	void setCar(ICar car);

	IDriver getDriver();

	void setDriver(IDriver driver);

	LoadingMethod getLoadingMethod();

	void setLoadingMethod(LoadingMethod loadingMethod);

	String getCargoType();

	void setCargoType(String cargoType);

	String getCargoWeightVolume();

	void setCargoWeightVolume(String cargoWeightVolume);

	ITransactionCost getCustomerCost();

	void setCustomerCost(ITransactionCost customerCost);

	Boolean getPaidCustomer();

	void setPaidCustomer(Boolean paidCustomer);

	ITransactionCost getCarrierCost();

	void setCarrierCost(ITransactionCost carrierCost);
	
	Boolean getPaidCarrier();

	void setPaidCarrier(Boolean paidCarrier);
	
	ITax getTax();
	
	void setTax(ITax tax);
	
	String 	getAdditionalConditions();
	
	void setAdditionalConditions (String additionalConditions);
	
	IEmployee getCreator();
	
	void setCreator(IEmployee creator);
	
	Set<IEmployee> getEmployees();

    void setEmployees(Set<IEmployee> employees);

}
