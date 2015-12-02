<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="tag"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>电瓶车信息管理系统</title>
        <%@include file="common/header.jsp" %>
        <link href="${pageContext.request.contextPath}/css/portal.css" rel="stylesheet" type="text/css"/>
        <script>
        <tag:menu></tag:menu>
        </script>
		<script src="${pageContext.request.contextPath}/js/portal.js"></script>
		<script src="${pageContext.request.contextPath}/js/pngFix.js"></script>
        <script>
            $(document).ready(function(){
            	
            	//$("#rmdt-logo").pngFix();
            	
                	portal.layout();
					portal.loadtree('workSpace');
					
					$(window).resize(function(){
						//window.location.reload();
						//portal.layout();
					});
					
					 
					var dialog = $("#dialog-form").omDialog({
						width : 350,
						autoOpen : false,
						modal : true,
						resizable : true,
						buttons :[{
							text : msg.submit, 
							click:function() {
								submitDialog();
								return false; //阻止form的默认提交动作
							}
						},{
							text:msg.cancel,
							click: function() {
								$("#dialog-form").omDialog("close");//关闭dialog
							}
						}] 
					});
					
					$("#changepassword").click(function() {
						validator.resetForm();
						dialog.omDialog("option", "title", msg.changepassword);
						dialog.omDialog("open");//显示dialog
					});

					var validator = $('#myform').validate({
						focusInvalid: true, 
						 onkeyup: true,
						rules : {
							oldpassword : "required",
							newpassword : "required",
							newpassword2 : {
								required : true,
								equalTo : "#newpassword"
							}
						},
						messages : {
							oldpassword : {
								required : msg.inputoldpassword
							},

							newpassword : {
								required : msg.inputnewpassword
							},

							newpassword2 : {
								required : msg.required_newpassword2,
								equalTo : msg.twonewpasswordneedsame
							}
						}
					});
					
					var submitDialog = function() {
						if (validator.form()) {
							var submitData = $("#myform").serializeObject();
							console.log(submitData);
							var url = "${pageContext.request.contextPath}/modifypassword";
							$.post(url, submitData, function(data) {
								if (!data.ok) {
									$.omMessageTip.show({
										title : msg.operror,
										content : data.msg,
										timeout : 1500
									});
									return;
								} else {
									$.omMessageTip.show({
										title : msg.opsuccess,
										content : msg.changepasswordsuccess,
										timeout : 1500
									});
								}
								$("#dialog-form").omDialog("close");
							}, 'json');
						}
					};
					
            });
        </script>
    </head>
    <body >
    <div id="rmdt-header">
        <div id="rmdt-logo" style="margin-top: 10px" >
       <h1>电瓶车管理系统</h1> 
        </div>
        <div id="rmdt-userStatus" style="margin-top: 10px">
         
            登陆人： <strong>${user.realName }</strong>
             - <a href="#" id="changepassword">修改密码</a> 
              - <a href="${pageContext.request.contextPath}/logout">注销</a>
        </div>
    </div>
        <div id="Main">
            <div id="center-panel">
                <div id="center-tab">
                <ul>
                </ul>
                </div>
            </div>
            <div id="west-panel">
                <ul id="navTree">
                </ul>
            </div>
        </div>
        
        
        <div id="dialog-form">
		<form id="myform">
			<label>&nbsp;&nbsp;&nbsp;原密码：</label> <input type="password"
				 name="oldpassword" id="oldpassword"
				  /> <br />
			<br />
			<label>&nbsp;&nbsp;&nbsp;新密码：</label> <input type="password"
				  name="newpassword" id="newpassword"
				  /> <br />
			<br /> <label>&nbsp;&nbsp;&nbsp;新密码：</label> <input type="password"  
				name="newpassword2" id="newpassword2"  />
			<br>
		</form>
	</div>
    </body>
</html>
