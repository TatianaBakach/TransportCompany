<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit payment</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesPayment}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
			<div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Payment date</label>
			</div>
		</div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:select path="orderId" disabled="${readonly}">
                    <form:options items="${ordersChoices}" />
                </form:select>
                <form:errors path="orderId" cssClass="red-text" />
                <label for="orderId">Payment order</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="companyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="companyId" cssClass="red-text" />
                <label for="companyId">Payment company</label>
            </div>
        </div>
        
      <div class="row">
			<div class="input-field col s12">
				<form:select path="currency" disabled="${readonly}">
					<form:options items="${currencyChoices}" />
				</form:select>
				<form:errors path="currency" cssClass="red-text" />
				<label for="currency">Payment currency</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input path="rate" type="text" disabled="${readonly}" />
				<form:errors path="rate" cssClass="red-text" />
				<label for="rate">Payment rate</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="amount" type="text" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount">Payment amount</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Payment note</label>
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
                <a class="btn waves-effect waves-light right" href="${pagesPayment}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>