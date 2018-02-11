// +----------------------------------------------------------------------
// | desc: layout js
// +----------------------------------------------------------------------
// | Created By 2016-11-28
// +----------------------------------------------------------------------
// | Author:张亮
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();

/**
 *desc:初始化加载
 *@param void;
 *@return void;
 */
$(function(){
   $.cookie("echatscookie1",null);
   $.cookie("echatscookie2",null);
   $("#loadinghidden").val("0");
   $("#click").val("0");
   web.setBaseTimes();
   web.autoheight();
   web.resizeDo();
   web.strategyTabsCheck();
   web.tplinit();
});


/**
 *desc：页面加载层
 *@return void;
 */
Web.prototype.dataLoading=function(type){
	if(type==1){
	  layer.load(2,{shade: [0.1,'#000']});
	}else{
      layer.closeAll();	
	}
};


/**
 *desc:停止实盘模拟
 *@param 1表示停止实盘模拟，0表示停止历史回测
 *@return void;
 */
Web.prototype.stops=function(type,num){
	if(num=='1'){
	 var title="停止实盘模拟";
	 var hint="停止此次实盘模拟后，不再进行实盘模拟";	
	}else{
	  var title="停止历史回测";
	  var hint="停止此次历史回测后，不再进行历史回测";	
	};
	layer.confirm(hint, {
		 title:title
    }, function(index){
    	stopTest(type,num);
	});
};





/**
 * DES:跳转到测试列表中
 * @param type==1表示实盘，2表示模拟
 * @return void
 */
Web.prototype.goTestList=function(type){
   var strategyId=common.GetQueryString("strategyId");
   var testId=common.GetQueryString("testId");
   var url =common.sysurl()+"/frontend/simulation.do?type="+type+"&strategyId="+strategyId+"&testId="+testId;
       location.href=url;	   

}


/**
 * DES:转换成图表需要的格式输出
 * @return void
 */
Web.prototype.dataIsEchats=function(obj){
	if(str.isblank(obj)){
		var obj=new Object();
	};
	if(obj['times']==null){
		obj['times']=['0'];
		var arr=new Array();
	    arr['0']=0;
	    arr['1']=0;
	    obj['values']=arr;
	}else{
    	for(var i=0;i<obj['values'].length;i++){
    		var arr=new Array();
    		    arr['0']=i;
    		    arr['1']=web.numbersFmt('2','2',obj['values'][i]);
    		obj['values'][i]=arr;
    	};
	}

	return obj;
}

/**
 * DES:转换成图表需要的格式输出
 * @return void
 */
Web.prototype.dataIsEchats2=function(obj){
	if(str.isblank(obj)){
		var obj=new Object();
	};
	if(obj['times']==null){
		obj['times']=['0'];
		var arr=new Array();
	    arr['0']=0;
	    arr['1']=0;
	    obj['values']=arr;
	}else{
    	for(var i=0;i<obj['values'].length;i++){
    		var arr=new Array();
    		    arr['0']=i;
    		    arr['1']=web.numbersFmt('2','2',obj['values'][i]*100);
    		obj['values'][i]=arr;
    		obj['times'][i]=common.getLocalTime(obj['times'][i]);
    	};
	}

	return obj;
}


/**
 * DES:转换成图表需要的格式输出
 * @return void
 */
Web.prototype.dataIsEchats3=function(obj){
	if(str.isblank(obj)){
		var obj=new Object();
	};
	if(obj['times']==null){
		obj['times']=['0'];
		var arr=new Array();
	    arr['0']=0;
	    arr['1']=0;
	    obj['values']=arr;
	}else{
    	for(var i=0;i<obj['values'].length;i++){
    		var arr=new Array();
    		    arr['0']=i;
    		    arr['1']=web.numbersFmt('2','2',obj['values'][i]*100);
    		obj['values'][i]=arr;
    		obj['times'][i]=common.getLocalTime(obj['times'][i],'d');
    	};
	}

	return obj;
}


