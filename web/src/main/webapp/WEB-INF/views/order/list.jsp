<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Orders</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="number">number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="ourCompany">our_company</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="customer">customer</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="carrier">carrier</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="car">car</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="driver">driver</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="loadingMethod">loading_method</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="cargoType">cargo_type</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="cargoWeightVolume">cargo_weight_volume</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="customerCost">customer_cost</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="paidCustomer">paid_customer</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="carrierCost">carrier_cost</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="paidCarrier">paid_carrier</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="tax">tax</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="additionalConditions">additional_conditions</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="creator">creator</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesOrder}" column="note">note</mytaglib:sort-link></th>
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