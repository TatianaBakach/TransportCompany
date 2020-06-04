<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<header>
	<nav>
		<div class="nav-wrapper container">
			<ul id="dropdown1" class="dropdown-content">
				<li><a href="${pagesCountry}"><spring:message code="page.country.title" /></a></li>
				<li><a href="${pagesRegion}"><spring:message code="page.region.title" /></a></li>
				<li><a href="${pagesLocality}"><spring:message code="page.locality.title" /></a></li>
				<li><a href="${pagesAddress}"><spring:message code="page.address.title" /></a></li>
			</ul>

			<ul id="dropdown2" class="dropdown-content">
				<li><a href="${pagesOrder}"><spring:message code="page.order.title" /></a></li>
				<li><a href="${pagesOrderReward}"><spring:message code="page.order_reward.title" /></a></li>
				<li><a href="${pagesOrderRewardPercent}"><spring:message code="page.reward_percent.title" /></a></li>
				<li><a href="${pagesRouteItem}"><spring:message code="page.route_item.title" /></a></li>
				<li><a href="${pagesTransactionCost}"><spring:message code="page.transaction_cost.title" /></a></li>
				<li><a href="${pagesPayment}"><spring:message code="page.payment.title" /></a></li>
				<li><a href="${pagesCar}"><spring:message code="page.car.title" /></a></li>
				<li><a href="${pagesDriver}"><spring:message code="page.driver.title" /></a></li>
				<li><a href="${pagesTax}"><spring:message code="page.taxes.title" /></a></li>
			</ul>

			<ul id="dropdown3" class="dropdown-content">
				<li><a href="${pagesCompany}">companies</a></li>
				<li><a href="${pagesEmployee}">employees</a></li>
				<li><a href="${pagesContract}">contracts</a></li>
				<li><a href="${pagesCfr}">cfr</a></li>
			</ul>

			<ul class="left hide-on-med-and-down">
				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown1"><spring:message code="page.address.title" /><i
						class="material-icons right">arrow_drop_down</i></a></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown2"><spring:message code="page.order.title" /><i class="material-icons right">arrow_drop_down</i></a></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown3"><spring:message code="page.company.title" /><i
						class="material-icons right">arrow_drop_down</i></a></li>

				<li><a href="${contextPath}"><i class="material-icons">home</i></a></li>


				<li><a href="${pagesCorrespondence}"></a><spring:message code="page.correspondence.title" /></li>

				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>

				<li><a class="highlighted-menu-ru" href="?lang=ru">RU</a></li>
				<li><a class="highlighted-menu-en" href="?lang=en">EN</a></li>

			</ul>
		</div>
	</nav>
</header>