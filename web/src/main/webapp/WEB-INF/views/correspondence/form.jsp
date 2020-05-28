<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit correspondence</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesCorrespondence}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s4">
                <form:select path="correspondenceType" disabled="${readonly}">
					<form:options items="${correspondenceTypesChoices}" />
				</form:select>
				<form:errors path="correspondenceType" cssClass="red-text" />
				<label for="correspondenceType">Correspondence correspondence type</label>
            </div>
            
            <div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Correspondence date</label>
			</div>
            
        </div>
        
        <div class="row">
            <div class="input-field col s4">
                <form:select path="orderId" disabled="${readonly}">
                    <form:options items="${ordersChoices}" />
                </form:select>
                <form:errors path="orderId" cssClass="red-text" />
                <label for="orderId">Correspondence order</label>
            </div>
            
            <div class="input-field col s4">
                <form:select path="companyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="companyId" cssClass="red-text" />
                <label for="companyId">Correspondence company</label>
            </div>
        </div>
        
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="content" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="content" cssClass="red-text" />
				<label for="content">Correspondence content</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="note" disabled="${readonly}" cssClass="materialize-textarea" />
				<form:errors path="note" cssClass="red-text" />
				<label for="note">Correspondence note</label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesCorrespondence}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>