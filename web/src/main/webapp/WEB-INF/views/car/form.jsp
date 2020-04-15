<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit car</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesCar}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="number" type="text" disabled="${readonly}" />
                <form:errors path="number" cssClass="red-text" />
                <label for="number">Car number</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s6">
                <form:input path="model" type="text" disabled="${readonly}" />
                <form:errors path="model" cssClass="red-text" />
                <label for="model">Car model</label>
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
                <a class="btn waves-effect waves-light right" href="${pagesCar}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>