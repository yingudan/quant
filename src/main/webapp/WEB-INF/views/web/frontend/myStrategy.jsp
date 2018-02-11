<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/myStrategy.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/myStrategy.js"></script>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<input id='geturl' type='hidden' value='${pageContext.request.contextPath}/firmoffer/findStrategyList.m'>
	<div class='header scroll'   data='scroll' dataid='${login_id}'>
	
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/strategy.do'>
					<i class='logoi' ></i>
				</a>
			</div>
			<div class='menu'>
				<ul classs='index'>
					<li><a  href='${pageContext.request.contextPath}/frontend/strategy.do'>策略工作站</a></li>
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
				<div class='unloginbox'>
					<a class='login' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
					
					<a  class='login' href='${pageContext.request.contextPath}/frontend/reg.do'>注册</a>
				</div>
				<div class='loginbox'>
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
	</div>
	<div class='main'>
		<div class='itemn'>
			<div class='bottom item'>
				<p class='s_title'><label class='tabnew'><a class='gupiao  on' href='javascript:void(0)'>股票策略</a><a class='qihuo' href='${web_quantfuture}/frontend/myStrategy.do'>期货策略</a></label><input id='searchtext' type='text' placeholder='搜索策略'/></p>
				<div id='list' class='list'>
					
				</div>
				<!--分页dom-->
				  <div class="data-page">
				   <div class='btn'></div>
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
				<div class='no_data ' style='display:none'>
						<p class='nodatatitle'>你还没有策略，赶快去创建吧！</p>
						<div class='nodatac'>
							<p class='nodatatop'>策略创建流程及操作流程</p>
							<p class='nodataimg'>
								<img src='${pageContext.request.contextPath}/images/frontend/strategy/nostrategy.png'/>
							</p>
							<p class=''>
								<a href='${pageContext.request.contextPath}/web/frontend/document.do'>进入文档</a>
								<label>如需了解更多，请点击查看文档</label>
							</p>
						</div>
				 </div>
		</div>
		</div>
	</div>
	<div class='tipboxshaow' style='display:none'></div>
	<div class='tipbox' data='' style='display:none'>
		<div class='tipboxn'>
			<p class='title'>
				<label>确认操作</label><a href='javascipt:void(0)'  onclick='mystr.closetip()'></a>
			</p>
			<p class='content'>
				确认删除信息吗？<br>
				删除该策略后，信息不可恢复
			</p>
			<p class='btn'>
				<a class='sure' href='javascript:void(0)' onclick='mystr.sdelete()'>确认删除</a>
				<a class='cancel' href='javascript:void(0)' onclick='mystr.closetip()'>取消</a>
			</p>
		</div>
	</div>
	<div class='wxbox' style='display:none'>
		<p class='title'>
			<label>微信订阅</label>
		</p>
		<div class='content'>
			<p>关注UJUQT有据量化微信公众号，接收实时调仓推送</p>
			<label>微信扫一扫，立即绑定</label>
			<img src='${pageContext.request.contextPath}/images/frontend/strategy/wx_img.png'>
		</div>
		<p class='btn'>
			<a class='cancel' href='javascript:void(0)' onclick='mystr.closewx()'>取消</a>
		</p>
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
	<script id="mystrategy_script" type="text/html">
 	 {{each records as v i}}
		<div class='citem' style='background:#fff;border-bottom:1px solid #efefef;'>
				<div class='left'>
					<p class='title'>{{v.strategyName}}</p>
					<p class='status {{v.statuslt}}'><i></i></p>
					<ul>
						<li class='two'><p>策略收益</p><p class='value spec'>{{v.profit}}</p></li>
						<li class='one'><p>阿尔法</p><p class='value'>{{v.alpha}}</p></li>
						<li class='one'><p>贝塔</p><p class='value'>{{v.beta}}</p></li>
						<li class='one'><p>夏普比率</p><p class='value'>{{v.shapratio}}</p></li>
						<li class='one'><p>收益波动率</p><p class='value'>{{v.returnVolatlity}}</p></li>
						<li class='one'><p>最大回撤</p><p class='value'>{{v.maxRetrace}}</p></li>
						<li class='one'><p>策略得分</p><p class='value'>{{if v.grade==''}}--{{else}}{{v.grade}}{{/if}}  </p></li>
					</ul>
				</div>
				<div class='right' data='{{v.classname}}' >
					<div class='inleft' >
						<p class='ctime'>创建时间：<label>{{v.startTime}} </label></p>
						<p class='lasttime'>上次运行：<label>{{v.endTime}}</label></p>
						<p class='history'>回测历史：<label><a href='${pageContext.request.contextPath}/{{v.url}}&type=2'>{{v.hisOfferNum}}</a></label></p>
						<p class='acl'>实盘模拟：<label><a href='${pageContext.request.contextPath}/{{v.url}}&type=1'>{{v.nowOfferNum}}</a></label></p>
					</div>
					<div class='inright' data='{{v.stype}}'>
						{{if v.stype==2}} 
						 	<label class='{{v.classname}}' href='javascript:void(0)' onclick='mystr.prebook(this,{{v.id}})' data='1'></label>
							<span class='alertwx' onclick='mystr.alertwx(this,{{v.id}})'></span>
						{{else if v.stype==3}} 
						{{else}} 
  							<label class='sdelete' href='javascript:void(0)' onclick='mystr.sdeletebox({{v.id}})'></label>
						{{/if}} 
						<a href='${pageContext.request.contextPath}/{{v.url2}}'>查看详情</a>
					</div>
				</div>
			</div>
 	 {{/each}}
	</script>
</body>
</html>