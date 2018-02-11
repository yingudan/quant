<!-- 实盘模拟侧栏 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="detail-left">
  <h1>导航</h1>
  <ul class="nav">
  	<li><a href="${pageContext.request.contextPath}/detail/firmEarnings.do?sidebar=1" id="s1">收益概览</a></li>
  	<li><a href="${pageContext.request.contextPath}/detail/firmDeal.do?sidebar=2" id="s2">交易详情</a></li>
  	<li><a href="${pageContext.request.contextPath}/detail/firmPosition.do?sidebar=3" id="s3">每日持仓</a></li>
  	<li><a href="${pageContext.request.contextPath}/detail/firmTacticsEarnings.do?sidebar=4" id="s4">策略收益</a></li>
  	<li><a href="${pageContext.request.contextPath}/detail/firmRiskKpi.do?sidebar=5" id="s5">风险指标</a></li>
  </ul>
</div>