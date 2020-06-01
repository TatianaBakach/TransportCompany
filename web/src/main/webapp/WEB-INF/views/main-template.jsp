<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="request" />
<c:set var="pagesCar" value="${contextPath}/car" scope="request" />
<c:set var="pagesDriver" value="${contextPath}/driver" scope="request" />
<c:set var="pagesCountry" value="${contextPath}/country" scope="request" />
<c:set var="pagesRegion" value="${contextPath}/region" scope="request" />
<c:set var="pagesLocality" value="${contextPath}/locality" scope="request" />
<c:set var="pagesAddress" value="${contextPath}/address" scope="request" />
<c:set var="pagesCompany" value="${contextPath}/company" scope="request" />
<c:set var="pagesTransactionCost" value="${contextPath}/transactionCost" scope="request" />
<c:set var="pagesEmployee" value="${contextPath}/employee" scope="request" />
<c:set var="pagesContract" value="${contextPath}/contract" scope="request" />
<c:set var="pagesOrderRewardPercent" value="${contextPath}/orderRewardPercent" scope="request" />
<c:set var="pagesTax" value="${contextPath}/tax" scope="request" />
<c:set var="pagesCfr" value="${contextPath}/cfr" scope="request" />
<c:set var="pagesOrder" value="${contextPath}/order" scope="request" />
<c:set var="pagesCorrespondence" value="${contextPath}/correspondence" scope="request" />
<c:set var="pagesOrderReward" value="${contextPath}/orderReward" scope="request" />
<c:set var="pagesPayment" value="${contextPath}/payment" scope="request" />
<c:set var="pagesRouteItem" value="${contextPath}/routeItem" scope="request" />
<c:set var="pagesHome" value="${contextPath}/" scope="request" />


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">

<script src="${contextPath}/resources/js/init-forms.js"></script>

</head>
<body>
	<tiles:insertAttribute name="header" />
	<main>
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
	</main>
	<tiles:insertAttribute name="footer" />
</body>
</html>