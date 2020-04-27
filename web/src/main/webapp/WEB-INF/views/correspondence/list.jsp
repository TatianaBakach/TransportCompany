<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Correspondences</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="correspondenceType">correspondence_type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="order">order</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="company">company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="content">content</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCorrespondence}" column="note">note</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="correspondence" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${correspondence.id}" /></td>
				<td><c:out value="${correspondence.correspondenceType}" /></td>
				<td><c:out value="${correspondence.order}" /></td>
				<td><c:out value="${correspondence.company}" /></td>
				<td><c:out value="${correspondence.date}" /></td>
				<td><c:out value="${correspondence.content}" /></td>
				<td><c:out value="${correspondence.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCorrespondence}/${correspondence.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCorrespondence}/${correspondence.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCorrespondence}/${correspondence.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCorrespondence}/add"><i
	class="material-icons">add</i></a>