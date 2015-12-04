<%@tag pageEncoding="UTF-8"%>
<%@tag import="my.web.RequestContext"%>

var data=[
<%
	if (false) {
%>
{ id: "0301", text: "系统管理", expanded: true }, { id: "030101", pid:
"0301", text: "微首页维护", url: WEB_ROOT+"/sys/site/list" },{ id: "030102",
pid: "0301", text: "医院介绍", url: WEB_ROOT+"/sys/hospital/list" },{ id:
"030103", pid: "0301", text: "科室管理", url: WEB_ROOT+"/sys/branch/list"
},{ id: "030104", pid: "0301", text: "医生管理", url:
WEB_ROOT+"/sys/doctor/list" } , { id: "030106", pid: "0301", text:
"出诊排班", url: WEB_ROOT+"/sys/roster/list" },{ id: "030107", pid: "0301",
text: "常用字典", url: WEB_ROOT+"/sys/dict/list" },{ id: "030108", pid:
"0301", text: "预约须知", url: WEB_ROOT+"/sys/yyxz/list" },{ id: "030109",
pid: "0301", text: "预约区间设置", url: WEB_ROOT+"/sys/syslesparm/list" },{
id: "030110", pid: "0301", text: "听课区间维护", url:
WEB_ROOT+"/sys/orderdatetype/list" },
<%
	}
%>
{ id: "0302", text: "报表", expanded: true },
{ id: "030201", pid: "0302", text: "司机工作分段统计", url: WEB_ROOT+"/rpt/driverSub" },
{ id: "030202", pid: "0302", text: "司机工作按天统计", url: WEB_ROOT+"/rpt/driverDay" },
{ id: "030203", pid: "0302", text: "司机工作按时统计", url: WEB_ROOT+"/rpt/driverHour" },
{ id: "030204", pid: "0302", text: "乘客按天统计", url: WEB_ROOT+"/rpt/passDay" },
{ id: "030205", pid: "0302", text: "乘客搭乘分布统计", url: WEB_ROOT+"/rpt/passDistribute" },
{ id: "030206", pid: "0302", text: "车辆充电分段统计", url: WEB_ROOT+"/rpt/vehicleChargeSub" },
{ id: "030207", pid: "0302", text: "车辆充电按天统计", url: WEB_ROOT+"/rpt/vehicleChargeDay" },
{ id: "030208", pid: "0302", text: "车辆行驶按时统计", url: WEB_ROOT+"/rpt/vehicleTripHour" }];
