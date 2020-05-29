<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row">
	<div class="col s3">
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

	<div class="col s3">
		<iframe
			style="width: 240px; border: 0; overflow: hidden; background-color: transparent; height: 241px"
			scrolling="no"
			src="https://fortrader.org/informers/getInformer?st=8&cat=7&title=%D0%9A%D1%83%D1%80%D1%81%D1%8B%20%D0%B2%D0%B0%D0%BB%D1%8E%D1%82%20%D0%A6%D0%91%20%D0%A0%D0%A4&texts=%7B%22toolTitle%22%3A%22%D0%92%D0%B0%D0%BB%D1%8E%D1%82%D0%B0%22%2C%22todayCourse%22%3A%22%22%2C%22tomorrowCourse%22%3A%22%D0%97%D0%B0%D0%B2%D1%82%D1%80%D0%B0%22%7D&mult=1&showGetBtn=0&hideHeader=0&hideDate=1&w=240&codes=1&colors=false&items=2%2C21%2C14&columns=todayCourse%2CtomorrowCourse&toCur=11111"></iframe>
	</div>

	<div class="col s3">
		<div id="convertor">
			<p>
				<strong>Конвертер валют ЦБ РФ</strong><br /> <span id="dateru"></span>
			</p>
			<span id="bodyru"></span>
		</div>
		<script type="text/javascript"
			src="https://kurs.blr.cc/js/calculator_ru.js"></script>
	</div>

</div>

<div class="row">
	<table class="highlight">
		<thead>
			<tr>
				<th>Name</th>
				<th>Item Name</th>
				<th>Item Price</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>Alvin</td>
				<td>Eclair</td>
				<td>$0.87</td>
			</tr>
			<tr>
				<td>Alan</td>
				<td>Jellybean</td>
				<td>$3.76</td>
			</tr>
			<tr>
				<td>Jonathan</td>
				<td>Lollipop</td>
				<td>$7.00</td>
			</tr>
		</tbody>
	</table>

</div>
