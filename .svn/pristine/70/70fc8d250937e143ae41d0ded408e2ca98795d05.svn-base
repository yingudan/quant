// +----------------------------------------------------------------------
// | des:实盘模拟 历史回测 
// +----------------------------------------------------------------------
// | 2017-04-20
// +----------------------------------------------------------------------
// | Author: andrew
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
var baseurl=common.sysurl();
//构造类
function HistoryList() {
	this.testId=-1;
	this.strategyId=-1;
	this.type=1;//1.回测收益  2.回测年华收益  3.基准收益  4.阿尔法  5.贝塔  6.夏普比率  7.收益波动率   8.最大回测
	this.orderType=1;//1 按时间排序   2 按收益率排序
	this.order=2;//1 asc  顺序  2  desc 倒序
}
/**
 * DES:tab切换
 * @param object 您选择的DOM
 * @return void
 */
HistoryList.prototype.tabchange=function(obj,type){
	 $(obj).addClass('on');
	 $(obj).siblings().removeClass('on');
	 if(type==1){
		$('.one').show();
		$('.two').hide();
	 }else{
	    $('.one').hide();
		$('.two').show();
	 }
	 
	 historyList.type=type;
	 historyList.getlist();
}
/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
HistoryList.prototype.radios=function(obj){
	 $(obj).parent().parent().find("em").removeClass("on");
	 $(obj).addClass("on");
}
/**
 * 显示更多参数
 * */
HistoryList.prototype.thmore=function(me){
	if($(me).attr('data')=='1'){//收缩状态 ->展开状态
		$(me).attr('data','2');
		$(".tableleft").width($('#list1').width());
		$(me).css('left',$('#list1').width()-34);
		$(me).text('<<');
		if($('#list1').width()>1200){
			$('.tableleft').css('overflow-x','auto');
		}

	}else{//展开状态 ->收缩状态
		$(me).attr('data','1');
		$(".tableleft").width('200px');
		$(me).css('left','167px');
		$(me).text('>>');
		$('.tableleft').css('overflow-x','inherit');

	}
}
/**
 * th筛选
 * */
