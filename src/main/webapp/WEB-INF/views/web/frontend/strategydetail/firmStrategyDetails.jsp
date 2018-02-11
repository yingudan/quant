<!-- 
// +----------------------------------------------------------------------
// | Author: liangsir 2017-07-12
// +---------------------------------------------------------------------- -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>有据，专注量化投资</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<%@ include file="commonjsp/jsCssfile.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/firmEcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/firmStrategyDetail.js"></script>
</head>
<body>
<%@ include file="commonjsp/header.jsp"%>
<%@ include file="commonjsp/firmLeft.jsp"%>   
<!--strategyRight-->
<div class="strategyRight">
 <div id="pagebox">
   <%@ include file="commonjsp/firmBase.jsp"%>
   <!-- 收 益 概 览 -->
   <div class="detailedBox" id="detailed_0">
     <%@ include file="commonjsp/firmEarnings.jsp"%>
   </div>
   
   <!-- 交易详情 -->
   <div class="detailedBox" id="detailed_1">
     <%@ include file="commonjsp/firmDeatil.jsp"%>
   </div>
   
   <!-- 每日持仓 -->
   <div class="detailedBox" id="detailed_2">
     <%@ include file="commonjsp/firmPosition.jsp"%>
   </div>
   <%@ include file="commonjsp/tenPage.jsp"%>
   
   <!-- 策略收益 -->
   <div class="detailedBox" id="detailed_3">
     <%@ include file="commonjsp/firmTacticsEarnings.jsp"%>
   </div>
   
   <!-- 风控指标 -->
   <div class="detailedBox" id="detailed_4">
     <%@ include file="commonjsp/firmRiskKpi.jsp"%>
   </div>
      
 </div>
</div>
<!--strategyRight-->
</body>
</html>