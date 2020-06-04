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

	<form:form class="col s12" method="POST" action="${pagesOrderReward}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s6">
				<form:select path="orderId" disabled="${readonly}">
					<form:options items="${ordersChoices}" />
				</form:select>
				<form:errors path="orderId" cssClass="red-text" />
				<label for="orderId"><spring:message code="table.column.order" /></label>
			</div>
			
			<div class="input-field col s6">
				<form:select path="employeeId" disabled="${readonly}">
					<form:options items="${employeesChoices}" />
				</form:select>
				<form:errors path="employeeId" cssClass="red-text" />
				<label for="employeeId"><spring:message code="table.column.employee" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:select path="rewardType" disabled="${readonly}">
					<form:options items="${rewardTypesChoices}" />
				</form:select>
				<form:errors path="rewardType" cssClass="red-text" />
				<label for="rewardType"><spring:message code="order_reward.column.reward_type" /></label>
			</div>
			
			<div class="input-field col s6">
				<form:select path="orderRewardPercentId" disabled="${readonly}">
					<form:options items="${orderRewardPercentsChoices}" />
				</form:select>
				<form:errors path="orderRewardPercentId" cssClass="red-text" />
				<label for="orderRewardPercentId"><spring:message code="page.reward_percent.title" /></label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s6">
				<form:input path="additionalExpenses" type="number"
					disabled="${readonly}" />
				<form:errors path="additionalExpenses" cssClass="red-text" />
				<label for="additionalExpenses"><spring:message code="order_reward.column.additional_expenses" /></label>
			</div>
			
			<div class="input-field col s6">
				<form:input path="amount" type="number" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount"><spring:message code="order_reward.column.amount" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:input path="paymentDate" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="paymentDate" cssClass="red-text" />
				<label for="paymentDate"><spring:message code="order_reward.column.payment_date" /></label>
			</div>
			
			<div class="input-field col s6">
				<div class="switch">
					<label> issued <form:checkbox path="rewardIssued"
							disabled="${readonly}" /> <span class="lever"></span> <spring:message code="order_reward.column.issued" />
					</label>
				</div>
				<label class="switch-label"><spring:message code="order_reward.column.not_issued" /></label> <br />
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}"
					cssClass="materialize-textarea" />
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
				<a class="btn waves-effect waves-light right red darken-2"
					href="${pagesOrderReward}"><spring:message code="page.button.cancel" /></a>
			</div>
		</div>
	</form:form>
</div>