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
</style>
</head>
<body>
	<div id="center-panel">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span class="label">建筑物编号：</span> <input id="buildingNo"
					name="buildingNo" /> <span class="label">&nbsp;&nbsp;&nbsp;选择日期：</span>
				<input id="day" name="day" size="10" value="${preDay}" />
			</form>
		</div>
		<div id="map">
			<svg id="svg-map"
				style="width:100%; height:100%; background:url(../img/T3C-F3_small.jpg) no-repeat; 
		background-size:cover;">
			</svg>
		</div>
	</div>
	<script src="../js/createPointCommon.js"></script>
	<script src="../js/createPoint.js"></script>
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
			var maph = $("#center-panel").height()
					- $("#buttonbar").outerHeight(true)
					- $("#search-panel").outerHeight(true) - 60;
			var mapw = 1.5 * maph ;
			$("#map").css({
				"width" : mapw,
				"height" : maph
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
			$('#day').omCalendar({
				dateFormat : "yy-mm-dd",
				date : '${preDay}',
				editable : true,
			});
		});

		function btnQuery_onClick() {
			$.ajax({
				url : WEB_ROOT + "/graph/getTakePlaceArray.do",
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
			var buildingNo = $("#buildingNo").val();
			backgroudChage(buildingNo, "small.jpg")
			createPoint(rst, false, buildingNo, false);
		}
	</script>
</body>
</html>