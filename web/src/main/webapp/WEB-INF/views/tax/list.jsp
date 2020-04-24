<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Taxes</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTax}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTax}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTax}" column="rate">rate</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="tax" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${tax.id}" /></td>
				<td><c:out value="${tax.name}" /></td>
				<td><c:out value="${tax.rate}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesTax}/${tax.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesTax}/${tax.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesTax}/${tax.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesTax}/add"><i
	class="material-icons">add</i></a>