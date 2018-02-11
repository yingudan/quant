<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/clipboard.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/simuParaHi.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/simuParaHi.js"></script>
</head>
<body>
	<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<div class='header scroll' data='scroll'>
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi' ></i>
				</a>
			</div>
			<div class='menu'>
				<ul classs='mindex'>
					<li><a class='select_btn' href='${pageContext.request.contextPath}/frontend/nindex.do'>策略工作站</a></li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/frontend/myStrategy.do'>我的策略</a>
					</li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/frontend/doc.do'>下载</a>
					</li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/web/frontend/document.do'>文档</a>
					</li>
				</ul>
			</div>
			<div class='userbox'>
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
	<div class="tools">
	     <a href="${pageContext.request.contextPath}/frontend/myStrategy.do" class="return"><img src="${pageContext.request.contextPath}/images/frontend/strategy/s_back.png"></a>
	     <span class="titles">有据策略一号</span>
	     <div class="toolbox toolbox1" style="display: block;"><!-- 就绪 -->
		     <span id='status'  class="status statusReady" style='cursor:pointer' onclick='simu.goDetail(this)'><i></i></span>
		    
	     </div>
	    
	</div>
	<div class='main'>
		<div class='itemn'>
		<div class='maininner'>
			<input id='geturl' type='hidden' value='${pageContext.request.contextPath}/firmoffer/findParams.m'>
			<div id='list' class='left'>
				<div class='title'>设置参数</div>
			
			</div>
			<div class='right'>
				<div class='title'>设置基本信息</div>
				<div class='rcen'>
					<p class='subtitle'>历史数据回测</p>
					<p class='item'>
						<label>设置初始资金：</label>
						<input id='total' class='inputcheckall' type='text' maxlength='13' placeholder='请输入初始资金，默认值为100,000.00元' data='100000.00' onblur='simuPara.checkAll(this)'>
					</p>
					<div class='item time'>
						<label>选择历史数据区间：</label>
						<span><input id='starttime' type='text' data='' placeholder='请选开始时间' ><p class='bottom' style='width:30px;height:40px;float:left;border-bottom:1px solid #ccc; margin:auto 12px;'></p><input id='endtime' type='text' data=''  placeholder='请选结束时间' ></span>
					</div>
					<div class="comm_input_box">
			          <span>设置策略频率：</span>
			          <div class="input_one comm_select">
						<input id='dateType' style="width:88px;"  data-type="0" class="text isclear isv fielda selectxl"  data-field="currency"  placeholder="TICK" oninput="common.writingValidate(this)" validrule="/^.{1,20}$/" readonly="readonly" onclick="common.select(this)" onfocus="common.select(this)" type="text">
						<ul style="width: 113px; display: none;">
							<li><a href="javascript:;" rel="0" style='width:103px;'>TICK</a></li>
							<li><a href="javascript:;" rel="2"  style='width:103px;'>分钟</a></li>
							<li><a href="javascript:;" rel="1"  style='width:103px;'>每天</a></li>
						</ul>
					  </div>
				   </div>
					<a class='btn' href='javascript:void(0)' onclick='simuPara.submit()'>开始运行</a>
				</div>
			</div>
		</div>
		</div>
	</div>
	<div class='popcode'>
		
	</div>
	<div class='popbox'>
		<p class='title'>提示</p>
		<div>
			此次历史回测，预计耗时<p></p>
		</div>
		<p class='btn'><a href='javascript:void(0)' onclick='simuPara.closePop()'>取消</a><a href='javascript:void(0)' onclick='simuPara.submit()''>确定</a></p>
	</div>
	<div id='errortips' class='errortips' style='display:none;text-align:left;'>
		<p>可能原因：</p>
		<p>	1.策略未处于就绪状态；</p>
		<p>	2.策略参数设置有误；</p>
		<p>	3.当前不是交易日或交易时间；</p>
		<p><a class='ebtn' href='javascript:void(0)' onclick='simuPara.closeError()'>确定</a></p>
	</div>
	<%@ include file="common/footer.jsp"%> 
<script id="list_script" type="text/html">
 	 {{each data as v i}}

	<div class='paraitem'>
			<div class='ileft'>{{v.paramName}}</div>
			<div class='iright'>
					<p><label><i data='1' class='on' onclick='simuPara.radios(this,1)'></i>固定值</label><label><i data='2'  onclick='simuPara.radios(this,2)'></i>循环值</label></p>
					<input  class='inputcheckarray oneinput' class='oneinput' data-id='{{v.id}}'  fieldName={{v.fieldName}} paramName='{{v.paramName}}' type='text' placeholder='请输入参数值，默认值为{{v.defaultVal}}' data='{{v.defaultVal}}'  onblur='simuPara.checkNum(this)'>
					<div class='threeinput'  style='display:none'>
					 <label class='ione'>
					 <i>起始值</i>
						 <input   class='inputcheck value' type='text' maxlength='14' onblur='simuPara.checkOne(this,"起始值")' style='text-indent:3px'>
					 </label>
					 <label class='itwo'>
					 	<i>步长</i>
					 	<input   class='inputcheck stepValue' type='text'  maxlength='14'  onblur='simuPara.checkOne(this,"步长")'  style='text-indent:3px'>
					 </label>
					 <label class='ithree'>
					 	<i>截止值</i>
					 	<input    class='inputcheck maxValue' type='text'  maxlength='14' onblur='simuPara.checkOne(this,"截止值")' style='text-indent:3px'>
					 </label>
				</div>
			</div>
	</div>
		
 	 {{/each}}
	</script>
</body>
</html>