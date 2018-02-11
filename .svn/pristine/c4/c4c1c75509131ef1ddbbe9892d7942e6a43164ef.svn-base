<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>有据量化-策略工作站</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common1.0.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/strategy/strategy.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/animate.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/wow/wow.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
	<script type="text/javascript">
	$(function(){
		var wow = new WOW({
		    boxClass: 'wow',
		    animateClass: 'animated',
		    offset: 0,
		    mobile: true,
		    live: true
		});
		wow.init();
	})
		
	</script>
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
				<ul classs='index'>
					<li><a class='select_btn on' href='${pageContext.request.contextPath}/frontend/index.do'>策略工作站</a></li>
					<li>
						<a class='' href='${pageContext.request.contextPath}/frontend/myStrategy.do'>我的策略</a>
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
							<li><a href='${pageContext.request.contextPath}/frontend/pcenter.do??type=2' >个人中心</a></li>
							<li><a href='javascript:void(0)' onclick='common.logout()'>退出登录</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class='main'>
		<div class='item bg'>
			<div class='itemw'>
				<div class='itemn'>
					<p class='t1 wow bounceInLeft'>
						策略工作站，量化投资者的高端装备
					</p>
					<P class='subt1 wow bounceInLeft'>最新前沿科技武装您的投资策略</P>
					<a class='wow bounceInLeft' href='${pageContext.request.contextPath}/frontend/myStrategy.do'>进入策略</a>
				</div>
			</div>
		</div>
		
		<div class='item advantage'>
			<div class='itemn'>
				<ul>
					<li class="first wow fadeInUpBig animated">
						<img src="${pageContext.request.contextPath}/images/frontend/strategy/s_1_1.png">
						<p class='title'>机器学习框架</p>
						<p class='content'>
								为量化交易研发和优化的机器学习模型与算法，分配高性能计算资源
						</p>
					</li>
					<li class='second wow fadeInUpBig animated' data-wow-delay="0.3s">
						<img src="${pageContext.request.contextPath}/images/frontend/strategy/s_1_2.png">
						<p class='title'>顶级回测体验</p>
						<p class='content'>
								支持对沪深A股、EFE、分级基金进行回测。我们支持每日、分钟两级回测，提供简洁、强大的API，回测结果实时显示、快速响应，专业的收益和风险分析。
						</p>
					</li>
					<li class='third wow fadeInUpBig animated' data-wow-delay="0.6s">
						<img src="${pageContext.request.contextPath}/images/frontend/strategy/s_1_3.png">
						<p class='title'>高仿真模拟交易</p>
						<p class='content'>
							最准确、实时的沪深A股、EFE实时模拟交易工具，支持基于tick级的模拟交易。
						</p>
					</li>
					<li class='fourth wow fadeInUpBig animated' data-wow-delay="0.9s">
						<img src="${pageContext.request.contextPath}/images/frontend/strategy/s_1_4.png">
						<p class='title'>多样风控模型</p>
						<p class='content'>
						支持独立清算、风控资金分配和仓位控制在多策略间灵活实现并实时清晰了解账户字长的绩效归因。
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%> 	
	</body>
</html>