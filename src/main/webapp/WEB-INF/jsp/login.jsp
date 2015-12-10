﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.*"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>登录 - 电瓶车管理系统</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/img/common.css" media=screen>
<META name=GENERATOR content="MSHTML 8.00.7600.16853">
</HEAD>
<BODY id=loginFrame>
	<div id=header>
		<div id="title">
			<h1>电瓶车管理系统</h1>
		</div>

	</div>
	<div id=loginBox>
		<div id=loginBoxHeader></div>
		<div id=loginBoxBody>
			<UL class=floatLeft>
				<LI>
					<H4>请用您的账号登录</H4>
				</LI>
				<form id="login" method="post"
					action="${pageContext.request.contextPath}/main">
					<LI>
						<P>用户名:</P>
						<INPUT id=username class=textInput maxLength=150 size=30 type=text
						name=username>
					</LI>
					<LI>
						<P>密码:</P>
						<INPUT id=password class=textInput maxLength=80 size=30
						type=password name=password> <A class=highlight href="#"
						target=_blank></A>
					</LI>
					<LI class=highlight><INPUT id=loginBtn onclick=this.blur();
						value=登录 type=submit> 
					</LI>
					<LI></LI>
				</form>
			</UL>

			<div class=floatRight>
				推荐使用ie9以上的浏览器</br>
			</div>

			<BR clear=all>
		</div>
		<div id=loginBoxFooter></div>
	</div>
	<div id=footer>@CopyRight 北京智慧图科技有限责任公司</div>

</BODY>
</HTML>
