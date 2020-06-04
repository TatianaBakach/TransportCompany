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

    <form:form class="col s12" method="POST" action="${pagesOrderRewardPercent}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
         <div class="row">
            <div class="input-field col s6">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name"><spring:message code="table.column.name" /></label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="percent" type="text" disabled="${readonly}" />
                <form:errors path="percent" cssClass="red-text" />
                <label for="percent"><spring:message code="page.reward_percent.title" /></label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesOrderRewardPercent}"><spring:message code="page.button.cancel" /></a>
            </div>
        </div>
    </form:form>
</div>