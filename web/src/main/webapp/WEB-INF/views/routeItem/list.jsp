<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header"><spring:message code="page.route_item.title" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="id"><spring:message code="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="order"><spring:message code="table.column.order" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="address"><spring:message code="table.column.address" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="date"><spring:message code="table.column.date" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="cargoWeight"><spring:message code="route-item.column.cargo_weight" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="cargoVolume"><spring:message code="route-item.column.cargo_volume" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="custom"><spring:message code="route-item.column.custom" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="contactPerson"><spring:message code="route-item.column.contact_person" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="contactPhone"><spring:message code="table.column.phone" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="note"><spring:message code="table.column.note" /></mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="routeItem" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${routeItem.id}" /></td>
				<td><c:out value="${routeItem.orderName}" /></td>
				<td><c:out value="${routeItem.addressName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${routeItem.date}" /></td>
				<td><c:out value="${routeItem.cargoWeight}" /></td>
				<td><c:out value="${routeItem.cargoVolume}" /></td>
				<td><c:out value="${routeItem.customName}" /></td>
				<td><c:out value="${routeItem.contactPerson}" /></td>
				<td><c:out value="${routeItem.contactPhone}" /></td>
				<td><c:out value="${routeItem.note}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesRouteItem}/${routeItem.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesRouteItem}/${routeItem.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesRouteItem}/${routeItem.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesRouteItem}/add"><i
	class="material-icons">add</i></a>