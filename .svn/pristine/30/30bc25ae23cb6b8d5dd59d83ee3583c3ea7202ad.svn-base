<!-- 
// +----------------------------------------------------------------------
// | des:这是一个公共的代码引入方式的模板,有着严格的引入方式和书写格式，为了统一全站代码风格和提高代码质量，请严格遵守。
// +----------------------------------------------------------------------
// | 1、将本模板复制到你需要的.jsp页面中。
// | 2、根据您的需求引入您需要的公共文件。
// | 3、引入您的私有文件
// +----------------------------------------------------------------------
// | Author: liangsir 2017-03-20
// +---------------------------------------------------------------------- -->

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>基金管理</title>
<!--公共引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<!--公共引入文件 end-->
<!--页面私有引入文件-->
<link rel="stylesheet" href="页面私有的css.css"/>
<script type="text/javascript" src="页面私有的js.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
    
    
    
  
<!--分页dom-->
  <div class="data-page">
   <div class="pagenation" id="pagenation">
	<a onclick="setPageNum(this,-2)" target="_blank" class="shouye">首页</a>
	<a onclick="setPageNum(this,-1)" target="_blank"class="page-prev"></a>
	<div id="page_div" class="pagediv"></div>
	<a onclick="setPageNum(this,1)" class="page-next"></a> 
	<a onclick="setPageNum(this,2)" target="_blank" class="shouye">尾页</a>
	<label name="pagelabel"></label> <input type="text" class="jump_k"> 
	<a onclick="setPageNum(this,8)" target="_blank" class="jump">GO</a>
   </div>
  </div>
<!--分页模板dom  end-->

<!--+++++++++++++++++++++数据绑定 start++++++++++++++++++++++++++-->  
<!--分页-->
<input id="curPage" type="hidden" value="1" /><!--当前属于第几页-->
<input id="pageSize" type="hidden" value="10" /><!--分页条数-->
<input id="total" type="hidden" /><!--分页 总记录-->
<input id="isSearch" type="hidden" /><!--分页 总记录-->
<script id="page_script" type="text/html">	
   {{each pageLi as value i}}
		{{if value.current=='1'}}
			<a onclick="setPageNum(this,0)" target="_blank" class="current number">{{value.num}}</a>
			{{else}}
			<a onclick="setPageNum(this,0)" target="_blank" class="number">{{value.num}}</a>
		{{/if}}
	{{/each}}
</script>
<!--分页 end-->
<!--数据表start -->
<script id="data-content-script" type="text/html">
{{each list as data i}}
<tr>
	<td class="frist" onclick="fundManage.showFundDetail({{data.id}},{{data.hid}})">{{rowNum+i+1}}</td>
</tr>
{{/each}}
</script>
<!--数据表 end-->
<!--+++++++++++++++++++++数据绑定 end++++++++++++++++++++++++++-->
</body>
</html>