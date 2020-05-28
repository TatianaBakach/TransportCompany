<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Payments</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="order">order</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="company">company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="currency">currency</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="rate">rate</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="amount">amount</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPayment}" column="note">note</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="payment" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${payment.id}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${payment.date}" /></td>
				<td><c:out value="${payment.orderName}" /></td>
				<td><c:out value="${payment.companyName}" /></td>
				<td><c:out value="${payment.currency}" /></td>
				<td><c:out value="${payment.rate}" /></td>
				<td><c:out value="${payment.amount}" /></td>
				<td><c:out value="${correspondence.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesPayment}/${payment.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesPayment}/${payment.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesPayment}/${payment.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesPayment}/add"><i
	class="material-icons">add</i></a>