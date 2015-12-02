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
				<span class="label">建筑物编号：</span> <input type="text"
					class="input-text" name="buildingNo" />
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
							$('#grid').omGrid({
								dataSource : null,
								limit : 0,
								height : gridh,
								showIndex : false,
								singleSelect : true,
								width : 'fit',
								method : 'post',
								colModel : [ {
									header : "统计日期",
									name : 'statsDay',
									width : 100,
									align : 'left'
								}, {
									header : "建筑物编号",
									name : 'buildingNo',
									width : 80,
									align : 'left'
								}, {
									header : "司机编号",
									name : 'driverNo',
									width : 80,
									align : 'left'
								}, {
									header : "车辆编号",
									name : 'vehicleNo',
									width : 80,
									align : 'left'
								} ],

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