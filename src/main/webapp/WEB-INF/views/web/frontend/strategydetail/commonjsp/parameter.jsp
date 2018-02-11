<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<ul class="parameter" id="paramlist"></ul>
<script id="param-script" type="text/html">	
{{each paramList as data i}}
 <li><span class="key">{{data.paramName}}</span>
     <span class="val">{{data.paramValue}}</span>
 </li>
{{/each}}        
</script>





