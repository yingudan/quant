<!-- Author: liangsir 2017-03-20 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>有据量化数据管理系统-登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/sysmanager/icon.png" type="image/x-icon" />
<link rel="icon" href="${pageContext.request.contextPath}/images/sysmanager/icon.png" type="image/png" >
<!--公共引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<!--公共引入文件 end-->
<!--页面私有引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sysmanager/login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/login.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div class="wrap">
	<div class="login-box">		
		<div class="box-body">
			<div class="login-logo">
				<img src="${pageContext.request.contextPath}/images/sysmanager/login-logo-img.png"/>
			</div>
			<div class="head"><img src="${pageContext.request.contextPath}/images/sysmanager/login-logo-txt.png"/></div>
			<form class="formlogin">
			    <div class="userdiv">
			      <div class="i"></div>
			      <input class="username isclear" name="username" id="username" placeholder="输入您的账号" maxlength="20"/>
			    </div>
				<div class="pwddiv">
			      <div class="i"></div>
			      <input class="pwd isclear" type="password" id="pwd"   placeholder="输入您的密码"  maxlength="20"/>
			    </div>
				<div class="codediv">
			      <div class="i" id="captcha2"></div>
			      <div class="code-img"><img id="captcha" alt="验证码,看不清楚?请点击刷新验证码"  data-echo="<c:url value="captcha.do"/>" src="<c:url value="/images/blank.gif"/>"></div>
			      <input class="pwd isclear" id="code"  placeholder="输入验证码"  maxlength="4"/>
			    </div>
			    <div class="tishi"></div>
		    	<a class="loginbtn" href="javascript:void(0)" rel="nofollow" onclick="login.logining()">登录</a>
		    </form>
		</div>
	</div>
	<div class="loginbottom">©2015-2016 千般 All Rights Reserved | 千般资本 Champions Capital | 蜀ICP备15020562号-5</div>
</div>   
</body>
</html>