/**
 * DES:转换成图表需要的格式输出
 * @return void
 */
Web.prototype.dataIsEchats4=function(obj){
	if(str.isblank(obj)){
		var obj=new Object();
	};
	if(obj['times']==null){
		obj['times']=['0'];
		var arr=new Array();
	    arr['0']=0;
	    arr['1']=0;
	    obj['values']=arr;
	}else{
    	for(var i=0;i<obj['values'].length;i++){
    		var arr=new Array();
    		    arr['0']=i;
    		    arr['1']=web.numbersFmt('2','2',obj['values'][i]);
    		obj['values'][i]=arr;
    		obj['times'][i]=common.getLocalTime(obj['times'][i],'d');
    	};
	}

	return obj;
}


/**
 * DES:开始测试跳转
 * @param type==1表示实盘，2表示模拟
 * @return void
 */
Web.prototype.goStartTest=function(type){
  var strategyId=common.GetQueryString("strategyId");
  if(type=='1'){
	 var url =common.sysurl()+"/frontend/simulationPara.do?strategyId="+strategyId;
  }else{
	 var url =common.sysurl()+"/frontend/simParaHistory.do?strategyId="+strategyId;  
  }
  location.href=url;
}


/**
 * DES:点击返回上一页
 * @param type==1表示策略详返回，2表示得分详情返回
 * @return void
 */
Web.prototype.goPrev=function(type){
	 if(type==1){
	   var form=common.GetQueryString("form");
	   var strategyId=common.GetQueryString("strategyId");
	   var testId=common.GetQueryString("testId");
	   var url =common.sysurl()+"/frontend/simulation.do?type="+form+"&strategyId="+strategyId+"&testId="+testId;
	   	location.href=url; 
	 }else if(type==2){
		 var form=common.GetQueryString("form");
		 if(form==1){
			 location.href=common.sysurl()+"/web/frontend/strategydetail/firmStrategyDetails.do"+location.search; 
		 }else{
			 location.href=common.sysurl()+"/web/frontend/strategydetail/hisStrategyDetails.do"+location.search;  
		 }
	 }else{
		 alert("错误代码：003");
	 }
	}


/**
 *desc:构造函数 
 *@param void;
 *@return void;
 */
function Web(){};

 /**
 *desc：屏幕变化执行
 *@param void;
 *@return void;
 */
Web.prototype.resizeDo=function(){
	$(window).resize(function(){
       web.autoheight();
	});
};



/**
 * DES:N/A不适用
 * @param num
 * @return void
 */
Web.prototype.na=function(num){
   if(num==0||num=="0"){
	  return num; 
   }else if(str.isblank(num)){
	  return  "N/A不适用";  
   }else{
	  return num;
   };
}



 /**
 *desc：布局高度高度自适应
 *@param void;
 *@return void;
 */
Web.prototype.autoheight=function(){
  $height=$(window).height();
  $(".strategyLeft").css('height',$height-121);
  $(".strategyRight").css('height',$height-130);
};


/**
 *desc：实盘历史选中状form 1表示实盘 2表示历史
 *@param void;
 *@return void;
 */
Web.prototype.strategyTabsCheck=function(){
	$form=common.GetQueryString("form");
	if($form==1){
	  $(".his").addClass("on");
	}else{
	  $(".firm").addClass("on");
	}
};


/**
 *desc：侧栏选中状态
 *@param void;
 *@return void;
 */
Web.prototype.strategyLeftCheck=function(obj){
  $(".strategyLeft a").removeClass("on");
  $(obj).addClass("on");	
};



/**
 *desc：侧栏加载新的页面
 *@param void;
 *@return void;
 */
Web.prototype.strategyLoadNewPage=function(obj){
   $("#loadinghidden").val("0");
   $("#click").val("1");
   web.strategyLeftCheck(obj);
   var data_tab=$(obj).attr("data-tab");
   var data_content=$(obj).attr("data-content");
   $(".detailedBox").hide();
   $("#detailed_"+data_tab).show();
   webFun.getData(data_content);
   if(data_content==2||data_content==3){
	 $("#pagenation").show();  
   }else{
	 $("#pagenation").hide();   
   };
};


