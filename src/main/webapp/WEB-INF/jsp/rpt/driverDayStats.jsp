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
					name="buildingNo" /> <span class="label">司机编号：</span> <input
					id="driverNo" name="driverNo" /> <span class="label">车辆编号：</span>
				<input id="vehicleNo" name="vehicleNo" /><br> <br> <span
					class="label">&nbsp;&nbsp;&nbsp;统计日期：</span> <input
					id="beginStatsDay" name="beginStatsDay" size="10" value="${preDay}" />
				<span class="label">-&nbsp;&nbsp;</span> <input id="endStatsDay"
					name="endStatsDay" size="10" value="${preDay}" />
			</form>
		</div>
		<table id="grid"></table>
		<div id="graph"
			style="width: 330px; height: 150px; position: fixed; bottom: 30px; right: 30px;"></div>
	</div>

	<script src="../highcharts/highcharts.js"></script>
	<script src="../highcharts/themes/grid-light.js"></script>
	<script src="../highcharts/pie.js"></script>
	<script>
		var currDate = new Date();
		var preDate = new Date(currDate.getTime() - 24 * 60 * 60 * 1000);

		$(function() {
			$(document).click(function() {
				$('#graph').css('display', 'none');
			})
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
				onRowClick : function onRowClick(rowIndex, rowData, event) {
					grid_onRowClick(rowIndex, rowData, event);
				},
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
					header : "司机编号",
					name : 'driverNo',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "车辆编号",
					name : 'vehicleNo',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "上岗时间",
					name : 'goToTime',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "下岗时间",
					name : 'goOffTime',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "工作时长（分钟）",
					name : 'workMins',
					width : 90,
					//autoextend : true,
					align : 'left'
				}, {
					header : "行驶里程（米）",
					name : 'tripDistance',
					width : 80,
					//autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "载客数量",
					name : 'passCount',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "有效扫描乘客数",
					name : 'scanPassCount',
					width : 80,
					//autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "拍照乘客数",
					name : 'photoPassCount',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "手动+1乘客数",
					name : 'manualAddPassCount',
					width : 80,
					//autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "急客数",
					name : 'scanHurriedPassCount',
					//width : 80,
					autoextend : true,
					sort : 'clientSide',
					align : 'left'
				}, {
					header : "有效扫描中的急客比率",
					name : 'scanHurriedRate',
					width : 120,
					//autoextend : true,
					sort : 'clientSide',
					align : 'left',
					renderer : function(colValue, rowData, rowIndex) {
						return (colValue * 100).toFixed(2) + '%';
					}
				} ]
			});
		});

		function btnQuery_onClick() {
			var data = $('#queryform').serializeObject();
			$('#grid').omGrid('options').extraData = data;
			$('#grid').omGrid("setData", WEB_ROOT + "/rpt/getRptDriverDay.do");
		}

		function grid_onRowClick(rowIndex, rowData, event) {
			$('#graph').css('display', 'block');
			var arr = [];
			arr[0] = [
					"有效扫描乘客数" + rowData.scanPassCount + "人<br>(其中急客数为:)"
							+ rowData.scanHurriedPassCount + '人',
					rowData.scanPassCount ];
			arr[1] = [ "拍照乘客数" + rowData.photoPassCount + "人",
					rowData.photoPassCount ];
			arr[2] = [ "手动+1乘客数" + rowData.manualAddPassCount + "人",
					rowData.manualAddPassCount ];
			show(arr);
			event.stopPropagation();
		}
	</script>
</body>
</html>