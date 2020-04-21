<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit contract</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesContract}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">Contract number</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:select path="ourCompany" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="ourCompany" cssClass="red-text" />
                <label for="ourCompany">Contract our company</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:select path="company" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="company" cssClass="red-text" />
                <label for="company">Contract company</label>
            </div>
        </div>
        
       <div class="row">
			<div class="input-field col s4">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date">Contract date</label>
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
                <a class="btn waves-effect waves-light right" href="${pagesCompany}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>