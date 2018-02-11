<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--header-->
<%@ include file="input.jsp"%>
 <div class="header headerfixed">
    <div class="nav">
      <div class="navbox">
        <h1 class="logo"><img src="${pageContext.request.contextPath}/images/frontend/logo2.png"></h1>
        <ul>
          <li><a href="${pageContext.request.contextPath}/frontend/strategy.do">策略工作站</a></li>
          <li><a href="${pageContext.request.contextPath}/frontend/myStrategy.do" class="ons">我的策略</a></li>
          <li><a href="${pageContext.request.contextPath}/frontend/doc.do">下载</a></li>
          <li><a href="${pageContext.request.contextPath}/web/frontend/document.do">文档</a></li>
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
    <div class="strategyTool">
      <span class="strategyName" onclick="web.goPrev(2);"><i class="i"></i><em></em></span>
      <!-- 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪,4历史掉线，5实时掉线 -->
      <span class="strategyStatus">
        <i class="i"></i>
        <em class="strategyStatusTxt0" onclick="common.toTesting(1)">实盘模拟中</em>
        <em class="strategyStatusTxt1" onclick="common.toTesting(2)">历史回测中</em>
        <em class="strategyStatusTxt2">未就绪</em>
        <em class="strategyStatusTxt3">就绪</em>
        <em class="strategyStatusTxt4" onclick="common.toTesting(1)">断线重连中</em>
        <em class="strategyStatusTxt5" onclick="common.toTesting(2)">断线重连中</em>
      </span>
    </div>
    <div class="hidden" id="sysurl">${pageContext.request.contextPath}</div>
 </div>
<!--header-->