HistoryList.prototype.thgetlist=function(orderType,me){
	historyList.orderType=orderType;
	if($(me).attr('data')=='2'){
		$(me).parent().siblings().find('i').attr('data','2');
		$(me).attr('data','1');
		historyList.order='2';
	}else{
		$(me).attr('data','2');
		historyList.order='1';
	}
	historyList.getlist();
}
/*获取列表数据*/
HistoryList.prototype.getlist=function(){
	/*需要的参数*/
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
    var strategyId = historyList.strategyId;
    var dataLink = 'pageSize=' + pageSize + '&pageNum=' + pageNum + '&id='+historyList.testId+'&orderType='+historyList.orderType+'&order='+historyList.order;
    //接口
	var url=common.sysurl()+"/firmoffer/findParamGroupList.m";			   
	//ajax
    ajax.tranDataParams(url,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   rs.data.rowNum = (pageNum - 1) * pageSize;
           pageFun(Math.ceil(rs.data.totalRecords/rs.data.pageSize));
           historyList.setlistfun(rs.data);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
           parent.layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	   parent.layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*停止实盘模拟*/
HistoryList.prototype.stopShipan=function(sid,testId){
	parent.layer.confirm("停止此次实盘模拟后，不再进行实盘模拟", {
		 title:"停止实盘模拟"
	}, function(index){
		stopTest('1','1')
	});	
}
/*停止历史回测*/
HistoryList.prototype.stopHi=function(sid,testId){
	parent.layer.confirm("停止此次历史回测后，不再进行历史回测", {
		 title:"停止历史回测"
	}, function(index){
		stopTest('0','2');
	});	
}
/*列表数据*/
HistoryList.prototype.setlistfun=function(msg){
	
	$('#status').removeClass('statusA');
	$('#status').removeClass('statusIng');
	$('#status').removeClass('statusNo');
	$('#status').removeClass('statusReady');
	//状态 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪
	if(msg.strategyStatus=='0'){//历史回测中
		$('#status').addClass('statusA');
		$("#startpan").text('停止实盘模拟');
		$("#startpan").attr('onclick','historyList.stopShipan('+historyList.strategyId+','+common.GetQueryString('testId')+')');
		$("#starthi").hide();
	}else if(msg.strategyStatus=='1'){//实盘模拟中
		$('#status').addClass('statusIng');
		$("#starthi").text('停止历史回测');
		$("#starthi").attr('onclick','historyList.stopHi('+historyList.strategyId+','+common.GetQueryString('testId')+')');
		$("#startpan").hide();
	}else if(msg.strategyStatus=='2'){//未就绪
		$('#status').addClass('statusNo');
		$('.toolbox1 .btns').hide();
		
	}else if(msg.strategyStatus=='3'){//就绪
		$('#status').addClass('statusReady');
		$("#startpan").attr('href',common.sysurl()+'/frontend/simulationPara.do?strategyId='+historyList.strategyId+'&testId='+common.GetQueryString('testId'));
		$("#starthi").attr('href',common.sysurl()+'/frontend/simParaHistory.do?strategyId='+historyList.strategyId+'&testId='+common.GetQueryString('testId'));
	}
	$('.titles').text(msg.strategyName);
	var thtd='';
	if(msg.records.length>0){
		for(var i=0;i<msg.records.length;i++){
			if(msg.records[i].frequency=='1'){
				msg.records[i].frequencytext='每天';
			}else if(msg.records[i].frequency=='0'){
				msg.records[i].frequencytext='TICK';
			}else if(msg.records[i].frequency=='2'){
				msg.records[i].frequencytext='分钟';
			}
			msg.records[i].testTime=common.getLocalTime(msg.records[i].testTime,'s');
			msg.records[i].hisDataStart=common.getLocalTime(msg.records[i].hisDataStart,'d');
			msg.records[i].hisDataEnd=common.getLocalTime(msg.records[i].hisDataEnd,'d');
			msg.records[i].url=common.sysurl()+'/detail/historyTestParamGroup.do?strategyId='+msg.records[i].strategyId;
			//msg.records[i].accumulatedReturn=historyList.numDeal2(msg.records[i].accumulatedReturn*100);
			msg.records[i].accumulatedReturn=common.numbersFmt('1','2',msg.records[i].accumulatedReturn);
			msg.records[i].yield=common.numbersFmt('1','2',msg.records[i].yield);
			//msg.records[i].benchmark=common.numbersFmt('1','2',msg.records[i].benchmark);
			msg.records[i].totalBenchmark=common.numbersFmt('1','2',msg.records[i].totalBenchmark);
			msg.records[i].alpha=common.numbersFmt('2','4',msg.records[i].alpha);
			msg.records[i].beta=common.numbersFmt('2','4',msg.records[i].beta);
			msg.records[i].shapratio=common.numbersFmt('2','4',msg.records[i].shapratio);
			msg.records[i].returnVolatlity=common.numbersFmt('2','4',msg.records[i].returnVolatlity);
			msg.records[i].maxRetrace=common.numbersFmt('1','2',msg.records[i].maxRetrace);
			msg.records[i].strategyId=msg.strategyId;
			
			var itdstr=msg.records[i].paramValueList;
			var iharr=itdstr.split(',');
			thtd+='<tr onclick="historyList.openzl('+msg.strategyId+','+msg.records[i].groupId+')">';
			for(var f=0;f<iharr.length;f++){
				thtd+='<td style="min-width:89px">'+iharr[f]+'</td>';
			}
			thtd+='</tr>';
		}
		$('#list1').html(thtd);
		$("#list").html(template("historyListlation2_script", msg));
		$('#list1').height(msg.records.length*52+'px');
		$('.tablecontainer .tableleft').height((msg.records.length*51+42)+'px');
		$('.tablecontainer .tableleft td').height('24px');
		$(".tablecontainer").height((msg.records.length*51+42)+'px');
		var htmlth='';
		var tdstr=msg.records[0].paramNameList;
		var tharr=tdstr.split(',');
		
		for(var j=0;j<tharr.length;j++){
			htmlth+='<th style="min-width:100px"><span>'+tharr[j]+'</span></th>';
		}
		$('#thlist').find('th').remove();
		$('#thlist').html(htmlth);
		
	}else{
		$("#list").find('tr').remove();
  		var html="<tr><td colspan='8' class='nodata'> 暂无数据</td></tr>";
  		$("#list").append(html);
	}
	
	$("#count").text(msg.totalRows);
	historyList.titleData(msg.testRecord);
	common.resetHeight();//数据加载后重置页脚
}
/*
 * 打开张亮页面
 * */
HistoryList.prototype.openzl=function(strategyId,id){
	//历史：/quant/detail/historyEarnings.do?sidebar=1&testId=5&strategyId=4

	//实盘：/quant/detail/firmEarnings.do?sidebar=1&testId=6&strategyId=4
	 window.location=common.sysurl()+'/web/frontend/strategydetail/hisStrategyDetails.do?form=2&testId='+common.GetQueryString('testId')+'&strategyId='+strategyId+'&groupId='+id;
}
/*页头赋值*/
HistoryList.prototype.titleData=function(data){
	if(data!==null){
		$(".startTime").text(common.getLocalTime(data.hisDataStart,'d'));
		$(".endTime").text(common.getLocalTime(data.hisDataEnd,'d'));
		$('.iniFunding').text(common.formatNumber(data.iniFunding));
		if(data.frequency=='1'){
			data.frequencytext='每天';
		}else if(data.frequency=='0'){
			data.frequencytext='TICK';
		}else if(data.frequency=='2'){
			data.frequencytext='分钟';
		}
		$('.frequency').text(data.frequencytext);
		
		$('.huiceyongshi').text(historyList.toMin(data.duration));
		var status='';
		if(data.testStatus=='0'){
			
		}else if(data.testStatus=='1'){
			
		}else if(data.testStatus=='2'){
			
		}else if(data.testStatus=='3'){
			
		}
	}
	
}
/*
*保留小数点前四位后四位小数
*/
HistoryList.prototype.numDeal2=function(num){
	var data=num.toString();
	var leftdata=data.substr(0,data.indexOf('.'));
	var left='';
	if(leftdata.length>=4){
		left=data.substr(data.indexOf(".")-4,4);
	}else{
		left=leftdata;
	}
	var right=data.substr(data.indexOf(".")+1,2);
	return left+'.'+right+'%';
}
/*初始化函数变量*/
HistoryList.prototype.Init=function(){
	var type=common.GetQueryString('type');//历史回测还是实盘模拟
	var id=common.GetQueryString('testId');
	var strategyId=common.GetQueryString('strategyId');
	historyList.strategyId=strategyId;
	historyList.type=type;
	if(type=='1'){
		$('#shipin').addClass('on');
		$('#his').removeClass('on');
	}else{
		$('#shipin').removeClass('on');
		$('#his').addClass('on');
	}
	historyList.testId=id;
	
}
/**
 * 时间秒数格式化
 * @param value 秒数
 * @returns {*} 格式化后的时分秒
 */
HistoryList.prototype.toMin=function(value){
	var theTime = parseInt(value);// 秒
	var theTime1 = 0;// 分
	var theTime2 = 0;// 小时
	// alert(theTime);
	if(theTime > 60) {
	theTime1 = parseInt(theTime/60);
	theTime = parseInt(theTime%60);
	// alert(theTime1+"-"+theTime);
	if(theTime1 > 60) {
	theTime2 = parseInt(theTime1/60);
	theTime1 = parseInt(theTime1%60);
	}
	}
	var result = ""+parseInt(theTime)+"秒";
	if(theTime1 > 0) {
	result = ""+parseInt(theTime1)+"分"+result;
	}
	if(theTime2 > 0) {
	result = ""+parseInt(theTime2)+"小时"+result;
	}
	return result; 
};
//实例化FundManagejs类
var historyList= new HistoryList();
$(function(){
	historyList.Init();
	historyList.getlist();
	$('#shipin').attr('href',common.sysurl()+'/frontend/simulation.do?testId='+historyList.testId+'&strategyId='+common.GetQueryString('strategyId')+'&type=1');
	$('#his').attr('href',common.sysurl()+'/frontend/simulation.do?testId='+historyList.testId+'&strategyId='+common.GetQueryString('strategyId')+'&type=2');
	
});
/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

//分页函数
function pageFun(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
};
//分页函数
function setPageNum(thiz, pageType) {
	
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  if(str.isblank(pno)){
		  $("#curPage").val(1);
	  }else{
		  $("#curPage").val(pno);
	  }
	  historyList.getlist();
	 
	});
};
/*停止测试*/
function stopTest(type,num){
	//接口
	var link=common.sysurl()+"/firmoffer/stopTheOffer.m";
	//需要传递的参数
	var testId=common.GetQueryString("testId");//单条回测记录id
	var strategyId=common.GetQueryString("strategyId");//策略id
	var dataLink="testId="+testId+"&type="+type;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
        	 parent.layer.alert('策略停止成功', {
      	         icon: 1,
      	       },function(index){
      	    	 if(num=='1'){
             		location.href=common.sysurl()+"/frontend/simulation.do?type=1&strategyId="+strategyId+'&testId='+testId;
      	    	 }else{
             		location.href=common.sysurl()+"/frontend/simulation.do?type=2&strategyId="+strategyId+'&testId='+testId;
      	    	 };
      	    	 parent.layer.close(index);
      	       });
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
           parent.layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       parent.layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}