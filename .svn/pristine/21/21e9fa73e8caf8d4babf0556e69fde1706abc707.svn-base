<!-- 
// +----------------------------------------------------------------------
// | Author: liangsir 2017-07-12
// +---------------------------------------------------------------------- -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>有据，专注量化投资</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
<%@ include file="commonjsp/jsCssfile.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/plugins/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/scoreEcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/frontend/strategydetail/scoreStrategyDetail.js"></script>
</head>
<body>
<%@ include file="commonjsp/scoreHeader.jsp"%>    
<div class="strategyScoreMain">
   <div class="baseintro baseintro0">
       <div class="ul">
          <dl class="li timebox">
            <dt>类型</dt>
            <dd>
             <span id="strategyKind"></span>
            </dd>
          </dl>
          <dl class="li timebox">
            <dt>模拟时间</dt>
            <dd><span class="startTime"></span> 至 <span class="endTime"></span></dd>
          </dl>
          <dl class="li timebox">
            <dt>运行天数</dt>
            <dd><span class="runday"></span></dd>
          </dl>
          <dl class="li">
            <dt>初始资金</dt>
            <dd><span>¥</span><span class="iniFunding"></span></dd>
          </dl>
          <dl class="li">
            <dt>策略频率</dt>
            <dd><span class="frequency"></span></dd>
          </dl>
          <dl class="li">
            <dt>回测状态</dt>
            <dd class="testStatus firmTestStatus">
               <span class="testing">正在回测中</span>
               <span class="tested">已完成</span>
            </dd>
          </dl>
          <dl class="li end">
            <dt>评分</dt>
            <dd><span class="fs">92.38</span></dd>
          </dl>
        </div>

        <ul class="parameter" id="paramlist"></ul>
		<script id="param-script" type="text/html">	
        {{each paramList as data i}}
        <li><span class="key">{{data.paramName}}</span>
          <span class="val">{{data.paramValue}}</span>
        </li>
        {{/each}}        
	    </script>
     </div>

     <div class="baseintro baseintro1">
       <div class="ul">
          <dl class="li">
            <dt>策略收益</dt>
            <dd><span class="accumulatedReturn"></span></dd>
          </dl>
          <dl class="li">
            <dt>策略年化收益</dt>
            <dd><span class="yield"></span></dd>
          </dl>
          <dl class="li">
            <dt>基准收益</dt>
            <dd><span class="benchmark"></span></dd>
          </dl>
          <dl class="li">
            <dt>阿尔法</dt>
            <dd><span class="alpha"></span></dd>
          </dl>
          <dl class="li">
            <dt>贝塔</dt>
            <dd><span class="beta"></span></dd>
          </dl>
          <dl class="li">
            <dt>夏普比率</dt>
            <dd><span class="shapratio"></span></dd>
          </dl>
          <dl class="li">
            <dt>收益波动率</dt>
            <dd><span class="returnVolatlity"></span></dd>
          </dl>
          <dl class="li">
            <dt>最大回撤</dt>
            <dd><span class="maxRetrace"></span></dd>
          </dl>
        </div>
     </div>


     <div class="detail-box echartsmap echartsmapscore">
        <dl class="title-table">
          <dt>得分走势</dt>
          <dd>
            <div class="detail-time detail-time-echartsmap">
             <div class="comm_input_box">
              <div class="input_one"><input class="text isclear" id="start3" readonly="readonly" placeholder="请选择开始时间" type="text"></div>
              <div class="hr">───</div>
              <div class="input_one"><input class="text isclear" id="end3" readonly="readonly" placeholder="请选择结束时间" type="text"></div>
             </div>
            </div>
          </dd>
        </dl>
        <div class="piczhexian scoreEcharts">
          <div id="main"></div>
        </div>
    </div>

   <div class="detail-box" id="position-content"></div>
   <div class="detail-box" id="warehouse-content"></div>

   <div class="detail-box radar">
      <dl class="title-table">
        <dt>月度收益归因分析</dt>
        <dd>
          <span id="radartimes" time=""></span>
          <a href="javascript:void(0);" onclick="webFun.radarTime(1)" class="s">查看上个月</a>
          <a href="javascript:void(0);" onclick="webFun.radarTime(2)" class="x">查看下个月</a>
        </dd>
      </dl>
      <div class="piczhexian radarbox">
         <div id="radarleft"></div>
         <div id="radarright"></div>
      </div>
   </div>
