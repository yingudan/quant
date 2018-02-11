// +----------------------------------------------------------------------
// | desc: layout js
// +----------------------------------------------------------------------
// | Created By 2017-11-28
// +----------------------------------------------------------------------
// | Author:张亮
// +----------------------------------------------------------------------
/**
 *desc:初始化加载
 *@param void;
 *@return void;
 */
$(function(){
	webFun.returnDataWithTime();
	web.gotoStrategyScoreDetails();
	web.getBase();  
	getEarnings();
	getEhartdata();
	web.dateTime($(".startTime").text(),$(".endTime").text())
	web.dateTime1($(".startTime").text(),$(".endTime").text())
	web.dateTime2($(".startTime").text(),$(".endTime").text())
});

/**
 *desc:构造函数 
 *@param void;
 *@return void;
 */
function WebFun(){};


/**
 *desc：加载层
 *@return void;
 */
WebFun.prototype.loadings=function(){
	web.dataLoading(1);	
	var setInterval1=setInterval(function(index){
		var strategyLeft=$("#strategyLeft a.on").attr("data-content");
		var click=$("#click").val();
		var loadinghidden=$("#loadinghidden").val();
		if(click=="1"){
		  if(strategyLeft=="1"){
			 var num='2';
		  }else{
			  var num='1'; 
		  };
		  if(loadinghidden==num){
			 web.dataLoading(-1);
			 $("#loadinghidden").val("0");
			 $("#click").val("0");
			 clearInterval(setInterval1);
		  }
		}else{
		  if(loadinghidden=="3"){
			 web.dataLoading(-1);
			 $("#loadinghidden").val("0");
			 $("#click").val("0");
			 clearInterval(setInterval1);
		  };
		}
    },"1000"); 
};




/**
 *desc：定时刷新器
 *@return void;
 */
WebFun.prototype.returnDataWithTime=function(){
	setInterval(function(index){
		web.getBase();
		var tooltype=$("#teststatusOff").val();
		var strategyLeft=$(".strategyLeft a.on").attr("data-content");
		var loadinghidden=$("#loadinghidden").val();
		if(strategyLeft==1){
			var num=2;
		}else{
			var num=1;
		};
		if(loadinghidden>num){
			$("#loadinghidden").val("0")
			if(tooltype=='0'||tooltype=='1'){
				if(strategyLeft=="1"){
					 getEarnings();
					 getEhartdata();
				 }else if(strategyLeft=="2"){
					 getDeal();
				 }else if(strategyLeft=="3"){
					 getPosition();
				 }else if(strategyLeft=="4"){
					 getTacticsEarningsAndriskApi(strategyLeft);
				 }else{
					 getTacticsEarningsAndriskApi(strategyLeft);
				 };
			}; 	
		}
		
    },"10000"); 
};


 /**
 *desc：屏幕变化执行
 *@param  type:1收益概览  2交易详情 3每日持仓 4策略收益 5阿尔法 6贝塔  7夏普比率  8索提洛比率  9信息比率  10收益波动率 11最大回撤;
 *@return void;
 */
WebFun.prototype.getData=function(type){
  if(type==1){
	  getEarnings(); 
	  getEhartdata();
  }else if(type==2){
	  getDeal(true);
  }else if(type==3){
	  getPosition(true);
  }else{
	 
	  getTacticsEarningsAndriskApi(type);
  };   
};
/**
 *desc:构造函数实例化
 */
var webFun = new WebFun();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	
        	if(rs.data==null){
        		location.href=common.sysurl()+"/frontend/simulation.do?type=2&strategyId="+strategyId+"&testId="+testId;
        	}else{
        		
        		earningsToHtml(rs.data);
        		if(groupId==''){
            		location.href=location.href+"&groupId="+rs.data.subAccountId;
            	}
        	}
        	
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



