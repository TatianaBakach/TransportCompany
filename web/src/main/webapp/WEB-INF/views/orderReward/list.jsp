<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Order Rewards</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="order">order</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="employee">employee</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="rewardType">reward type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="orderRewardPercent">order reward percent</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="additionalExpenses">additional expenses</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="amount">amount</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="paymentDate">payment date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="rewardIssued">reward issued</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrderReward}" column="note">note</mytaglib:sort-link></th>
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