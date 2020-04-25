<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit cfr</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesCfr}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
         <div class="row">
            <div class="input-field col s6">
                <form:select path="companyId" disabled="${readonly}">
                    <form:options items="${companiesChoices}" />
                </form:select>
                <form:errors path="companyId" cssClass="red-text" />
                <label for="companyId">Cfr company</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="year" type="text" disabled="${readonly}" />
                <form:errors path="year" cssClass="red-text" />
                <label for="year">Cfr year</label>
            </div>
        </div>
        
        <div class="row">
            <div class="col s12"></div>
            <div class="col s3">
                <c:if test="${!readonly}">
                    <button class="btn waves-effect waves-light right" type="submit">Save</button>
                </c:if>
            </div>
            <div class="col s3">
                <a class="btn waves-effect waves-light right" href="${pagesCfr}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>

