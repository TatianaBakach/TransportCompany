<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header">Countries</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}" column="name">name</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="country" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${country.id}" /></td>
				<td><c:out value="${country.name}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCountry}/${country.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCountry}/${country.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCountry}/${country.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCountry}/add"><i
	class="material-icons">add</i></a>