/**
 *desc:提示登录失败并且跳转到登录页面
 *@return void;
 */
Web.prototype.failToLogin=function(message){
  layer.alert(message,{
    icon: 7,
  },function(index){
    location.href=common.sysurl()+'/frontend/login.do';
    layer.close(index);
  });
};


/**
 *desc:根据策略状态和记录状态配置页面布局。0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪,4历史掉线，5实时掉线
 *@return void;模拟单次记录模拟状态，测试状态 0.等待中 1. 测试中 2.已完成 3.失败
 */
Web.prototype.statusConfigLayout=function(strategy,test){
  if(!$(".strategyTool").hasClass("strategyStatus"+strategy)){
	  $(".strategyTool").removeClass("strategyStatus0"); 
	  $(".strategyTool").removeClass("strategyStatus1"); 
	  $(".strategyTool").removeClass("strategyStatus2"); 
	  $(".strategyTool").removeClass("strategyStatus3"); 
	  $(".strategyTool").removeClass("strategyStatus4"); 
	  $(".strategyTool").removeClass("strategyStatus5"); 
  };
  if(!$(".baseintro0").hasClass("testStatus"+test)){
	  $(".baseintro0").removeClass("testStatus0"); 
	  $(".baseintro0").removeClass("testStatus1"); 
	  $(".baseintro0").removeClass("testStatus2"); 
	  $(".baseintro0").removeClass("testStatus3"); 
  };
  $(".strategyTool").addClass("strategyStatus"+strategy);
  $(".baseintro0").addClass("testStatus"+test);
  
  //历史回测跳转，完成跳转
  var form=common.GetQueryString("form");//1表示实盘模拟，2表示历史回测
  var groupId=common.GetQueryString("groupId");
  if(form==2&&str.isblank(groupId)&&test==2&&test==3){
	   web.goTestList(2);
  };
};





/**
 * 时间秒数格式化
 * @param value 秒数
 * @returns {*} 格式化后的时分秒
 */
Web.prototype.toMin=function(value){
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


/**
 *desc:数据格式化
 *@param type 1表示加%,0表示不加%。
 *@param num 保留多少位。
 *@param number 需要被格式化的数据。
 *@return void;
 */
Web.prototype.numbersFmt=function(type,num,number){
  if(number=='0'||number=="0.00"){
	  var number=Number(number); 
	  if(type=='1'){
		  number=number*100;
		  number=number.toFixed(num);
		  number=number+"%";
	  }else{
		  number=number.toFixed(num);
	  }
	  return number; 
  }else if(number==""||number==null||number=="null"){
	  return "--"; 
  }else{
	  var number=Number(number); 
	  if(type=='1'){
		  number=number*100;
		  number=number.toFixed(num);
		  number=number+"%";
	  }else{
		  number=number.toFixed(num);
	  }
	  return number;
  }

};



/**
 *desc:选择时间
 *@return void;
 */
Web.prototype.dateTime=function(time,time2){
	var start = {
	  elem: '#start',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	     end.min = datas; //开始日选好后，重置结束日的最小日期
	     end.start = datas //将结束日的初始值设定为开始日
	     getDeal();
	  }
	};
	var end = {
	  elem: '#end',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	    start.max = datas; //结束日选好后，重置开始日的最大日期
	    getDeal();
	  }
	};
	laydate(start);
	laydate(end);
};


/**
 *desc:选择时间-1
 *@return void;
 */
Web.prototype.dateTime1=function(time,time2){
	var start = {
	  elem: '#start1',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	     end.min = datas; //开始日选好后，重置结束日的最小日期
	     end.start = datas //将结束日的初始值设定为开始日
	     getPosition();
	  }
	};
	var end = {
	  elem: '#end1',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	    start.max = datas; //结束日选好后，重置开始日的最大日期
	    getPosition();
	  }
	};
	laydate(start);
	laydate(end);
};


