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
	webFun.strategyChanageLayout();
	web.getBase();  
	getEarnings();
	findPosition();
	getScoreTrends();
	web.dateTime3($(".startTime").text(),$(".endTime").text())
	getDivisor();
 	findWarehouse();
 	web.dataLoading();
});


/**
 *desc：加载层
 *@return void;
 */
WebFun.prototype.loadings=function(){
	web.dataLoading(1);	
	var setInterval1=setInterval(function(index){
		var click=$("#click").val();
		var loadinghidden=$("#loadinghidden").val();
		  if(loadinghidden=="6"){
			 web.dataLoading(-1);
			 $("#loadinghidden").val("0");
			 $("#click").val("0");
			 clearInterval(setInterval1);
		  };
    },"1000"); 
};



/**
 *desc:构造函数 
 *@param void;
 *@return void;
 */
function WebFun(){};

/**
 *desc：修改页面状态
 *@param void;
 *@return void;
 */
WebFun.prototype.strategyChanageLayout=function(){
  var form=common.GetQueryString("form");//1表示实盘模拟，2表示历史回测
  if(form==1){
	 $("#strategyKind").html("实盘模拟");
  }else if(form==2){
	 $("#strategyKind").html("历史回测");
  };
	
};

/**
 *desc：雷达图时间选择切换
 *@param type 1表示上一个月 2表示下一个月;
 *@return void;
 */
WebFun.prototype.radarTime=function(type){
	var basetime=getBaseTime();
	var	startTime=basetime["startTime"];	
	var	endTime=basetime["endTime"];	

	$start=new Date(parseInt(startTime));
	$end=new Date(parseInt(endTime));
	var yearS=$start.getFullYear(); 
	var monthS=$start.getMonth()+1; 
	var yearE=$end.getFullYear(); 
	var monthE=$end.getMonth()+1; 
	$newstart=common.transdate(yearS+"-"+monthS+"-"+"01");
	$newend=common.transdate(yearE+"-"+monthE+"-"+"01");
	$radartimes=parseInt($("#radartimes").attr("time"));
	$preMonth=common.transdate(webFun.getPreMonth(common.getLocalTime($radartimes,"ms")+"-01"));
	$getNextMonth=common.transdate(webFun.getNextMonth(common.getLocalTime($radartimes,"ms")+"-01"));
    if(type==1){
    	if($preMonth<$newstart){
    		layer.alert("没有更多数据了！", {
 	          icon: 5,
 	        },function(index){
 	          parent.layer.close(index);
 	        });
    	}else{
    		getDivisor($preMonth);
    	}
    }else if(type==2){
    	if($getNextMonth>$newend){
    		layer.alert("没有更多数据了！", {
   	          icon: 5,
   	        },function(index){
   	          parent.layer.close(index);
   	        });
    	}else{
    		getDivisor($getNextMonth);
    	}
    }else{
    	
    }
};


/** 
 * 获取上一个月 
 * 
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25 
 */ 
WebFun.prototype.getPreMonth=function(date) {  
    var arr = date.split('-');  
    var year = arr[0]; //获取当前日期的年份  
    var month = arr[1]; //获取当前日期的月份  
    var day = arr[2]; //获取当前日期的日  
    var days = new Date(year, month, 0);  
    days = days.getDate(); //获取当前日期中月的天数  
    var year2 = year;  
    var month2 = parseInt(month) - 1;  
    if (month2 == 0) {  
        year2 = parseInt(year2) - 1;  
        month2 = 12;  
    }  
    var day2 = day;  
    var days2 = new Date(year2, month2, 0);  
    days2 = days2.getDate();  
    if (day2 > days2) {  
        day2 = days2;  
    }  
    if (month2 < 10) {  
        month2 = '0' + month2;  
    }  
    var t2 = year2 + '-' + month2 + '-' + day2;  
    return t2;  
} 



/** 
 * 获取下一个月 
 * 
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25 
 */          
