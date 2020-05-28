<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Route items</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="order">order</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="address">address</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="cargoWeight">cargo weight</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="cargoVolume">cargo volume</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="custom">custom</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="contactPerson">contact person</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="contactPhone">contact phone</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesRouteItem}" column="note">note</mytaglib:sort-link></th>
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