</div>    
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="position-script" type="text/html">	
{{each   as data i}}  
<dl class="title-table">
    <dt>我的持仓</dt>
    <dd>
      <span>总资产：<font>¥{{formatNumber(list.totalAssets,2)}}</font></span>
      <span>可用金额：<font>¥{{formatNumber(list.usableAssets)}}</font></span>
      <span>当前仓位：<font>{{numbersFmt('1','2',list.nowSpace)}}</font></span>
    </dd>
  </dl>
<div class="download"><a onclick="web.downExcel(7)"><i class="i"></i>导出表格</a></div>
<div class="tablebox">
 <table class="table">
     <thead>
       <tr>
         <th class="comm" width="110">股票名称</th>
         <th class="comm" width="110">现价(元)</th>
         <th class="comm" width="110">成交均价(元)</th>
         <th class="comm" width="110">持仓数量(股) </th>
         <th class="comm" width="110">收益(元)</th>
       </tr> 
      </thead>  
      <tbody>
      {{if list['dpList'].length>0}}
       {{each list['dpList'] as datas i}}
	   <tr>
	  	<td>{{datas.stockName}}{{datas.code}}</td>
        <td>{{formatNumber(datas.nowPrice)}}</td>
	  	<td>{{formatNumber(datas.tranPrice)}}</td>
        <td>{{numFmt1(datas.positionNum)}}</td>
	  	<td>{{formatNumber(datas.profit)}}</td>
	   </tr>
       {{/each}}
      {{else}}
       <tr>
         <td colspan="100">暂无数据</td>
       </tr>
      {{/if}}
	  </tbody>
   </table>
</div>
{{/each}}
</script>

<script id="warehouse-script" type="text/html">	
{{each   as data i}}  
<dl class="title-table">
    <dt>最新调仓</dt>
    <dd>
      <span>2017-05-08</span>
    </dd>
  </dl>
<div class="download"><a onclick="web.downExcel(8)"><i class="i"></i>导出表格</a></div>
<div class="tablebox">
 <table class="table">
     <thead>
       <tr>
         <th class="comm" width="110">股票名称</th>
         <th class="comm" width="110">时间</th>
         <th class="comm" width="110">买卖方向</th>
         <th class="comm" width="110">成交均价(元)</th>
         <th class="comm" width="110">成交仓位(元) </th>
         <th class="comm" width="110">仓位变化(元)</th>
       </tr> 
      </thead>  
      <tbody>
      {{if listd['list'].length>0}}
       {{each listd['list'] as datas i}}
	   <tr>
	  	<td>{{datas.stockName}}{{datas.code}}</td>
        <td>{{datas.dealTime}}</td>
	  	<td>
          {{if datas.applayType==1}}<font style="color:red;">买入</font>{{/if}}
          {{if datas.applayType==2}}<font style="color:green;">卖出</font>{{/if}}
        </td>
        <td>{{formatNumber(datas.dealPrice)}}</td>
        <td>
            {{if datas.applayType==1}}{{numbersFmt('1','2',datas.afMixPosition)}}{{/if}}
            {{if datas.applayType==2}}-{{numbersFmt('1','2',datas.afMixPosition)}}{{/if}}
        </td>
	  	<td>
             {{if datas.applayType==1}}<font style="color:{{redOrgreen2(datas.bfMixPosition,datas.afMixPosition)}};">{{numbersFmt('1','2',datas.bfMixPosition-datas.afMixPosition)}}->{{numbersFmt('1','2',datas.bfMixPosition)}}</font>{{/if}}
             {{if datas.applayType==2}}<font style="color:{{redOrgreen2(datas.bfMixPosition,datas.afMixPosition)}};">{{numbersFmt('1','2',datas.bfMixPosition-(-datas.afMixPosition))}}->{{numbersFmt('1','2',datas.bfMixPosition)}}</font>{{/if}}


                
        </td>
	   </tr>
       {{/each}}
      {{else}}
       <tr>
         <td colspan="100">暂无数据</td>
       </tr>
      {{/if}}
	  </tbody>
   </table>
</div>
{{/each}}
</script>


</body>
</html>