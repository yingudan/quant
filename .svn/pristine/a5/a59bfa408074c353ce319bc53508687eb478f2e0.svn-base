<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/clipboard.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/doc.css"/>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<input id='geturl' type='hidden' value='${pageContext.request.contextPath}/firmoffer/findStrategyList.m'>
	<div class='header scroll'   data='none' dataid='${login_id}'>
	
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi' ></i>
				</a>
			</div>
			<div class='menu'>
				<ul classs='index'>
					<li><a  href='${pageContext.request.contextPath}/frontend/strategy.do'>策略工作站</a></li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/frontend/myStrategy.do'>我的策略</a>
					</li>
					<li>
						<a class='on select_btn ' href='${pageContext.request.contextPath}/frontend/doc.do'>下载</a>
					</li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/web/frontend/document.do'>文档</a>
					</li>
				</ul>
			</div>
			<div class='userbox'>
				<div class='unloginbox'>
					<a class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
					
					<a  class='login' href='${pageContext.request.contextPath}/frontend/reg.do'>注册</a>
				</div>
				<div class='loginbox'>
					<a id='loginc' class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
					<div class='usercon'>
						<a id='username' href='javascript:void(0)' class='username' data='1' onclick='common.showUl(this)'></a>
						<ul>
							<li><a href='${pageContext.request.contextPath}/frontend/pcenter.do?type=2' >个人中心</a></li>
							<li><a href='javascript:void(0)' onclick='common.logout()'>退出登录</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class='main'>
		
		<div class='outerweb'>
			<div class='inner item'>
				<p class='t1'>我们致力于为您的投资提供更好的金融技术服务</p>
				<p  class='t2'>We strive to provide better Fintech service for your investment</p>
				<a href='${pageContext.request.contextPath}/web/frontend/document.do' target='_blank'>查看文档</a>
			</div>
		</div>
		<div class='item'>
			<div class='itemn'>
				<div class='pdf fileitem'>
					<i></i>
					<div>
						<p class='filename'>有据量化策略开发接口使用说明文档.pdf</p>
						<p class='filesize'>文件大小：<label>1.6M</label></p>
						<p class='filetime'>上传时间：<lable>2017-04-06</lable></p>
					</div>
					<a class='hover' href='${pageContext.request.contextPath}/frontend/getFile.do'>
						立即下载
					</a>
				</div>
				<div class='file fileitem'>
					<i></i>
					<div>
						<p class='filename'>有据量化策略开发SDK.zip</p>
						<p class='filesize'>文件大小：<label>1.6M</label></p>
						<p class='filetime'>上传时间：<lable>2017-04-06</lable></p>
					</div>
					<a class='hover' href='${pageContext.request.contextPath}/frontend/getFile.do?type=1'>
						立即下载
					</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="common/footer.jsp"%> 
</body>
</html>