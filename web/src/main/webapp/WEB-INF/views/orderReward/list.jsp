<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.order_reward.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="order"><spring:message code="table.column.order" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="employee"><spring:message code="table.column.employee" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="rewardType"><spring:message code="order_reward.column.reward_type" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="orderRewardPercent"><spring:message code="page.reward_percent.title" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="additionalExpenses"><spring:message code="order_reward.column.additional_expenses" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="amount"><spring:message code="order_reward.column.amount" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="paymentDate"><spring:message code="order_reward.column.payment_date" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="rewardIssued"><spring:message code="order_reward.column.reward_issued" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="note"><spring:message code="table.column.note" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="orderReward" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${orderReward.id}" /></td>
				<td><c:out value="${orderReward.orderName}" /></td>
				<td><c:out value="${orderReward.employeeName}" /></td>
				<td><c:out value="${orderReward.rewardType}" /></td>
				<td><c:out value="${orderReward.orderRewardPercentName}" /></td>
				<td><c:out value="${orderReward.additionalExpenses}" /></td>
				<td><c:out value="${orderReward.amount}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${orderReward.paymentDate}" /></td>
				<td><c:out value="${orderReward.rewardIssued}" /></td>
				<td><c:out value="${orderReward.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesOrderReward}/${orderReward.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesOrderReward}/${orderReward.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesOrderReward}/${orderReward.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesOrderReward}/add"><i
	class="material-icons">add</i></a>