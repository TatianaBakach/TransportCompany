<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit order</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesOrder}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s4">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">Order number</label>
            </div>
            
            <div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Order date</label>
			</div>
			
            <div class="input-field col s4">
            <form:select path="creatorId" disabled="${readonly}">
                    <form:options items="${employeesChoices}" />
                </form:select>
                <form:errors path="creatorId" cssClass="red-text" />
                <label for="creatorId">Order creator</label>
            </div>
			
        </div>
        
		 <div class="row">
            <div class="input-field col s4">
                <form:select path="ourCompanyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="ourCompanyId" cssClass="red-text" />
                <label for="ourCompanyId">Order our company</label>
            </div>
            
            <div class="input-field col s4">
                <form:select path="customerId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="customerId" cssClass="red-text" />
                <label for="customerId">Order customer</label>
            </div>
            
            <div class="input-field col s4">
                <form:select path="carrierId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="carrierId" cssClass="red-text" />
                <label for="carrierId">Order carrier</label>
            </div>
        </div>
		
        <div class="row">
            <div class="input-field col s6">
                <form:select path="carId" disabled="${readonly}">
                    <form:options items="${carsChoices}" />
                </form:select>
                <form:errors path="carId" cssClass="red-text" />
                <label for="carId">Order car</label>
            </div>
            
             <div class="input-field col s6">
                <form:select path="driverId" disabled="${readonly}">
                    <form:options items="${driversChoices}" />
                </form:select>
                <form:errors path="driverId" cssClass="red-text" />
                <label for="driverId">Order driver</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s4">
                <form:select path="loadingMethod" disabled="${readonly}">
					<form:options items="${loadingMethodsChoices}" />
				</form:select>
				<form:errors path="loadingMethod" cssClass="red-text" />
				<label for="loadingMethod">Order loading method</label>
            </div>
            
             <div class="input-field col s4">
                <form:input path="cargoType" type="text" disabled="${readonly}" />
                <form:errors path="cargoType" cssClass="red-text" />
                <label for="cargoType">Order cargo type</label>
            </div>
            
            <div class="input-field col s4">
                <form:input path="cargoWeightVolume" type="text" disabled="${readonly}" />
                <form:errors path="cargoWeightVolume" cssClass="red-text" />
                <label for="cargoWeightVolume">Order cargo weight volume</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s3">
                <form:select path="customerCostId" disabled="${readonly}">
                    <form:options items="${transactionCostsChoices}" />
                </form:select>
                <form:errors path="customerCostId" cssClass="red-text" />
                <label for="customerCostId">Order customer cost</label>
            </div>
            
            <div class="input-field col s3">
				<div class="switch">
					<label> paid <form:checkbox path="paidCustomer" disabled="${readonly}" /> <span
						class="lever"></span> paid
					</label>
				</div>
				<label class="switch-label">not</label> <br />
			</div>
			
			<div class="input-field col s3">
                <form:select path="carrierCostId" disabled="${readonly}">
                    <form:options items="${transactionCostsChoices}" />
                </form:select>
                <form:errors path="carrierCostId" cssClass="red-text" />
                <label for="carrierCostId">Order carrier cost</label>
            </div>
            
            <div class="input-field col s3">
				<div class="switch">
					<label> paid <form:checkbox path="paidCarrier" disabled="${readonly}" /> <span
						class="lever"></span> paid
					</label>
				</div>
				<label class="switch-label">not</label> <br />
			</div>
        </div>
        
        <div class="row">
            <div class="input-field col s4">
                <form:select path="taxId" disabled="${readonly}">
                    <form:options items="${taxesChoices}" />
                </form:select>
                <form:errors path="taxId" cssClass="red-text" />
                <label for="taxId">Order tax</label>
            </div>
            
            <div class="input-field col s4">
                <form:textarea path="additionalConditions" disabled="${readonly}" cssClass="materialize-textarea" />
                <form:errors path="additionalConditions" cssClass="red-text" />
                <label for="additionalConditions">Order additional conditions</label>
            </div>
            
            <div class="input-field col s4">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Order note</label>
			</div>
        </div>
        
		<div class="row">
            <div class="input-field  col s12">
                <form:select path="employeeIds" disabled="${readonly}" multiple="true">
                    <option value="" disabled ${empty formModel.employeeIds? "selected":""}>выберите сотрудников</option>
                    <form:options items="${employeesChoices}" />
                </form:select>
                <form:errors path="employeeIds" cssClass="red-text" />
                <label for="employeeIds" class="multiselect-label">сотрудники</label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit">Save</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesOrder}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>