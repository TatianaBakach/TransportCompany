<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.reward_percent.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrderRewardPercent}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderRewardPercent}" column="name"><spring:message code="table.column.name" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderRewardPercent}" column="percent"><spring:message code="page.reward_percent.title" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="orderRewardPercent" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${orderRewardPercent.id}" /></td>
				<td><c:out value="${orderRewardPercent.name}" /></td>
				<td><c:out value="${orderRewardPercent.percent}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesOrderRewardPercent}/${orderRewardPercent.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesOrderRewardPercent}/${orderRewardPercent.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesOrderRewardPercent}/${orderRewardPercent.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesOrderRewardPercent}/add"><i
	class="material-icons">add</i></a>