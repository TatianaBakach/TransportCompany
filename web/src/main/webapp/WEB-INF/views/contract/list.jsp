<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Contracts</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="number">number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="ourCompany">our_company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="company">company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="date">date</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="contract" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${contract.id}" /></td>
				<td><c:out value="${contract.number}" /></td>
				<td><c:out value="${contract.ourCompany}" /></td>
				<td><c:out value="${contract.company}" /></td>
				<td><c:out value="${contract.date}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesContract}/${contract.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesContract}/${contract.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesContract}/${contract.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesContract}/add"><i
	class="material-icons">add</i></a>