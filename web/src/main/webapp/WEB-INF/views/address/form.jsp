<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${!readonly}">
<h4 class="header"><spring:message code="page.editing" /></h4>
</c:if>
<c:if test="${readonly}">
<h4 class="header"><spring:message code="page.view" /></h4>
</c:if>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesAddress}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s6">
				<form:input path="postcode" type="text" disabled="${readonly}" />
				<form:errors path="postcode" cssClass="red-text" />
				<label for="postcode"><spring:message code="address.column.postcode" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6">
				<form:select path="country" cssClass="browser-default" disabled="${readonly}"/>
			</div>
		</div>
		<div class="row">
			<div class="col s6">
				<form:select path="region" cssClass="browser-default" disabled="${readonly}"/>
			</div>
		</div>
		<div class="row">
			<div class="col s6">
				<form:select path="city" cssClass="browser-default" disabled="${readonly}" >
				<form:options items="${citiesChoices}" />
				</form:select>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:textarea path="exactAddress" disabled="${readonly}"
					cssClass="materialize-textarea" />
				<form:errors path="exactAddress" cssClass="red-text" />
				<label for="exactAddress"><spring:message code="address.column.exact_address" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s6">
				<form:textarea path="note" disabled="${readonly}"
					cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note"><spring:message code="table.column.note" /></label>
			</div>
		</div>

		<div class="row">
			<div class="col s12"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right green darken-3"
						type="submit"><spring:message code="page.button.save" /></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right red darken-2"
					href="${pagesAddress}"><spring:message code="page.button.cancel" /></a>
			</div>
		</div>
	</form:form>
</div>

<script
	src="${pageContext.request.contextPath}/resources/js/init-combos.js"></script>
<script>
	initComboboxes('${pageContext.request.contextPath}');
</script>