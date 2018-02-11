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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/detail/firmEcharts.js"></script>
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
        <!-- 基本数据 -->
        <div class="detail-box">
         <div class="detail-intro">
			<dl>
			   <dt>模拟收益</dt>
			   <dd class="accumulatedReturn"></dd>
			</dl>
			<dl>
			   <dt>模拟年化收益</dt>
			   <dd class="yield"></dd>
			</dl>
			<dl>
			   <dt>基准收益</dt>
			   <dd class="benchmark"></dd>
			</dl>
			<dl>
			   <dt>阿尔法</dt>
			   <dd class="alpha"></dd>
			</dl>
			<dl>
			   <dt>贝塔</dt>
			   <dd class="beta"></dd>
			</dl>
			<dl>
			   <dt>夏普比率</dt>
			   <dd class="shapratio"></dd>
			</dl>
			<dl>
			   <dt>收益波动率</dt>
			   <dd class="returnVolatlity"></dd>
			</dl>
			<dl>
			   <dt>最大回撤</dt>
			   <dd class="maxRetrace"></dd>
			</dl>
		</div> 
	  </div>
	  
	  
	  <!-- 收益曲线-->
	   <div class="detail-box">
	    <dl class="title-table">
	      <dt>收益曲线<div class="qiehuanpic" type="1" onclick="webFun.dangquan(this)">查看全部策略收益>></div></dt>
	    </dl>
	    <div class="echartsmap clearfix">
	      <div class="tianjians">
	        <div class="uew-radio">
			  <em data="1" onclick="webFun.radios(this)" class="on">累计收益</em>
			  <em data="2" onclick="webFun.radios(this)">相对收益</em>
			  <em data="3" onclick="webFun.radios(this)">对数收益</em>
		    </div>
		    <%@ include file="commonjsp/times.jsp"%> 
		    <style>
		      .detail-time-echartsmap{height:45px;}
		      .detail-time-echartsmap .comm_input_box{display:none;}
		    </style>
		  </div>
		    <div class="piczhexian">
	          <div id="main" style="width: 960px;min-height:250px;background:#fff;"></div>
	          <div class="lefttitlless">当日策略收益</div>
	        </div>
	        <script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/detail/firmEcharts.js"></script>
	    </div>
       </div>
      <!-- 我的持仓 -->
	  <div class="detail-box" id="theDplist-content"></div>
	   <!-- 我的当日交易 -->
	   <div class="detail-box" id="theTclist-content"></div></div>
    </div>
 </div> 
 <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<!--我的持仓 -->
<script id="theDplist-script" type="text/html">	
{{each theDplistFmt as data i}}
<dl class="title-table">
   <dt>我的持仓</dt>
   <dd>
      <span>总资产：<font>¥{{formatNumber(data.totalAssets,2)}}</font></span>
      <span>可用金额：<font>¥{{formatNumber(data.usableAssets)}}</font></span>
      <span>当前仓位：<font>{{numbersFmt('1','2',data.nowSpace)}}</font></span>
   </dd>
 </dl>
<table class="table tables">
	<thead>
	 <tr>
	   <th width="120">股票名称</th>
	   <th>现价</th>
	   <th>成交均价</th>
	   <th>持仓数量</th>
	   <th>收益</th>
	 </tr>
	 </thead>
	<tbody>
    {{each data['dpList'] as datas i}}
	  <tr>
	  	<td>{{datas.stockName}}{{datas.code}}</td>
        <td>{{formatNumber(datas.nowPrice)}}</td>
	  	<td>{{formatNumber(datas.tranPrice)}}</td>
        <td>{{numFmt1(datas.positionNum)}}</td>
	  	<td>{{formatNumber(datas.profit)}}</td>
	  </tr>
    {{/each}}
	</tbody>
</table>
{{/each}}
</script>

                          
<!--我的当日交易 -->
<script id="theTclist-script" type="text/html">	
{{each theTclistFmt as data i}}
<dl class="title-table">
  <dt>我的当日交易</dt>
  <dd>
    <span>买进：<font>¥{{formatNumber(data.buying)}}</font></span>
    <span>卖出：<font>¥{{formatNumber(data.sale)}}</font></span>
    <span style="color:#008DFF;">交易纪录：<font style="color:#008DFF;">{{data.sum}}</font></span>
  </dd>
</dl>
<table class="table tables">
	<thead>
	 <tr>
	   <th width="120">股票名称</th>
	   <th>买卖方向</th>
	   <th>成交均价</th>
	   <th>成交时间</th>
	   <th>成交数量</th>
	   <th>成交金额</th>
	 </tr>
	 </thead>
	<tbody>
     {{each data['ttrList'] as datas i}}
	  <tr>
	  	<td>{{datas.stockName}}{{datas.stockCode}}</td>
	  	<td>
           {{if datas.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
           {{if datas.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
        </td>
	  	<td>{{formatNumber(datas.aveTranPrice)}}</td>
	  	<td>{{datas.applayTime}}</td>
	  	<td>{{formatNumber(datas.tranNum)}}</td>
	  	<td>{{formatNumber(datas.amount)}}</td>
	  </tr>
     {{/each}}
	</tbody>
</table>
{{/each}}
</script>
<!-- ++++++++++++++++++绑定数据+++++++++++++++++++++ -->
<input type="hidden" id="celueStatus" value=""><!-- 策略状态-->
<input type="hidden" id="testStatus" value=""><!-- 策略状态-->

</body>
</html>