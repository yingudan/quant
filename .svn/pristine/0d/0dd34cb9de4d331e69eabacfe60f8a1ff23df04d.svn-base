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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/detail/historyDetail.js"></script>
</head>
<body>
<%@ include file="commonjsp/head.jsp"%> 
<div class="main">
<%@ include file="commonjsp/historytabs.jsp"%> 
<%@ include file="commonjsp/historyStatus.jsp"%> 
<%@ include file="commonjsp/historyParam.jsp"%> 
<div class="maincontent">
<%@ include file="commonjsp/historySidebar.jsp"%> 
 <div class="detail-right"> 
 
	<div class="detail-box">
	   <h2 class="detail-title">我的交易</h2>
	   <%@ include file="commonjsp/times.jsp"%> 
       <div id="deal-content"></div> 
       <%@ include file="commonjsp/tenPage.jsp"%> 
    </div>
  </div>
 </div>
</div>
 <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="deal-script" type="text/html">	
{{each records as data i}}
<table class="table morearray">
  <thead>
	<tr>
	  <th class="comm text-center" width="100">日期</th>
      <th class="comm text-center" width="120">成交时间</th>
	  <th class="comm" width="140">股票名称</th>
      <th class="comm" width="120">买卖方向</th>
	  <th class="comm" width="120">成交均价</th>
	  <th class="comm" width="120">成交数量</th>
      <th class="comm" width="120">成交金额</th>
      <th class="comm" width="120">交易费用(元)</th>
	</tr>	
   </thead>
   <tbody>  
   {{each data['ttrList'] as datas j}}
   	 <tr>
   	 	<td class="comm text-center" width="100">{{if j==0}}{{getLocalTime(data.theTime/1000,'d')}}{{/if}}</td>
   	 	<td class="comm text-center" width="120">{{datas.applayTime}}</td>
   	 	<td class="comm" width="140">{{datas.stockName}}{{datas.stockCode}}</td>
   	 	<td width="110">
          {{if datas.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
          {{if datas.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
        </td>
   	 	<td class="comm" width="120">{{formatNumber(datas.aveTranPrice)}}</td>
   	 	<td class="comm" width="120">{{datas.tranNum}}</td>
        <td class="comm" width="120">{{formatNumber(datas.amount)}}</td>
        <td class="comm" width="120">{{formatNumber(datas.cost)}}</td>
   	 </tr>
    {{/each}}
 	 <tr>
		<td colspan="100">
		  <div class="tr">
		  <em>统计：<strong>{{data.sum}}</strong>次交易</em>
		  <em>买入（<strong class="red">¥：{{formatNumber(data.buying)}}</strong>）</em>
		  <em>卖出（<strong class="green">¥：{{formatNumber(data.sale)}}</strong>）</em>
		  </div>
		</td>  
     </tr>
   </tbody>
</table>
{{/each}}
</script>






</body>
</html>