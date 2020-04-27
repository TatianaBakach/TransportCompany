<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4 class="header">Edit Order Reward Percent</h4>
<div class="row">

    <form:form class="col s12" method="POST" action="${pagesOrderRewardPercent}"
        modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        
         <div class="row">
            <div class="input-field col s6">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name">Order Reward Percent name</label>
            </div>
        </div>
        
        <div class="row">
            <div class="input-field col s6">
                <form:input path="percent" type="text" disabled="${readonly}" />
                <form:errors path="percent" cssClass="red-text" />
                <label for="percent">Order Reward Percent percent</label>
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
                <a class="btn waves-effect waves-light right" href="${pagesOrderRewardPercent}">Cancel</a>
            </div>
        </div>
    </form:form>
</div>