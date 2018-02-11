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
	   <h2 class="detail-title">策略收益</h2>
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
            	<tbody id="tacticsEarnings-content"></tbody>
            </table>
      </div>
       
	
	
  </div>
 </div>
</div>
 <%@ include file="commonjsp/footer.jsp"%> 
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="tacticsEarnings-script" type="text/html">	
{{each data as data i}}
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