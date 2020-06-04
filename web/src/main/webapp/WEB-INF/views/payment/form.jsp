<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${!readonly}">
<h4 class="header"><spring:message code="page.editing" /></h4>
</c:if>
<c:if test="${readonly}">
<h4 class="header"><spring:message code="page.view" /></h4>
</c:if>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesPayment}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
			<div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date"><spring:message code="table.column.date" /></label>
			</div>
			
			<div class="input-field col s4">
                <form:select path="orderId" disabled="${readonly}">
                    <form:options items="${ordersChoices}" />
                </form:select>
                <form:errors path="orderId" cssClass="red-text" />
                <label for="orderId"><spring:message code="table.column.order" /></label>
            </div>
            
             <div class="input-field col s4">
                <form:select path="companyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="companyId" cssClass="red-text" />
                <label for="companyId"><spring:message code="payment.column.company" /></label>
            </div>
		</div>

      <div class="row">
			<div class="input-field col s4">
				<form:select path="currency" disabled="${readonly}">
					<form:options items="${currencyChoices}" />
				</form:select>
				<form:errors path="currency" cssClass="red-text" />
				<label for="currency"><spring:message code="payment.column.currency" /></label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="rate" type="text" disabled="${readonly}" />
				<form:errors path="rate" cssClass="red-text" />
				<label for="rate"><spring:message code="transaction_cost.column.rate" /></label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="amount" type="text" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount"><spring:message code="payment.column.amount" /></label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note"><spring:message code="table.column.note" /></label>
			</div>
		</div>
        
        <div class="row">
            <div class="col s6"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit"><spring:message code="page.button.save" /></button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesPayment}"><spring:message code="page.button.cancel" /></a>
            </div>
        </div>
    </form:form>
</div>