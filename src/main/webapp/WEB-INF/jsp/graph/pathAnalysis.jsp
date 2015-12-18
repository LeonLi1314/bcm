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
					name="buildingNo" /> <span class="label">司机编号：</span> <input
					id="driverNo" name="driverNo" /> <span class="label">&nbsp;&nbsp;&nbsp;时间范围：</span>
				<input id="beginTime" name="beginTime" size="18"
					value="${beginTime}" /> <span class="label">-&nbsp;&nbsp;</span> <input
					id="endTime" name="endTime" size="18" value="${endTime}" />
			</form>
		</div>

		<div id="map"
			style="width: 500px; height: 333px; bottom: 0; right: 0;">
			<svg id="svg-map"
				style="width:100%; height:100%; background:url(../img/T3C-F3_small.jpg) no-repeat; 
		background-size:cover;">
			</svg>
		</div>
		<script src="../js/createPointMove.js"></script>
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
				var mapw = 945 * maph / 630;
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
			});

			function btnQuery_onClick() {
				//校验起始结束时间
				
				$.ajax({
					//url : WEB_ROOT + "/graph/getCoordinates.do",
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
				//alert(rst);
				var x = 134529;
				var y = 190531;
				var arr = [];
				for (var i = 0; i < 50; i++) {
					arr.push([ x += 5000, y += 5000 ]);
				}

				createPoint(rst);
			}
		</script>
</body>
</html>