<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Transaction Costs</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="currency">currency</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="amount">amount</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="rate">rate</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="intermediateCurrency">intermediate_currency</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="intermediateCurrencyRate">intermediate_currency_rate</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="paymentPeriod">payment_period</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="paymentTermsType">payment_terms_type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="note">note</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="transactionCost" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${transactionCost.id}" /></td>
				<td><c:out value="${transactionCost.date}" /></td>
				<td><c:out value="${transactionCost.currency}" /></td>
				<td><c:out value="${transactionCost.amount}" /></td>
				<td><c:out value="${transactionCost.rate}" /></td>
				<td><c:out value="${transactionCost.intermediateCurrency}" /></td>
				<td><c:out value="${transactionCost.intermediateCurrencyRate}" /></td>
				<td><c:out value="${transactionCost.paymentPeriod}" /></td>
				<td><c:out value="${transactionCost.paymentTermsType}" /></td>
				<td><c:out value="${transactionCost.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesTransactionCost}/${transactionCost.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesTransactionCost}/${transactionCost.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesTransactionCost}/${transactionCost.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesTransactionCost}/add"><i
	class="material-icons">add</i></a>