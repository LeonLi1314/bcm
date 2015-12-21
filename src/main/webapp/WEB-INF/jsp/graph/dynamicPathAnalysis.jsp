<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%>
<style>
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

#plus, #minus {
	width: 20px;
	height: 20px;
	background: #eee;
	cursor: pointer;
	position: absolute;
	left: 0;
	text-align: center;
	line-height: 20px;
	box-shadow: 1px 1px 5px #ccc;
}
#plus {
	top: 10px;
}
#minus {
	top: 50px;
}
</style>
</head>
<body>
	<div id="center-panel">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span class="label">建筑物编号：</span> <input id="buildingNo"
					name="buildingNo" /> <span class="label">司机编号：</span> <input
					id="driverNo" name="driverNo" /> <span class="label">&nbsp;&nbsp;&nbsp;时间范围：</span>
				<input id="beginTime" name="beginTime" size="18"
					value="${beginTime}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endTime" name="endTime" size="18" value="${endTime}" />
			</form>
		</div>
		<div id="map" style="overflow: hidden; position: relative;">
			<svg id="svg-map"
				style="width:100%; height:100%; background:url(../img/T3C-F3.jpg) no-repeat; 
		background-size:cover; position: absolute;left:0; top:0;">
			</svg>
			<div id="plus">+</div>
			<div id="minus">-</div>
		</div>

	</div>
	<script src="../js/dynamicPathAnalysis.js"></script>
	<script type="text/javascript">
		var currDate = new Date();
		var preDate = new Date(currDate.getTime() - 24 * 60 * 60 * 1000);
		var svgMapW;
		var svgMapH;
		var svgMapInitW;
		var svgMapInitH;
		$(function() {
			$("#center-panel").height($('body').height());
			$("#center-panel").width($('body').width());
			$("#search-panel").omPanel({
				collapsible : true,
				collapsed : false,
				header : false
			});
			var maph = $("#center-panel").height()
					- $("#buttonbar").outerHeight(true)
					- $("#search-panel").outerHeight(true) - 60;
			var mapw = 1.5 * maph;
			$("#map").css({
				"width" : mapw,
				"height" : maph
			});
			svgMapInitW=mapw;
			svgMapInitH=maph;
			svgMapW = mapw;
			svgMapH = maph;
			
			$('#buttonbar').omButtonbar({
				btns : [ {
					label : "查询",
					id : "btnQuery",
					disabled : false,
					icons : {
						left : WEB_ROOT + '/img/search.png'
					},
					onClick : btnQuery_onClick
				} ]
			});

			$('#buildingNo').omCombo({
				dataSource : WEB_ROOT + '/base/building.do',
				optionField : 'text',
				editable : false,
				value : 'T3C',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#driverNo').omCombo({
				dataSource : WEB_ROOT + '/base/driver.do',
				optionField : 'text',
				editable : true,
				value : 'A01',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#beginTime').omCalendar({
				dateFormat : "yy-mm-dd H:i:s",
				date : '${beginTime}',
				editable : true,
				showTime : true
			});
			$('#endTime').omCalendar({
				dateFormat : "yy-mm-dd H:i:s",
				date : '${endTime}',
				editable : true,
				showTime : true
			});
			$('#plus').click(plus);
			$('#minus').click(minus);
			$('#svg-map').drag();
			$('#svg-map').wheelEvent(function(down) {
				if (down) {
					minus();
				} else {
					plus();
				}
			});
		});

		function btnQuery_onClick() {
			//校验起始结束时间

			$.ajax({
				url : WEB_ROOT + "/graph/getCoordinateArray.do",
				async : false,
				data : $('#queryform').serializeObject(),
				success : function(rst) {
					btnQuerySuccess(rst);
				},
				error : function(error) {
				}
			});
		}

		function btnQuerySuccess(rst) {
			createPointMove(rst);
		}
	</script>
</body>
</html>