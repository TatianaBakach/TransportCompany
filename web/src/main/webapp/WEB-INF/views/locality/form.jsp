<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit locality</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesLocality}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name">Locality name</label>
            </div>
        </div>
        
         <div class="row">
            <div class="input-field col s6">
                <form:select path="regionId" disabled="${readonly}">
                    <form:options items="${regionsChoices}" />
                </form:select>
                <form:errors path="regionId" cssClass="red-text" />
                <label for="regionId">Locality region</label>
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
                <a class="btn waves-effect waves-light right red darken-2" href="${pagesLocality}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
