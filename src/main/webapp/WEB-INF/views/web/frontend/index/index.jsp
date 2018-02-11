<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>有据，专注量化投资</title>
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/reset.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common1.0.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/frontend/index/index.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/animate.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/jquery.cookie.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/wow/wow.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/common.js"></script>
	<script type="text/javascript">
	/*$(function(){
		var wow = new WOW({
		    boxClass: 'wow',
		    animateClass: 'animated',
		    offset: 0,
		    mobile: true,
		    live: true
		});
		wow.init();
	})*/
	$(function(){
		var winEl = $(window);

		/*滚动监听*/
		winEl.scroll(function(){
			/*animate*/
			$('.scrollanimate').each(function(){
				var self = $(this);

				if(!self.data('initanimate') && (winEl.scrollTop()+winEl.height() >= self.offset().top)){
					self.addClass('animated '+self.data('animate')).data('initanimate',true);
				}
			});
		}).scroll();
	});
	</script>
</head>
<body>
<div class='hiddenid' id='${login_id}' uname='${login_user.userName}'></div>
<div id="sysurl" style="display:none;">${pageContext.request.contextPath}</div>
	<div class='header '  data='${login_id}'>
		<div class='topmenu'>
			<div class='logo'>
				<a href='${pageContext.request.contextPath}/frontend/index.do'>
					<i class='logoi'></i>
				</a>
			</div>
			<div class='menu'>
				<ul class='mindex'>
					<li><a class='select_btn index' href='${pageContext.request.contextPath}/frontend/index.do'>首页</a></li>
					<li class='menudown'>
						<a class='has_select indexbtn' href='javascript:void(0)'>产品服务</a>
						<div class='submenu'>
							<a  href='${web_dataoffer}/frontend/index.do' target='_blank'>数据工厂</a>
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
							<li><a href='${pageContext.request.contextPath}/frontend/pcenter.do' >个人中心</a></li>
							<li><a href='javascript:void(0)' onclick='common.logout()'>退出登录</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class='main'>
		<div class='item bg'>
			<div class='itemw '>
				<div class='animated fadeInUp'>
					<p class='t1'>
						科技改变投资方式
					</p>
					<P class='subt1 '>专注量化投资，助力客户财富增长</P>
				</div>
			</div>
		</div>
		<div class='item one item1'>
			<div class='itemn  '>
				<div class='left letter scrollanimate animated ' data-animate='fadeInUp'>
					<a class='ptitle' href='${web_dataoffer}/frontend/index.do' target='_blank'>数据工厂</a>
					<p>标准化+定制化 大数据挖掘技术 智能化分析工具 节省数据获取与整理时间</p>
					<div>在量化投资时代背景下，市场各类参与者因投资思路不同，对于标准化数据仓库有迫切需求，同时对此之外的个性化数据需求更为突出。广大量化投资者往往需要投入大量的人力和时间来搭建数据系统，对接上游金融市场数据，整理收集个性化数据与指标。这是很高的数据壁垒。数据工厂可以提供精准、完善的策略数据源，日行情数据，历史数据，回放支持，各种指标因子等标准化数据库；同时提供包含数据采集、智能化整理+财务专家清洗模式、数据可视化分析、数据平台搭建等个性化数据服务，帮助企业构建贴合自身业务的数据系统，帮助沉淀数据资产，轻松挖掘大数据价值，更好服务客户的投资需求。</div>
				</div>
				<div class='right img scrollanimate animated '  data-animate='fadeInRight'>
					<img src="${pageContext.request.contextPath}/images/frontend/index/data.png">
				</div>
			</div>
			
		</div>
		<div class='item one even item2'>
			<div class='itemn'>
				<div class='left img scrollanimate animated ' data-animate='fadeInRight'>
					<img src="${pageContext.request.contextPath}/images/frontend/index/server.png">
				</div>
				<div class='right letter scrollanimate animated ' data-animate='fadeInUp'>
					<a class='ptitle' href='${pageContext.request.contextPath}/frontend/strategy.do' target='_blank'>研究工作平台</a>
					<p>更前沿的研究工具 更方便的接入到使用者个性化的策略研究体系</p>
					<div>科技更新迭代不断加速，如何让投资者享受到强大计算能力和先进研究方法工具是每一家金融科技公司不断为之努力的目标。有据在高性能云计算服务器集群的基础上，提供标准的平台接入接口，使用大数据挖掘引擎、集成人工智能模块、机器学习算法辅助决策等一系列前沿技术，注重引入互联网用户体验思维，方便策略持续运行和监控；同时在系统设计上做到数据接口加密、资源独立，在平台运行数据更高安全性的前提下保持更好的性能。</div>
				</div>
			</div>
			
		</div>
		<div class='item one item3'>
			<div class='itemn'>
				<div class='left letter scrollanimate  animated ' data-animate='fadeInUp'>
					<a class='ptitle'  href='${pageContext.request.contextPath}/frontend/company.do' target='_blank'>定制化开发服务</a>
					<p>低成本 高效 快速实施全套本地化系统</p>
					<div>如今，低成本建设个性化投资研究、交易管理系统越来越受到投资机构及中小量化投资团队的迫切需求。但这样的系统的构建往往需要深挖客户具体需求，对接、整合上下游资源，更需具备深厚的底层技术支撑。有据以多年的行业经验积累，为客户提供一应俱全的数据采集系统，采后数据管理系统，分析研究、回溯验证、模拟交易系统，下单模块系统，风控系统，底层计算和网络通讯及其安全基础设施，为客户高效实施部署低成本的高质量投研管理系统。 </div>
				</div>
				<div class='right img scrollanimate animated ' data-animate='fadeInRight'>
					<img src="${pageContext.request.contextPath}/images/frontend/index/place.png">
				</div>
			</div>
			
		</div>
		<div class='item one even item4'>
			<div class='itemn'>
				<div class='left img scrollanimate animated ' data-animate='fadeInRight'>
					<img src="${pageContext.request.contextPath}/images/frontend/index/good.png">
				</div>
				<div class='right letter scrollanimate animated ' data-animate='fadeInUp'>
					<a class='ptitle'>策略商城</a>
					<p>统一化评判标准 简单化跟进流程 实现更简单的投资方式</p>
					<div>量化策略市场鱼目混杂，投资者水平参差不齐，市场缺乏标准化的评判标准，普通投资者更是缺乏专业的行业知识，从而加深了抓住宝贵投资机会的难度。有据策略商城旨在提供科学化、专业化、统一化的评判标准和体系，在保障投资者和策略开发者双方权益的前提下，将策略高手的选股技巧、期货操盘手的交易手法、时下热点的主题以及量化投资，组合衍生出“策略”带给普通投资者，让双方能够进行针对策略的实时展示、宣传、沟通，以及后续订阅、咨询等商城服务。</div>
				</div>
			</div>
		</div>
		<div class='item advantage scrollanimate' data-delay='300'>
			<div class='itemn '>
				<p class='ptitle  wow bounceInLeft'>我们的优势</p>
				<ul class=''>
					<li class="first">
						<img class='animated scrollanimate ' data-animate='fadeInDown' src="${pageContext.request.contextPath}/images/frontend/index/icon1.png">
						<p class='title scrollanimate animated 'data-animate='fadeInUp' >安全</p>
						<p class='content scrollanimate animated ' data-animate='fadeInUp' >
								<span>*</span>API调取数据接口<br>
								<span>*</span>策略本地运行<br>
								<span>*</span>网络传输加密
						</p>
					</li>
					<li class='second' data-delay="0.3s">
						<img class='animated scrollanimate' data-animate='fadeInDown'  data-delay="0.3s" src="${pageContext.request.contextPath}/images/frontend/index/icon2.png">
						<p class='title scrollanimate animated ' data-animate='fadeInUp'  data-delay="0.3s">开放</p>
						<p class='content scrollanimate animated '  data-animate='fadeInUp'  data-delay="0.3s">
								<span>*</span>灵活的接入第三方数据源<br>
								<span>*</span>支持C++/JAVA/Python/Matlab   等语言
						</p>
					</li>
					<li class='third '>
						<img class='animated scrollanimate'  data-animate='fadeInDown'  data-delay="0.6s" src="${pageContext.request.contextPath}/images/frontend/index/icon3.png">
						<p class='title scrollanimate animated '  data-animate='fadeInUp'  data-delay="0.6s">先进</p>
						<p class='content scrollanimate animated'   data-animate='fadeInUp'  data-delay="0.6s">

							<span>*</span>人工智能<br>

							<span>*</span>机器学习算法<br>

							<span>*</span>辅助决策
						</p>
					</li>
					<li class='fourth'>
						<img class='animated scrollanimate'  data-animate='fadeInDown' data-delay="0.9s" src="${pageContext.request.contextPath}/images/frontend/index/icon4.png">
						<p class='title scrollanimate animated '  data-animate='fadeInUp' data-delay="0.9s">速度</p>
						<p class='content scrollanimate animated '  data-animate='fadeInUp' data-delay="0.9s">
							<span>*</span>支持算法优化
							<br>
							<span>*</span>支持分布式计算架构
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%> 	
	</body>
</html>