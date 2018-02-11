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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/hisEcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/hisStrategyDetail.js"></script>
</head>
<body>
<%@ include file="commonjsp/header.jsp"%>
<%@ include file="commonjsp/hisLeft.jsp"%>   
<!--strategyRight-->
<div class="strategyRight">
 <div id="pagebox">
   <%@ include file="commonjsp/hisBase.jsp"%>
   
   <!-- 收 益 概 览 -->
   <div class="detailedBox" id="detailed_0">
     <%@ include file="commonjsp/hisEarnings.jsp"%>
   </div>
   
   <!-- 交易详情 -->
   <div class="detailedBox" id="detailed_1">
     <%@ include file="commonjsp/hisDeatil.jsp"%>
   </div>
   
   <!-- 每日持仓 -->
   <div class="detailedBox" id="detailed_2">
     <%@ include file="commonjsp/hisPosition.jsp"%>
   </div>
   <%@ include file="commonjsp/tenPage.jsp"%>
   
   <!-- 风控指标 -->
   <div class="detailedBox" id="detailed_3">
     <%@ include file="commonjsp/hisTacticsEarningsAndriskKpi.jsp"%>
   </div>
   
 </div>
</div>
<!--strategyRight-->
</body>
</html>