WebFun.prototype.getNextMonth=function(date) {
    var arr = date.split('-');  
    var year = arr[0]; //获取当前日期的年份  
    var month = arr[1]; //获取当前日期的月份  
    var day = arr[2]; //获取当前日期的日  
    var days = new Date(year, month, 0);  
    days = days.getDate(); //获取当前日期中的月的天数  
    var year2 = year;  
    var month2 = parseInt(month) + 1;  
    if (month2 == 13) {  
        year2 = parseInt(year2) + 1;  
        month2 = 1;  
    }  
    var day2 = day;  
    var days2 = new Date(year2, month2, 0);  
    days2 = days2.getDate();  
    if (day2 > days2) {  
        day2 = days2;  
    }  
    if (month2 < 10) {  
        month2 = '0' + month2;  
    }  
  
    var t2 = year2 + '-' + month2 + '-' + day2;  
    return t2;  
}  


/**
 *desc:构造函数实例化
 */
var webFun = new WebFun();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
/*读取收益概览*/
function getEarnings(){
	//接口
	var form=common.GetQueryString("form");
	if(form==1){
		var link=common.sysurl()+"/firmoffer/findNowIncomeSituation.m";
	}else{
		var link=common.sysurl()+"/firmoffer/findHisIncomeSituation.m";
	}
	
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



/*得分走势*/
function getScoreTrends(){
	var basetime=getBaseTime();
	//接口
	var link=common.sysurl()+"/firmoffer/queryGradeDtoList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start3").val());
	var endTime=common.transdate($("#end3").val());
	if(str.isblank(startTime)){
		startTime=basetime["startTime"];	
	};
	if(str.isblank(endTime)){
		endTime=basetime["endTime"];	
	};
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&startTime="+startTime+"&endTime="+endTime;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	var obj= new Object();
        		obj["list"]=web.dataIsEchats4(rs.data['gradeDto']);//我的累计收益
        	ehartjs.setEhartjs(obj);	
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


