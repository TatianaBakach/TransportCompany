<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.region.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesRegion}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRegion}" column="name"><spring:message code="table.column.name" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRegion}" column="country"><spring:message code="region.column.country" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="region" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${region.id}" /></td>
				<td><c:out value="${region.name}" /></td>
				<td><c:out value="${region.countryName}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesRegion}/${region.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesRegion}/${region.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesRegion}/${region.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesRegion}/add"><i
	class="material-icons">add</i></a>

