// +----------------------------------------------------------------------
// | des:历史回测详情js
// +----------------------------------------------------------------------
// | 2017-04-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
//初始化执行
$(function(){
	$("#isReal").val("1");
	webFun.readData();
});
//构造类
function WebFun() {}

/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
WebFun.prototype.radios=function(obj){
	 $(obj).parent().parent().find("em").removeClass("on");
	 $(obj).addClass("on");
	 getBase();
}


/**
 * DES:单条记录集的状态
 * @param 模拟单次记录模拟状态，测试状态 0.等待中 1. 测试中 2.已完成3.失败
 * @return void
 */
WebFun.prototype.testStatus=function(status){	
	if(status==0||status==1){
		$(".testing").show();	
		$(".progress").show();
		$(".parameters-right").show();
	}else{
		$(".tested").show();
	    $(".parameters-right").remove()
	}	
}


/**
 * DES:动态调用方法读取数据的
 * @param sidebar 1收益概览，2交易详情，3每日持仓，4策略收益,5风险指标
 * @return void
 */
WebFun.prototype.readData=function(){
	getBase();
	var celueStatus=$("#celueStatus").val();
	var groupId=common.GetQueryString("groupId");//组id
	setInterval(function(){
       getBase(1);
   },4000);
	
}



/**
 * DES:跳转到下一组
 * @return void
 */
WebFun.prototype.yesdownCluster=function(){
	layer.confirm("跳转到下一次，上一组参数将不保存！", {
		 title:"提示"
	}, function(index){
		layer.close(index);
		downCluster();
	});	
}



//实例化firmDetail.js类
var webFun= new WebFun();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

/*读取历史回测基本信息*/
function getBase(dotype){
	//接口
	var link=common.sysurl()+"/firmoffer/findHisTStrategyDto.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	detailcomm.statusPages(rs.data.status);
        	baseToHtml(rs.data,dotype);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}

/*非图表数据-收益概览*/
function getEarnings(){
	//接口
	var link=common.sysurl()+"/firmoffer/findHisIncomeSituation.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	if(str.isblank(startTime)&&str.isblank(endTime)){
		var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId;
	}else{
		var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;
	}
	
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	earningsToHtml(rs.data);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}


