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
				<td><c:out value="${order.date}" /></td>
				<td><c:out value="${order.ourCompany}" /></td>
				<td><c:out value="${order.customer}" /></td>
				<td><c:out value="${order.carrier}" /></td>
				<td><c:out value="${order.car}" /></td>
				<td><c:out value="${order.driver}" /></td>
				<td><c:out value="${order.loadingMethod}" /></td>
				<td><c:out value="${order.cargoType}" /></td>
				<td><c:out value="${order.cargoWeightVolume}" /></td>
				<td><c:out value="${order.customerCost}" /></td>
				<td><c:out value="${order.paidCustomer}" /></td>
				<td><c:out value="${order.carrierCost}" /></td>
				<td><c:out value="${order.paidCarrier}" /></td>
				<td><c:out value="${order.tax}" /></td>
				<td><c:out value="${order.additionalConditions}" /></td>
				<td><c:out value="${order.creator}" /></td>
				<td><c:out value="${order.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesOrder}/${company.id}"><i class="material-icons">info</i></a>
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