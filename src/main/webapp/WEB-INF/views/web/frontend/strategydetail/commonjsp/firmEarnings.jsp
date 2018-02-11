<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="baseintro baseintro1">
       <div class="ul">
          <dl class="li">
            <dt>模拟收益</dt>
            <dd><span class="accumulatedReturn"></span></dd>
          </dl>
          <dl class="li">
            <dt>模拟年化收益</dt>
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
  
  <div class="detail-box echartsmap firmechartsmap" style="padding-right:10px;">
        <dl class="title-table">
          <dt>
           <div class="titles-h">收益曲线</div>
           <div class="qiehuanpic" type="1" onclick="webFun.dangquan(this)">查看全部策略收益&gt;&gt;</div>
          </dt>
        </dl>
        <div class="shuaixuan">
          <div class="uew-radio">
            <em data="1" onclick="webFun.radios(this)" class="on">累计收益</em>
            <em data="2" onclick="webFun.radios(this)">相对收益</em>
            <em data="3" onclick="webFun.radios(this)">对数收益</em>
          </div>
          <div class="detail-time detail-time-echartsmap">
             <div class="comm_input_box">
             <div class="input_one"><input class="text isclear" id="start2" readonly="readonly" placeholder="请选择开始时间" type="text"></div>
             <div class="hr">───</div>
             <div class="input_one"><input class="text isclear" id="end2" readonly="readonly" placeholder="请选择结束时间" type="text"></div>
            </div>
          </div>
        </div>
        <div class="piczhexian firmEcharts">
          <div id="main"></div>
          <div class="lefttitlless">当日策略收益</div>
        </div>
    </div>
    
  
  <div class="firmTwoList">
    <div class="detail-box firmTwoList-left" id="chichang-content"></div>
    <div class="detail-box  firmTwoList-right" id="dangrijiaoyi-content"></div>
  </div>
    
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="chicang-script" type="text/html">	
{{each  as data i}}  
<dl class="title-table">
    <dt>我的持仓</dt>
    <dd>
      <span>总资产：<font>¥{{formatNumber(data.totalAssets,2)}}</font></span>
      <span>可用金额：<font>¥{{formatNumber(data.usableAssets)}}</font></span>
      <span>当前仓位：<font>{{numbersFmt('1','2',data.nowSpace)}}</font></span>
    </dd>
  </dl>
<div class="download"><a onclick="web.downExcel(1)"><i class="i"></i>导出表格</a></div>
 <table class="table">
     <thead>
       <tr>
         <th class="comm" width="110">股票名称</th>
         <th class="comm" width="110">现价(元)</th>
         <th class="comm" width="110">成交均价(元)</th>
         <th class="comm" width="110">持仓数量(股)</th>
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
{{/each}}
</script>

<script id="dangrijiaoyi-script" type="text/html">	
{{each  as data i}}  
<dl class="title-table">
    <dt>我的当日交易</dt>
    <dd>
       <span>买进：<font>¥{{formatNumber(data.buying)}}</font></span>
       <span>卖出：<font>¥{{formatNumber(data.sale)}}</font></span>
       <span style="color:#008DFF;">交易纪录：<font style="color:#008DFF;">{{data.sum}}</font></span>
    </dd>
  </dl>
<div class="download"><a onclick="web.downExcel(2)"><i class="i"></i>导出表格</a></div>
 <table class="table">
    <thead>
	 <tr>
	   <th width="120">股票名称</th>
	   <th>买卖方向</th>
	   <th>成交均价(元)</th>
	   <th>成交时间</th>
	   <th>成交金额(元)</th>
	   <th>交易费用(元)</th>
	 </tr>
	 </thead>
	<tbody>
     {{if list['ttrList'].length>0}}
      {{each list['ttrList'] as datas i}}
	   <tr>
	  	<td>{{datas.stockName}}{{datas.stockCode}}</td>
	  	<td>
           {{if datas.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
           {{if datas.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
        </td>
	  	<td>{{formatNumber(datas.aveTranPrice)}}</td>
	  	<td>{{datas.applayTime}}</td>
	  	<td>{{formatNumber(datas.amount)}}</td>
	  	<td>{{formatNumber(datas.cost)}}</td>
	   </tr>
      {{/each}}
     {{else}}
       <tr><td colspan="100">暂无数据</td></tr>
     {{/if}}
	</tbody>
   </table>
{{/each}}
</script>
  
  
  
  


