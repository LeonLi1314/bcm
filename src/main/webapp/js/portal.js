var portal = {};
// 顶部菜单点击
portal.visit = function(div) {
	$("ul[id='top_menu'] > li > div").attr("class", "");
	$(div).attr("class", "curr");
};

// 加载左侧菜单
portal.loadtree = function(mod) {
	$("#navTree").empty();

	var tabElement = $('#center-tab');
	var ifh = tabElement.height()
			- tabElement.find(".om-tabs-headers").outerHeight(true) - 4;

	var data = [ {
		id : "0101",
		text : "报表",
		expanded : true
	}, {
		id : "010101",
		pid : "0101",
		text : "司机分段统计报表",
		url : WEB_ROOT + "/rpt/driverSub"
	}, {
		id : "010102",
		pid : "0101",
		text : "司机工作按天统计",
		url : WEB_ROOT + "/rpt/driverDay"
	}, {
		id : "010103",
		pid : "0101",
		text : "司机工作按时统计",
		url : WEB_ROOT + "/rpt/driverHour"
	}, {
		id : "010104",
		pid : "0101",
		text : "乘客按天统计",
		url : WEB_ROOT + "/rpt/passDay"
	}, {
		id : "010105",
		pid : "0101",
		text : "乘客搭乘分布统计",
		url : WEB_ROOT + "/rpt/passDistribute"
	}, {
		id : "010106",
		pid : "0101",
		text : "车辆充电按天统计",
		url : WEB_ROOT + "/rpt/vehicleChargeSub"
	}, {
		id : "010107",
		pid : "0101",
		text : "司机工作按时统计",
		url : WEB_ROOT + "/rpt/vehicleChargeDay"
	}, {
		id : "010108",
		pid : "0101",
		text : "车辆行驶按时统计",
		url : WEB_ROOT + "/rpt/vehicleTripHour"
	}, {
		id : "0102",
		text : "图表展示",
		expanded : true
	}, {
		id : "010201",
		pid : "0102",
		text : "图表样例",
		url : WEB_ROOT + "/graph/graphTest"
	} ];

//	$.ajax({
//		url : WEB_ROOT + '/allEnabledMenus.do',
//		success : function(result) {
//			alert(result);
//		},
//		error : function(e) {
//			alert("加载菜单失败！");
//		}
//	});

	$("#navTree")
			.omTree(
					{
						dataSource : WEB_ROOT + '/getCurrentUserSysMenus.do',
						simpleDataModel : true,
						onClick : function(nodeData, event) {
							event.stopPropagation();
							event.preventDefault();
							if (nodeData.url) {
								var tabId = tabElement.omTabs('getAlter',
										'tab_' + nodeData.id);
								if (tabId) {
									tabElement.omTabs('activate', tabId);
								} else {
									tabElement
											.omTabs(
													'add',
													{
														title : nodeData.text,
														tabId : 'tab_'
																+ nodeData.id,
														content : "<iframe id='"
																+ nodeData.id
																+ "'  class='myframe' border=0 frameBorder='no' name='inner-frame' src='"
																+ nodeData.url
																+ "' height='"
																+ ifh
																+ "' width='100%'></iframe>",
														closable : true
													});
								}
							}
							return false;

						}
					});
};

portal.layout = function() {
	$('#Main').height($(window).height() - 43);
	$('#Main').width($(window).width());

	$('#Main').omBorderLayout({
		panels : [ {
			id : "center-panel",
			header : false,
			region : "center"
		}, {
			id : "west-panel",
			resizable : true,
			collapsible : true,
			title : "导航",
			region : "west",
			width : 150,
			onCollapse : function() {
				$('.myframe').each(function(i, val) {
				});
			},
			onExpand : function() {
				$('.myframe').each(function(i, val) {
				});
			}
		} ]
	});

	// var tabElement = $('#center-tab').omTabs({
	// height: $('#Main').height()-2,
	// closable:[1,1000000000],
	// tabMenu : true,
	// onCloseAll : function(event) {
	// }
	// });

	var tabElement = $('#center-tab')
			.omTabs(
					{
						height : $('#Main').height() - 4,
						closable : true,
						tabMenu : true,
						onBeforeClose : function(n, event) {
							// alert('tab ' + n + ' will be closed!');
							if (n == 0) {
								return false;
							}
						},
						onCloseAll : function(event) {
							$('#center-tab')
									.omTabs(
											'add',
											{
												title : '首页',
												tabId : 'tab_1',
												content : "<iframe id='mc'  class='myframe' border=0 frameBorder='no'   src='"
														+ WEB_ROOT
														+ "/desktop'  width='100%'></iframe>",
												closable : false
											});

							var ifh = $('#center-tab').height()
									- $('#center-tab').find(".om-tabs-headers")
											.outerHeight(true) - 4;
							// $('#tab_1').height(tabElement.height() -
							// tabElement.find(".om-tabs-headers").outerHeight());
							$('#mc').height(ifh);
						}
					});

	// $('#center-tab').omTabs('getActivated');

	var ifh = tabElement.height()
			- tabElement.find(".om-tabs-headers").outerHeight(true) - 15;
	// $('#tab_1').height(tabElement.height() -
	// tabElement.find(".om-tabs-headers").outerHeight());
	// $('#mc').height(ifh);

	// var ifh = tabElement.height() -
	// tabElement.find(".om-tabs-headers").outerHeight() - 4;
	tabElement
			.omTabs(
					'add',
					{
						title : '首页',
						tabId : 'tab_1',
						content : "<iframe id='mc'  class='myframe' border=0 frameBorder='no' name='inner-frame' src='"
								+ WEB_ROOT
								+ "/desktop' height='"
								+ ifh
								+ "' width='100%'></iframe>",
						closable : false
					});

};