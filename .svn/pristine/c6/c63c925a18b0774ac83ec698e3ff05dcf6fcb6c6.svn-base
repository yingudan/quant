<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>有据,专注量化投资</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common1.0.css"/>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/user.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/login.js"></script>

<!--[if lt IE 9]>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/ie8.css"/>
  <![endif]-->
</head>
<body>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<div class='header'  data='none'>
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi'></i>
				</a>
			</div>
			<div class='menu'>
				<ul class='mindex'>
					<li><a  href='${pageContext.request.contextPath}/frontend/index.do'>首页</a></li>
					<li class='menudown'>
						<a class='has_select' href='javascript:void(0)'>产品服务</a>
						<div class='submenu'>
							<a href='${web_dataoffer}/frontend/index.do'  target='_blank'>数据工厂</a>
							<a href='${pageContext.request.contextPath}/frontend/strategy.do'  target='_blank'>策略工作站</a>
							<a href='${pageContext.request.contextPath}/frontend/company.do'  target='_blank'>企业版</a>
						</div>
					</li>
					<li class='menudown'>
						<a class='has_select' href='javascript:void(0)'>企业介绍</a>
						<div class='submenu'>
							<a href='${pageContext.request.contextPath}/frontend/aboutus.do'>关于我们</a>
							<a href='${pageContext.request.contextPath}/frontend/joinus.do'>加入我们</a>
						</div>	
					</li>
				</ul>
			</div>
			<div class='userbox'>
				<div class='unlogin'>
					<a class='login on' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
					<span>|</span>
					<a  class='login' href='${pageContext.request.contextPath}/frontend/reg.do'>注册</a>
				</div>
				
			</div>
		</div>
	</div>
	<div class='main' style='margin-top:0;'>
		<div class='loginboxc '>
			<input id='loginurl' type='hidden' value='${pageContext.request.contextPath}/member/login.m'>
			<input id='codeurl' type='hidden' value='${pageContext.request.contextPath}/member/getImageValidate.m'>
			<div class='opbox'>
				<p class='title'>我们致力于为您的投资提供更好的金融技术服务</p>
				<p class='title2'>We strive to provide better Fintech service for your investment</p>
				<div class='loginform'>
					<input id='uname' type='text' placeholder='账号（注册时的手机号码）' onblur='login.checkPhone(this)' maxlength='11'/>
					<input id='pword' type='password' placeholder='密    码'  onblur='login.checkPass(this)'  maxlength='14'/>
					<p class='error'></p>
					<div class='code'>
						<input id='imgcode' type='text' placeholder='验证码' maxlength='4'><img alt="验证码" src="" onclick='login.reCode(this,"${pageContext.request.contextPath}/member/getImageValidate.m")' style='cursor:pointer'>
					</div>
					<p class='submit'><a href='javascript:void(0)' onclick='login.logining()'>登&nbsp;&nbsp;录</a></p>
					<p class='lregister'><a href='${pageContext.request.contextPath}/frontend/reg.do'>没有账号,点击注册</a></p>
					<p class="lforget"><a href='${pageContext.request.contextPath}/frontend/forget.do'>忘记密码?</a></p>
				</div>	
				
			</div>	
			
		</div>
		<div class='footer'>
			© 2015-2017 有据 All Rights Reserved | 成都有据量化科技有限公司 | 蜀ICP备17016749号-1
		</div>
	</div>
	
</body>
</html>