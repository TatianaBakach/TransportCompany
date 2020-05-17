<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit driver</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesDriver}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s4">
                <form:input path="firstName" type="text" disabled="${readonly}" />
                <form:errors path="firstName" cssClass="red-text" />
                <label for="firstName">Driver first_name</label>
            </div>
        
            <div class="input-field col s4">
                <form:input path="middleName" type="text" disabled="${readonly}" />
                <form:errors path="middleName" cssClass="red-text" />
                <label for="middleName">Driver middle_name</label>
            </div>

            <div class="input-field col s4">
                <form:input path="lastName" type="text" disabled="${readonly}" />
                <form:errors path="lastName" cssClass="red-text" />
                <label for="lastName">Driver last_name</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="passportData" type="text" disabled="${readonly}" />
                <form:errors path="passportData" cssClass="red-text" />
                <label for="passportData">Driver passport_data</label>
            </div>

            <div class="input-field col s6">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone">Driver phone</label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesDriver}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>