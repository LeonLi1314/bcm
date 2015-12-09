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
				<input id="beginStatsDay" name="beginStatsDay" size="12"
					value="${preDayBeginHour}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endStatsDay" name="endStatsDay" size="12" value="${preDayEndHour}" />
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
				dataSource : WEB_ROOT + '/base/building.do',
				optionField : 'text',
				editable : false,
				value : 'T3C',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#vehicleNo').omCombo({
				dataSource : WEB_ROOT + '/base/vehicle.do',
				optionField : 'text',
				editable : true,
				value : 'ALL',
				autofilter : true,
				filterStrategy : 'first',
				listMaxHeight : 100
			});
			$('#beginStatsDay').omCalendar({
				dateFormat : "yy-mm-dd H",
				date : preDate,
				maxDate : currDate,
				editable : false,
				showTime : true
			});
			$('#beginStatsDay').omCalendar('setDate', preDate);
			$('#endStatsDay').omCalendar({
				dateFormat : "yy-mm-dd H",
				date : preDate,
				maxDate : currDate,
				editable : false,
				showTime : true
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
					name : 'statsHour',
					width : 100,
					//autoextend : true,
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
					align : 'left'
				}, {
					header : "实际行驶距离（米）",
					name : 'tripDistance',
					width : 80,
					//autoextend : true,
					align : 'left'
				}, {
					header : "理论行驶距离（米）",
					name : 'theoryTripDistance',
					width : 80,
					// autoextend : true,
					align : 'left'
				},  {
					header : "饱和度（%）",
					name : 'saturation',
					width : 120,
					//autoextend : true,
					align : 'left',
					renderer : function(colValue, rowData, rowIndex) {
						return colValue * 100 + '%';
					}
				} ]
			});
		});

		function btnQuery_onClick() {
			var data = $('#queryform').serializeObject();
			$('#grid').omGrid('options').extraData = data;
			$('#grid').omGrid("setData", WEB_ROOT + "/rpt/getRptVehicleTripHour.do");
		}
	</script>
</body>
</html>