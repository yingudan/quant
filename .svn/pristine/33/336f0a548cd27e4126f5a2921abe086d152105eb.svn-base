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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sysmanager/role.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/role.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div class="mainbox">
   <div class="form" id="role">
   	  <div class="smalltitle"> <em>基本信息</em><p></p></div>
   	  <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>角色名称:<em>*</em></span>
		<div class="input_one">
			<input class="text isclear isv" id="name" style="width:430px;" maxlength="10" placeholder="角色名称，1~10字" oninput="common.writingValidate(this)" validrule="/^.{1,10}$/" type="text">
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->

      <!--input输入框 -->
   	  <div class="comm_input_box">
		<span>角色描述:<em></em></span>
		<div class="input_one">
		    <textarea class="addValid isclear isv" id="description" style="width:440px;height:110px;" maxlength="100" placeholder="角色描述，0~100字" oninput="common.writingValidate(this)" validrule="/^.{0,100}$/"></textarea>
			<div class="tishi"></div>
		</div>
	  </div>
	  <!--input输入框 end-->
      <div class="hr"></div>
   	  <div class="smalltitle"> <em>分配权限</em><p></p></div>
       <div class="uew-chekbox" id="roleStr"></div>
   </div>
   
   <div class="addbtnbox">
	<span class="toolBtnyes" onclick="role.saveData()">确定添加</span>
	<span class="toolBtnno" onclick="javasript:window.location.href='index.do?pagetype=1';" >放弃添加</span>
   </div>
</div>

<!--数据表start -->
<script id="allRoles-script" type="text/html">
{{each data as data i}}
 <dt data-id="{{data.id}}" class="{{if data.checked==true}}on{{/if}}">{{data.name}}</dt>
  <dd>
    {{each data['child'] as datas i}}
     <div class="em"><i class="i {{if datas.checked==true}}on{{/if}}" onclick="role.check(this)" data-id="{{datas.id}}"></i>{{datas.name}}</div>
    {{/each}}
  </dd>
 </dl>  
{{/each}}
</script>
<!--数据表 end-->
</body>
</html>