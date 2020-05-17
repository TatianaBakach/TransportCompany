<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit contract</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesContract}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">Contract number</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s6">
                <form:select path="ourCompanyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="ourCompanyId" cssClass="red-text" />
                <label for="ourCompanyId">Contract our company</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:select path="companyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="companyId" cssClass="red-text" />
                <label for="companyId">Contract company</label>
            </div>
        </div>
        
       <div class="row">
			<div class="input-field col s6">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Contract date</label>
			</div>
		</div>
        
        <div class="row">
            <div class="col s12"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit">Save</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesContract}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>