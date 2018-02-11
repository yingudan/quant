<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>基金管理</title>
<!--公共引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<!--公共引入文件 end-->
<!--页面私有引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sysmanager/suser.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/suser.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div class="mainbox">
   <div class="form" id="suser">
   	  <div class="smalltitle"> <em>基本信息</em><p></p></div>
   	  <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>员工姓名:<em>*</em></span>
		<div class="input_one">
			<input class="text isclear isv" id="name" style="width:430px;" maxlength="10" placeholder="员工姓名，1~10字" oninput="common.writingValidate(this)" validrule="/^.{1,10}$/" type="text">
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->

      <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>员工职务:<em>*</em></span>
		<div class="input_one">
			<input class="text isclear isv" id="duties" style="width:430px;" maxlength="10" placeholder="员工职务，1~10字" oninput="common.writingValidate(this)" validrule="/^.{1,10}$/" type="text">
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->

	  <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>登录账号:<em>*</em></span>
		<div class="input_one">
			<input class="text isclear isv" id="username" style="width:430px;" maxlength="20" placeholder="登录账号,中英文、数字、下划线组成，6~20字" oninput="common.writingValidate(this)" validrule="/^[A-Za-z0-9@#.-]{6,20}$/" type="text">
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->


	  <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>登录密码:<em>*</em></span>
		<div class="input_one">
			<input class="text isclear isv" id="password" style="width:430px;" maxlength="16" placeholder="登录密码,中英文、数字、下划线组成，6~14字" oninput="suser.passvalid(this)" validrule="/^[A-Za-z0-9@#.-]{6,16}$/" type="text">
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->
	  <div class="hr"></div>
   	  <div class="smalltitle"> <em>用户类型</em><p></p></div>
   	  <div class="input_one" style="overflow:hidden;">
   	    <div class="uew-radio" id="isSystem" >
		  <div class="em"><i class="i" onclick="suser.radios(this)" data-id="1"></i>系统用户</div>
		  <div class="em"><i class="i" onclick="suser.radios(this)" data-id="0"></i>普通用户</div>
	    </div>
	  </div>
	  
      <div class="hr"></div>
   	  <div class="smalltitle"> <em>分配角色</em><p></p></div>
      <div class="uew-chekbox" id="roleStr"></div>
   </div>
   
   <div class="addbtnbox">
	<span class="toolBtnyes" onclick="suser.saveData()">确定添加</span>
	<span class="toolBtnno" onclick="javasript:window.location.href='index.do?pagetype=1';" >放弃添加</span>
   </div>
</div>
<!--数据表start -->
<script id="allRoles-script" type="text/html">
{{each data as data i}}
	<div class="em {{if data.isSystem==1}}isSystem{{else}}noSystem{{/if}}"><i class="i" onclick="common.check(this)" data-id="{{data.id}}"></i>{{data.name}}</div>
{{/each}}
</script>
<!--数据表 end-->
</body>
</html>