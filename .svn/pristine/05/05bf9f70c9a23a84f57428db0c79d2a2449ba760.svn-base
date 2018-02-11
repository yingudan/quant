<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="detail-box">
        <h2 class="detail-title">每日持仓</h2>
        <div class="detail-time detail-time-echartsmap">
           <div class="comm_input_box">
           <div class="input_one"><input class="text isclear" id="start1" readonly="readonly" placeholder="请选择开始时间" type="text"></div>
           <div class="hr">───</div>
           <div class="input_one"><input class="text isclear" id="end1" readonly="readonly" placeholder="请选择结束时间" type="text"></div>
          </div>
        </div>
        <div class="download"><a onclick="web.downExcel(4)"><i class="i"></i>导出表格</a></div>
        <div id="position-content"></div> 
    </div>
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<!--我的持仓 -->
<script id="position-script" type="text/html">	
{{each list as data i}}
<table class="table morearray">
  {{if i==0}}
  <thead>
	<tr>
	  <th class="comm text-center" width="110">日期</th>
	  <th class="comm" width="190">股票名称</th>
	  <th class="comm" width="110">收盘价</th>
	  <th class="comm" width="110">持仓数量(股)</th>
      <th class="comm" width="110">成交价(元)</th>
	  <th class="comm" width="110">持仓金额(元)</th>
	  <th class="comm" width="110">收益(元)</th>
	</tr>	
   </thead>
   {{/if}}
   <tbody>

   {{each data['dpList'] as datas j}}
   	 <tr>
   	 	<td class="comm text-center" width="110">{{if j==0}}{{getLocalTime(data.time/1000,'d')}}{{/if}}</td>
   	 	<td class="comm" width="190">{{datas.stockName}}{{datas.code}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.closingPrice)}}</td>
   	 	<td class="{{redOrgreen(data.positionNum)}}"  width="110">{{datas.positionNum}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.tranPrice)}}</td>
   	 	<td class="comm" width="110">{{formatNumber(datas.positionPrice)}}</td>
        <td class="comm" width="110">{{formatNumber(datas.profit)}}</td>
   	 </tr>
    {{/each}}
   	 <tr>
   	 	<td colspan="100">
   	 	  <div class="tr">
   	 	   <em>统计：<strong>{{data.transNum}}</strong>次交易</em>
   	 	   <em>总资产（<strong class="red">¥：{{formatNumber(data.totalAssets)}}</strong>）</em>
   	 	   <em>持仓资产（<strong class="{{redOrgreen(data.positionAssets)}}">¥：{{formatNumber(data.positionAssets)}}</strong>）</em>
   	 	   <em>可用资产（<strong>¥：{{formatNumber(data.usableAssets)}}</strong>）</em>
   	 	   <em>总收益（<strong class="{{redOrgreen(data.totalRevenue)}}">¥：{{formatNumber(data.totalRevenue)}}</strong>）</em>
   	 	   <em>总交易费用（<strong>¥：{{formatNumber(data.allCost)}}</strong>）</em>
   	 	  </div>
   	 	</td>
   	 </tr>
   </tbody>
</table>
{{/each}}
</script>