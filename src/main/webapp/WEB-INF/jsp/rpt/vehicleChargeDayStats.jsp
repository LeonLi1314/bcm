<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@include file="../common/tag.jsp"%> --%>
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
					name="buildingNo" /> <span class="label">车辆编号：</span> <input
					id="vehicleNo" name="vehicleNo" /><span class="label">&nbsp;&nbsp;&nbsp;统计日期：</span>
				<input id="beginStatsDay" name="beginStatsDay" size="10"
					value="${preDay}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endStatsDay" name="endStatsDay" size="10" value="${preDay}" />
			</form>
		</div>
		<table id="grid"></table>
	</div>

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
			$('#vehicleNo').omCombo({
				dataSource : WEB_ROOT + '/base/vehicleWithAll.do',
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

			$('#grid').omGrid({
				dataSource : null,
				limit : 0,
				//height : gridh,
				height : 'fit',
				showIndex : false,
				singleSelect : true,
				//autoFit:true,
				wrap : true,
				width : 'fit',
				method : 'post',
				colModel : [ {
					header : "统计日期",
					name : 'statsDay',
					//width : 100,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'

				}, {
					header : "建筑物编号",
					name : 'buildingNo',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "车辆编号",
					name : 'vehicleNo',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				},{
					header : "充电总次数",
					name : 'chargeCount',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "充电总时长（分钟）",
					name : 'chargeMinutes',
					width : 100,
					// autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "一天行驶里程（米）",
					name : 'averageDistance',
					width : 100,
					//autoextend : true,
					sort : 'clientSide',
					align : 'left'
				} ]
			});
		});

		function btnQuery_onClick() {
			var data = $('#queryform').serializeObject();
			$('#grid').omGrid('options').extraData = data;
			$('#grid').omGrid("setData", WEB_ROOT + "/rpt/getRptVehicleChargeDay.do");
		}
	</script>
</body>
</html>