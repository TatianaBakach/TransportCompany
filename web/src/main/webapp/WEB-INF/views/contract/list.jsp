<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Contracts</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesContract}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="companyType">company_type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="payerRegistrationNumber">payer_registration_number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="legalAddress">legal_address</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="postAddress">	post_address</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="bankData">bank_data</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="mail">e-mail</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="phone">phone</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCompany}" column="creator">creator</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="company" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${company.id}" /></td>
				<td><c:out value="${company.companyType}" /></td>
				<td><c:out value="${company.name}" /></td>
				<td><c:out value="${company.payerRegistrationNumber}" /></td>
				<td><c:out value="${company.legalAddressName}" /></td>
				<td><c:out value="${company.postAddressName}" /></td>
				<td><c:out value="${company.bankData}" /></td>
				<td><c:out value="${company.mail}" /></td>
				<td><c:out value="${company.phone}" /></td>
				<td><c:out value="${company.creatorName}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCompany}/${company.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCompany}/${company.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCompany}/${company.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCompany}/add"><i
	class="material-icons">add</i></a>