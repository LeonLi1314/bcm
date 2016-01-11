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
					name="buildingNo" /> <span class="label">&nbsp;&nbsp;&nbsp;统计日期：</span>
				<input id="beginStatsDay" name="beginStatsDay" size="10"
					value="${preDay}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endStatsDay" name="endStatsDay" size="10" value="${preDay}" />
			</form>
		</div>
		<table id="grid"></table>
		<div id="map"
			style="width: 500px; height: 333px; position: fixed; bottom: 0; right: 0; display: none">
			<svg id="svg-map"
				style="width: 100%; height: 100%; background: url(../img/T3C-F3_small.png) no-repeat; background-size: cover;">
		</svg>
		</div>
	</div>

	<!-- 	<script src="../js/createPoint.js"></script> -->
	<script src="../js/createPointRecursived.js"></script>

	<script>
		var currDate = new Date();
		var preDate = new Date(currDate.getTime() - 24 * 60 * 60 * 1000);

		$(function() {
			$(document).click(function() {
				$('#map').css('display', 'none');
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
					align : 'left'

				}, {
					header : "建筑物编号",
					name : 'buildingNo',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "搭乘时间",
					name : 'takeTime',
					width : 140,
					//autoextend : true,
					align : 'left'
				}, {
					header : "搭乘点X坐标",
					name : 'xPoint',
					width : 80,
					//autoextend : true,
					align : 'left'
				}, {
					header : "搭乘点Y坐标",
					name : 'yPoint',
					width : 80,
					//autoextend : true,
					align : 'left'
				}, {
					header : "航班号",
					name : 'fltNo',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "航班计划起飞时间",
					name : 'estimatedDepatureTime',
					width : 100,
					//autoextend : true,
					align : 'left'
				}, {
					header : "登机口编号",
					name : 'gateNo',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "登机口X坐标",
					name : 'xGatePoint',
					width : 80,
					//autoextend : true,
					align : 'left'
				}, {
					header : "登机口Y坐标",
					name : 'yGatePoint',
					width : 80,
					//autoextend : true,
					align : 'left'
				}, {
					header : "搭乘距离",
					name : 'takeDistance',
					//width : 80,
					autoextend : true,
					align : 'left'
				}, {
					header : "区域内搭乘",
					name : 'inArea',
					//width : 80,
					autoextend : true,
					align : 'left',
					renderer : areaRenderer
				} ]
			});
		});
		function areaRenderer(colValue, rowData, rowIndex) {
			if (colValue == 0) {
				return '<span style="color:red;"><b>' + colValue
						+ '</b></span>';
			} else {
				return '<span style="color:green;"><b>' + colValue
						+ '</b></span>';
			}
		}
		function btnQuery_onClick() {
			var data = $('#queryform').serializeObject();
			$('#grid').omGrid('options').extraData = data;
			$('#grid').omGrid("setData",
					WEB_ROOT + "/rpt/getRptPassDistribute.do");
		}

		var timer = null;
		function grid_onRowClick(rowIndex, rowData, event) {
			$('#map').css('display', 'block');

			var rst = [ [ rowData.xPoint, rowData.yPoint ],
					[ rowData.xGatePoint, rowData.yGatePoint ] ]

			createPoint(rst, true);
			event.stopPropagation();
		}
	</script>
</body>
</html>