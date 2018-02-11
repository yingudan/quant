<!-- 实盘模拟参数 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="parameters">
	<ul class="parameters-left" style="width:1170px;" id="paramlist"></ul>
</div>
<script id="param-script" type="text/html">	
{{each paramList as data i}}
  <li>{{data.paramName}}：{{data.paramValue}}</li>
{{/each}}
</script>