/*我的持仓*/
function findPosition(){
	//接口
	var link=common.sysurl()+"/firmoffer/findPosition.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId="";
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId;
	//ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs){
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	//我的持仓
        	var obj=new Object();
        	obj['list']=rs['data'];
        	positionToHtml(obj);
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

/*最新调仓*/
function findWarehouse(){
	//接口
	var link=common.sysurl()+"/firmoffer/queryMixSpace.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var groupId=common.GetQueryString("groupId");//组id
	var dataLink="testId="+testId+"&strategyId="+strategyId;
	//ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs){
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	//最新调仓
        	var objf=new Object();
        	var obj=new Object();
        	obj['time']=new Date().getTime();
        	obj['list']=rs['data'];
        	warehouseToHtml(obj);
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




/*月度因子*/
function getDivisor(time){
	//接口
	var link=common.sysurl()+"/firmoffer/querystrategymonthanalysisList.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var startTime=common.transdate($("#start3").val());
	var endTime=common.transdate($("#end3").val());
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId="";
	}
	var dataLink="testId="+testId+"&strategyId="+strategyId+"&time="+time+"&groupId="+groupId;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	if(str.isblank(time)){
        		$("#radartimes").attr("time",rs.data['theMonth']);
            	$("#radartimes").text(common.getLocalTime(rs.data['theMonth']/1000,"ms"));
        	}else{
        		$("#radartimes").attr("time",time);
            	$("#radartimes").text(common.getLocalTime(time/1000,"ms"));
        	};
        	
        	var obj= new Object();
        	//左边
        	var objleft=new Array()
        	var dbL=rs.data['monthanalysisList'];
        	objleft[1]="市净率倒数";
        	objleft[2]="市盈率倒数";
        	objleft[3]="贝塔";
        	objleft[4]="收益波动率";
        	objleft[5]="销售毛利率";
        	objleft[6]="日换手率";
        	objleft[7]="日收益率";
        	objleft[8]="账面杠杆";
        	objleft[9]="市值对数";
        	var objleftLength=objleft.length;
        	var arrdbL=new Array();
    		var valueL=new Array();
    		//获取最大值
    		var maxL=new Array();
    		var objdbls=new Object();
    		for(var i=1;i<dbL.length;i++){
    			maxL.push(dbL[i]['profit']);
    			$type=dbL[i]['type']+"a";
    			$profit=dbL[i]['profit'];
    			objdbls[$type]=$profit;
    		};
    
    		var maxnuml=Math.max.apply(null,maxL)*1.3;
        	for(var i=1;i<objleftLength;i++){
        		var objl=new Object();
			    objl['name']=objleft[i];
			    $is=i+"a";
			    if(str.isblank(objdbls[$is])){
			    	var profit=0;
			    }else{
			    	var profit=objdbls[$is];
			    }
			    valueL.push(profit);
			    objl['max']=maxnuml;
			    arrdbL.push(objl);
        	}
        	
        	
        	
        	
        	
        	//右边
        	var objright=new Array()
        	var dbR=rs.data['industryFactorList'];
        	objright[1]="采掘";
        	objright[2]="传媒";
        	objright[3]="电气设备";
        	objright[4]="电子";
        	objright[5]="房地产";
        	objright[6]="纺织服装";
        	objright[7]="非银金融";
        	objright[8]="钢铁";
        	objright[9]="公用事业";
        	objright[10]="国防军工";
        	objright[11]="化工";
        	objright[12]="机械设备";
        	objright[13]="计算机";
        	objright[14]="家用电器";
        	objright[15]="建筑材料";
        	objright[16]="建筑装饰";
        	objright[17]="交通运输";
        	objright[18]="农林牧渔";
        	objright[19]="汽车";
        	objright[20]="轻工制造";
        	objright[21]="商业贸易";
        	objright[22]="食品饮料";
        	objright[23]="通信";
        	objright[24]="休闲服务";
        	objright[25]="医药生物";
        	objright[26]="银行";
        	objright[27]="有色金属";
        	objright[27]="综合";
        	var objrightLength=objright.length;
        	var arrdbR=new Array();
    		var valueR=new Array();
    		//获取最大值
    		var maxR=new Array();
    		var objdbrs=new Object();
    		for(var i=1;i<dbR.length;i++){
    			maxR.push(dbR[i]['value']);
    			$industryId=dbR[i]['industryId']+"a";
    			$value=dbR[i]['value'];
    			objdbrs[$industryId]=$value;
    		};
    		var maxnumr=Math.max.apply(null,maxR)*1.3;
    		
        	for(var i=1;i<objrightLength;i++){
        		var objR=new Object();
        		objR['name']=objright[i];
        		$is=i+"a";
			    if(str.isblank(objdbrs[$is])){
			      var value=0;
			    }else{
			      var value=objdbrs[$is];
			    }
			    valueR.push(value);
        		objR['max']=maxnumr;
			    arrdbR.push(objR);
        	}
        	
        	
        	obj['leftdb']=arrdbL;
        	obj['leftv']=valueL;
        	
        	obj['rightdb']=arrdbR;
        	obj['rightv']=valueR;
        	
        	console.log(obj);
        	ehartjs.setEhartjsRadar(obj);	
        	
        	
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


//我的持仓写入html
function positionToHtml(obj){
	if(str.isblank(obj.list)){
	  $("#position-content").html("");
	}else{
	  $("#position-content").html(template("position-script",obj));
	}
}


//最新调仓写入html
function warehouseToHtml(obj){
	var obj2=new Object();
	obj2['listd']=obj;
	if(str.isblank(obj.list)){
		var html='<dl class="title-table"><dt>最新调仓</dt></dl>';
		    html=html+'<div class="tablebox">';
		    html=html+'<table class="table">';
		    html=html+'<thead>';
		    html=html+'<tr>';
		    html=html+'<th class="comm" width="110">股票名称</th>';
		    html=html+'<th class="comm" width="110">时间</th>';
		    html=html+'<th class="comm" width="110">买卖方向</th>';
		    html=html+'<th class="comm" width="110">成交均价(元)</th>';
		    html=html+'<th class="comm" width="110">成交仓位(元) </th>';
		    html=html+'<th class="comm" width="110">仓位变化(元)</th>';
		    html=html+'</tr> ';
		    html=html+'</thead> '; 
		    html=html+'<tbody>';
		    html=html+'<tr>';
		    html=html+'<td colspan="100">暂无数据</td>';
		    html=html+'</tbody>';
		    html=html+'</table>';
		    html=html+'</div>';
	  $("#warehouse-content").html(html);
	}else{
	  $("#warehouse-content").html(template("warehouse-script",obj2));
	}
}


















