<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="detail-box">
    <h2 class="detail-title" id="shouyifengxian"></h2>
    <table class="table">
    <thead>
     <tr>
       <th width="20%">日期(年/月)</th>
       <th width="20%">1个月</th>
       <th width="20%">3个月</th>
       <th width="20%">6个月</th>
       <th width="20%">12个月</th>
     </tr><tr>
    </tr>
    </thead>
    <tbody id="riskKpi-content"></tbody>
 </table>
</div>
<script id="riskKpi-script" type="text/html">	
{{each data as datas i}}
<tr><td colspan="100" class="textleft ths">
  {{if i=='alphaList'}}阿尔法{{/if}}
  {{if i=='betaList'}}贝塔{{/if}}
  {{if i=='infomationratioList'}}信息比率{{/if}}
  {{if i=='maxRetraceList'}}最大回撤{{/if}}
  {{if i=='returnVolatlityList'}}收益波动率{{/if}}
  {{if i=='shapratioList'}}夏普比率{{/if}}
  {{if i=='sotiroratioList'}}索提洛比率{{/if}}
  <em class="em1"></em><em class="em"></em></td></tr>

{{if data[i]==null}}
<tr><td colspan='100'>暂无数据</td></tr>
{{else}}
{{if data[i].length>0}}
{{each data[i] as datass}}
<tr>
 <td>{{datass.time}}</td>
 <td>{{na(datass.oneMonth)}}</td>
 <td>{{na(datass.threeMonth)}}</td>
 <td>{{na(datass.sixMonth)}}</td>
 <td>{{na(datass.twelveMonth)}}</td>
</tr>
{{/each}}
{{else}}
<tr><td colspan='100'>暂无数据</td></tr>
{{/if}}
{{/if}}

{{/each}}
</script>




