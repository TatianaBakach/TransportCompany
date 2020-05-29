package by.itacademy.tatabakach.transportcompany.web.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;

public class HomeTableDTO {
	
	private Integer id;
	
	@Size(min = 1, max = 250)
	private String number;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@NotNull
	private Integer ourCompanyId;
	@Size(min = 1, max = 250)
	private String ourCompanyName;

	@NotNull
	private Integer customerId;
	@Size(min = 1, max = 250)
	private String customerName;

	@NotNull
	private Integer carrierId;
	@Size(min = 1, max = 250)
	private String carrierName;

	private Integer carId;
	private String carName;

	private Integer driverId;
	private String driverName;

	private LoadingMethod loadingMethod;

	private String cargoType;

	private String cargoWeightVolume;

	@NotNull
	private Integer customerCostId;
	@Size(min = 1, max = 250)
	private String customerCostName;

	@NotNull
	private Boolean paidCustomer;

	@NotNull
	private Integer carrierCostId;
	@Size(min = 1, max = 250)
	private String carrierCostName;

	@NotNull
	private Boolean paidCarrier;

	private Integer taxId;
	private String taxName;

	private String additionalConditions;

	@NotNull
	private Integer creatorId;
	@Size(min = 1, max = 250)
	private String creatorName;

	private String note;
	
	private Set<Integer> employeeIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getOurCompanyId() {
		return ourCompanyId;
	}

	public void setOurCompanyId(Integer ourCompanyId) {
		this.ourCompanyId = ourCompanyId;
	}

	public String getOurCompanyName() {
		return ourCompanyName;
	}

	public void setOurCompanyName(String ourCompanyName) {
		this.ourCompanyName = ourCompanyName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public LoadingMethod getLoadingMethod() {
		return loadingMethod;
	}

	public void setLoadingMethod(LoadingMethod loadingMethod) {
		this.loadingMethod = loadingMethod;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getCargoWeightVolume() {
		return cargoWeightVolume;
	}

	public void setCargoWeightVolume(String cargoWeightVolume) {
		this.cargoWeightVolume = cargoWeightVolume;
	}

	public Integer getCustomerCostId() {
		return customerCostId;
	}

	public void setCustomerCostId(Integer customerCostId) {
		this.customerCostId = customerCostId;
	}

	public String getCustomerCostName() {
		return customerCostName;
	}

	public void setCustomerCostName(String customerCostName) {
		this.customerCostName = customerCostName;
	}

	public Boolean getPaidCustomer() {
		return paidCustomer;
	}

	public void setPaidCustomer(Boolean paidCustomer) {
		this.paidCustomer = paidCustomer;
	}

	public Integer getCarrierCostId() {
		return carrierCostId;
	}

	public void setCarrierCostId(Integer carrierCostId) {
		this.carrierCostId = carrierCostId;
	}

	public String getCarrierCostName() {
		return carrierCostName;
	}

	public void setCarrierCostName(String carrierCostName) {
		this.carrierCostName = carrierCostName;
	}

	public Boolean getPaidCarrier() {
		return paidCarrier;
	}

	public void setPaidCarrier(Boolean paidCarrier) {
		this.paidCarrier = paidCarrier;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getAdditionalConditions() {
		return additionalConditions;
	}

	public void setAdditionalConditions(String additionalConditions) {
		this.additionalConditions = additionalConditions;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Integer> getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(Set<Integer> employeeIds) {
		this.employeeIds = employeeIds;
	}
	

}