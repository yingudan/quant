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
	   <h2 class="detail-title">每日持仓</h2>
	   <%@ include file="commonjsp/times.jsp"%> 
	   
	    <div id="position-content"></div>
        <%@ include file="commonjsp/twentyPage.jsp"%> 
       </div>
      </div>
    </div>
 </div> 
 <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<!--我的持仓 -->
<script id="position-script" type="text/html">	
{{each data['list'] as data i}}
<table class="table morearray">
  {{if i==0}}
  <thead>
	<tr>
	  <th class="comm text-center" width="110">日期</th>
	  <th class="comm" width="190">股票名称</th>
	  <th class="comm" width="110">收盘价</th>
	  <th class="comm" width="110">持仓数量</th>
      <th class="comm" width="110">成交价</th>
	  <th class="comm" width="110">持仓金额(元)</th>
	  <th class="comm" width="110">收益(元)</th>
	</tr>	
   </thead>
   {{/if}}
   <tbody>

   {{each data['dpList'] as datas j}}
   	 <tr>
   	 	<td class="comm text-center" width="110">{{if j==0}}{{getLocalTime(data.time/1000,'d')}}{{/if}}</td>
   	 	<td class="comm" width="190">{{datas.stockName}}{{datas.code}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.closingPrice)}}</td>
   	 	<td class="{{redOrgreen(data.positionNum)}}"  width="110">{{datas.positionNum}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.tranPrice)}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.positionPrice)}}</td>
        <td class="comm" width="110">{{formatNumber(datas.profit)}}</td>
   	 </tr>
    {{/each}}
   	 <tr>
   	 	<td colspan="100">
   	 	  <div class="tr">
   	 	   <em>统计：<strong>{{data.transNum}}</strong>次交易</em>
   	 	   <em>总资产（<strong class="{{redOrgreen(data.totalAssets)}}">¥：{{formatNumber(data.totalAssets)}}</strong>）</em>
   	 	   <em>持仓资产（<strong class="{{redOrgreen(data.positionAssets)}}">¥：{{formatNumber(data.positionAssets)}}</strong>）</em>
   	 	   <em>可用资产（<strong>¥：{{formatNumber(data.usableAssets)}}</strong>）</em>
   	 	   <em>总收益（<strong class="{{redOrgreen(data.totalRevenue)}}">¥：{{formatNumber(data.totalRevenue)}}</strong>）</em>
   	 	   <em>总交易费用（<strong>¥：{{formatNumber(data.allCost)}}</strong>）</em>
   	 	  </div>
   	 	</td>
   	 </tr>
   </tbody>
</table>
{{/each}}
</script>
</body>
</html>