<!-- 头部 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   <div class="baseintro baseintro0">
       <div class="ul">
          <dl class="li timebox">
            <dt>历史数据区间</dt>
            <dd><span class="startTime"></span> 至 <span class="endTime"></span></dd>
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
               <span class="testing">实盘模拟中</span>
               <span class="tested">已完成</span>
            </dd>
          </dl>
          <dl class="li end">
            <dt>评分</dt>
            <dd><span class="fs"></span><a href="" id="strategyScoreDetails">查看详情</a></dd>
          </dl>
        </div>

         <%@ include file="parameter.jsp"%>
     </div>


