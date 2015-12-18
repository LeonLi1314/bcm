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
		<div id="graph"></div>
	</div>
	<!-- 	<script src="../js/jquery-1.7.2.min.js"></script> -->
	<script src="../highcharts/highcharts.js"></script>
	<!--支持3D图表-->
	<script src="../highcharts/highcharts-3d.js"></script>
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
				dataSource : WEB_ROOT + '/base/buildingWithAll.do',
				optionField : 'text',
				editable : false,
				value : 'T3C',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#driverNo').omCombo({
				dataSource : WEB_ROOT + '/base/driverWithAll.do',
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
					url : WEB_ROOT + "/graph/getPassFlowVolume.do",
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
				var arr = [];
				var dayArr = rst[0]["name"].split('-');
				for (var i = 0; i < rst.length; i++) {
					arr.push(Math.floor(rst[i]["value"]));
				}
				
				$('#graph')
						.highcharts(
								{
									chart : {
										zoomType : 'x',
										spacingRight : 20
									},
									title : {
										text : ''
									},
									xAxis : {
										type : 'datetime',
										title : {
											text : 'Days'
										}
									},
									yAxis : {
										title : {
											text : 'People'
										}
									},
									tooltip : {
										shared : true
									//true和false没有什么区别
									},
									legend : {
										enabled : false
									//如果是true图表最下面会出现USD to EUR(series.name)
									},
									plotOptions : {
										area : {
											//改变渐变颜色
											fillColor : {
												linearGradient : {
													x1 : 0,
													y1 : 0,
													x2 : 0,
													y2 : 1
												},
												stops : [
														[
																0,
																Highcharts
																		.getOptions().colors[0] ],
														[
																1,
																Highcharts
																		.Color(
																				Highcharts
																						.getOptions().colors[0])
																		.setOpacity(
																				0)
																		.get(
																				'rgba') ] ]
											},
											lineWidth : 1,
											marker : {
												enabled : false
											//是否出现标记点
											},
											shadow : false,//是否出现阴影
											states : {
												hover : {
													lineWidth : 1
												}
											},
											threshold : null
										}
									},
									series : [ {
										type : 'area',
										name : 'People Number',
										pointInterval : 24 * 3600000, //以天为单位显示
										pointStart : Date.UTC(dayArr[0],
												dayArr[1] - 1, dayArr[2]),
										data : arr
									} ]
								});
			}
		});
	</script>
</body>
</html>