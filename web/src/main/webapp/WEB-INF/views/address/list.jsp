<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.address.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesAddress}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAddress}" column="postcode"><spring:message code="address.column.postcode" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAddress}" column="locality"><spring:message code="address.column.locality" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAddress}" column="exactAddress"><spring:message code="address.column.exact_address" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesAddress}" column="note"><spring:message code="table.column.note" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="address" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${address.id}" /></td>
				<td><c:out value="${address.postcode}" /></td>
				<td><c:out value="${address.localityName}" /></td>
				<td><c:out value="${address.exactAddress}" /></td>
				<td><c:out value="${address.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesAddress}/${address.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesAddress}/${address.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesAddress}/${address.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesAddress}/add"><i
	class="material-icons">add</i></a>