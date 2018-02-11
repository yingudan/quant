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
	   <h2 class="detail-title">风险指标</h2>
         <table class="table tables">
            	<thead>
            	 <tr>
            	   <th width="120">日期(年/月)</th>
            	   <th>1个月</th>
            	   <th>3个月</th>
            	   <th>6个月</th>
            	   <th>12个月</th>
            	 </tr><tr>
            	</tr></thead>
            	 
                <!-- 阿尔法 -->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>阿尔法</td>
            	  </tr>
            	</tbody>
            	<tbody id="alphaList-content"></tbody>
            	 <!-- 阿尔法 end -->
            	 
            	 <!--贝塔列表 -->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>贝塔</td>
            	  </tr>
            	</tbody>
            	<tbody id="betaList-content"></tbody>
            	 <!-- 贝塔列表 end -->
            	 
            	 <!--夏普比率列表-->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>夏普比率</td>
            	  </tr>
            	</tbody>
            	<tbody id="shapratioList-content"></tbody>
            	 <!-- 夏普比率列表 end -->
            	 
            	 
            	  <!--索提洛比率列表-->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>索提洛比率</td>
            	  </tr>
            	</tbody>
            	<tbody id="sotiroratioList-content"></tbody>
            	 <!-- 索提洛比率列表 end -->
            	 
            	 <!--信息比率列表-->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>信息比率</td>
            	  </tr>
            	</tbody>
            	<tbody id="infomationratioList-content"></tbody>
            	 <!-- 信息比率列表 end -->
            	 
            	 <!--收益波动率列表 -->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>收益波动率</td>
            	  </tr>
            	</tbody>
            	<tbody id="returnVolatlityList-content"></tbody>
            	 <!-- 收益波动率列表  end -->
            	 
            	 <!--最大回撤列表 -->
            	<tbody>
            	  <tr class="title-td">
            	    <td colspan="100"><i class="hrs"></i><i class="hrs2"></i>最大回撤</td>
            	  </tr>
            	</tbody>
            	<tbody id="maxRetraceList-content"></tbody>
            	 <!-- 最大回撤列表  end -->
            	 
            	 
            	 
            </table>

       </div>
      </div>
    </div>
 </div>  
 <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<!-- 阿尔法列表 --> 
<script id="alphaList-script" type="text/html">	
{{each alphaList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>

<!-- 贝塔列表 -->
<script id="betaList-script" type="text/html">	
{{each betaList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>


<!-- 夏普比率列表 -->
<script id="shapratioList-script" type="text/html">	
{{each shapratioList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>


<!-- 索提洛比率列表 -->
<script id="sotiroratioList-script" type="text/html">	
{{each sotiroratioList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>


<!-- 信息比率列表 -->
<script id="infomationratioList-script" type="text/html">	
{{each infomationratioList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>


<!-- 收益波动率列表 -->
<script id="returnVolatlityList-script" type="text/html">	
{{each returnVolatlityList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>

<!-- 最大回撤列表 -->
<script id="maxRetraceList-script" type="text/html">	
{{each maxRetraceList as data i}}
<tr>
 <td>{{data.time}}</td>
 <td>{{na(data.oneMonth)}}</td>
 <td>{{na(data.threeMonth)}}</td>
 <td>{{na(data.sixMonth)}}</td>
 <td>{{na(data.twelveMonth)}}</td>
</tr>
{{/each}}
</script>


</body>
</html>