<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>有据,专注量化投资</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/xcommon.css"/>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/index.css"/>
</head>
<body>
<div class='header-c'>
	<div class='header item'>
	<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
		<div class='logo'><a href='${pageContext.request.contextPath}/frontend/index.do'><img src="${pageContext.request.contextPath}/images/frontend/index/logo.png"></a></div>
		<div class='right'>
			<div class='ileft'>
				<a class="on" href='${pageContext.request.contextPath}/frontend/index.do'>首页</a>
				<a  href="javascript:void(0)" onclick='common.islogin()'>策略</a>
				<a href='${pageContext.request.contextPath}/frontend/doc.do'>文档</a>
			</div>
			<div class='iright'>
				<a id='loginc' href='${pageContext.request.contextPath}/frontend/login.do'>登录</a>
				<div class='usercon'>
					<a id='username' href='javascript:void(0)' class='username' data='1' onclick='common.showUl(this)'></a>
					<ul>
						<li><a href='javascript:void(0)' onclick='common.showP()'>查看密钥</a></li>
						<li><a href='javascript:void(0)' onclick='common.logout()'>退出登录</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class='main'>
		<div class='item1'>
			<div class="inner item">
				<div class='left'>
					<img alt="" src="${pageContext.request.contextPath}/images/frontend/index/bannerlt.png">
					<a href='javascript:void(0)'  onclick='common.islogin()'>立即体验</a>
				</div>
				<div class='right'><img alt="" src="${pageContext.request.contextPath}/images/frontend/index/bannerimg.png"></div>
			</div>
		</div>
		<div class='item2 item'>
			<p>量化策略服务</p>
			<div>
				<ul>
					<li>
						<div>
							<img src='${pageContext.request.contextPath}/images/frontend/index/item21.png'>
							<label>海量数据接入</label>
							<p>
								我们覆盖3000多家上市公司、8000多家新三板公司、3000多家创业公司的基本面数据、上市公司财报、千万份公告研究报告、数百个财经新闻、行业宏观资讯、社交媒体、所有的公募基金、行情数据。
							</p>
						</div>
					</li>
					<li>
						<div>
							<img src='${pageContext.request.contextPath}/images/frontend/index/item22.png'>
							<label>API接口服务</label>
							<p>
								数据即服务：通过云服务接口和行情SDK我们把所有覆盖的数据、标准、分析能力、大数据的挖掘能力、人工智能在金融上的应用能力输出到您的应用中。
							</p>
						</div>
					</li>
					<li>
						<div>
							<img src='${pageContext.request.contextPath}/images/frontend/index/item23.png'>
							<label>历史数据回测系统</label>
							<p>
								我们提供了2005年到至今的所有股票的历史行情，可用于用户的历史回测，参数可以循环设置，系统自动加载参数回测，回测结果及详情一目了然，便于研究策略。
							</p>
						</div>
					</li>
					<li>
						<div>
							<img src='${pageContext.request.contextPath}/images/frontend/index/item24.png'>
							<label>实盘数据模拟系统</label>
							<p>
								通过API接口和SDK，您可以把你的策略用于实盘模拟，检验策略的真实有效性，实盘模拟可以随着交易时间自动中止和开启，模拟结果可以和真实数据对比研究。
							</p>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class='item3'>
			<div class="inner item">
				<p>使用教程</p>
				<div>
					<ul>
						<li>
							<div class='left'>
								<img src='${pageContext.request.contextPath}/images/frontend/index/item31.png'>
								<p>下载操作文档</p>
							</div>
							<div class='right'>
								<i></i>
							</div>
						</li>
						<li>
							<div class='left'>
								<img src='${pageContext.request.contextPath}/images/frontend/index/item32.png'>
								<p>线下开发策略</p>
							</div>
							<div class='right'>
								<i></i>
							</div>
						</li>
						<li>
							<div class='left'>
								<img src='${pageContext.request.contextPath}/images/frontend/index/item33.png'>
								<p>注册登录用户</p>
							</div>
							<div class='right'>
								<i></i>
							</div>
						</li>
						<li>
							<div class='left'>
								<img src='${pageContext.request.contextPath}/images/frontend/index/item34.png'>
								<p>管理策略</p>
							</div>
							<div class='right'>
								<i></i>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class='item4 item'>
			<p>如有疑问，请联系我们</p>
			<div>
				<img src='${pageContext.request.contextPath}/images/frontend/index/contact.png'>
			</div>
			
		</div>
	</div>
	<%@ include file="user/popbox.jsp"%> 
	<%@ include file="footer.jsp"%> 
</body>
</html>