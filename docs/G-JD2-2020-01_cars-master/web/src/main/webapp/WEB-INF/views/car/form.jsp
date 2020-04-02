<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit car</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCar}/add"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="vin" type="text" disabled="${readonly}" />
				<form:errors path="vin" cssClass="red-text" />
				<label for="vin">vin</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="modelId" disabled="${readonly}">
					<form:options items="${modelsChoices}" />
				</form:select>
				<form:errors path="modelId" cssClass="red-text" />
				<label for="modelId">model</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<div class="switch">
					<label> free <form:checkbox path="sold" disabled="${readonly}" /> <span
						class="lever"></span> sold
					</label>
				</div>
				<label class="switch-label">is sold</label> <br />
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<form:input path="soldDate" type="text" disabled="${readonly}" cssClass="datepicker" />
				<form:errors path="soldDate" cssClass="red-text" />
				<label for="soldDate">sold date</label>
			</div>
			<div class="input-field col s6">
			<form:input path="soldTime" type="text" disabled="${readonly}" cssClass="timepicker" />
				<form:errors path="soldTime" cssClass="red-text" />
				<label for="soldTime">sold time</label>
			</div>


		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesCar}">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
