<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@include file="../common/tag.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%-- <%@include file="../common/header.jsp"%> --%>
<%-- <%@include file="../common/css.jsp"%> --%>
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
		<div id="graph" style="min-width:800px;min-height:500px">
		
		
		</div>
		<script src="../js/jquery-1.11.3.min.js"></script>
		<script src="../highcharts/highcharts.js"></script>
		<!-- 支持图表导出 -->
		<script src="../highcharts/modules/exporting.js" type="text/javascript"></script> 
		<!-- 添加主题，此处引入gray主题 -->
		<script src="../highcharts/themes/gray.js" type="text/javascript"></script> 
		<script>
		$(function() {
			 $('#graph').highcharts({                   //图表展示容器，与div的id保持一致
			        chart: {
			            type: 'column'                         //指定图表的类型，默认是折线图（line）
			        },
			        title: {
			            text: 'My first Highcharts chart'      //指定图表标题
			        },
			        xAxis: {
			            categories: ['my', 'first', 'chart']   //指定x轴分组
			        },
			        yAxis: {
			            title: {
			                text: 'something'                  //指定y轴的标题
			            }
			        },
			        series: [{                                 //指定数据列
			            name: 'Jane',                          //数据列名
			            data: [1, 0, 4]                        //数据
			        }, {
			            name: 'John',
			            data: [5, 7, 3]
			        }]
			    });
		})
	</script>
</body>
</html>