<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit employee</h4>
<div class="row">

	<form:form class="col s12" method="POST"
		action="${pagesEmployee}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s4">
				<form:input path="firstName" type="text" disabled="${readonly}" />
				<form:errors path="firstName" cssClass="red-text" />
				<label for="firstName">Employee first name</label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="middleName" type="text" disabled="${readonly}" />
				<form:errors path="middleName" cssClass="red-text" />
				<label for="middleName">Employee middle name</label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="lastName" type="text" disabled="${readonly}" />
				<form:errors path="lastName" cssClass="red-text" />
				<label for="lastName">Employee last name</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s6">
				<form:select path="department" disabled="${readonly}">
					<form:options items="${departmentChoices}" />
				</form:select>
				<form:errors path="department" cssClass="red-text" />
				<label for="department">Employee department</label>
			</div>
			
			<div class="input-field col s6">
				<form:select path="position" disabled="${readonly}">
					<form:options items="${positionChoices}" />
				</form:select>
				<form:errors path="position" cssClass="red-text" />
				<label for="position">Employee position</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s6">
				<form:input path="mail" type="text" disabled="${readonly}" />
				<form:errors path="mail" cssClass="red-text" />
				<label for="mail">Employee e-mail</label>
			</div>
			
			<div class="input-field col s6">
				<form:input path="phone" type="text" disabled="${readonly}" />
				<form:errors path="phone" cssClass="red-text" />
				<label for="phone">Employee phone</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s4">
				<form:select path="role" disabled="${readonly}">
					<form:options items="${roleChoices}" />
				</form:select>
				<form:errors path="role" cssClass="red-text" />
				<label for="role">Employee role</label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="password" type="text" disabled="${readonly}" />
				<form:errors path="password" cssClass="red-text" />
				<label for="password">Employee password</label>
			</div>
			
			<div class="input-field col s4">
				<form:input path="salary" type="text" disabled="${readonly}" />
				<form:errors path="salary" cssClass="red-text" />
				<label for="salary">Employee salary</label>
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
				<a class="btn waves-effect waves-light right red darken-2" href="${pagesEmployee}">Cancel</a>
			</div>
		</div>
	</form:form>
</div>