/**
 *desc:选择时间-1
 *@return void;
 */
Web.prototype.dateTime2=function(time,time2){
	var start = {
	  elem: '#start2',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	     end.min = datas; //开始日选好后，重置结束日的最小日期
	     end.start = datas //将结束日的初始值设定为开始日
	     getEhartdata();
	  }
	};
	var end = {
	  elem: '#end2',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	    start.max = datas; //结束日选好后，重置开始日的最大日期
	    getEhartdata();
	  }
	};
	laydate(start);
	laydate(end);
};


/**
 *desc:选择时间-1
 *@return void;
 */
Web.prototype.dateTime3=function(time,time2){
	var start = {
	  elem: '#start3',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	     end.min = datas; //开始日选好后，重置结束日的最小日期
	     end.start = datas //将结束日的初始值设定为开始日
	     getScoreTrends();
	  }
	};
	var end = {
	  elem: '#end3',
	  format: 'YYYY-MM-DD',
	  min: time, //设定最小日期为当前日期
	  max:time2, //最大日期
	  istime: true,
	  istoday: false,
	  choose: function(datas){
	    start.max = datas; //结束日选好后，重置开始日的最大日期
	    getScoreTrends();
	  }
	};
	laydate(start);
	laydate(end);
};


//分页函数
function pageFun(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
};


//分页函数
function setPageNum(thiz, pageType) {
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  $("#curPage").val(pno);
	  $pages=$(".strategyLeft .on").attr("data-content");
	  if($pages==2){
		  getDeal(false); 
	  }else if($pages==3){
		  getPosition(false); 
	  };
	  
	});
};


/**
 *desc:描红描绿
 *@param num 大于等于0 red， 小于0 green。
 *@return void;
 */
Web.prototype.redOrgreen=function(num){
  if(num<0){
	  return "green";
  }else{
	  return "red"; 
  }
};


/**
 *desc:为空返回--
 *@return void;
 */
Web.prototype.numFmt1=function(number){
	if(number=="0"||number==0){
		 return number; 
	  }else if(str.isblank(number)){
	     return "--";
	  }else{
		return number;   
	  }
};

/**
 *desc:描红描绿
 *@param num 大于等于0 red， 小于0 green。
 *@return void;
 */
Web.prototype.redOrgreen2=function(num1,num2){
  if((num1-num2)<0){
	  return "green";
  }else{
	  return "red"; 
  }
};


//temples模板自定义函数
Web.prototype.tplinit = function(){
	template.helper("formatNumber",common.formatNumber);
	template.helper("getLocalTime",common.getLocalTime);
	template.helper("numbersFmt",web.numbersFmt);
	template.helper("redOrgreen",web.redOrgreen);
	template.helper("numFmt1",web.numFmt1);
	template.helper("na",web.na);
	template.helper("redOrgreen2",web.redOrgreen2);
}


//查看评分详情页面
Web.prototype.gotoStrategyScoreDetails= function(){
	  var href=common.sysurl()+"/web/frontend/strategydetail/strategyScoreDetails.do"+location.search; 
	  $("#strategyScoreDetails").attr("href",href);
}


//给全局时间赋值

Web.prototype.setBaseTimes= function(){
	var basetime=getBaseTime();
	var	startTime=basetime["startTime"];	
	var	endTime=basetime["endTime"];	
	
	$(".endTime").text(common.getLocalTime(endTime,'d'));
	$(".endTime").attr("data-time",endTime);
	$(".startTime").text(common.getLocalTime(startTime,'d'));
	$(".startTime").attr("data-time",startTime);
	
	
	$("#start").val(common.getLocalTime(startTime,'d'));
	$("#end").val(common.getLocalTime(endTime,'d'));

	$("#start1").val(common.getLocalTime(startTime,'d'));
	$("#end1").val(common.getLocalTime(endTime,'d'));
	
	$("#start2").val(common.getLocalTime(startTime,'d'));
	$("#end2").val(common.getLocalTime(endTime,'d'));
	
	$("#start3").val(common.getLocalTime(startTime,'d'));
	$("#end3").val(common.getLocalTime(endTime,'d'));
}


