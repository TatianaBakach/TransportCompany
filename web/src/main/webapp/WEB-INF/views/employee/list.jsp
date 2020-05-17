<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Employees</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="firstName">first_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="middleName">middle_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="lastName">last_name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="department">department</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="position">position</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="mail">e-mail</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="phone">phone</mytaglib:sort-link></th>
			<%-- <th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="login">login</mytaglib:sort-link></th> --%>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="password">password</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEmployee}" column="salary">salary</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="employee" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${employee.id}" /></td>
				<td><c:out value="${employee.firstName}" /></td>
				<td><c:out value="${employee.middleName}" /></td>
				<td><c:out value="${employee.lastName}" /></td>
				<td><c:out value="${employee.department}" /></td>
				<td><c:out value="${employee.position}" /></td>
				<td><c:out value="${employee.mail}" /></td>
				<td><c:out value="${employee.phone}" /></td>
				<%-- <td><c:out value="${employee.login}" /></td> --%>
				<td><c:out value="${employee.password}" /></td>
				<td><c:out value="${employee.salary}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesEmployee}/${employee.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesEmployee}/${employee.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesEmployee}/${employee.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesEmployee}/add"><i
	class="material-icons">add</i></a>