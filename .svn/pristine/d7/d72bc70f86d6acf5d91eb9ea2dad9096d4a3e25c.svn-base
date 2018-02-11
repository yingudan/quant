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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/echarts.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/detail/historyEcharts.js"></script> 
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
        <!-- 基本数据 -->
        <div class="detail-box">
         <div class="detail-intro">
				<dl>
			   <dt>回测收益</dt>
			   <dd class="accumulatedReturn"></dd>
			</dl>
			<dl>
			   <dt>回测年化收益</dt>
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
			   <dt>索提洛比率</dt>
			   <dd class="sotiroratio"></dd>
			</dl>
			<dl>
			   <dt>信息比率</dt>
			   <dd class="infomationratio"></dd>
			</dl>
			<dl>
			   <dt>收益波动率</dt>
			   <dd class="returnVolatlity"></dd>
			</dl>
			<dl>
			   <dt>最大回撤</dt>
			   <dd class="maxRetrace"></dd>
			</dl>
			<dl>
			   <dt>跟踪误差</dt>
			   <dd class="tracking"></dd>
			</dl>
			<dl>
			   <dt>下跌风险 </dt>
			   <dd class="downsideRisk"></dd>
			</dl>
		</div> 
	  </div>
	 
	  
	  <!-- 我的当日交易 -->
	   <div class="detail-box">
	    <dl class="title-table">
	      <dt>收益曲线</dt>
	    </dl>
	    <div class="echartsmap clearfix">
	       <div class="tianjians">
	        <div class="uew-radio">
			  <em data="1" onclick="webFun.radios(this)" class="on">累计收益</em>
			  <em data="2" onclick="webFun.radios(this)">相对收益</em>
			  <em data="3" onclick="webFun.radios(this)">对数收益</em>
		    </div>
		    <%@ include file="commonjsp/times.jsp"%> 
		   </div>
		   
		    <div class="echartmain"><div id="main" style="width: 960px;min-height:250px;"></div></div>
			<div class="echartmain"><div id="main2" style="width: 960px;min-height:250px;"></div></div>
			<div class="echartmain"><div id="main3" style="width: 960px;min-height:250px;"></div></div>
	    </div>
       </div>
       
      </div>
    </div>
 </div>  
 
  <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++绑定数据+++++++++++++++++++++ -->
<input type="hidden" id="isReal" value="1"><!-- 1表示初始加载，2 表示累加数据-->
<input type="hidden" id="startReal" value=""><!-- 是否实施累加-->
<input type="hidden" id="celueStatus" value=""><!-- 策略状态-->
<input type="hidden" id="shouyitime" value=""><!-- 累加数据最后时间-->


</body>
</html>