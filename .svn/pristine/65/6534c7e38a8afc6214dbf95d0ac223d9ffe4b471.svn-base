// +----------------------------------------------------------------------
// | des:mystr.js  111
// +----------------------------------------------------------------------
// | 2017-03-20  
// +----------------------------------------------------------------------
// | Author: liangsir update： andrew
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//初始化执行
$(function(){
	console.log(111);
	mystr.getlist('');	
	time();
});
//构造类
function MyStrategy() {
	this.countdown=10;
	this.defaultdown=10;//页面需要多少秒刷新
	this.flag=0;//1回调成功  0 回调失败
}


	
/*
 * 重新获取验证码
 * */
MyStrategy.prototype.getlist=function(stext){
	var index = layer.load(1, {
		  shade: [0.1,'#000'] //0.1透明度的白色背景
	});
	//接口
	var link=$('#geturl').val();
	//需要传递的参数
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
   
    var searchText = stext==undefined?'':stext;
    
   
    var dataLink = 'pageSize=' + pageSize + '&pageNum=' + pageNum +  '&searchText='+searchText+'&userId='+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	mystr.setlistfun(rs.data);
        	mystr.pageFun(rs.data.totalPage);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('该账号不存在，请核对后输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
        layer.closeAll();
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*列表数据*/
MyStrategy.prototype.setlistfun=function(msg){
	
	if(msg.records.length>0){
		
		for(var i=0;i<msg.records.length;i++){
			msg.records[i].stype=1;//标记是否需要显示删除按钮1显示 2不显示
			msg.records[i].alpha=common.numbersFmt('0','4',msg.records[i].alpha);
			msg.records[i].beta=common.numbersFmt('0','4',msg.records[i].beta);
			msg.records[i].shapratio=common.numbersFmt('0','4',msg.records[i].shapratio);
			msg.records[i].maxRetrace=common.numbersFmt('1','2',msg.records[i].maxRetrace);
			msg.records[i].profit=common.numbersFmt('1','2',msg.records[i].profit);
			msg.records[i].returnVolatlity=common.numbersFmt('0','4',msg.records[i].returnVolatlity);
			//状态 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪 4.历史回测的掉线  5.实盘模拟的掉线
			if(msg.records[i].status=='0'){
				msg.records[i].stype=2;
				msg.records[i].statuslt='statusA';
				msg.records[i].url2='web/frontend/strategydetail/firmStrategyDetails.do?form=1&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}else if(msg.records[i].status=='1'){
				msg.records[i].stype=3;
				msg.records[i].statuslt='statusIng';
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url2='web/frontend/strategydetail/hisStrategyDetails.do?form=2&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}else if(msg.records[i].status=='2'){
				msg.records[i].statuslt='statusNo';
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url2='frontend/simulation.do?type=1&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}else if(msg.records[i].status=='3'){
				msg.records[i].statuslt='statusReady';
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url2='frontend/simulation.do?type=1&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}else if(msg.records[i].status=='4'){
				msg.records[i].statuslt='relink';
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url2='web/frontend/strategydetail/hisStrategyDetails.do?form=1&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}else if(msg.records[i].status=='5'){
				msg.records[i].statuslt='relink';
				msg.records[i].url='frontend/simulation.do?testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
				msg.records[i].url2='web/frontend/strategydetail/hisStrategyDetails.do?form=2&testId='+msg.records[i].testId+'&strategyId='+msg.records[i].id;
			}
			msg.records[i].classname=msg.records[i].isAttention==0?'openwx':'closewx';
			msg.records[i].startTime=common.getLocalTime(msg.records[i].startTime,'s');
			msg.records[i].endTime=common.getLocalTime(msg.records[i].endTime,'s');
		}
		
		$("#list").html(template("mystrategy_script", msg));
		
		$('.data-page').show();
		$('.no_data').hide();
  		$('.s_title').show();
	}else{
		
		$("#list").find('div').remove();
		$("#list").find('tr').remove();
  		$('.no_data').show();
  		$('.data-page').hide();
  		$('.s_title').hide();
	}
	$("#count").text(msg.totalRows);
	common.resetHeight();//数据加载后重置页脚
	//mystr.isBook();
}
MyStrategy.prototype.prebook=function(me,id){
	var type=1;//1.开启  0.取消
	if($(me).hasClass('closewx')){//有类 表示已经订阅直接取消预订
		mystr.book(me,id,0);
	}else{
		type=1;
		mystr.getCode(me,id,'1');
	}
}
/*用户手动打开微信提示框*/
MyStrategy.prototype.alertwx=function(me,id){
	mystr.getCode(me,id,'2');
}
/*订阅 取消订阅按钮*/
MyStrategy.prototype.book=function(me,id,type){
	
	//接口
	var link=common.sysurl()+'/firmoffer/updateIspush.m';
	//需要传递的参数
    var dataLink = 'id='+id+'&type='+type;//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
        	if(rs.data==1){
        		$('.closewx').addClass('openwx');
        		$('.openwx').removeClass('closewx');
        	}
        	window.location.reload();
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('该账号不存在，请核对后输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*查看用户是否已经订阅*/
MyStrategy.prototype.isBook=function(me,id){
	//接口
	var link=common.sysurl()+'/firmoffer/queryUserIsAttenTion.m';
	//需要传递的参数
    var dataLink = 'userId='+$('.header').attr('dataid')+'&strategyType=1';//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
	
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('该账号不存在，请核对后输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*请求二维码之前的第一次请求*/
MyStrategy.prototype.removePoll=function(id){
	//接口
	var link=common.sysurl()+'/firmoffer/removePoll.m';
	//需要传递的参数
    var dataLink = '';//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('该账号不存在，请核对后输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
//请求二维码的key
MyStrategy.prototype.getCode=function(me,id,type){
	//接口
	var link=common.sysurl()+'/firmoffer/queryWechatTiket.m';
	//需要传递的参数
    var dataLink = 'userId='+$('.header').attr('dataid')+'&strategyId='+id+'&type='+type+'&strategyType=1';//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	
        	if(rs.data==1){//订阅过直接请求
        		mystr.book(me,id,1);
        	}else{//请求二维码 并且弹出弹出框
        		mystr.removePoll();
        		mystr.openwx(rs.data);
        		isBackRoll();
        	}
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('数据加载错误', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
//轮询方法
MyStrategy.prototype.queryPoll=function(id){
	//接口
	var link=common.sysurl()+'/firmoffer/queryPoll.m';
	//需要传递的参数
    var dataLink = 'userId='+$('.header').attr('dataid');//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	if(rs.data==1){//回调成功 ,关闭二维码页面
        		mystr.closewx(rs.data);
        		mystr.flat=0;
        	}else{
        		
        	}
    		
        	
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('获取信息错误', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
MyStrategy.prototype.sdeletebox=function(id){
	$('.tipbox').show();
	$('.tipboxshaow').show();
	$('.tipbox').attr('data',id);
}
/* 关闭删除提示框*/
MyStrategy.prototype.closetip=function(){
	$('.tipboxshaow').hide();
	$('.tipbox').hide();
}
/* 打开微信提示框*/
MyStrategy.prototype.openwx=function(token){
	$('.tipboxshaow').show();
	$('.wxbox').show();
	var url='https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket='+token;
	$('.wxbox .content img').attr('src',url);
	isBackRoll();
}

/* 关闭微信提示框*/
MyStrategy.prototype.closewx=function(){
	$('.tipboxshaow').hide();
	$('.wxbox').hide();
	mystr.flag=1;
	window.location.reload();
}
/*删除策略*/

MyStrategy.prototype.sdelete=function(id){
	//接口
	var link=common.sysurl()+'/firmoffer/deleteTStrategy.m';
	//需要传递的参数
    var dataLink = 'id=' + $('.tipbox').attr('data');//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	if(rs.data==true){
        		mystr.closetip();
        		layer.alert('删除成功', {
   	 	         icon: 1,
   	 	       },function(index){
   	 	    	   window.location.reload();
   	 	    	   layer.close(index);
   	 	       });
        	}else{
        		mystr.closetip();
        		layer.alert('删除失败，请重试', {
   	 	         icon: 1,
   	 	       },function(index){
   	 	    	   layer.close(index);
   	 	       });
        	}
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
        	layer.alert('该账号不存在，请核对后输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*
*保留小数点前四位后四位小数
*/
MyStrategy.prototype.numDeal=function(num){
	var data=num.toString();
	var leftdata=data.substr(0,data.indexOf('.'));
	var left='';
	if(leftdata.length>=4){
		left=data.substr(data.indexOf(".")-4,4);
	}else{
		left=leftdata;
	}
	var right=data.substr(data.indexOf(".")+1,4);
	return left+'.'+right;
}
/*
*保留小数点前四位后四位小数
*/
MyStrategy.prototype.numDeal2=function(num){
	var data=num.toString();
	var leftdata=data.substr(0,data.indexOf('.'));
	var left='';
	if(leftdata.length>=4){
		left=data.substr(data.indexOf(".")-4,4);
	}else{
		left=leftdata;
	}
	var right=data.substr(data.indexOf(".")+1,2);
	return left+'.'+right;
}
/*分页*/
MyStrategy.prototype.pageFun=function(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
}
//分页函数
MyStrategy.prototype.setPageNum=function(thiz, pageType) {
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  $("#curPage").val(pno);
	  //获取搜索字段
	  var searchText=$.trim($("#searchtext").val());
	  var isSearch=$("#isSearch").val();
	  console.log(searchText);
	  console.log(isSearch);
	  if(str.isNotblank(searchText)&&isSearch==1){
		  mystr.getlist(searchText);
	  }else{
		  mystr.getlist(searchText);
	  };
	});
};
//搜索框点击时间
MyStrategy.prototype.searchclick=function() {
		var searchText=$.trim($("#searchtext").val());
		mystr.getlist(searchText);
};

/**
 * DES:回车键执行
 * @param void;
 * @return void;
 */
$(document).keypress(function(e) {
	// 回车键事件
	if (e.which == 13) {
		 var searchText=$.trim($("#searchtext").val());
		 mystr.getlist(searchText);
	}
});

//实例化FundManagejs类
var mystr= new MyStrategy();

//分页函数
function setPageNum(thiz, pageType) {
	
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  if(str.isblank(pno)){
		  $("#curPage").val(1);
	  }else{
		  $("#curPage").val(pno);
	  }
	  mystr.getlist();
	});
};

/*
 * 定时间 定时刷新列表 初始10秒
 * */
function time(){
    if (mystr.countdown == 0) { 
    	if($('#searchtext').val()!==''){
    		mystr.getlist($('#searchtext').val());	
    	}else{
    		mystr.getlist('');	
    	}
    	mystr.countdown=mystr.defaultdown;
    	time();
    } else { 
    	mystr.countdown--;
        setTimeout(function() {
            time();
        },
        1000)
    }
}
/*
 * 轮询查询状态
 * */
function isBackRoll(){
    if (mystr.flag == 1) { //
    	location.reload();
    	
    } else { 
    	mystr.queryPoll();
        setTimeout(function() {
        	isBackRoll();
        },
        3000)
    }
}
