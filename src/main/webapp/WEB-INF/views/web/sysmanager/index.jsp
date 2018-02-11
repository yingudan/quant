<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>有据量化数据管理系统</title>
<!--公共引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<!--公共引入文件 end-->
<!--页面私有引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/web/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/ajaxfileupload.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div id="header">
  <div class="logo-img" data-href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/images/sysmanager/login-logo-img.png"/></div>
  <div class="logo-txt"><img src="${pageContext.request.contextPath}/images/sysmanager/logo-txt2.png"/></div>
  <div class="loginout" onclick="login.logout()"><i class="i"></i></div>
  <div class="user"><i class="i"></i>${pageContext.request.userPrincipal.principal.name}</div>
</div>   

<div id="left">
   <div id="menudata" style="display:none;">${menus}</div>
   <div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
   <div id="sontoggle" class="i"  onclick="index.menutoggle(2)"></div>
   <div id="father">
   	 <p class="head"><a class="i changebtn" href="javascript:void(0)" data="show" onclick="index.menutoggle(1)"></a></p>
   	 <ul class="mainmenulist" id="father-content"></ul>
   </div>
   <div id="son"></div>
</div>


<div id="right">
  <div class="map"><span>目前位置：</span><p id="webmap"></p></div>
  <div id="iframecontainer">
	<iframe id="iframepage" name="iframepage" frameborder="0" width="100%" height="100%" marginheight="0" marginwidth="0" scrolling="0" src=""></iframe>
  </div>
</div>


<!--+++++++++++++++++++++数据绑定 start++++++++++++++++++++++++++-->  
<!--数据表start -->
<script id="father-content-script" type="text/html">
{{each data as data i}}
<li>
  <a class="btn-a" href="javascript:void(0);" onclick="index.method('${pageContext.request.contextPath}{{data['url']}}?pagetype=1',{{i}},this,1)">
    <i class="i i{{i}}"></i>
    <span class="inner-text">{{data.name}}</span>
  </a>
</li>
  
{{/each}}
</script>
<script id="son-content-script" type="text/html">
{{each data as data i}}
  <div class="subitembox">
	<p><span class="i i{{i}}"></span></p>
	<ul class="subitem">
       {{each data['child'] as datason j}}
		<li onclick="index.method('${pageContext.request.contextPath}{{datason.url}}?pagetype=1',{{i}},this,2)"><a href="javascript:void(0)" url="${pageContext.request.contextPath}{{datason.url}}?pagetype=1">{{datason.name}}</a></li>
       {{/each}}
	</ul>	
 </div>
{{/each}}
<!--数据表 end-->
</script>
<!--+++++++++++++++++++++数据绑定 end++++++++++++++++++++++++++-->

<!--+++++++++++++++++++++弹出层  start++++++++++++++++++++++++++-->  
<%@ include file="../web/popup/datapopup.jsp"%>
<!--+++++++++++++++++++++弹出层 end++++++++++++++++++++++++++-->  
<!--+++++++++++++++++++++andrew弹出层  start++++++++++++++++++++++++++-->  
<%@ include file="../web/popup/xdatapopup.jsp"%>
<!--+++++++++++++++++++++andrew弹出层 end++++++++++++++++++++++++++-->  
</body>
</html>