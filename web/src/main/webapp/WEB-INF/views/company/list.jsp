<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Companies</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>company_type_id</th>
			<th>name</th>
			<th>payer_registration_number</th>
			<th>legal_address_id</th>
			<th>post_address_id</th>
			<th>bank_data</th>
			<th>e_mail</th>
			<th>phone</th>
			<th></th>
		</tr>
		<c:forEach var="company" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${company.id}" /></td>
				<td><c:out value="${company.company_type_id}" /></td>
				<td><c:out value="${company.name}" /></td>
				<td><c:out value="${company.payer_registration_number}" /></td>
				<td><c:out value="${company.legal_address_id}" /></td>
				<td><c:out value="${company.post_address_id}" /></td>
				<td><c:out value="${company.bank_data}" /></td>
				<td><c:out value="${company.e_mail}" /></td>
				<td><c:out value="${company.phone}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCompany}/${company.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCompany}/${company.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCompany}/${company.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesCompany}/add"><i
	class="material-icons">add</i></a>