<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<!--公共引入文件 end-->
<!--页面私有引入文件-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sysmanager/role.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/role.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div class="mainbox">
<a class="addBtn" href="${pageContext.request.contextPath}/sys/role/roleedit.do"><span> <i class="i"></i>添加角色</span></a>
<div class="searchCount">
	<div class="count">
		角色总数: <em id="count"></em>
	</div>
	<div class="search">
		<input type="text"  class="isclear" placeholder="角色名称" id="searchText"/> <i class="i" onclick="role.clickSearch()"></i>
	</div>
</div>
<table class="table">
		<tr>
			<th class="frist">序号</th>
			<th class="comm">角色名称</th>
			<th class="comm">角色描述</th>
			<th class="end">操作</th>
		</tr>
		<tbody id="data-content"></tbody>
</table>
</div>
    
 
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
<input id="isSearch" type="hidden" /><!--搜索-->
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
{{each records as data i}}
<tr>
<td class="frist">{{rowNum+1+i}}</td>
<td class="comm">{{data.name}}</td>
<td class="comm">{{data.description}}</td>
<td class="end">
	<a href="${pageContext.request.contextPath}/sys/role/roleedit.do?id={{data.id}}">修改</a>
	<a href="javascript:void(0)" onclick="role.deleteUser({{data.id}},'{{data.name}}')">删除</a>
</td>
</tr>
{{/each}}
</script>
<!--数据表 end-->
<!--+++++++++++++++++++++数据绑定 end++++++++++++++++++++++++++-->
</body>
</html>