/*图表数据-收益概览*/
function getEhartdata(){
	var basetime=getBaseTime();
	//接口
	var link=common.sysurl()+"/firmoffer/findHisDataMap.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var groupId=common.GetQueryString("groupId");//组id
	var startTime=common.transdate($("#start2").val());
	var endTime=common.transdate($("#end2").val());
	if(str.isblank(groupId)){
		groupId='';
	}
	if(str.isblank(startTime)){
		startTime=basetime["startTime"];	
	};
	if(str.isblank(endTime)){
		endTime=basetime["endTime"];	
	};
	var uew=$(".uew-radio .on").attr("data");
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;
	
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	/*--------------------循环累计收益-------------------------*/
        	var obj=new Object();
        	var obj1=new Object();
        	var obj2=new Object();
        	var obj3=new Object();
        	var obj4=new Object();
        	var obj5=new Object();
        	var obj6=new Object();
        	var obj7=new Object();
        	var obj8=new Object();
        	var times=new Array();
        	var value1=new Array();
        	var value2=new Array();
        	var value3=new Array();
        	var value4=new Array();
        	var value5=new Array();
        	var value6=new Array();
        	var value7=new Array();
        	var value8=new Array();
        	var data=rs.data['tMinIncomeMap'];
        	var len=data.length;
        	if(len>0){
        	  for(var i=0;i<len;i++){
        		  $time=common.getLocalTime(data[i]['createTime'],"d");
        		  $accumulated=data[i]['accumulatedReturn'];
        		  if(str.isblank($accumulated)){$accumulated=0};
        		  
        		  $benchmark=data[i]['benchmarkReturn'];
        		  if(str.isblank($benchmark)){$benchmark=0};
        		  
        		  $benchmarkSz50=data[i]['totalBenchmarkSz50'];
        		  if(str.isblank($benchmarkSz50)){$benchmarkSz50=0};
        		  
        		  $benchmarkZz500=data[i]['totalBenchmarkZz500'];
        		  if(str.isblank($benchmarkZz500)){$benchmarkZz500=0};
        		  
        		  $relativereturn=data[i]['relativeReturn'];
        		  if(str.isblank($relativereturn)){$relativereturn=0};
        		  
        		  $relativeReturnSz50=data[i]['relativeReturnSz50'];
        		  if(str.isblank($relativeReturnSz50)){$relativeReturnSz50=0};
        		  
        		  $relativeReturnZz500=data[i]['relativeReturnZz500'];
        		  if(str.isblank($relativeReturnZz500)){$relativeReturnZz500=0};
        		  
        		  $logarithmicreturn=data[i]['logarithmicReturn'];
        		  if(str.isblank($logarithmicreturn)){$logarithmicreturn=0};
        		  times.push($time);
        		  //我的累计收益-累计收益
        		  value1.push($accumulated);
        		  //沪深300-累计收益
        		  value2.push($benchmark);
        		  //上证50-累计收益
        		  value3.push($benchmarkSz50);
        		  //中证500-累计收益
        		  value4.push($benchmarkZz500);
        		  //沪深300-相对收益
        		  value5.push($relativereturn);
        		  //上证50-相对收益
        		  value6.push($relativeReturnSz50);
        		  //中证500-相对收益
        		  value7.push($relativeReturnZz500);
        		  //对数收益-对数收益
        		  value8.push($logarithmicreturn);
        	  };
        	  obj1['times']=times;
        	  obj1['values']=value1;
        	  
        	  obj2['times']=times;
        	  obj2['values']=value2;
        	  
        	  obj3['times']=times;
        	  obj3['values']=value3;
        	  
        	  obj4['times']=times;
        	  obj4['values']=value4;
        	  
        	  obj5['times']=times;
        	  obj5['values']=value5;
        	  
        	  obj6['times']=times;
        	  obj6['values']=value6;
        	  
        	  obj7['times']=times;
        	  obj7['values']=value7;
        	  
        	  obj8['times']=times;
        	  obj8['values']=value8;
        	}else{
        	  obj1['times']=null;
          	  obj1['values']=null;
          	  
          	  obj2['times']=null;
          	  obj2['values']=null;
          	  
          	  obj3['times']=null;
          	  obj3['values']=null;
          	  
          	  obj4['times']=null;
          	  obj4['values']=null;
          	  
          	  obj5['times']=null;
          	  obj5['values']=null;
          	  
          	  obj6['times']=null;
          	  obj6['values']=null;
          	  
          	  obj7['times']=null;
          	  obj7['values']=null;
          	  
          	  obj8['times']=null;
          	  obj8['values']=null;
        	}
            
	          obj["accumulated"]=obj1;
	      	  obj["benchmark"]=obj2;
	      	  obj["benchmarkSz50"]=obj3;
	      	  obj["benchmarkZz500"]=obj4;
	      	  obj["relativereturn"]=obj5;
	      	  obj["relativeReturnSz50"]=obj6;
	      	  obj["relativeReturnZz500"]=obj7;
	      	  obj["logarithmicreturn"]=obj8;

	      	/*--------------------当日亏损-------------------------*/ 
	      	var obj9=new Object();
	      	var value9=new Array();
	      	var times2=new Array();
	      	var data2=rs.data['dailyAssetsMap'];
        	var len2=data2.length;
  		    var objecttext=new Object();
        	if(len2>0){
          	  for(var i=0;i<len2;i++){
          		$time=data2[i]['time'];
      		    $dailyAssetsMap=data2[i]['gapMoney'];
      		    if(str.isblank($dailyAssetsMap)){$dailyAssetsMap=0}; 
      		    objecttext[$time]=$dailyAssetsMap;
          	  };
        	};

        	for(var i=0;i<len;i++){
        		$dailyAssetsMap=objecttext[data[i]['createTime']];
        		if(str.isblank($dailyAssetsMap)){$dailyAssetsMap="0";}
	    		value9.push($dailyAssetsMap);   
        	};
        	obj9["times"]=times;
        	obj9["values"]=value9;
        	obj["dailyAssetsMap"]=obj9; 	
        
        	/*--------------------每日买卖-------------------------*/ 
	      	var obj10=new Object();
	      	var obj11=new Object();
	      	var value10=new Array();
	      	var value11=new Array();
	      	var times3=new Array();
	      	var data3=rs.data['byAndSaleMap'];
        	var len3=data3.length;
        	var objecttext2=new Object();
        	var objecttext3=new Object();
        	if(len3>0){
          	  for(var i=0;i<len3;i++){
          		$time=data3[i]['time'];
          		$lenlist=data3[i]['list']
          		if($lenlist.length==2){
          			var mid=data3[i]["list"]['0']['tradingDirection'];
                    var mid1=data3[i]["list"]['1']['tradingDirection'];
                    if(mid=="1"){
                  	  $mairu=data3[i]["list"]['0']['amount'];   
                    }else{
                      $maichu=-data3[i]["list"]['0']['amount'];
                    };
                    if(mid1=="1"){
                      $mairu=data3[i]["list"]['1']['amount'];   
                    }else{
                      $maichu=-data3[i]["list"]['1']['amount'];
                    };
          		}else{
                    var mid=data3[i]["list"]['0']['tradingDirection'];
                    if(mid=="1"){
                      $mairu=data3[i]["list"]['0']['amount'];
                      $maichu=0;
                    }else{
                      $maichu['1']=-data3[i]["list"]['0']['amount']; 
                      $mairu=0;
                    }
                };
                objecttext2[$time]=$mairu;
                objecttext3[$time]=$maichu;
          	  };
        	};
        	for(var i=0;i<len;i++){
        		$mairu=objecttext2[data[i]['createTime']];
        		if(str.isblank($mairu)){$mairu="0";}
        		$maichu=objecttext3[data[i]['createTime']];
        		if(str.isblank($maichu)){$maichu="0";}
        		value10.push($mairu); 
        		value11.push($maichu);
        	};
        	obj10["times"]=times;
        	obj10["values"]=value10;
        	obj11["times"]=times;
        	obj11["values"]=value11;
        	
        	obj["mairu"]=obj10; 
        	obj["maichu"]=obj11;
        
        	if(uew==1){//累计收益
        		obj["accumulated"]=web.dataIsEchats3(obj["accumulated"]);//我的累计收益
        		obj["benchmark"]=web.dataIsEchats3(obj["benchmark"]);//沪深300
        		obj["benchmarkSz50"]=web.dataIsEchats3(obj["benchmarkSz50"]);//上证50
        		obj["benchmarkZz500"]=web.dataIsEchats3(obj["benchmarkZz500"]);//中证500
        	}else if(uew==2){//相对收益
        		obj["relativereturn"]=web.dataIsEchats(obj["relativereturn"]);//沪深300
        		obj["relativeReturnSz50"]=web.dataIsEchats(obj["relativeReturnSz50"]);//上证50
        		obj["relativeReturnZz500"]=web.dataIsEchats(obj["relativeReturnZz500"]);//中证500
        	}else if(uew==3){//对数收益
        		obj["logarithmicreturn"]=web.dataIsEchats(obj["logarithmicreturn"]);//对数收益
        	}else{
        	    alert("错误：004");
        	}

        	obj["dailyAssetsMap"]=web.dataIsEchats(obj["dailyAssetsMap"]);//每日盈亏
        	obj["mairu"]=web.dataIsEchats(obj["mairu"]);//买入
        	obj["maichu"]=web.dataIsEchats(obj["maichu"]);//买出
        	ehartjs.setEhartjs(obj,uew);	
        }else{
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





/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
WebFun.prototype.radios=function(obj){
	 $(obj).parent().parent().find("em").removeClass("on");
	 $(obj).addClass("on");
	 getEhartdata();
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
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
		groupId=$(".overParam").attr("paramid");
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
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
	var startTime=common.transdate($("#start1").val());
	var endTime=common.transdate($("#end1").val());
	var groupId=common.GetQueryString("groupId");//组id
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();

	if(str.isblank(groupId)){
		groupId=$(".overParam").attr("paramid");
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
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


/*策略收益和风控指标*/
function getTacticsEarningsAndriskApi(type){
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
	
	//接口
	if(type==4){
	  var link=common.sysurl()+"/firmoffer/findTStrategicReturnList.m";
	  var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime;
	}else{
	  if(type==5){
		  var dataType=2
	  }else if(type==6){
		  var dataType=3
	  }else if(type==7){
		  var dataType=4
	  }else if(type==8){
		  var dataType=5
	  }else if(type==9){
		  var dataType=6
	  }else if(type==10){
		  var dataType=7
	  }else if(type==11){
		  var dataType=8
	  }else{
		  var dataType="";
	  }
      var link=common.sysurl()+"/firmoffer/findRiskIndicator.m";
      var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId+"&startTime="+startTime+"&endTime="+endTime+"&dataType="+dataType;
	};
	
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	var obj=new Array();
           if(type==4){
        	   $("#shouyifengxian").text("策略收益");
        	   obj['list']=rs.data;
           }else if(type==5){
        	   $("#shouyifengxian").text("阿尔法");
        	   obj['list']=rs.data.alphaList;
           }else if(type==6){
        	   $("#shouyifengxian").text("贝塔");
        	   obj['list']=rs.data.betaList;
           }else if(type==7){
        	   $("#shouyifengxian").text("夏普比率");
        	   obj['list']=rs.data.shapratioList;
           }else if(type==8){
        	   $("#shouyifengxian").text("索提洛比率");
        	   obj['list']=rs.data.sotiroratioList;
           }else if(type==9){
        	   $("#shouyifengxian").text("信息比率");
        	   obj['list']=rs.data.infomationratioList;
           }else if(type==10){
        	   $("#shouyifengxian").text("收益波动率");
        	   obj['list']=rs.data.returnVolatlityList;
           }else if(type==11){
        	   $("#shouyifengxian").text("最大回撤");
        	   obj['list']=rs.data.maxRetraceList;
           };
          tacticsEarningsAndriskApiToHtml(obj); 
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
//收益概览写入html
function earningsToHtml(obj){
	//1、回测收益
	$(".accumulatedReturn").text(web.numbersFmt('1','2',obj['accumulatedReturn']));
	if(obj['accumulatedReturn']>=0){
		$(".accumulatedReturn").css("color",'red');	
	}else{
		$(".accumulatedReturn").css("color",'green');
	};
	//2、年化收益
	$(".yield").text(web.numbersFmt('1','2',obj['yield']));
	if(obj['yield']>=0){
		$(".yield").css("color",'red');	
	}else{
		$(".yield").css("color",'green');
	};
	//3、基准收益
	$(".benchmark").text(web.numbersFmt('1','2',obj['totalBenchmark']));
	if(obj['totalBenchmark']>=0){
		$(".benchmark").css("color",'red');	
	}else{
		$(".benchmark").css("color",'green');
	};
	//4、阿尔法
	$(".alpha").text(web.numbersFmt('2','4',obj['alpha']));
	//5、贝塔
	$(".beta").text(web.numbersFmt('2','4',obj['beta']));
	//6、夏普比率
	$(".shapratio").text(web.numbersFmt('2','4',obj['shapratio']));
	//7、索提洛比率
	$(".sotiroratio").text(web.numbersFmt('2','4',obj['sotiroratio']));
	//8、信息比率
	$(".infomationratio").text(web.numbersFmt('2','4',obj['infomationratio']));
	//9、收益波动率
	$(".returnVolatlity").text(web.numbersFmt('1','2',obj['returnVolatlity']));
	//10、最大回撤 
	$(".maxRetrace").text(web.numbersFmt('1','2',obj['maxRetrace']));
	//11、跟踪误差 
	$(".tracking").text(web.numbersFmt('1','2',obj['tracking']));
	//12、下跌风险
	$(".downsideRisk").text(web.numbersFmt('1','2',obj['downsideRisk']));
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


//策略收益和风险写入html
function tacticsEarningsAndriskApiToHtml(obj){
	if(str.isblank(obj.list)){
	  $("#shouyifengxian-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	}else{
	  $("#shouyifengxian-content").html(template("shouyifengxian-script",obj));
	}
}



















