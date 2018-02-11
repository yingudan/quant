// +----------------------------------------------------------------------
// | des:实盘模拟详情js
// +----------------------------------------------------------------------
// | 2017-04-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
//初始化执行
$(function(){
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
	 getEhartdata();
}


/**
 * DES:动态调用方法读取数据的
 * @param sidebar 1收益概览，2交易详情，3每日持仓，4策略收益,5风险指标
 * @return void
 */
WebFun.prototype.readData=function(){
	getBase();
	setInterval(function(){
    	 getBase(1);
   },4000);
	
}



/**
 * DES:单条记录集的状态
 * @param 模拟单次记录模拟状态，测试状态 0.等待中 1. 测试中 2.已完成3.失败
 * @return void
 */
WebFun.prototype.testStatus=function(status){
	if(status==0){
	  $(".testStatus").text("等待中");
	  $(".testStatus").css("color","#23BD00");
	}else if(status==1){
	  $(".testStatus").text("测试中");
	  $(".testStatus").css("color","#23BD00");
	}else if(status==2){
	  $(".testStatus").text("已完成");
	  $(".testStatus").css("color","green");
	}else if(status==3){
	  $(".testStatus").text("失败");
	  $(".testStatus").css("color","#23BD00");
	}else{
		alert("testStatus():参数错误");
	}
	
	
}




/**
 * DES:当日策略收益和全部策略收益切换
 * @param type 1当时策略收益，2全部策略收益
 * @return void
 */
WebFun.prototype.dangquan=function(obj){
  var type=$(obj).attr("type");  
  if(type=='1'){
	  $(obj).attr("type",'2');  
	  $(obj).text("查看当日策略收益>>");
	  $(".lefttitlless").text("全部策略收益");
	  $(".detail-time-echartsmap .comm_input_box").show();
	  getBase();
  }else{
	  $(obj).attr("type",'1'); 
	  $(obj).text("查看全部策略收益>>");
	  $(".lefttitlless").text("当日策略收益");
	  $(".detail-time-echartsmap .comm_input_box").hide();
	  getBase();
  };
}




//实例化firmDetail.js类
var webFun= new WebFun();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

/*读取实盘模拟基本信息*/
function getBase(dotype){
	//接口
	var link=common.sysurl()+"/firmoffer/findTStrategyDto.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var dataLink="testId="+testId+"&strategyId="+strategyId;
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

/*非图表数据-收益概览*/
function getEarnings(){
	//接口
	var link=common.sysurl()+"/firmoffer/findNowIncomeSituation.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	var dataLink="testId="+testId+"&strategyId="+strategyId;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	if(str.isblank(rs.data)){
        		$(".detail-intro dd").text("--");
                twolistToHtml(rs.data);	
        	}else{
        		if(str.isblank(rs.data.theDplist)){
            		var arr=new Array();
            		arr['0']="";
            		rs.data.theDplistFmt=arr;
            	}else{
            		var arr=new Array();
            		arr['0']=rs.data.theDplist;
            		rs.data.theDplistFmt=arr;
            	};
            	
            	if(str.isblank(rs.data.theTclist)){
            		var arr2=new Array();
            		arr2['0']="";
            		rs.data.theTclistFmt=arr2;
            	}else{
            		var arr2=new Array();
            		arr2['0']=rs.data.theTclist;
            		rs.data.theTclistFmt=arr2;
            	};
            	earningsToHtml(rs.data);  
        	}
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


/*图表数据-收益概览*/
function getEhartdata(){
	//接口
	var link=common.sysurl()+"/firmoffer/findNowCharDate.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var uew=$(".uew-radio .on").attr("data");
	var type=$(".qiehuanpic").attr("type");//当日还是全部收益，1表示当日,2表示全部
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime+"&type="+type;

    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	var obj=rs.data;
        	//我的累计收益------------------------------------------------------------------
        	var obj=rs.data;
        	if(obj["accumulated"]['times']==null){
        		obj["accumulated"]['times']=['0'];
        		obj["accumulated"]['values']=['0'];
        	}else{
            	for(var i=0;i<obj["accumulated"]['times'].length;i++){
            		obj["accumulated"]['times'][i]=common.getLocalTime(obj["accumulated"]['times'][i]/1000);
            	};
            	for(var i=0;i<obj["accumulated"]['values'].length;i++){
            		var arr=new Array();
            		    arr['0']=i;
            		    arr['1']=detailcomm.numbersFmt('2','2',obj["accumulated"]['values'][i]*100);
            		obj["accumulated"]['values'][i]=arr;
            	};
        	}
        	
        	//基准累计收益---------------------------------------------------------------------
        	if(obj["benchmark"]['times']==null){
        		obj["benchmark"]['times']=['0'];
        		obj["benchmark"]['values']=['0'];
        	}else{
	        	for(var i=0;i<obj["benchmark"]['times'].length;i++){
	        		obj["benchmark"]['times'][i]=common.getLocalTime(obj["accumulated"]['times'][i]/1000);
	        	};
	        	for(var i=0;i<obj["benchmark"]['values'].length;i++){
	        		var arr=new Array();
	        		    arr['0']=i;
	        		    arr['1']=detailcomm.numbersFmt('2','2',obj["benchmark"]['values'][i]*100);
	        		obj["benchmark"]['values'][i]=arr;
	        	};
        	}
        	
        	//相对收益---------------------------------------------------------------------
        	if(obj["relativereturn"]['times']==null){
        		obj["relativereturn"]['times']=['0'];
        		obj["relativereturn"]['values']=['0'];
        	}else{
	        	for(var i=0;i<obj["relativereturn"]['times'].length;i++){
	        		obj["relativereturn"]['times'][i]=common.getLocalTime(obj["relativereturn"]['times'][i]/1000);
	        	};
	        	for(var i=0;i<obj["relativereturn"]['values'].length;i++){
	        		var arr=new Array();
	        		    arr['0']=i;
	        		    arr['1']=detailcomm.numbersFmt('2','2',obj["relativereturn"]['values'][i]*100);
	        		obj["relativereturn"]['values'][i]=arr;
	        	};
        	}
        	
        	//对数收益---------------------------------------------------------------------
        	if(obj["logarithmicreturn"]['times']==null){
        		obj["logarithmicreturn"]['times']=['0'];
        		obj["logarithmicreturn"]['values']=['0'];
        	}else{
	        	for(var i=0;i<obj["logarithmicreturn"]['times'].length;i++){
	        		obj["logarithmicreturn"]['times'][i]=common.getLocalTime(obj["logarithmicreturn"]['times'][i]/1000);
	        	};
	        	for(var i=0;i<obj["logarithmicreturn"]['values'].length;i++){
	        		var arr=new Array();
	        		    arr['0']=i;
	        		    arr['1']=detailcomm.numbersFmt('2','2',obj["logarithmicreturn"]['values'][i]*100);
	        		obj["logarithmicreturn"]['values'][i]=arr;
	        	};
        	}
        	
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



/*交易详情*/
function getDeal(){
	//接口
	var link=common.sysurl()+"/firmoffer/findNowDetails.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
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


/*每日持仓*/
function getPosition(){
	//接口
	var link=common.sysurl()+"/firmoffer/findNowDailyList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start").val());
	var endTime=common.transdate($("#end").val());
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	positionToHtml(rs);
        	var totalPages=rs.data.totalPage;
        	if(str.isblank(totalPages)){
        		totalPages=1;
        	}
        	pageFun(totalPages);
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

/*策略收益*/
function getTacticsEarnings(){
	//接口
	var link=common.sysurl()+"/firmoffer/findTStrategicReturnList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	if(str.isblank(startTime)){
		startTime="";
	}
	if(str.isblank(endTime)){
		endTime="";
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	tacticsEarningsToHtml(rs);
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

/*风险指标*/
function getRiskKpi(){
	//接口
	var link=common.sysurl()+"/firmoffer/findRiskIndicator.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=$(".startTime").attr("data-time");
	var endTime=$(".endTime").attr("data-time");
	if(str.isblank(startTime)){
		startTime="";
	}
	if(str.isblank(endTime)){
		endTime="";
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	riskKpiToHtml(rs.data);
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

/*++++++++++++++++++++++++++++++++++++++++++++++写入html++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//基本信息写入html
function baseToHtml(obj,dotype){
	//策略名称
	$(".strategyName").text(obj['strategyName']);
	//策略状态
	detailcomm.statusPages(obj['status']);
	$("#celueStatus").val(obj['status']);
	detailcomm.statusPages(obj['status']);
	$("#testStatus").val(obj['testStatus']);
	//次数
	$(".hisOfferNum").text(obj['hisOfferNum']);
	
	if(str.isNotblank($("#end").val())&&(obj['testStatus']=='2'||obj['testStatus']=='3')){
		
	}else{
		var sidebar=common.GetQueryString("sidebar");
		//模拟开始时间
		$(".startTime").text(common.getLocalTime(obj['startTime'],'d'));
		$(".startTime").attr("data-time",obj['startTime']);
		//模拟结束时间
		if(str.isblank(obj['endTime'])){
			var nowDate = new Date().getTime();
			$(".endTime").text(common.getLocalTime(nowDate,'d'));
			$(".endTime").attr("data-time",nowDate);
			if(str.isNotblank($("#start").val())&&sidebar!=1){
			
			}else{
				$("#start").val(common.getLocalTime(obj['startTime'],'d'));
			};
			if(str.isNotblank($("#end").val())&&sidebar!=1){
				
			}else{
				$("#end").val(common.getLocalTime(nowDate,'d'));
			};
		}else{
			$(".endTime").text(common.getLocalTime(obj['endTime'],'d'));
			$(".endTime").attr("data-time",obj['endTime']);
			if(str.isNotblank($("#start").val())&&sidebar!=1){
				
			}else{
				$("#start").val(common.getLocalTime(obj['startTime'],'d'));
			};
			if(str.isNotblank($("#end").val())&&sidebar!=1){
				
			}else{
				$("#end").val(common.getLocalTime(obj['endTime'],'d'));
			};
		}
	}

	//初始资金
	$(".iniFunding").text(common.formatNumber(obj['iniFunding']));
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
		  getEhartdata();
	  }
	}else if(sidebar==2&&dotype!=1){
	  detailcomm.times(common.getLocalTime(obj['startTime'],'d'),common.getLocalTime(obj['endTime'],'d'),"2");
	  getDeal();
	}else if(sidebar==3&&dotype!=1){
	  detailcomm.times(common.getLocalTime(obj['startTime'],'d'),common.getLocalTime(obj['endTime'],'d'),"3");
	  getPosition();
	}else if(sidebar==4&&dotype!=1){
	  getTacticsEarnings();
	}else if(sidebar==5&&dotype!=1){
	  getRiskKpi();
	}else{

	}

}

//收益概览写入html
function earningsToHtml(obj){
	//1、模拟收益
	$(".accumulatedReturn").text(detailcomm.numbersFmt('1','2',obj['accumulatedReturn']));
	if(obj['accumulatedReturn']>=0){
		$(".accumulatedReturn").css("color",'red');	
	}else{
		$(".accumulatedReturn").css("color",'green');
	};
	//2、模拟年化收益
	$(".yield").text(detailcomm.numbersFmt('1','2',obj['yield']));
	if(obj['yield']>=0){
		$(".yield").css("color",'red');	
	}else{
		$(".yield").css("color",'green');
	};
	//3、基准收益
	$(".benchmark").text(detailcomm.numbersFmt('1','2',obj['benchmark']));
	if(obj['benchmark']>=0){
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
	//7、收益波动率
	$(".returnVolatlity").text(detailcomm.numbersFmt('2','4',obj['returnVolatlity']));
	//8、最大回撤 
	$(".maxRetrace").text(detailcomm.numbersFmt('2','4',obj['maxRetrace']));
	//我的持仓和每日交易
	twolistToHtml(obj);
}
    
//交易概览里面-我的持仓和每日交易
function twolistToHtml(obj){
	//我的持仓list
	var html1="<dl class='title-table'>";
	    html1=html1+"<dt>我的持仓</dt>";
	    html1=html1+"<dd>";
	    html1=html1+"<span>总资产：<font>--</font></span>";
	    html1=html1+  "<span>可用金额：<font>--</font></span>";
	    html1=html1+  "<span>当前仓位：<font>--</font></span>";
	    html1=html1+"</dd>";
	    html1=html1+"</dl>";
	    html1=html1+"<table class='table tables'>";
	    html1=html1+"<thead>";
	    html1=html1+ "<tr>";
	    html1=html1+   "<th width='120'>股票名称</th>";
	    html1=html1+   "<th>现价(元)</th>";
	    html1=html1+   "<th>成交均价(元)</th>";
	    html1=html1+   "<th>持仓数量(股)</th>";
	    html1=html1+   "<th>收益(元)</th>";
	    html1=html1+ "</tr>";
	    html1=html1+ "</thead>";
	    html1=html1+"<tbody>";
	    html1=html1+  "<tr>";
	    html1=html1+  	"<td style='text-align: center;' colspan='100'>暂无数据</td>";
	    html1=html1+  "</tr>";
	    html1=html1+"</tbody>";
	    html1=html1+"</table>";
	    if(str.isblank(obj)){
	    	$("#theDplist-content").html(html1);
	    }else{
	    	$("#theDplist-content").html(template("theDplist-script",obj));
	    };

	//我的当日交易list
	var html2="<dl class='title-table'>";
		html2=html2+"<dt>我的持仓</dt>";
		html2=html2+"<dd>";
		html2=html2+"<span>总资产：<font>--</font></span>";
		html2=html2+  "<span>可用金额：<font>--</font></span>";
		html2=html2+  "<span>当前仓位：<font>--</font></span>";
		html2=html2+"</dd>";
		html2=html2+"</dl>";
		html2=html2+"<table class='table tables'>";
		html2=html2+"<thead>";
		html2=html2+ "<tr>";
		html2=html2+   "<th width='120'>股票名称</th>";
		html2=html2+   "<th>买卖方向</th>";
		html2=html2+   "<th>成交均价(元)</th>";
		html2=html2+   "<th>成交金额(元)</th>";
		html2=html2+   "<th>交易费用(元)</th>";
		html2=html2+ "</tr>";
		html2=html2+ "</thead>";
		html2=html2+"<tbody>";
		html2=html2+  "<tr>";
		html2=html2+  	"<td style='text-align: center;' colspan='100'>暂无数据</td>";
		html2=html2+  "</tr>";
		html2=html2+"</tbody>";
		html2=html2+"</table>";
		if(str.isblank(obj)){
	    	$("#theTclist-content").html(html2);
	    }else{
	    	$("#theTclist-content").html(template("theTclist-script",obj));
	    };
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
	if(str.isblank(obj.data)){
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
function riskKpiToHtml(obj){
	$("#alphaList-content").html(template("alphaList-script",obj));
	$("#betaList-content").html(template("betaList-script",obj));
	$("#shapratioList-content").html(template("shapratioList-script",obj));
	$("#sotiroratioList-content").html(template("sotiroratioList-script",obj));
	$("#infomationratioList-content").html(template("infomationratioList-script",obj));
	$("#returnVolatlityList-content").html(template("returnVolatlityList-script",obj));
	$("#maxRetraceList-content").html(template("maxRetraceList-script",obj));
}











