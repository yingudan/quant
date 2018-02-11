<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common1.0.css"/>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/template.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/clipboard.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/historylist.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/historylist.js"></script>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<div class='header scroll' data='scroll'>
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi' ></i>
				</a>
			</div>
			<div class='menu'>
				<ul classs='mindex'>
					<li><a class='' href='${pageContext.request.contextPath}/frontend/index.do'>策略工作站</a></li>
					<li>
						<a class='select_btn on' href='${pageContext.request.contextPath}/frontend/myStrategy.do'>我的策略</a>
					</li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/frontend/doc.do'>下载</a>
					</li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/web/frontend/document.do'>文档</a>
					</li>
				</ul>
			</div>
			<div class='userbox'>
				<a id='loginc' class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
				<div class='usercon'>
					<a id='username' href='javascript:void(0)' class='username' data='1' onclick='common.showUl(this)'></a>
					<ul>
						<li><a href='${pageContext.request.contextPath}/frontend/pcenter.do?type=2' >个人中心</a></li>
						<li><a href='javascript:void(0)' onclick='common.logout()'>退出登录</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="tools">
	     <a href="${pageContext.request.contextPath}/frontend/myStrategy.do" class="return"><img src="${pageContext.request.contextPath}/images/frontend/strategy/s_back.png"></a>
	     <span class="titles">有据策略一号</span>
	     <div class="toolbox toolbox1" style="display: block;"><!-- 就绪 -->
		     <span id='status'  class="status statusA" style='cursor:pointer' onclick='simu.goDetail(this)'><i></i></span>
		     <div class="btns">
		      <a id='startpan' href="javascript:void(0)" class="ist">开始实盘模拟</a>
		      <a id='starthi' href="javascript:void(0)" class="ist2">开始历史回测</a>
		     </div>
	     </div>
	     <div class="shangbaotai">
			   <a id='shipin' href="javascript:void(0)" onclick='simu.tabchange(this,1)'>实盘模拟</a>
			   <a id='his' href="javascript:void(0)" class="on"  onclick='simu.tabchange(this,2)'>历史回测</a>
		 </div>
	</div>
	<div class='main'>
	<div class='itemn'>
		<%@ include file="detail/commonjsp/historyStatus.jsp"%> 
		<div class='tablecontainer'>
			<div class='tableleft'>
				<table class='table tableleft1'>
					<thead>
						<tr id='thlist' >
							
						</tr>
					</thead>
					<tbody id='list1'>
					</tbody>
				</table>
				<a class='tablebtn' href='javascript:void(0)' onclick='historyList.thmore(this)' data='1'>>></a>
			</div>
			
			
			<table class='table tableright'>
				<thead>
					<tr>
						<th ><span>回测收益</span><i onclick='historyList.thgetlist(1,this)' data='2'></i></th>
						<th ><span>回测年化收益</span><i onclick='historyList.thgetlist(2,this)' data='2'></i></th>
						<th ><span>基准收益</span><i onclick='historyList.thgetlist(3,this)' data='2'></i></th>
						<th ><span>阿尔法</span><i onclick='historyList.thgetlist(4,this)' data='2'></i></th>
						<th ><span>贝塔</span><i onclick='historyList.thgetlist(5,this)' data='2'></i></th>
						<th ><span>夏普比率</span><i onclick='historyList.thgetlist(6,this)' data='2'></i></th>
						<th ><span>收益波动率</span><i onclick='historyList.thgetlist(7,this)' data='2'></i></th>
						<th ><span>回撤</span><i onclick='historyList.thgetlist(8,this)' data='2'></i></th>
					</tr>
				</thead>
				<tbody id='list'>
				</tbody>
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
	</div>
	</div>
	<%@ include file="common/footer.jsp"%> 
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
	<script id="historyListlation_script" type="text/html">
 	 {{each records as v i}}
		<tr>
			<td><input type='checkbox'>{{i}}</td>
			<td>{{v.}}</td>
			<td>2017-04-10至2017-05-12</td>
			<td>TICK</td>
			<td>100,000.000</td>
			<td>40.32%</td>
			<td>已完成</td>
			<td><a href='javascript:void(0)'>查看结果</a></td>
		</tr>
 	 {{/each}}
	</script>
	<script id="historyListlation2_script" type="text/html">
 	 {{each records as v i}}
		<tr onclick="historyList.openzl({{v.strategyId}},{{v.groupId}})" style='cursor:pointer'>
			<td>{{v.accumulatedReturn}}</td>
			<td>{{v.yield}}</td>
			<td>{{v.totalBenchmark}}</td>
			<td>{{v.alpha}}</td>
			<td>{{v.beta}}</td>
			<td>{{v.shapratio}}</td>
			<td>{{v.returnVolatlity}}</td>	
			<td>{{v.maxRetrace}}</td>	
		</tr>
 	 {{/each}}
	</script>
</body>
</html>