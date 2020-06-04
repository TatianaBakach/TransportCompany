<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.order.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="number"><spring:message code="table.column.order" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="date"><spring:message code="table.column.date" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="ourCompany"><spring:message code="order.column.our_company" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="customer"><spring:message code="order.column.customer" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="carrier"><spring:message code="order.column.carrier" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="car"><spring:message code="order.column.car" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="driver"><spring:message code="order.column.driver" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="loadingMethod"><spring:message code="order.column.loading_method" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="cargoType"><spring:message code="order.column.cargo_type" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="cargoWeightVolume"><spring:message code="order.column.cargo_weight_volume" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="customerCost"><spring:message code="order.column.customer_cost" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="paidCustomer"><spring:message code="order.column.paid_customer" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="carrierCost"><spring:message code="order.column.carrier_cost" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="paidCarrier"><spring:message code="order.column.paid_carrier" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="tax"><spring:message code="tax.column.rate" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="additionalConditions"><spring:message code="order.column.additional_conditions" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="creator"><spring:message code="order.column.creator" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="note"><spring:message code="table.column.note" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="order" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${order.id}" /></td>
				<td><c:out value="${order.number}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${order.date}" /></td>
				<td><c:out value="${order.ourCompanyName}" /></td>
				<td><c:out value="${order.customerName}" /></td>
				<td><c:out value="${order.carrierName}" /></td>
				<td><c:out value="${order.carName}" /></td>
				<td><c:out value="${order.driverName}" /></td>
				<td><c:out value="${order.loadingMethod}" /></td>
				<td><c:out value="${order.cargoType}" /></td>
				<td><c:out value="${order.cargoWeightVolume}" /></td>
				<td><c:out value="${order.customerCostName}" /></td>
				<td><c:out value="${order.paidCustomer}" /></td>
				<td><c:out value="${order.carrierCostName}" /></td>
				<td><c:out value="${order.paidCarrier}" /></td>
				<td><c:out value="${order.taxName}" /></td>
				<td><c:out value="${order.additionalConditions}" /></td>
				<td><c:out value="${order.creatorName}" /></td>
				<td><c:out value="${order.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesOrder}/${order.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesOrder}/${order.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesOrder}/${order.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesOrder}/add"><i
	class="material-icons">add</i></a>