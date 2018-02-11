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
	web.gotoStrategyScoreDetails();
	web.getBase();  
	getEarnings();
	getEhartdata();
	web.dateTime2($(".startTime").text(),$(".endTime").text());
    web.dataLoading();
    web.dateTime($(".startTime").text(),$(".endTime").text());
    web.dateTime1($(".startTime").text(),$(".endTime").text());
    webFun.returnDataWithTime();
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
	setInterval(function(){
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
					 getTacticsEarnings();
				 }else{
					 getRiskKpi();
				 };
			}; 	
		}
		
    },"50000"); 
};

 /**
 *desc：屏幕变化执行
 *@param  type:1收益概览  2交易详情 3每日持仓 4策略收益 5风险指标;
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
  }else if(type==4){
	  getTacticsEarnings(true);
  }else if(type==5){
	  getRiskKpi(true);
  }else{
	  alert("参数错误：002");
  };   
};

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
	  getEhartdata();
  }else{
	  $(obj).attr("type",'1'); 
	  $(obj).text("查看全部策略收益>>");
	  $(".lefttitlless").text("当日策略收益");
	  $(".detail-time-echartsmap .comm_input_box").hide();
	  getEhartdata();
  };
}


/**
 *desc:构造函数实例化
 */
var webFun = new WebFun();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	if(str.isblank(rs.data)){
        		$(".detail-intro dd").text("--");
            
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
	//接口
	var link=common.sysurl()+"/firmoffer/findNowCharDate.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start2").val());
	var endTime=common.transdate($("#end2").val());
	var uew=$(".uew-radio .on").attr("data");
	var type=$(".qiehuanpic").attr("type");//当日还是全部收益，1表示当日,2表示全部
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime+"&type="+type;

    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	var obj=rs.data;
        	if(uew==1){//累计收益
        		obj["accumulated"]=web.dataIsEchats2(obj["accumulated"]);//我的累计收益
        		obj["benchmark"]=web.dataIsEchats2(obj["benchmark"]);//沪深300
        		obj["benchmarkSz50"]=web.dataIsEchats2(obj["totalBenchmarkSz50"]);//上证50
        		obj["benchmarkZz500"]=web.dataIsEchats2(obj["totalBenchmarkZz500"]);//中证500
        	}else if(uew==2){//相对收益
        		obj["relativereturn"]=web.dataIsEchats2(obj["relativereturn"]);//沪深300
        		obj["relativeReturnSz50"]=web.dataIsEchats2(obj["relativeReturnSz50"]);//上证50
        		obj["relativeReturnZz500"]=web.dataIsEchats2(obj["relativeReturnZz500"]);//中证500
        	}else if(uew==3){//对数收益
        		obj["logarithmicreturn"]=web.dataIsEchats2(obj["logarithmicreturn"]);//对数收益
        	}else{
        	    alert("错误：004");
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
function getDeal(loadPage){
	if(loadPage){
	   $("#curPage").val(1);
     }
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
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
	var link=common.sysurl()+"/firmoffer/findNowDailyList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start1").val());
	var endTime=common.transdate($("#end1").val());
	var curPage = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime+"&pageNum="+curPage+"&pageSize="+pageSize;
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
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
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	riskKpiToHtml(rs);
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
	//1、模拟收益
	$(".accumulatedReturn").text(web.numbersFmt('1','2',obj['accumulatedReturn']));
	if(obj['accumulatedReturn']>=0){
		$(".accumulatedReturn").css("color",'red');	
	}else{
		$(".accumulatedReturn").css("color",'green');
	};
	//2、模拟年化收益
	$(".yield").text(web.numbersFmt('1','2',obj['yield']));
	if(obj['yield']>=0){
		$(".yield").css("color",'red');	
	}else{
		$(".yield").css("color",'green');
	};
	//3、基准收益
	$(".benchmark").text(web.numbersFmt('1','2',obj['benchmark']));
	if(obj['benchmark']>=0){
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
	//7、收益波动率
	$(".returnVolatlity").text(web.numbersFmt('2','4',obj['returnVolatlity']));
	//8、最大回撤 
	$(".maxRetrace").text(web.numbersFmt('2','4',obj['maxRetrace']));
	//我的持仓
	var obj2=new Object();
	obj2['list']=obj['theDplist'];
	$("#chichang-content").html(template("chicang-script",obj2));
	//我的当日交易
	var obj3=new Object();
	obj3['list']=obj['theTclist'];
	$("#dangrijiaoyi-content").html(template("dangrijiaoyi-script",obj3));
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
	var html='<table class="table morearray">';
		html=html+'<thead>';
		html=html+'	<tr>';
		html=html+'	  <th class="comm text-center" width="110">日期</th>';
		html=html+'	  <th class="comm" width="190">股票名称</th>';
		html=html+'	  <th class="comm" width="110">收盘价</th>';
		html=html+'	  <th class="comm" width="110">持仓数量(股)</th>';
		html=html+'      <th class="comm" width="110">成交价(元)</th>';
		html=html+'	  <th class="comm" width="110">持仓金额(元)</th>';
		html=html+'	  <th class="comm" width="110">收益(元)</th>';
		html=html+'	</tr>';	
		html=html+'   </thead>';
		html=html+'   <tbody>';
		html=html+'   <tr><td colspan="100">暂无数据</td></tr>';
		html=html+'   </tbody>';
		html=html+'</table>';
	  $("#position-content").html("");
	}else{
	  $("#position-content").html(template("position-script",obj));
	}
}


//策略收益和风险写入html
function tacticsEarningsToHtml(obj){
	if(str.isblank(obj.data)){
	  $("#shouyifengxian-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	}else{
	  $("#shouyifengxian-content").html(template("shouyifengxian-script",obj));
	}
}



//风险指标写入html
function riskKpiToHtml(obj){
	$("#riskKpi-content").html(template("riskKpi-script",obj));
}


















