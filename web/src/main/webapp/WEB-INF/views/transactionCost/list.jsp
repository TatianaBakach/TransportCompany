<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Cars</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTransactionCost}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="model">model</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="number">number</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="car" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${car.id}" /></td>
				<td><c:out value="${car.model}" /></td>
				<td><c:out value="${car.number}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesCar}/${car.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesCar}/${car.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesCar}/${car.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCar}/add"><i
	class="material-icons">add</i></a>