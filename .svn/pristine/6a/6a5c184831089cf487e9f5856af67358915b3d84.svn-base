<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
<div class="detail-head">
   <div class="nav">
     <h1 class="logo"><a href="${pageContext.request.contextPath}/frontend/index.do"><img src="${pageContext.request.contextPath}/images/frontend/logo.png" /></a></h1>
     <ul class="navs">
       <li><a href="${pageContext.request.contextPath}/frontend/index.do">首页</a></li>
       <li><a href="${pageContext.request.contextPath}/frontend/myStrategy.do"  class="on">策略</a></li>
       <li><a href="${pageContext.request.contextPath}/frontend/doc.do">文档</a></li>
     </ul>
     
     <div class="iright">
		<div class="usercon">
			<a id="username" href="javascript:void(0)" class="username" data="1" onclick="common.showUl(this)"></a>
			<ul>
				<li><a href="javascript:void(0)" onclick="common.showP()">查看密钥</a></li>
				<li><a href="javascript:void(0)" onclick="common.logout()">退出登录</a></li>
			</ul>
		</div>
	</div>
   </div>
   <div class="tools">
     <a href="javascript:void(0)" onclick="detailcomm.goBack();" class="return"><img src="${pageContext.request.contextPath}/images/frontend/return.jpg" /></a>
     <span class="titles strategyName"></span>

      <div class="toolbox toolbox3"><!-- 就绪 -->
	     <span class="stu"><i></i>就绪</span>
	     <div class="btns">
	      <a href="javascript:void(0)" class="ist" onclick="detailcomm.goStartTest('1')">开始实盘模拟</a>
	      <a href="javascript:void(0)" class="ist2" onclick="detailcomm.goStartTest('2')">开始历史回测</a>
	     </div>
     </div>

     <div class="toolbox toolbox1"><!-- 历史回测中 -->
	     <span class="stu" onclick="common.toTesting(0)"><i>▶</i>历史回测中</span>
	     <div class="btns">
	      <a href="javascript:void(0)" class="ist2" onclick="detailcomm.stops('1','2');">停止历史回测</a>
	      <a href="javascript:void(0)" class="a" onclick="detailcomm.goTestList('2')">查看回测历史(<font class="hisOfferNum"></font>)</a>
	     </div>
     </div>
     
     <div class="toolbox toolbox0"><!-- 实盘模拟中 -->
	     <span class="stu" onclick="common.toTesting(1)"><i>▶</i>实盘模拟中</span>
	     <div class="btns">
	      <a href="javascript:void(0)" class="ist" onclick="detailcomm.stops('1','1');">停止实盘模拟</a>
	      <a href="javascript:void(0)" class="a" onclick="detailcomm.goTestList('1')">查看模拟历史(<font class="hisOfferNum"></font>)</a>
	     </div>
     </div>

     <div class="toolbox toolbox2"><!-- 未就绪 -->
	     <span class="stu"><i></i>未就绪 </span>
     </div>
     
     <div class="relink toolbox toolbox4"><!-- 掉线重连中 -->
	     <span class="stu"><img src="${pageContext.request.contextPath}/images/frontend/detail/relink.png" /> </span>
     </div>
     
     <div class="relink toolbox toolbox5"><!-- 掉线重连中 -->
	     <span class="stu"><img src="${pageContext.request.contextPath}/images/frontend/detail/relink.png" /> </span>
     </div>
     
   </div>
</div>
<%@ include file="../../user/popbox.jsp"%>







