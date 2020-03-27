<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit address</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesAddress}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="postcode" type="text" disabled="${readonly}" />
                <form:errors path="postcode" cssClass="red-text" />
                <label for="postcode">Address postcode</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:input path="localityId" type="text" disabled="${readonly}" />
                <form:errors path="localityId" cssClass="red-text" />
                <label for="localityId">Address locality</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s12">
                <form:input path="exactAddress" type="text" disabled="${readonly}" />
                <form:errors path="exactAddress" cssClass="red-text" />
                <label for="exactAddress">Address exact address</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s12">
                <form:input path="note" type="text" disabled="${readonly}" />
                <form:errors path="note" cssClass="red-text" />
                <label for="note">Address note</label>
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
                <a class="btn waves-effect waves-light right" href="${pagesAddress}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>