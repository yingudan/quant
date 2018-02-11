<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
 <div class="pagenation" id="pagenation">
	<a onclick="setPageNum(this,-2)" target="_blank" class="shouye">首页</a>
	<a onclick="setPageNum(this,-1)" target="_blank"class="page-prev"></a>
	<div id="page_div" class="pagediv"></div>
	<a onclick="setPageNum(this,1)" class="page-next"></a> 
	<a onclick="setPageNum(this,2)" target="_blank" class="shouye">尾页</a>
	<label name="pagelabel"></label> <input type="text" class="jump_k"> 
	<a onclick="setPageNum(this,8)" target="_blank" class="jump">GO</a>
</div>
<!--分页 -->
<script id="page_script" type="text/html">	
   {{each pageLi as value i}}
		{{if value.current=='1'}}
			<a onclick="setPageNum(this,0)" target="_blank" class="current number">{{value.num}}</a>
			{{else}}
			<a onclick="setPageNum(this,0)" target="_blank" class="number">{{value.num}}</a>
		{{/if}}
	{{/each}}
</script>
<!--分页  end-->
<input id="curPage" type="hidden" value="1" /><!--当前属于第几页-->
<input id="pageSize" type="hidden" value="20" /><!--分页条数-->
<input id="total" type="hidden" /><!--分页 总记录-->
