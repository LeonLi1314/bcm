<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%>
<style>
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
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
					id="driverNo" name="driverNo" /> <span class="label">车辆编号：</span>
				<input id="vehicleNo" name="vehicleNo" /><br> <br> <span
					class="label">统计日期：</span> <input id="beginStatsDay"
					name="beginStatsDay" /> <span class="label"> - </span> <input
					id="endStatsDay" name="endStatsDay" />
			</form>
		</div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;

							$('#buildingNo').omCombo({
								dataSource : WEB_ROOT + '/base/building.do',
								optionField : 'text',
								editable : false,
								lazyLoad : true,
								filterStrategy : 'first',
								listMaxHeight : 100
							});
							$('#driverNo').omCombo({
								dataSource : WEB_ROOT + '/base/driver.do',
								optionField : 'text',
								editable : true,
								lazyLoad : true,
								autofilter : true,
								filterStrategy : 'first',
								listMaxHeight : 100
							});
							$('#vehicleNo').omCombo({
								dataSource : WEB_ROOT + '/base/vehicle.do',
								optionField : 'text',
								editable : true,
								lazyLoad : true,
								autofilter : true,
								filterStrategy : 'first',
								listMaxHeight : 100
							});

							var currDate = new Date();
							var preDate = new Date(currDate.getTime() - 24 * 60
									* 60 * 1000);
							//preDate = ${"preDay"};
							$('#beginStatsDay').omCalendar({
								date : preDate,
								maxDate : currDate,
								editable : false,
								showTime : false
							});
							$('#beginStatsDay').omCalendar('setDate', preDate);
							$('#endStatsDay').omCalendar({
								date : preDate,
								maxDate : currDate,
								editable : false,
								showTime : false
							});
							$('#endStatsDay').omCalendar('setDate', preDate);

							$('#grid')
									.omGrid(
											{
												dataSource : null,
												limit : 0,
												height : gridh,
												showIndex : false,
												singleSelect : true,
												//autoFit:true,
												width : 'fit',
												method : 'post',
												colModel : [
														{
															header : "统计日期",
															name : 'statsDay',
															//width : 100,
															autoextend : true,
															align : 'left'

														},
														{
															header : "建筑物编号",
															name : 'buildingNo',
															width : 80,
															align : 'left'
														},
														{
															header : "司机编号",
															name : 'driverNo',
															width : 80,
															align : 'left'
														},
														{
															header : "车辆编号",
															name : 'vehicleNo',
															width : 80,

															align : 'left'
														},
														{
															header : "上岗时间",
															name : 'goToTime',
															width : 80,
															align : 'left'
														},
														{
															header : "下岗时间",
															name : 'goOffTime',
															width : 80,
															align : 'left'
														},
														{
															header : "工作时长（分钟）",
															name : 'workMins',
															//width : 80,
															autoextend : true,
															align : 'left'
														},
														{
															header : "行驶里程（米）",
															name : 'tripDistance',
															width : 80,
															align : 'left'
														},
														{
															header : "载客数量",
															name : 'passCount',
															width : 80,
															align : 'left'
														},
														{
															header : "有效扫描乘客数",
															name : 'scanPassCount',
															width : 80,
															align : 'left'
														},
														{
															header : "拍照乘客数",
															name : 'photoPassCount',
															width : 80,
															align : 'left'
														},
														{
															header : "手动+1乘客数",
															name : 'manualAddPassCount',
															width : 80,
															align : 'left'
														},
														{
															header : "有效扫描乘客数中的急客数",
															name : 'scanHurriedPassCount',
															width : 80,
															align : 'left'
														},
														{
															header : "有效扫描中的急客比率",
															name : 'scanHurriedRate',
															width : 80,
															align : 'left',
															renderer : function(
																	colValue,
																	rowData,
																	rowIndex) {
																return colValue
																		* 100
																		+ '%';
															}
														} ]
											});

							$(window).resize(function() {
								$("#center-panel").height($('body').height());
								$("#center-panel").width($('body').width());
								$('#grid').omGrid("resize");
							});

							$("#search-panel").omPanel({
								collapsible : true,
								collapsed : false,
								header : false
							});

							$('#buttonbar')
									.omButtonbar(
											{
												btns : [ {
													label : "查询",
													id : "button-search",
													disabled : false,
													icons : {
														left : WEB_ROOT
																+ '/img/search.png'
													},
													onClick : function() {
														var data = $(
																'#queryform')
																.serializeObject();
														$('#grid').omGrid(
																'options').extraData = data;
														$('#grid')
																.omGrid(
																		"setData",
																		WEB_ROOT
																				+ "/rpt/getRptDriverSubform.do");
													}
												} ]
											});
						});
	</script>
</body>
</html>