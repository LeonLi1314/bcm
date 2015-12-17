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

#graph {
	width: 50%;
	height: 80%;
}
</style>
</head>
<body>
	<div id="center-panel">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span class="label">建筑物编号：</span> <input id="buildingNo"
					name="buildingNo" /> <span class="label">&nbsp;&nbsp;&nbsp;统计日期：</span>
				<input id="beginStatsDay" name="beginStatsDay" size="10"
					value="${preDay}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endStatsDay" name="endStatsDay" size="10" value="${preDay}" />
			</form>
		</div>
		<div id="graph" ></div>
	</div>
	<!-- 	<script src="../js/jquery-1.7.2.min.js"></script> -->
	<script src="../highcharts/highcharts.js"></script>
	<script src="../highcharts/double-pie.js"></script>
	<script>
		var currDate = new Date();
		var preDate = new Date(currDate.getTime() - 24 * 60 * 60 * 1000);

		$(function() {
			$("#center-panel").height($('body').height());
			$("#center-panel").width($('body').width());
			$("#search-panel").omPanel({
				collapsible : true,
				collapsed : false,
				header : false
			});
			var gridh = $("#center-panel").height()
					- $("#buttonbar").outerHeight(true)
					- $("#search-panel").outerHeight(true) - 60;
			$(window).resize(function() {
				$("#center-panel").height($('body').height());
				$("#center-panel").width($('body').width());
				$('#grid').omGrid("resize");
			});

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
				value : 'ALL',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#beginStatsDay').omCalendar({
				dateFormat : "yy-mm-dd",
				date : preDate,
				maxDate : currDate,
				editable : false,
				showTime : false
			});
			$('#beginStatsDay').omCalendar('setDate', preDate);
			$('#endStatsDay').omCalendar({
				dateFormat : "yy-mm-dd",
				date : preDate,
				maxDate : currDate,
				editable : false,
				showTime : false
			});
			$('#endStatsDay').omCalendar('setDate', preDate);

			function btnQuery_onClick() {
				$.ajax({
					url : WEB_ROOT + "/graph/getTotalDriverWork.do",
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
				var temp;
				var json = {};
				var data = [];
				var firstDimensionArr = [];
				var colors = Highcharts.getOptions().colors;
				
				for (var i = 0; i < rst.length; i++) {
					if (temp == rst[i].firstDimension) {
						json.y += parseInt(rst[i].value);
						json.drilldown.categories.push(rst[i].secondDimension);
						json.drilldown.data.push(parseInt(rst[i].value));
					} else {
						if (json != null && temp != null) {
							data.push(json);
							json = {};
							temp = null;
						}
						if (temp == null) {
							json.drilldown = {};
							json.drilldown.categories = [];
							json.drilldown.data = [];
							temp = rst[i].firstDimension;
							firstDimensionArr.push(temp);
							json.y = parseInt(rst[i].value);
							json.color = colors[i];
							json.drilldown.name = rst[i].firstDimension;
							json.drilldown.color = colors[i];
							json.drilldown.categories
									.push(rst[i].secondDimension);
							json.drilldown.data.push(parseInt(rst[i].value));
							continue;
						}
					}
				}
				if (json != null)
					data.push(json);
				
				createDpie(data, firstDimensionArr);
			}
		});
	</script>
</body>
</html>