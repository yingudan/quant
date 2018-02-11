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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/forget.js"></script>
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
				<a id='loginc' class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
				<span>|</span>
				<a id='loginc' class='login' href='${pageContext.request.contextPath}/frontend/reg.do'>注册</a>
			</div>
		</div>
	</div>
	<div class='main' style='margin-top:0'>
		<div class='regbox'>
			<div class='regbg forget'>
				<input id='checkurl' type='hidden' value='${pageContext.request.contextPath}/member/checkRegister.m'>
				<input id='codeurl' type='hidden' value='${pageContext.request.contextPath}/member/getImageValidate.m'>
				<input id='msgcodeurl' type='hidden' value='${pageContext.request.contextPath}/member/sendSmsCode.m'>
				<input id='step1url' type='hidden' value='${pageContext.request.contextPath}/member/checkSmsCode.m'>
				<input id='step2url' type='hidden' value='${pageContext.request.contextPath}/member/register.m'>
				<input id='login' type='hidden' value='${pageContext.request.contextPath}/frontend/login.do'>
					<div class='formbox '>
						<p class='title'>忘记密码</p>
						<p class='progtop'>
							<label class='one on'>1</label>
							<label class='hr'></label>
							<label class='two'>2</label>
						</p>
						<p class='progbottom'>
							<label class='one'>
								验证手机号
							</label>
							<label class='two' style='display:none'>
								设置登录密码
							</label>
						</p>
						<div class='step1' >
							<input id='phonenum' type='text' placeholder='请输入您的手机号' onblur='forget.checkPhone(this)' maxlength='11'/>
							<p class='error'></p>
							<div>
								<input class='code' type='text' placeholder='输入短信验证码'  onblur='forget.checkCode(this)' > 
								<a class='getcode' href='javascript:void(0)' onclick='forget.getImgCode(3,"${pageContext.request.contextPath}/member/getImageValidate.m")'>获取验证码</a>
							</div>
							<p class='error' style='padding-top:0'></p>
							<a class='btn' href='javascript:void(0)' onclick='forget.next();'>下一步</a>
						</div>
						<div class='step2' >
							<input id='password1' type='password' placeholder='设置密码，英文、数字、下划线组成，6-14位' onblur='forget.checkPass(this)'/>
							<input id='password2' type='password' placeholder='确认设置的密码' onblur='forget.checkRePass(this)'/>
							<p class='error'></p>
							<a class='btn' href='javascript:void(0)' onclick='forget.submit()'>下一步</a>
						</div>
						
				</div>
				<div class='footer'>
					© 2015-2017 有据 All Rights Reserved | 成都有据量化科技有限公司 | 蜀ICP备17016749号-1
				</div>
			</div>
			
			
		</div>
		
	</div>
	<div class='popcode'>
		
	</div>
	<div class='popbox'>
		<p class='title'>图形验证</p>
		<div>
			<input id='imgcode' maxlength='4'  class='code' type='text' placeholder='请输入验证码' > 
			<img src='' alt='验证码' onclick='forget.regetImgCode("${pageContext.request.contextPath}/member/getImageValidate.m")'/>
		</div>
		<p class='btn'><a href='javascript:void(0)' onclick='forget.closePop()'>取消</a><a href='javascript:void(0)' onclick='forget.sure("${pageContext.request.contextPath}/member/getImageValidate.m")' >确定</a></p>
	</div>
	
</body>
</html>