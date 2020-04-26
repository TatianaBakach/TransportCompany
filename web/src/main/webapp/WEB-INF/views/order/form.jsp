<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit order</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesOrder}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">Order number</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="companyType" disabled="${readonly}">
					<form:options items="${companyTypesChoices}" />
				</form:select>
				<form:errors path="companyType" cssClass="red-text" />
				<label for="companyType">Company company type</label>
            </div>
        </div>
        
        
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="payerRegistrationNumber" type="text" disabled="${readonly}" />
                <form:errors path="payerRegistrationNumber" cssClass="red-text" />
                <label for="payerRegistrationNumber">Company payer registration number</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:select path="legalAddressId" disabled="${readonly}">
                    <form:options items="${addressesChoices}" />
                </form:select>
                <form:errors path="legalAddressId" cssClass="red-text" />
                <label for="legalAddressId">Company legal address</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="postAddressId" disabled="${readonly}">
                    <form:options items="${addressesChoices}" />
                </form:select>
                <form:errors path="postAddressId" cssClass="red-text" />
                <label for="postAddressId">Company post address</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:textarea path="bankData" disabled="${readonly}" cssClass="materialize-textarea" />
                <form:errors path="bankData" cssClass="red-text" />
                <label for="bankData">Company bank data</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="mail" type="text" disabled="${readonly}" />
                <form:errors path="mail" cssClass="red-text" />
                <label for="mail">Company e-mail</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone">Company phone</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
            <form:select path="creatorId" disabled="${readonly}">
                    <form:options items="${employeesChoices}" />
                </form:select>
                <form:errors path="creatorId" cssClass="red-text" />
                <label for="creatorId">Company creator</label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">Save</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${pagesCompany}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>