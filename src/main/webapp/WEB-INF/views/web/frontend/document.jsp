<!-- 
// +----------------------------------------------------------------------
// | Author: liangsir 2017-07-12
// +---------------------------------------------------------------------- -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>有据，专注量化投资</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/document.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.wordexport.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/strategydetail/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/strategydetail/strategydetail.css"/>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
<!--header-->
 <div class="header headerfixed">
 <div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
    <div class="nav">
      <div class="navbox">
        <h1 class="logo"><img src="${pageContext.request.contextPath}/images/frontend/logo2.png"></h1>
        <ul>
          <li><a href="${pageContext.request.contextPath}/frontend/strategy.do">策略工作站</a></li>
          <li><a href="${pageContext.request.contextPath}/frontend/myStrategy.do">我的策略</a></li>
          <li><a href="${pageContext.request.contextPath}/frontend/doc.do">下载</a></li>
          <li><a href="${pageContext.request.contextPath}/web/frontend/document.do" class="ons">文档</a></li>
        </ul>
        <div class='userbox'>
				<div class='unloginbox' style="display:none;">
					<a class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
					<a  class='login' href='${pageContext.request.contextPath}/frontend/reg.do'>注册</a>
				</div>
				<div class='loginbox' style="display:none;">
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
 </div>
<!--header-->
<div class="docLeft">
  <div class="ul"></div>
</div>
<div class="docRight">
  <div id="pagebox">
    <dl class="means">
      <dt>帮助—在线文档<a href="javascript:void(0);" class="jquery-word-export">下载文档</a></dt>
      <dd>感谢您使用有据量化（UJUQT）交易平台，以下内容主要介绍有据量化交易平台的API使用方法。<br/>
          内容较多，可使用Ctrl+F进行搜索。<br/>
          如果以下内容仍没有解决您的问题，请您通过社区提问的方式告诉我们，谢谢。<br/>
       </dd>
    </dl>
    <div class="dataquyu" id="dataquyu" style="display:none;">${content}</div>
    <div class="content" id="content"></div>
  </div>
</div>


<!--js脚本-->
<script type="text/javascript">
	$(function(){
		$("a.jquery-word-export").click(function(event) {
			$("#content").wordExport();
		});
	});
</script>
</body>
</html>