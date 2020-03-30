<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Localities</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesLocality}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesLocality}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesLocality}" column="region">region</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="locality" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${locality.id}" /></td>
				<td><c:out value="${locality.name}" /></td>
				<td><c:out value="${locality.regionName}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesLocality}/${locality.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesLocality}/${locality.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesLocality}/${locality.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesLocality}/add"><i
	class="material-icons">add</i></a>