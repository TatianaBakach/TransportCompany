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

    <form:form class="col s12" method="POST" action="${pagesCar}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number"><spring:message code="car.column.number" /></label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s6">
                <form:input path="model" type="text" disabled="${readonly}" />
                <form:errors path="model" cssClass="red-text" />
                <label for="model"><spring:message code="car.column.model" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s12"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right green darken-3" type="submit"><spring:message code="page.button.save" /></button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesCar}"><spring:message code="page.button.cancel" /></a>
            </div>
        </div>
    </form:form>
</div>