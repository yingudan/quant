// +----------------------------------------------------------------------
// | des:实盘模拟详情js
// +----------------------------------------------------------------------
// | 2017-04-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//构造类
function Detailcomm() {}
$(function(){
	detailcomm.sidebar();
	detailcomm.tplinit();
	detailcomm.addAlink();	
});



/**
 * DES:渲染侧栏跳转链接
 * @param void
 * @return void
 */
Detailcomm.prototype.addAlink=function(){
    //获取当前链接的参数
	var testId=common.GetQueryString("testId");
	var strategyId=common.GetQueryString("strategyId");
	var groupId=common.GetQueryString("groupId");
	var urls="&testId="+testId+"&strategyId="+strategyId;
	if(groupId>0){
		urls=urls+"&groupId="+groupId;
	}
	$(".detail-left .nav a").each(function(){
		$(this).attr("href",$(this).attr("href")+urls);
	});
}


/**
 * DES:跳转到测试列表中
 * @param type==1表示实盘，2表示模拟
 * @return void
 */
Detailcomm.prototype.goTestList=function(type){
   var strategyId=common.GetQueryString("strategyId");
   var testId=common.GetQueryString("testId");
   var url =common.sysurl()+"/frontend/simulation.do?type="+type+"&strategyId="+strategyId+"&testId="+testId;
       location.href=url;	   

}


/**
 * DES:N/A不适用
 * @param num
 * @return void
 */
Detailcomm.prototype.na=function(num){
   if(num==0||num=="0"){
	  return num; 
   }else if(str.isblank(num)){
	  return  "N/A不适用";  
   }else{
	  return num;
   };
}




/**
 * DES:开始测试跳转
 * @param type==1表示实盘，2表示模拟
 * @return void
 */
Detailcomm.prototype.goStartTest=function(type){
  var strategyId=common.GetQueryString("strategyId");
  if(type=='1'){
	 var url =common.sysurl()+"/frontend/simulationPara.do?strategyId="+strategyId;
  }else{
	 var url =common.sysurl()+"/frontend/simParaHistory.do?strategyId="+strategyId;  
  }
  location.href=url;
}


/**
 *desc:回到顶部根据高度显示隐藏
 *@param void;
 *@return void;
 */
Detailcomm.prototype.showTop=function(){
    $(window).scroll(function() {
      $scrollTop=$(document).scrollTop();
      if($scrollTop>160){
        $(".detail-left").addClass("on");
      }else{
        $(".detail-left").removeClass("on");
      }
   });
 };
 
 
 /**
  *desc:根据不同的状态显示不同的按钮
  *@param status 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪,4历史掉线，5实时掉线
  *@return void;
  */
 Detailcomm.prototype.statusPages=function(status){
  $(".toolbox0").hide();
  $(".toolbox1").hide();
  $(".toolbox2").hide();
  $(".toolbox3").hide();
  $(".toolbox4").hide();
  $(".toolbox5").hide();
  $(".toolbox"+status).show(); 
};


/**
 *desc:描红描绿
 *@param num 大于等于0 red， 小于0 green。
 *@return void;
 */
Detailcomm.prototype.redOrgreen=function(num){
  if(num<0){
	  return "green";
  }else{
	  return "red"; 
  }
};


/**
 *desc:根据不同的页面选中侧栏
 *@param status 1收益概览，2交易详情，3每日持仓，4策略收益,5风险指标
 *@return void;
 */
Detailcomm.prototype.sidebar=function(status){
 var sidebar=common.GetQueryString("sidebar");
 $("#s"+sidebar).addClass("on");
};


/**
 *desc:数据格式化
 *@param type 1表示加%,0表示不加%。
 *@param num 保留多少位。
 *@param number 需要被格式化的数据。
 *@return void;
 */
Detailcomm.prototype.numbersFmt=function(type,num,number){
  if(number=="0"||number==0){
	  var number=Number(number); 
	  if(type=='1'){
		  number=number*100;
		  number=number.toFixed(num);
		  number=number+"%";
	  }else{
		  number=number.toFixed(num);
	  }
	  return number; 
  }else if(str.isblank(number)){
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
 *desc:为空返回--
 *@return void;
 */
Detailcomm.prototype.numFmt1=function(number){
	if(number=="0"||number==0){
		 return number; 
	  }else if(str.isblank(number)){
	     return "--";
	  }else{
		return number;   
	  }
};



/**
 * 时间秒数格式化
 * @param value 秒数
 * @returns {*} 格式化后的时分秒
 */
Detailcomm.prototype.toMin=function(value){
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
 *desc:选择时间
 *@return void;
 */
Detailcomm.prototype.times=function(time,time2,type){
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
	     if(type=='1'){
	    	 getBase();
	     }else if(type=='2'){
	       getDeal();
	     }else if(type=='3'){
	       getPosition();
	     }else{
	    	alert("times():参数错误！"); 
	     }
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
	    if(type=='1'){
	    	getBase();
	     }else if(type=='2'){
	       getDeal();
	     }else if(type=='3'){
	       getPosition();
	     }else{
	    	alert("times():参数错误！"); 
		}
	  }
	};
	laydate(start);
	laydate(end);
};


//temples模板自定义函数
Detailcomm.prototype.tplinit = function(){
	template.helper("formatNumber",common.formatNumber);
	template.helper("getLocalTime",common.getLocalTime);
	template.helper("numbersFmt",detailcomm.numbersFmt);
	template.helper("na",detailcomm.na);
	template.helper("redOrgreen",detailcomm.redOrgreen);
	template.helper("numFmt1",detailcomm.numFmt1);
}


/**
 *desc:停止实盘模拟
 *@param 1表示停止实盘模拟，0表示停止历史回测
 *@return void;
 */
Detailcomm.prototype.stops=function(type,num){
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
 *desc:提示登录失败并且跳转到登录页面
 *@return void;
 */
Detailcomm.prototype.failToLogin=function(message){
  layer.alert(message,{
    icon: 7,
  },function(index){
    location.href=common.sysurl()+'/frontend/login.do';
    layer.close(index);
  });
};



/**
 *desc:停止实盘模拟
 *@param 1表示停止实盘模拟，0表示停止历史回测
 *@return void;
 */
Detailcomm.prototype.goBack=function(){
  	var s=$("#sorm").text();
  	var strategyId=common.GetQueryString("strategyId");
  	if(s=="2"){
  		var url =common.sysurl()+"/frontend/simulation.do?strategyId="+strategyId+"&type=2";
  	}else{
  		var url =common.sysurl()+"/frontend/simulation.do?strategyId="+strategyId+"&type=1"; 
  	}
  	location.href=url;
};

	
/*停止测试*/
function stopTest(type,num){
	//接口
	var link=common.sysurl()+"/firmoffer/stopTheOffer.m";
	//需要传递的参数
	var testId=common.GetQueryString("testid");//单条回测记录id
	var strategyId=common.GetQueryString("strategyid");//策略id
	var dataLink="testId="+testId+"&type="+type;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
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


//分页函数
function pageFun(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
};


//分页函数
function setPageNum(thiz, pageType) {
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  $("#curPage").val(pno);
	  var sidebar=common.GetQueryString("sidebar");
	  if(sidebar==2){
		  getDeal(false);
	  }else{
		  getPosition(false);  
	  }
	});
};
    
   
//实例化Detailcomm类
var detailcomm= new Detailcomm();










