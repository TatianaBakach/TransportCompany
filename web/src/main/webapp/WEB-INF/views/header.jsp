<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				
				<li><a href="${contextPath}">home</a></li>

				<li><a href="${pagesCar}">cars</a></li>
				<li><a href="${pagesDriver}">drivers</a></li>
				<li><a href="${pagesCountry}">countries</a></li>
				<li><a href="${pagesRegion}">regions</a></li>
				<li><a href="${pagesLocality}">localities</a></li>
				<li><a href="${pagesAddress}">addresses</a></li>
				<li><a href="${pagesCompany}">companies</a></li>
				<li><a href="${pagesTransactionCost}">transaction costs</a></li>
				<li><a href="${pagesEmployee}">employees</a></li>
				<li><a href="${pagesContract}">contracts</a></li>
				<li><a href="${pagesOrderRewardPercent}">order reward percents</a></li>
				<li><a href="${pagesTax}">taxes</a></li>
				<li><a href="${pagesCfr}">cfrs</a></li>

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