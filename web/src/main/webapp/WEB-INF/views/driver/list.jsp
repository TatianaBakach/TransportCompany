<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Drivers</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="firstName">first_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="middleName">middle_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="lastName">last_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="passportData">passport_data</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesDriver}" column="phone">phone</mytaglib:sort-link></th>
			
		</tr>
		<c:forEach var="driver" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${driver.id}" /></td>
				<td><c:out value="${driver.firstName}" /></td>
				<td><c:out value="${driver.middleName}" /></td>
				<td><c:out value="${driver.lastName}" /></td>
				<td><c:out value="${driver.passportData}" /></td>
				<td><c:out value="${driver.phone}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesDriver}/${driver.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesDriver}/${driver.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesDriver}/${driver.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesDriver}/add"><i
	class="material-icons">add</i></a>