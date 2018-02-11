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
<div class="download"><a onclick="web.downExcel(3)"><i class="i"></i>导出表格</a></div>
        <table class="table">
            <thead>
              <tr>
                <th class="comm text-center" width="110">股票名称</th>
                <th class="comm" width="190">成交时间</th>
                <th class="comm" width="110">买卖方向</th>
                <th class="comm" width="110">成交均价（元）</th>
                <th class="comm" width="110">成交数量（股）</th>
                <th class="comm" width="110">成交金额（元）</th>
                <th class="comm" width="110">交易费用（元）</th>
              </tr> 
             </thead>
             <tbody id="deal-content"></tbody>
          </table>
    </div>      
<!-- ++++++++++++++++++模拟绑定数据+++++++++++++++++++++ -->
<script id="deal-script" type="text/html">	
{{each records as data i}}
<tr>
 <td>{{data.stockName}}{{data.stockCode}}</td>
 <td>{{numFmt1(data.applayDate)}} {{numFmt1(data.applayTime)}}</td>
 <td>
   {{if data.tradingDirection==1}}<font style="color:red;">买入</font>{{/if}}
   {{if data.tradingDirection==2}}<font style="color:green;">卖出</font>{{/if}}
 </td>
 <td>{{numFmt1(data.aveTranPrice)}}</td>
 <td>{{numFmt1(data.tranNum)}}</td>
<td>{{formatNumber(data.amount)}}</td>
<td>{{formatNumber(data.cost)}}</td>
</tr>
{{/each}}
</script>

    