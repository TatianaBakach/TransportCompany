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

    <form:form class="col s12" method="POST" action="${pagesDriver}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s4">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName"><spring:message code="table.column.first_name" /></label>
            </div>
        
            <div class="input-field col s4">
                <form:input path="middleName" type="text" disabled="${readonly}" />
                <form:errors path="middleName" cssClass="red-text" />
                <label for="middleName"><spring:message code="table.column.middle_name" /></label>
            </div>

            <div class="input-field col s4">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName"><spring:message code="table.column.last_name" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="passportData" type="text" disabled="${readonly}" />
                <form:errors path="passportData" cssClass="red-text" />
                <label for="passportData"><spring:message code="driver.column.passport_data" /></label>
            </div>

            <div class="input-field col s6">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone"><spring:message code="table.column.phone" /></label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesDriver}"><spring:message code="page.button.cancel" /></a>
            </div>
        </div>
    </form:form>
</div>