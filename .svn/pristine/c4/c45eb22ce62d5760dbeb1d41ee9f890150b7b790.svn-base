<!-- 
// +----------------------------------------------------------------------
// | Author: liangsir 2017-04-24
// +---------------------------------------------------------------------- -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<%@ include file="commonjsp/jsCssfile.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/detail.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/detail/firmDetail.js"></script>
</head>
<body>
<%@ include file="commonjsp/head.jsp"%> 
<div class="main">
<%@ include file="commonjsp/firmtabs.jsp"%> 
<%@ include file="commonjsp/firmStatus.jsp"%> 
<%@ include file="commonjsp/firmParam.jsp"%> 
<div class="maincontent">
<%@ include file="commonjsp/firmSidebar.jsp"%> 
  <div class="detail-right">
	   <!-- 我的当日交易 -->
	   <div class="detail-box">
	   <h2 class="detail-title">我的交易</h2>
	   <%@ include file="commonjsp/times.jsp"%> 
        <table class="table tables">
	          	<thead>
	          	 <tr>
	          	   <th width="120">股票名称</th>
	          	   <th>成交时间</th>
	          	   <th>买卖方向</th>
	          	   <th>成交均价</th>
	          	   <th>成交数量</th>
	          	   <th>成交金额</th>
	          	   <th>交易费用(元)</th>
	          	 </tr>
	          	 </thead>
	          	<tbody id="deal-content"></tbody>
	          </table>
       </div>
        <%@ include file="commonjsp/twentyPage.jsp"%> 
      </div>
    </div>
 </div> 
  <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<!--我的持仓 -->
<script id="deal-script" type="text/html">	
{{each records as data i}}
<tr>
 <td>{{data.stockName}}{{data.stockCode}}</td>
 <td>{{numFmt1(data.applayDate)}} {{numFmt1(data.applayTime)}}</td>
 <td>
   {{if data.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
   {{if data.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
 </td>
 <td>{{numFmt1(data.aveTranPrice)}}</td>
 <td>{{numFmt1(data.tranNum)}}</td>
<td>{{formatNumber(data.amount)}}</td>
<td>{{formatNumber(data.cost)}}</td>
</tr>
{{/each}}
</script>


</body>
</html>