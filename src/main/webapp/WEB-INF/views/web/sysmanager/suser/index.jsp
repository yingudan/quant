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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sysmanager/suser.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/sysmanager/suser.js"></script>
<!--页面私有引入文件 end-->
</head>
<body>
<div class="mainbox">
<a class="addBtn" href="${pageContext.request.contextPath}/sys/suser/suseredit.do"><span> <i class="i"></i>添加员工</span></a>
<div class="searchCount">
	<div class="count">
		员工总数: <em id="count"></em>
	</div>
	<div class="search">
		<input type="text"  class="isclear" placeholder="员工姓名/登录账号" id="searchText"/> <i class="i" onclick="suser.clickSearch()"></i>
	</div>
</div>
<table class="table">
		<tr>
			<th class="frist">序号</th>
			<th class="comm textleft">员工姓名</th>
			<th class="comm">登录账号</th>
			<th class="comm textleft">员工职务</th>
            <th class="comm">
				<span>
					<em class="runtype" data-type="2">状态</em>
					<div class="run">
					    <a href="javascript:void(0);" onclick="suser.displaydata(this)" data-type="2">全部</a>
						<a href="javascript:void(0);" onclick="suser.displaydata(this)" data-type="0">正常</a>
						<a href="javascript:void(0);" onclick="suser.displaydata(this)" data-type="1">暂停</a>
					</div>
					<i class="i"></i>
				</span>
			</th>
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
<td class="comm textleft">{{data.name}}</td>
<td class="comm">{{data.username}}</td>
<td class="comm textleft">{{data.duties}}</td>
<td class="comm">{{if data.disabled==0}}正常{{else}}暂停{{/if}}</td>
<td class="end">
	<a href="${pageContext.request.contextPath}/sys/suser/suseredit.do?id={{data.id}}">修改</a>
	<span>
		更多
		<div class="moretool i">
			 <a href="javascript:void(0)" onclick="suser.setUserStatus({{data.id}},{{data.disabled}},'{{data.name}}')">{{if data.disabled==0}}暂停{{else}}恢复{{/if}}</a>
			 <a href="javascript:void(0)" onclick="suser.deleteUser({{data.id}},'{{data.name}}')">删除</a>
	    </div>
	</span>
</td>
</tr>
{{/each}}
</script>
<!--数据表 end-->
<!--+++++++++++++++++++++数据绑定 end++++++++++++++++++++++++++-->
</body>
</html>