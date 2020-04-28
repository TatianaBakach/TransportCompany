<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit order reward</h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesOrderReward}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="orderId" disabled="${readonly}">
					<form:options items="${ordersChoices}" />
				</form:select>
				<form:errors path="orderId" cssClass="red-text" />
				<label for="orderId">Order reward order</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="employeeId" disabled="${readonly}">
					<form:options items="${employeesChoices}" />
				</form:select>
				<form:errors path="employeeId" cssClass="red-text" />
				<label for="employeeId">Order reward employee</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="rewardType" disabled="${readonly}">
					<form:options items="${rewardTypesChoices}" />
				</form:select>
				<form:errors path="rewardType" cssClass="red-text" />
				<label for="rewardType">Order reward reward type</label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s12">
				<form:select path="orderRewardPercentId" disabled="${readonly}">
					<form:options items="${orderRewardPercentsChoices}" />
				</form:select>
				<form:errors path="orderRewardPercentId" cssClass="red-text" />
				<label for="orderRewardPercentId">Order reward order reward
					percent</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="additionalExpenses" type="number"
					disabled="${readonly}" />
				<form:errors path="additionalExpenses" cssClass="red-text" />
				<label for="additionalExpenses">Order reward additional
					expenses</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="amount" type="number" disabled="${readonly}" />
				<form:errors path="amount" cssClass="red-text" />
				<label for="amount">Order reward amount</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s4">
				<form:input path="paymentDate" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="paymentDate" cssClass="red-text" />
				<label for="paymentDate">Order reward payment date</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> issued <form:checkbox path="rewardIssued"
							disabled="${readonly}" /> <span class="lever"></span> issued
					</label>
				</div>
				<label class="switch-label">not</label> <br />
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}"
					cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Order reward note</label>
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
					href="${pagesOrderReward}">Cancel</a>
			</div>
		</div>
	</form:form>
</div>