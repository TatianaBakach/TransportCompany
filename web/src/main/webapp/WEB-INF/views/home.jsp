<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>


<div class="row" style="text-align: center;">
	<div class="col s4">
		<iframe frameborder="0" height="131" marginheight="0" marginwidth="0"
			scrolling="no" src="https://admin.myfin.by/outer/informer/grodno"
			width="240"></iframe>
	</div>
	
	<div class="col s3">
		<div id="convertor">
			<p>
				<strong>Конвертер валют НБРБ</strong><br /> <span id="dateby"></span>
			</p>
			<span id="bodyby"></span>
		</div>
		<script type="text/javascript"
			src="https://kurs.blr.cc/js/calculator.js"></script>
	</div>

	<div class="col s4">
		<iframe
			style="width: 240px; border: 0; overflow: hidden; background-color: transparent; height: 241px"
			scrolling="no"
			src="https://fortrader.org/informers/getInformer?st=8&cat=7&title=%D0%9A%D1%83%D1%80%D1%81%D1%8B%20%D0%B2%D0%B0%D0%BB%D1%8E%D1%82%20%D0%A6%D0%91%20%D0%A0%D0%A4&texts=%7B%22toolTitle%22%3A%22%D0%92%D0%B0%D0%BB%D1%8E%D1%82%D0%B0%22%2C%22todayCourse%22%3A%22%22%2C%22tomorrowCourse%22%3A%22%D0%97%D0%B0%D0%B2%D1%82%D1%80%D0%B0%22%7D&mult=1&showGetBtn=0&hideHeader=0&hideDate=1&w=240&codes=1&colors=false&items=2%2C21%2C14&columns=todayCourse%2CtomorrowCourse&toCur=11111"></iframe>
	</div>

	<!-- <div class="col s3">
		<div id="convertor">
			<p>
				<strong>Конвертер валют ЦБ РФ</strong><br /> <span id="dateru"></span>
			</p>
			<span id="bodyru"></span>
		</div>
		<script type="text/javascript"
			src="https://kurs.blr.cc/js/calculator_ru.js"></script>
	</div> -->

</div>

<h4 class="header">Main Table</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesMainTable}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesMainTable}" column="number">number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesMainTable}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesMainTable}" column="customer">customer</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesMainTable}" column="carrier">carrier</mytaglib:sort-link></th>
            <th></th>
		</tr>
		<c:forEach var="mainTable" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${mainTable.id}" /></td>
				<td><c:out value="${mainTable.number}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${mainTable.date}" /></td>
				<td><c:out value="${mainTable.customerName}" /></td>
				<td><c:out value="${mainTable.carrierName}" /></td>
				<td class="right">
				<a class="btn-floating"	href="${pagesMainTable}/${mainTable.id}"><i class="material-icons">info</i></a>
				<a class="btn-floating" href="${pagesMainTable}/${mainTable.id}/edit"><i class="material-icons">edit</i></a> 
				<a class="btn-floating red"	href="${pagesMainTable}/${mainTable.id}/delete"><i class="material-icons">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesMainTable}/add"><i
	class="material-icons">add</i></a>
