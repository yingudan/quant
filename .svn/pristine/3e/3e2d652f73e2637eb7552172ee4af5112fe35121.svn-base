<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="detail-box">
        <h2 class="detail-title">我的交易</h2>
        <div class="detail-time detail-time-echartsmap">
           <div class="comm_input_box">
           <div class="input_one"><input class="text isclear" id="start" readonly="readonly" placeholder="请选择开始时间" type="text"></div>
           <div class="hr">───</div>
           <div class="input_one"><input class="text isclear" id="end" readonly="readonly" placeholder="请选择结束时间" type="text"></div>
          </div>
        </div>
        <div class="download"><a onclick="web.downExcel(5)"><i class="i"></i>导出表格</a></div>
        <div id="deal-content"></div> 
    </div>
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="deal-script" type="text/html">	
{{each records as data i}}
<table class="table morearray">
  <thead>
	<tr>
	  <th class="comm text-center" width="100">日期</th>
      <th class="comm text-center" width="120">成交时间</th>
	  <th class="comm" width="140">股票名称</th>
      <th class="comm" width="120">买卖方向</th>
	  <th class="comm" width="120">成交均价(元)</th>
	  <th class="comm" width="120">成交数量(股)</th>
      <th class="comm" width="120">成交金额(元)</th>
      <th class="comm" width="120">交易费用(元)</th>
	</tr>	
   </thead>
   <tbody>  
   {{each data['ttrList'] as datas j}}
   	 <tr>
   	 	<td class="comm text-center" width="100">{{if j==0}}{{getLocalTime(data.theTime/1000,'d')}}{{/if}}</td>
   	 	<td class="comm text-center" width="120">{{datas.applayTime}}</td>
   	 	<td class="comm" width="140">{{datas.stockName}}{{datas.stockCode}}</td>
   	 	<td width="110">
          {{if datas.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
          {{if datas.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
        </td>
   	 	<td class="comm" width="120">{{formatNumber(datas.aveTranPrice)}}</td>
   	 	<td class="comm" width="120">{{datas.tranNum}}</td>
        <td class="comm" width="120">{{formatNumber(datas.amount)}}</td>
        <td class="comm" width="120">{{formatNumber(datas.cost)}}</td>
   	 </tr>
    {{/each}}
 	 <tr>
		<td colspan="100">
		  <div class="tr">
		  <em>统计：<strong>{{data.sum}}</strong>次交易</em>
		  <em>买入（<strong class="red">¥：{{formatNumber(data.buying)}}</strong>）</em>
		  <em>卖出（<strong class="green">¥：{{formatNumber(data.sale)}}</strong>）</em>
		  </div>
		</td>  
     </tr>
   </tbody>
</table>
{{/each}}
</script>