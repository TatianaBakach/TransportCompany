<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit route item</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesRouteItem}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s6">
                <form:select path="orderId" disabled="${readonly}">
                    <form:options items="${ordersChoices}" />
                </form:select>
                <form:errors path="orderId" cssClass="red-text" />
                <label for="orderId">Route item order</label>
            </div>
            
            <div class="input-field col s6">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Route item date</label>
			</div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:select path="addressId" disabled="${readonly}">
                    <form:options items="${addressesChoices}" />
                </form:select>
                <form:errors path="addressId" cssClass="red-text" />
                <label for="addressId">Route item address</label>
            </div>
            
             <div class="input-field col s6">
                <form:select path="customId" disabled="${readonly}">
                     <form:option value="">&nbsp;</form:option>
                    <form:options items="${addressesChoices}" />
                </form:select>
                <form:errors path="customId" cssClass="red-text" />
                <label for="customId">Route item custom</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s4">
                <form:input path="cargoWeight" type="text" disabled="${readonly}" />
                <form:errors path="cargoWeight" cssClass="red-text" />
                <label for="cargoWeight">Route item cargo weight</label>
            </div>
            
             <div class="input-field col s4">
                <form:input path="cargoVolume" type="text" disabled="${readonly}" />
                <form:errors path="cargoVolume" cssClass="red-text" />
                <label for="cargoVolume">Route item cargo volume</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s4">
                <form:input path="contactPerson" type="text" disabled="${readonly}" />
                <form:errors path="contactPerson" cssClass="red-text" />
                <label for="contactPerson">Route item contact person</label>
            </div>
            
            <div class="input-field col s4">
                <form:input path="contactPhone" type="text" disabled="${readonly}" />
                <form:errors path="contactPhone" cssClass="red-text" />
                <label for="contactPhone">Route item contact phone</label>
            </div>
        </div>
        
        <div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Route item note</label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesRouteItem}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>