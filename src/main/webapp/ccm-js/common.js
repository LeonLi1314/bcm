/*
 * 公共使用
 */
$(function() {

	var obj = window.location;
	var contextPath = obj.pathname.split("/")[1];
	var basePath = "http://" + obj.host + "/" + contextPath + "/";
	/*
	 * 必须通过跳转 页面进入
	 */
	if (top.name != "clearance") {
		var obj = window.location;
		var contextPath = obj.pathname.split("/")[1];
		var refPath = basePath + "index.html";
		location.replace(basePath);
		return;
	}

	/*
	 * 未登录跳转到登录页面
	 */
//	var pathname = location.pathname;
//	if ($.getCookie("name") == null
//			&& pathname.substr(pathname.length - 12, 12) != "/signIn.html") {
//		var refPath = basePath + "signIn.html";
//		return;
//	}

});

function closeMyself(){
	window.close();
}