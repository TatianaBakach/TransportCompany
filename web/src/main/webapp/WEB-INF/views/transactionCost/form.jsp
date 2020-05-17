<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit transaction cost</h4>
<div class="row">

	<form:form class="col s12" method="POST"
		action="${pagesTransactionCost}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Transaction cost date</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="currency" disabled="${readonly}">
					<form:options items="${currencyChoices}" />
				</form:select>
				<form:errors path="currency" cssClass="red-text" />
				<label for="currency">currency</label>
			</div>

		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="amount" type="text" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount">Transaction cost amount</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="rate" type="text" disabled="${readonly}" />
				<form:errors path="rate" cssClass="red-text" />
				<label for="rate">Transaction cost rate</label>
			</div>
		</div>
		
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="intermediateCurrency" disabled="${readonly}">
					<form:options items="${currencyChoices}" />
				</form:select>
				<form:errors path="intermediateCurrency" cssClass="red-text" />
				<label for="intermediateCurrency">intermediate currency</label>
			</div>

		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="intermediateCurrencyRate" type="text"
					disabled="${readonly}" />
				<form:errors path="intermediateCurrencyRate" cssClass="red-text" />
				<label for="intermediateCurrencyRate">Transaction cost
					intermediate currency rate</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="paymentPeriod" type="number"
					disabled="${readonly}" />
				<form:errors path="paymentPeriod" cssClass="red-text" />
				<label for="paymentPeriod">Transaction cost payment period</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="paymentTermsType" disabled="${readonly}">
					<form:options items="${paymentTermsTypesChoices}" />
				</form:select>
				<form:errors path="paymentTermsType" cssClass="red-text" />
				<label for="paymentTermsType">intermediate currency</label>
			</div>

		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Transaction cost note</label>
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
				<a class="btn waves-effect waves-light right"
					href="${pagesTransactionCost}">Cancel</a>
			</div>
		</div>
	</form:form>
</div>