//设置加载完ajax的值
Web.prototype.ajaxed= function(status){
	if(status){
		$("#loadinghidden").val(parseInt($("#loadinghidden").val())+1);
	}else{
		$("#loadinghidden").val("0");
	}
}


/**
 *desc:构造函数实例化
 */
var web = new Web();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
/*读取基本信息*/
Web.prototype.getBase=function(typeo){
	//接口
	var form=common.GetQueryString("form");//1表示实盘模拟，2表示历史回测
	var groupId=common.GetQueryString("groupId");//1表示实盘模拟，2表示历史回测
	if(form==1){
	  var link=common.sysurl()+"/firmoffer/findTStrategyDto.m";
	}else{
	  var link=common.sysurl()+"/firmoffer/findHisTStrategyDto.m";	
	}
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	if(form==1){
	  var dataLink="testId="+testId+"&strategyId="+strategyId;
	}else{
	  var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId;	
	}
	
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	web.ajaxed(true);
    	if(rs.code==411){
    		Web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	web.baseToHtml(rs.data);
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


/*下载EXCEL*/
Web.prototype.downExcel=function(type){
	//接口
	var name=$(".strategyName em").text()+"_";
	var basetime=getBaseTime();
	var groupId=common.GetQueryString("groupId");//组id
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
    //ajax
    $link=common.sysurl()+"/firmoffer/exportExcle.m";  
    $datalink="?strategyId="+strategyId+"&testId="+testId+"&type="+type;
    if(type==1){
      name=name+"实盘模拟_我的持仓";
      $datalink=$datalink;
    }else if(type==2){
      name=name+"实盘模拟_我的当日交易";
      $datalink=$datalink;
    }else if(type==3){
       //时间
	    var startTime=common.transdate($("#start").val());
		var endTime=common.transdate($("#end").val());
		if(str.isblank(startTime)){
			startTime=basetime["startTime"];	
		};
		if(str.isblank(endTime)){
			endTime=basetime["endTime"];	
		};
      name=name+"实盘模拟_交易详情("+common.getLocalTime(startTime/1000,'d')+"至"+common.getLocalTime(endTime/1000,'d')+")";
      $datalink=$datalink+"&startTime="+startTime+"&endTime="+endTime+"&pageNum=1"+"&pageSize=1000";   	
    }else if(type==4){
    	//时间
	    var startTime=common.transdate($("#start1").val());
		var endTime=common.transdate($("#end1").val());
		if(str.isblank(startTime)){
			startTime=basetime["startTime"];	
		};
		if(str.isblank(endTime)){
			endTime=basetime["endTime"];	
		};
		
    	name=name+"实盘模拟_每日持仓("+common.getLocalTime(startTime/1000,'d')+"至"+common.getLocalTime(endTime/1000,'d')+")";
        $datalink=$datalink+"&startTime="+startTime+"&endTime="+endTime+"&pageNum=1"+"&pageSize=1000";   	
    }else if(type==5){
    	//时间
	    var startTime=common.transdate($("#start").val());
		var endTime=common.transdate($("#end").val());
		if(str.isblank(startTime)){
			startTime=basetime["startTime"];	
		};
		if(str.isblank(endTime)){
			endTime=basetime["endTime"];	
		};
		
    	name=name+"历史回测_交易详情("+common.getLocalTime(startTime/1000,'d')+"至"+common.getLocalTime(endTime/1000,'d')+")";
      $datalink=$datalink+"&startTime="+startTime+"&endTime="+endTime+"&pageNum=1"+"&pageSize=1000"+"&groupId="+groupId;  	
    }else if(type==6){
    	//时间
	    var startTime=common.transdate($("#start1").val());
		var endTime=common.transdate($("#end1").val());
		if(str.isblank(startTime)){
			startTime=basetime["startTime"];	
		};
		if(str.isblank(endTime)){
			endTime=basetime["endTime"];	
		};
		
    	name=name+"历史回测_每日持仓("+common.getLocalTime(startTime/1000,'d')+"至"+common.getLocalTime(endTime/1000,'d')+")";
        $datalink=$datalink+"&startTime="+startTime+"&endTime="+endTime+"&pageNum=1"+"&pageSize=1000"+"&groupId="+groupId;    	
    }else if(type==7){
    	name=name+"得分详情_我的持仓";
        $datalink=$datalink+"&groupId="+groupId;   	
    }else if(type==8){
    	name=name+"得分详情_最新调仓";
    	$datalink=$datalink;
    };
    $datalink=$datalink+"&name="+name;
    var href=$link+$datalink;
    location.href=href;
}

/*停止测试*/
function stopTest(type,num){
	//接口
	var link=common.sysurl()+"/firmoffer/stopTheOffer.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var dataLink="testId="+testId+"&type="+type;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
 	         if(num=='1'){
        		location.href=common.sysurl()+"/frontend/simulation.do?type=1&strategyId="+strategyId;
        	 }else{
        		location.href=common.sysurl()+"/frontend/simulation.do?type=2&strategyId="+strategyId;
        	 };
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


/*取出全局开始和结束时间*/
function getBaseTime(){
	var objtime=new Object();
	//接口
	var link=common.sysurl()+"/firmoffer/queryTime.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var groupId=common.GetQueryString("groupId");//组id
	if(str.isblank(groupId)){
		groupId='';
	}
    var dataLink="testId="+testId+"&strategyId="+strategyId+"&groupId="+groupId;
    //ajax
    ajax.tranDataParams(link,dataLink, false, "post", function(rs) {
    	if(rs.code==411){
    		web.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
        	$startTime=rs.data["startTime"];
        	$endTime=rs.data["endTime"];
        	if(str.isblank($startTime)){
        		$startTime=Date.parse(new Date());
        	};
            if(str.isblank($endTime)){
            	$endTime=Date.parse(new Date());
        	};
        	objtime["startTime"]=$startTime;
            objtime["endTime"]=$endTime;
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
    return objtime;
}

/*++++++++++++++++++++++++++++++++++++++++++++++写入html++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//基本信息写入html
Web.prototype.baseToHtml=function(obj){
	//strategyTool 
	$(".strategyName em").text(obj['strategyName']);
 	web.statusConfigLayout(obj['status'],obj['testStatus']);
 	$("#teststatusOff").val(obj['testStatus']);
	$(".hisOfferNum").text(obj['hisOfferNum']);
 	/*baseintro*/
	
	//运行天数
	$startTimex=obj['startTime'];
	$endTimex=obj['endTime'];
	if(str.isblank($startTimex)){
		$startTimex=Date.parse(new Date());
	};
    if(str.isblank($endTimex)){
    	$endTimex=Date.parse(new Date());
	};
	
	$(".runday").text(Math.floor(($endTimex-$startTimex)/864000)/100);
	
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
	//用时huiceyongshi
	$(".huiceyongshi").text(web.toMin(obj['overTime']));
	//实际用时shijiyongshi
	$shijiyongshi=web.toMin(obj['actualTime']);
    $(".shijiyongshi").text($shijiyongshi);
    //模拟参数列表
	$("#paramlist").html(template("param-script",obj));
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
    //回测进度
	$(".progress .bar").css("width",web.numbersFmt('1','2',obj["testPlan"]));
	$(".progress .barvalue").text(web.numbersFmt('1','2',obj["testPlan"]));
	//评分
	if(web.numbersFmt(0,2,obj['grade'])=="--"){
		$("#strategyScoreDetails").hide();
	}else{
		$("#strategyScoreDetails").show();
	}
	$(".fs").text(web.numbersFmt(0,2,obj['grade']));
	
		
}