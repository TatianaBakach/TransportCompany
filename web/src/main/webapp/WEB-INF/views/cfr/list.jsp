<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Cfrs</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCfr}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCfr}" column="company">company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCfr}" column="year">year</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="cfr" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${cfr.id}" /></td>
				<td><c:out value="${cfr.companyName}" /></td>
				<td><c:out value="${cfr.year}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCfr}/${cfr.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCfr}/${cfr.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCfr}/${cfr.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCfr}/add"><i
	class="material-icons">add</i></a>