/*图表数据-收益*/
function getEchartShouyiData(status){
	//接口
	var link=common.sysurl()+"/firmoffer/findTMinIncomeMap.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var uew=$(".uew-radio .on").attr("data");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	var isReal=$("#isReal").val();//是否是累加
	var status=status;//当前记录状态
	if(status=="2"){
		var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;	
	}else{
	    var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime;
	}

    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
    	//times时间  accumulated累计收益 benchmark基准收益  relative相对收益  logarithmic对数收益
        if(rs.code==0){
        	var obj=rs.data;
        	var ecData=new Object();
        	if(str.isblank(obj)){
        		ecData["times"]=['0'];
        		ecData['accumulated']=['0'];
        		ecData['benchmark']=['0'];
        		ecData['relative']=['0'];
        		ecData['logarithmic']=['0'];
        	}else{
        		//获取最后一条数据的时间
        		$("#shouyitime").val(obj[obj.length-1]["createTime"]);
        		$("#isReal").val(parseInt($("#isReal").val())+1);
        		if($("#isReal").val()>2){
        		  var isRealDo=true;
        		}else{
        		  var isRealDo=false;	
        		}
        		//格式化数据
        		var arrT=new Array();
        		var arrA=new Array();
        		var arrA1=new Array();
        		var arrB=new Array();
        		var arrB1=new Array();
        		var arrR=new Array();
        		var arrR1=new Array();
        		var arrL=new Array();
        		var arrL1=new Array();
        		for(var i=0;i<obj.length;i++){
        			arrA1=[];
        			arrB1=[];
        			arrR1=[];
        			arrL1=[];
        			arrT.push(common.getLocalTime(obj[i]["createTime"]/1000,'d'));
        			arrA1['0']=i;
        			arrA1['1']=detailcomm.numbersFmt('2','2',obj[i]["accumulatedReturn"]*100);
        			arrB1['0']=i;
        			arrB1['1']=detailcomm.numbersFmt('2','2',obj[i]["benchmarkReturn"]*100);
        			arrR1['0']=i;
        			arrR1['1']=detailcomm.numbersFmt('2','2',obj[i]["relativeReturn"]*100);
        			arrL1['0']=i;
        			arrL1['1']=detailcomm.numbersFmt('2','2',obj[i]["logarithmicReturn"]*100);
        			arrA.push(arrA1);
        			arrB.push(arrB1);
        			arrR.push(arrR1);
        			arrL.push(arrL1);
            	};
            	ecData["times"]=arrT;
            	ecData['accumulated']=arrA;
            	ecData['benchmark']=arrB;
            	ecData['relative']=arrR;
            	ecData['logarithmic']=arrL;
        	}
        	shouyiEchart(ecData,uew);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}



/*图表数据-亏损*/
function getEchartKuisunData(status){
	//接口
	var link=common.sysurl()+"/firmoffer/findDailyAssetsMap.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var uew=$(".uew-radio .on").attr("data");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	var isReal=$("#isReal").val();//是否是累加
	var status=status;//当前记录状态
	if(status=="2"){
		var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;	
	}else{
	    var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime;
	}
	
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
    	//当日盈亏todayPftAndLoss
        if(rs.code==0){
        	var obj=rs.data;
        	var ecData=new Object();
        	if(str.isblank(obj)){
        		ecData["times"]=['0'];
        		ecData['todayPftAndLoss']=['0'];
        	}else{
        		var arrT=new Array();
        		var arrA=new Array();
        		var arrA1=new Array();
        		for(var i=0;i<obj.length;i++){
        			arrA1=[];
        			arrT.push(common.getLocalTime(obj[i]["time"]/1000,'d'));
        			arrA1['0']=i;
        			arrA1['1']=obj[i]["gapMoney"];
        			arrA.push(arrA1);
            	};
            	ecData["times"]=arrT;
            	ecData['todayPftAndLoss']=arrA;
        	}
        	kuisunEchart(ecData);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}



/*图表数据-买卖*/
function getEchartMaimaiData(status){
	//接口
	var link=common.sysurl()+"/firmoffer/findByAndSaleMap.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var uew=$(".uew-radio .on").attr("data");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	if(str.isblank(groupId)){
		groupId='';
	}
	var isReal=$("#isReal").val();//是否是累加
	var status=status;//当前记录状态
	if(status=="2"){
		var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;	
	}else{
	    var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime;
	}
	
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	//times时间  todaySale当日卖  todayBuying当日买
        	var obj=rs.data;
        	var ecData=new Object();
        	if(str.isblank(obj)){
        		ecData["times"]=['0'];
        		ecData['todaySale']=['0'];
        		ecData['todayBuying']=['0'];
        	}else{
        		var arrT=new Array();
        		var arrA=new Array();
        		var arrA1=new Array();
        		var arrB=new Array();
        		var arrB1=new Array();
        		for(var i=0;i<obj.length;i++){
        			arrA1=[];
        			arrB1=[];
        			arrT.push(common.getLocalTime(obj[i]["time"]/1000,'d'));
        			arrA1['0']=i;
        			arrB1['0']=i;
                    if(obj[i]["list"].length>1){
                      var mid=obj[i]["list"]['0']['tradingDirection'];
                      var mid1=obj[i]["list"]['1']['tradingDirection'];
                      if(mid=="1"){
                    	  arrB1['1']=obj[i]["list"]['0']['amount'];   
                      }else{
                    	  arrA1['1']=-obj[i]["list"]['0']['amount'];
                      };
                      if(mid1=="1"){
                    	  arrB1['1']=obj[i]["list"]['1']['amount'];   
                      }else{
                    	  arrA1['1']=-obj[i]["list"]['1']['amount'];
                      };
                    }else{
                      var mid=obj[i]["list"]['0']['tradingDirection'];
                      if(mid=="1"){
                    	  arrB1['1']=obj[i]["list"]['0']['amount'];
                    	  arrA1['1']=0;
                      }else{
                    	  arrA1['1']=-obj[i]["list"]['0']['amount']; 
                    	  arrB1['1']=0;
                      }
                    };
        			
        			arrA.push(arrA1);
        			arrB.push(arrB1);
            	};
            	ecData["times"]=arrT;
            	ecData['todaySale']=arrA;
            	ecData['todayBuying']=arrB;
        	}
        	maimaiEchart(ecData);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}





/*交易详情*/
function getDeal(loadPage){
	if(loadPage){
	   $("#curPage").val(1);
	}
	//接口
	var link=common.sysurl()+"/firmoffer/findTransactionDto.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var groupId=common.GetQueryString("groupId");//组id
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	if(str.isblank(groupId)){
		groupId='';
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	dealToHtml(rs.data);
        	var totalPages=rs.data.totalPage;
        	if(str.isblank(totalPages)){
        		totalPages=1;
        	}
        	pageFun(totalPages);
 
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}




/*每日持仓*/
function getPosition(loadPage){
	if(loadPage){
	   $("#curPage").val(1);
	}
	//接口
	var link=common.sysurl()+"/firmoffer/findDailyList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var groupId=common.GetQueryString("groupId");//组id
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();

	if(str.isblank(groupId)){
		groupId=$(".overParam").attr("paramid");
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	positionToHtml(rs.data);
        	var totalPages=rs.data.totalPage;
        	if(str.isblank(totalPages)){
        		totalPages=1;
        	}
        	pageFun(totalPages);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	         layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}



/*策略收益*/
function getTacticsEarnings(){
	//接口
	var link=common.sysurl()+"/firmoffer/findTStrategicReturnList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	tacticsEarningsToHtml(rs);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}

/*风险指标*/
function getRiskKpi(sidebar){
	//接口
	var link=common.sysurl()+"/firmoffer/findRiskIndicator.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	riskKpiToHtml(rs.data,sidebar);
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	         layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}


/*跳转到下一组*/
function downCluster(){
	//接口
	var link=common.sysurl()+"/firmoffer/retNextGroup.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var paramId=$(".overParam").attr("paramid");//正在回测id
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+paramId;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	location.reload();
        }else{
           layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	         layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}

/*++++++++++++++++++++++++++++++++++++++++++++++写入html++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//基本信息写入html
function baseToHtml(obj,dotype){
	//策略名称
	$(".strategyName").text(obj['strategyName']);
	//策略状态
	detailcomm.statusPages(obj['status']);
	$("#celueStatus").val(obj['status']);
	var groupId=common.GetQueryString("groupId");//组id
	
	//数据实施刷新完毕以后的跳转
	if(str.isblank(groupId)&&(obj['testStatus']=='2'||obj['testStatus']=='3')){
	   var testId=common.GetQueryString("testid");//单条回测记录id
	   var strategyId=common.GetQueryString("strategyid");//策略id
	   //location.href="/detail/historyEarnings.do?sidebar=1&testId="+testId+"&strategyId="+strategyId+"&groupId="+obj['paramId'];
	   location.href=common.sysurl()+"/frontend/simulation.do?type=2&strategyId="+strategyId;
	};
	//次数
	$(".hisOfferNum").text(obj['hisOfferNum']);
	//模拟开始时间
	$(".startTime").text(common.getLocalTime(obj['startTime'],'d'));
	$(".startTime").attr("data-time",obj['startTime']);
	if(str.isNotblank($("#start").val())&&(obj['testStatus']=='2'||obj['testStatus']=='3')){
	    	
	}else{
		var sidebar=common.GetQueryString("sidebar");
		if(str.isNotblank($("#start").val())&&sidebar!=1){
			
		}else{
			$("#start").val(common.getLocalTime(obj['startTime'],'d'));
		}
		
	}
	
	//模拟结束时间
	$(".endTime").text(common.getLocalTime(obj['endTime'],'d'));
	$(".endTime").attr("data-time",obj['endTime']);
	if(str.isNotblank($("#end").val())&&(obj['testStatus']=='2'||obj['testStatus']=='3')){
       	
	}else{
		var sidebar=common.GetQueryString("sidebar");
		if(str.isNotblank($("#end").val())&&sidebar!=1){
			
		}else{
			$("#end").val(common.getLocalTime(obj['endTime'],'d'));
		}
	}
	
	//初始资金
	$(".iniFunding").text(common.formatNumber(obj['iniFunding'],2));
	//策略频率
	if(obj['frequency']==0){
	  $(".frequency").text("TICK");
	}else if(obj['frequency']==1){
	  $(".frequency").text("每天");
	}else if(obj['frequency']==2){
	  $(".frequency").text("每分钟");
	}else{
	  $(".frequency").text("--");	
	};
	//回测进度
	$(".progress .bar").css("width",detailcomm.numbersFmt('1','2',obj["testPlan"]));
	$(".progress .t2").text(detailcomm.numbersFmt('1','2',obj["testPlan"]));
	//预计用时predict
	$(".predict").text(detailcomm.toMin(obj['predict']));
	
	//用时huiceyongshi
	$(".huiceyongshi").text(detailcomm.toMin(obj['overTime']));
	
	//实际用时shijiyongshi
	$shijiyongshi=detailcomm.toMin(obj['actualTime']);
    $(".shijiyongshi").text($shijiyongshi);
    
	//一共多少组
    $(".allParamNum").text(obj['allParamNum']);
    //正在进行第几组
    if(obj['overParam']==obj['allParamNum']){
    	$(".overParam").text(obj['allParamNum']);
    	$(".parameters-right").hide();
    }else{
    	$(".parameters-right").show();
    	$(".overParam").text(obj['overParam']+1);
    };
    $(".overParam").attr('paramId',obj['paramId']);
    
	//模拟单次记录模拟状态，测试状态 0.等待中 1. 测试中 2.已完成3.失败
	webFun.testStatus(obj['testStatus']);
	//模拟参数列表
	$("#paramlist").html(template("param-script",obj));
	
	/*动态读取对应的数据*/
	var sidebar=common.GetQueryString("sidebar");
	if(sidebar==1){
	  if((obj['testStatus']==2||obj['testStatus']==3)&&dotype==1){
		    
	  }else{
		  detailcomm.times(common.getLocalTime(obj['startTime'],'d'),common.getLocalTime(obj['endTime'],'d'),"1");
		  getEarnings();
		  getEchartShouyiData(obj['testStatus']);
		  getEchartKuisunData(obj['testStatus']);
		  getEchartMaimaiData(obj['testStatus']);
	  }
	}else if(sidebar==2&&dotype!=1){
	  detailcomm.times(common.getLocalTime(obj['startTime'],'d'),common.getLocalTime(obj['endTime'],'d'),"2");
	  getDeal(true);
	}else if(sidebar==3&&dotype!=1){
	  detailcomm.times(common.getLocalTime(obj['startTime'],'d'),common.getLocalTime(obj['endTime'],'d'),"3");
	  getPosition(true);
	}else if(sidebar==4&&dotype!=1){
	  getTacticsEarnings();
	}else if(dotype!=1){
	  getRiskKpi(sidebar);	
	}
}

//收益概览写入html
function earningsToHtml(obj){
	//1、回测收益
	$(".accumulatedReturn").text(detailcomm.numbersFmt('1','2',obj['accumulatedReturn']));
	if(obj['accumulatedReturn']>=0){
		$(".accumulatedReturn").css("color",'red');	
	}else{
		$(".accumulatedReturn").css("color",'green');
	};
	//2、年化收益
	$(".yield").text(detailcomm.numbersFmt('1','2',obj['yield']));
	if(obj['yield']>=0){
		$(".yield").css("color",'red');	
	}else{
		$(".yield").css("color",'green');
	};
	//3、基准收益
	$(".benchmark").text(detailcomm.numbersFmt('1','2',obj['totalBenchmark']));
	if(obj['totalBenchmark']>=0){
		$(".benchmark").css("color",'red');	
	}else{
		$(".benchmark").css("color",'green');
	};
	//4、阿尔法
	$(".alpha").text(detailcomm.numbersFmt('2','4',obj['alpha']));
	//5、贝塔
	$(".beta").text(detailcomm.numbersFmt('2','4',obj['beta']));
	//6、夏普比率
	$(".shapratio").text(detailcomm.numbersFmt('2','4',obj['shapratio']));
	//7、索提洛比率
	$(".sotiroratio").text(detailcomm.numbersFmt('2','4',obj['sotiroratio']));
	//8、信息比率
	$(".infomationratio").text(detailcomm.numbersFmt('2','4',obj['infomationratio']));
	//9、收益波动率
	$(".returnVolatlity").text(detailcomm.numbersFmt('2','4',obj['returnVolatlity']));
	//10、最大回撤 
	$(".maxRetrace").text(detailcomm.numbersFmt('1','2',obj['maxRetrace']));
	//11、跟踪误差 
	$(".tracking").text(detailcomm.numbersFmt('1','2',obj['tracking']));
	//12、下跌风险
	$(".downsideRisk").text(detailcomm.numbersFmt('1','2',obj['downsideRisk']));
}



//交易详情写入html
function dealToHtml(obj){
	if(str.isblank(obj.records)){
	  $("#deal-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	}else{
	  $("#deal-content").html(template("deal-script",obj));
	}
}


//每日持仓写入html
function positionToHtml(obj){
	if(str.isblank(obj.list)){
	  $("#position-content").html("");
	}else{
	  $("#position-content").html(template("position-script",obj));
	}
}


//策略收益写入html
function tacticsEarningsToHtml(obj){
	if(str.isblank(obj.data)){
	  $("#tacticsEarnings-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	}else{
	  $("#tacticsEarnings-content").html(template("tacticsEarnings-script",obj));
	}
}

//风险指标写入html
function riskKpiToHtml(obj,sidebar){
	if(sidebar==5){
	  $("#kpi-content").html(template("kpi-script",obj['alphaList']));
      $(".detail-title").text("阿尔法");
	}else if(sidebar==6){
	  $("#kpi-content").html(template("kpi-script",obj['alphaList']));
	  $(".detail-title").text("贝塔");
	}else if(sidebar==7){
	  $("#kpi-content").html(template("kpi-script",obj['shapratioList']));
	  $(".detail-title").text("夏普比率");
	}else if(sidebar==8){
	  $("#kpi-content").html(template("kpi-script",obj['sotiroratioList']));
	  $(".detail-title").text("索提洛比率");
	}else if(sidebar==9){
	  $("#kpi-content").html(template("kpi-script",obj['infomationratioList']));
	  $(".detail-title").text("信息比率");
	}else if(sidebar==10){
	  $("#kpi-content").html(template("kpi-script",obj['returnVolatlityList']));
	  $(".detail-title").text("收益波动率");
	}else if(sidebar==11){
	  $("#kpi-content").html(template("kpi-script",obj['maxRetraceList']));
	  $(".detail-title").text("最大回撤");
	}else{
		alert("riskKpiToHtml():参数错误");
		$(".detail-title").text("");
	}

	
}
