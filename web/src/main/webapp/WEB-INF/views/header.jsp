<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<header>
	<nav>
		<div class="nav-wrapper container">
			<ul id="dropdown1" class="dropdown-content">
				<li><a href="${pagesCountry}">countries</a></li>
				<li><a href="${pagesRegion}">regions</a></li>
				<li><a href="${pagesLocality}">localities</a></li>
				<li><a href="${pagesAddress}">addresses</a></li>
			</ul>

			<ul id="dropdown2" class="dropdown-content">
				<li><a href="${pagesOrder}">orders</a></li>
				<li><a href="${pagesOrderReward}">order rewards</a></li>
				<li><a href="${pagesOrderRewardPercent}">order reward
						percents</a></li>
				<li><a href="${pagesRouteItem}">route items</a></li>
				<li><a href="${pagesTransactionCost}">transaction costs</a></li>
				<li><a href="${pagesPayment}">payments</a></li>
				<li><a href="${pagesCar}">cars</a></li>
				<li><a href="${pagesDriver}">drivers</a></li>
				<li><a href="${pagesTax}">taxes</a></li>
			</ul>

			<ul id="dropdown3" class="dropdown-content">
				<li><a href="${pagesCompany}">companies</a></li>
				<li><a href="${pagesEmployee}">employees</a></li>
				<li><a href="${pagesContract}">contracts</a></li>
				<li><a href="${pagesCfr}">cfr</a></li>
			</ul>

			<ul class="left hide-on-med-and-down">
				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown1">Addresses<i
						class="material-icons right">arrow_drop_down</i></a></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown2">Orders<i class="material-icons right">arrow_drop_down</i></a></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown3">Companies<i
						class="material-icons right">arrow_drop_down</i></a></li>

				<li><a href="${contextPath}"><i class="material-icons">home</i></a></li>


				<li><a href="${pagesCorrespondence}">Correspondence</a></li>

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