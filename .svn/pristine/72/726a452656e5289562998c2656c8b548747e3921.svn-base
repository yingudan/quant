<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>有据量化-首页</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common1.0.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/person/pcenter.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/StringUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/AjaxUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/md5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/pager.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/clipboard.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/pcenter/pcenter.js"></script>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<div class='header scroll' data='scroll' id='${login_id}' name='${web_quant}'>
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi' ></i>
				</a>
			</div>
			<div class='menu'>
				<ul class='mindex noindex' style='display:none' >
					<li><a class='' href='${pageContext.request.contextPath}/frontend/index.do' style='float:right;'>首页</a></li>
					<li class='menudown'>
						<a class='has_select' href='javascript:void(0)'>产品服务</a>
						<div class='submenu'>
							<a href='${web_dataoffer}/frontend/index.do'  target='_blank'>数据工厂</a>
							<a href='${pageContext.request.contextPath}/frontend/strategy.do'  target='_blank'>策略工作站</a>
							<a href='${pageContext.request.contextPath}/frontend/company.do'  target='_blank'>企业版</a>
						</div>
					</li>
					<li class='menudown'>
						<a class='has_select' href='javascript:void(0)'>企业介绍</a>
						<div class='submenu'>
							<a href='${pageContext.request.contextPath}/frontend/aboutus.do'>关于我们</a>
							<a href='${pageContext.request.contextPath}/frontend/joinus.do'>加入我们</a>
						</div>	
					</li>
				</ul>
				<ul classs='mindex twoindex'  >
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
				<ul class='mindex oneindex'  style='display:none'>
					<li><a class='on' href='${web_dataoffer}/frontend/index.do'>数据工厂</a></li>
					<li>
						<a class='' href='${web_dataoffer}/frontend/product.do'>产品服务</a>
					</li>
					<li>
						<a class='' href='${web_dataoffer}/frontend/doc.do'>API文档</a>
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
	<div class='main'>
		<div class='itemn'>
			<div class='left'>
				<div class='myaccount'>
					<p>我的账户</p>
					<a class='on' href='javascript:void(0)' onclick='pcenter.leftmchange(this)' data='cmyaccount'>账户主页</a>
					<!--  a href='javascript:void(0)' onclick='pcenter.leftmchange(this)' data='infocenter'>消息中心</a-->
				</div>
				<div class='dataserver'>
					<p>数据服务</p>
					<a href='javascript:void(0)' onclick='pcenter.leftmchange(this)' data='mybuy'>我购买的</a>
				</div>
				<div class='set'>
					<p>设置</p>
					<a id='baseinfoc' href='javascript:void(0)' onclick='pcenter.leftmchange(this)' data='baseinfo'>基本信息</a>
					<a href='javascript:void(0)' onclick='pcenter.leftmchange(this)' data='mypass'>登录密码</a>
				</div>
			</div>
			<div class='right'>
				<!--账户主页开始  -->
				<div id='cmyaccount' class='cmyaccount'>
					<div class='myinfo white'>
						<p class='name'>KingSong</p>
						<p class="phonenum">15295377800</p>
						<div>
							<p class='scribe'>欢迎使用有据量化（UJUQT）,请写一句话评价自己吧</p><a class='updatescribe' href='javascript:void(0)' onclick='pcenter.tobaseinfo()'>去写一下</a>
						</div>
					</div>
					<div class='mykey'>
						<p  class='ktitle'>我的策略秘钥</p>
						<div class='myinfo white'>
							<div>
								<p id='key' class='key'>12345453435435435435453453</p>
								<a class='copy' href='javscipt:void(0)' onclick='common.copy()'  data-clipboard-text=''>点击复制</a>
							</div>
							<p class="tip">注：策略秘钥请妥善保管，不可告知他人</p>
							
						</div>
					</div>
					<div class='mystrategy'>
						<p class='ktitle'>我的策略</p>
						<div class='myinfo '>
							<p>
								<span>全部策略</span>
								<label class='count'><a href='${pageContext.request.contextPath}/frontend/myStrategy.do'></a></label>
							</p>
							<p>
								<span>历史回测</span>
								<label class='his'><a href='${pageContext.request.contextPath}/frontend/myStrategy.do'></a></label>
							</p>
							<p>
								<span>实盘模拟</span>
								<label class='now'><a href='${pageContext.request.contextPath}/frontend/myStrategy.do'></a></label>
							</p>
						</div>
					</div>
					<div class='qustion'>
						<p  class='ktitle'>常见问题</p>
						<div class='qustionc white'>
							<a href='${pageContext.request.contextPath}/web/frontend/document.do?q=有据量化数据常见疑问汇总' target='_blank'>1、有据量化数据常见疑问汇总</a>
							<a href='${pageContext.request.contextPath}/web/frontend/document.do?q=真实价格下分红、除复权处理' target='_blank'>2、真实价格下分红、除复权处理</a>
							<a href='${pageContext.request.contextPath}/web/frontend/document.do?q=教你如何调试程序BUG' target='_blank'>3、教你如何调试程序BUG</a>
							
						</div>
					</div>
				</div>
				<!--账户主页结束  -->
				<!--消息中心开始  -->
				<div id='infocenter' class='infocenter white ' style='display:none'>
					<p class='nav'>
						<a class='on' href='javascript:void(0)' onclick='pcenter.tabchange(this,1)'>未读消息</a>
						<a href='javascript:void(0)' onclick='pcenter.tabchange(this,2)'>已读消息</a>
					</p>
					<div class='noread'>
						<div class='infoitem'>
							<p class='avatar'></p>
							<div>
								<p class='content'>【系统通知】2017年6月2日，因有据的上交所行情出现异常，可能导致您的模拟交易运行出错，如需重跑，请联系有据量化运营。</p>
								<p class='time'>
									2017-06-12 15:26:36
								</p>
							</div>
						</div>
						<div class='infoitem'>
							<p class='avatar'></p>
							<div>
								<p class='content'>您上个月登录了13天，您获得1个免费使用的模拟交易；再接再厉，当月登录超过20天，下个月即可获得2个免费使用的模拟交易；当月全勤，下个月即可获得3个免费使用的模拟交易。点击查看活动详情>></p>
								<p class='time'>
									2017-06-12 15:26:36
								</p>
							</div>
						</div>
					</div>
					<div class='read'>
						<div class='infoitem'>
							<p class='avatar'></p>
							<div>
								<p class='content'>【系统通知】2017年6月2日，因有据的上交所行情出现异常，可能导致您的模拟交易运行出错，如需重跑，请联系有据量化运营。</p>
								<p class='time'>
									2017-06-12 15:26:36
								</p>
							</div>
						</div>
						<div class='infoitem'>
							<p class='avatar'></p>
							<div>
								<p class='content'>【系统通知】2017年6月2日，因有据的上交所行情出现异常，可能导致您的模拟交易运行出错，如需重跑，请联系有据量化运营。</p>
								<p class='time'>
									2017-06-12 15:26:36
								</p>
							</div>
						</div>
					
					</div>
				</div>
				<!--消息中心开结束 -->
				<!--我购买的开始  -->
				<div id='mybuy' class='mybuy' style='display:none'>
					<div class='mykey'>
						<p  class='ktitle'>我的策略秘钥</p>
						<div class='myinfo white'>
							<div>
								<p class='key'>12345453435435435435453453</p>
								<a class='copy' href='javscipt:void(0)' onclick='common.copy()'  data-clipboard-text=''>点击复制</a>
							</div>
							<p class="tip">注：策略秘钥请妥善保管，不可告知他人</p>
							
						</div>
					</div>
					<div class='mykey'>
						<p  class='ktitle'>我购买的数据套餐</p>
						<div class='myinfo white'>
							<table class='table'>
							    <thead>
									<tr>
										<th>产品</th>
										<th class='sp'>套餐</th>
										<th>开始时间</th>
										<th>截止时间</th>
										<th>已使用次数</th>
										<th style='text-indent:0;'>续费</th>
									</tr>
								</thead>
								<tbody id='tablebuy'>
									<!--tr>
										<td>股票</td>
										<td>
											<p class='level'>初级版</p>
											<p class='data'>一个月，5万次</p>	
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											3,666 
										</td>
										<td>
											<a href='javascript:void(0)'>续费</a> 
										</td>
									</tr>
									<tr>
										<td>期货</td>
										<td>
											<p class='level'>中级版</p>
											<p class='data'>一个月，50万次</p>	
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											3,666 
										</td>
										<td>
											<a href='javascript:void(0)'>续费</a> 
										</td>
									</tr>
									<tr>
										<td>期货</td>
										<td>
											<p class='level'>高级</p>
											<p class='data'>一个月，500万次</p>	
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											2017-02-15 
										</td>
										<td>
											3,666 
										</td>
										<td>
											<a href='javascript:void(0)'>续费</a> 
										</td>
									</tr-->
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!--我购买的结束  -->
				<!--基本信息开始  -->
				<div id='baseinfo' class='baseinfo'style='display:none'>
					<p  class='ktitle'>我的基本信息</p>
					<div class='white'>
						<p class='unamec'><input class='uname' type='text' placeholder='用户名'></p>
						<p class='pingjiac'><input class='pingjai' type='text' placeholder='请输入个人简介0-50字'></p>
						<a href='javascript:void(0)' onclick='pcenter.saveBaseInfo()'>保存</a>
					</div>
				</div>
				<!--基本信息结束  -->
				<!--登录密码开始  -->
				<div id='mypass' class='baseinfo mypass' style='display:none'>
					<p  class='ktitle'>修改您的登录密码</p>
					<div class='white'>
						<p class='unamec'><input class='uname' type='password' placeholder='原密码'></p>
						<p class='pingjiac'><input id='pass' class='pingjai' type='password' placeholder='新密码'></p>
						<p class='pingjiac'><input id='repass' class='pingjai' type='password' placeholder='确认新密码'></p>
						<a href='javascript:void(0)' onclick='pcenter.updatePass()'>确定</a>
					</div>
				</div>
				<!--登录密码结束  -->
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%> 	
		
	</body>
</html>