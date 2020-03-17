<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Drivers</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>first_name</th>
			<th>middle_name</th>
			<th>last_name</th>
			<th>passport_data</th>
			<th>phone</th>
			<th></th>
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
<a class="waves-effect waves-light btn right" href="${pagesDriver}/add"><i
	class="material-icons">add</i></a>