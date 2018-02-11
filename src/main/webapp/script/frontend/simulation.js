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
function Simulation() {
	this.strategyId=-1;
	this.type=1;//1 实盘模拟 2历史回测
	this.orderType=1;//1 按时间排序   2 按收益率排序
	this.order=2;//1 asc  顺序  2  desc 倒序
}
/*thead 点击时间*/
Simulation.prototype.thclick=function(type){
	if(type==1){
		simu.orderType=1;
		
	}else{
		simu.orderType=2;
	}
	simu.order=simu.order==2?1:2;
	simu.getlist();
}
/**
 * DES:tab切换
 * @param object 您选择的DOM
 * @return void
 */
Simulation.prototype.tabchange=function(obj,type){
	 $(obj).addClass('on');
	 $(obj).siblings().removeClass('on');
	 if(type==1){
		$('.one').show();
		$('.two').hide();
	 }else{
	    $('.one').hide();
		$('.two').show();
	 }
	 
	 simu.type=type;
	 simu.getlist();
	 simu.checkOp(3);
}
/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
Simulation.prototype.radios=function(obj){
	 $(obj).parent().parent().find("em").removeClass("on");
	 $(obj).addClass("on");
}
/**
 * DES:全选 全不选
 * @param object 您选择的DOM
 * @return void
 */
Simulation.prototype.checkAll=function(obj){
	 if($(obj).attr('data')=='1'){//1不选中 2选中
		 
		 $(obj).attr('data','2');
		 $('.table td input').each(function(){
			 this.checked=true;
		 });
	 }else{
		 $(obj).attr('data','1');
		 $('.table td input').each(function(){
			 this.checked=false;
		 });
	 }
}
/**
 * 点击头部状态跳转到正在模拟的页面
 * 
 * */
Simulation.prototype.goDetail=function(me){
	var typ=1;
	if($(me).hasClass('statusA')){//实盘模拟
		type=1;
		//location.href=common.sysurl()+'/detail/firmEarnings.do?sidebar=1&testId='+common.GetQueryString('testId')+'&strategyId='+common.GetQueryString('strategyId');
	}else if($(me).hasClass('statusIng')){//历史回测
		type=0;
		//location.href=common.sysurl()+'/detail/historyEarnings.do?sidebar=1&testId='+common.GetQueryString('testId')+'&strategyId='+common.GetQueryString('strategyId');
	}
	common.toTesting(type);
}
/*获取列表数据*/
Simulation.prototype.getlist=function(){
	/*需要的参数*/
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
    var strategyId = simu.strategyId;
    var dataLink = 'pageSize=' + pageSize + '&pageNum=' + pageNum +  '&strategyId='+strategyId+'&type='+simu.type+'&strategy='+simu.strategyId+'&orderType='+simu.orderType+'&order='+simu.order;
    //接口
	var url=common.sysurl()+"/firmoffer/findTestRecordList.m";			   
	//ajax
    ajax.tranDataParams(url,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   rs.data.rowNum = (pageNum - 1) * pageSize;
           pageFun(Math.ceil(rs.data.totalRecords/rs.data.pageSize));
           simu.setlistfun(rs.data);
        }else if(rs.code==411){
       	   layer.alert(rs.message, {
    	         icon: 7,
    	       },function(index){
    	    	   location.href=common.sysurl()+'/frontend/login.do';
    	    	   layer.close(index);
    	       });
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
/*停止实盘模拟*/
Simulation.prototype.stopShipan=function(sid,testId){
	parent.layer.confirm("停止此次实盘模拟后，不再进行实盘模拟", {
		 title:"停止实盘模拟"
	}, function(index){
		stopTest('1','1')
	});	
}
/*停止历史回测*/
Simulation.prototype.stopHi=function(sid,testId){
	parent.layer.confirm("停止此次历史回测后，不再进行历史回测", {
		 title:"停止历史回测"
	}, function(index){
		stopTest('0','2');
	});	
}
/*列表数据*/
Simulation.prototype.setlistfun=function(msg){
	$('#status').removeClass('statusA');
	$('#status').removeClass('statusIng');
	$('#status').removeClass('statusNo');
	$('#status').removeClass('statusReady');
	//状态 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪
	if(msg.strategyStatus=='0'){//历史回测中
		$('#status').addClass('statusA');
		$("#startpan").text('停止实盘模拟');
		$("#startpan").attr('onclick','simu.stopShipan('+simu.strategyId+','+common.GetQueryString('testId')+')');
		$("#starthi").hide();
	}else if(msg.strategyStatus=='1'){//实盘模拟中
		$('#status').addClass('statusIng');
		$("#starthi").text('停止历史回测');
		$("#starthi").attr('onclick','simu.stopHi('+simu.strategyId+','+common.GetQueryString('testId')+')');
		$("#startpan").hide();
		
	}else if(msg.strategyStatus=='2'){//未就绪
		$('#status').addClass('statusNo');
		$('.toolbox1 .btns').hide();
		
	}else if(msg.strategyStatus=='3'){//就绪
		$('#status').addClass('statusReady');
		$("#startpan").attr('href',common.sysurl()+'/frontend/simulationPara.do?strategyId='+simu.strategyId+'&testId='+common.GetQueryString('testId'));
		$("#starthi").attr('href',common.sysurl()+'/frontend/simParaHistory.do?strategyId='+simu.strategyId+'&testId='+common.GetQueryString('testId'));
	}else{
		$('#status').addClass('relink');
		$("#starthi").hide();
		$("#startpan").hide();
	}
	$('.titles').text(msg.strategyName);
	
	if(msg.records.length>0){
		for(var i=0;i<msg.records.length;i++){
			if(msg.records[i].frequency=='1'){
				msg.records[i].frequencytext='每天';
			}else if(msg.records[i].frequency=='0'){
				msg.records[i].frequencytext='TICK';
			}else if(msg.records[i].frequency=='2'){
				msg.records[i].frequencytext='分钟';
			}
			msg.records[i].frequency=msg.records[i].frequencytext;
			msg.records[i].testTime=msg.records[i].testTime==''?'--':common.getLocalTime(msg.records[i].testTime,'s');
			msg.records[i].hisDataStart=msg.records[i].hisDataStart==null?'--':common.getLocalTime(msg.records[i].hisDataStart,'d');
			msg.records[i].hisDataEnd=msg.records[i].hisDataEnd==null?'--':common.getLocalTime(msg.records[i].hisDataEnd,'d');
			msg.records[i].endTime=msg.records[i].endTime==null?'--':common.getLocalTime(msg.records[i].endTime,'s');
			msg.records[i].statustext=msg.records[i].status=='1'?'进行中':'已完成';
			if(simu.type==1){//实盘模拟中
				if(msg.records[i].status==1){
					msg.records[i].url=common.sysurl()+'/web/frontend/strategydetail/firmStrategyDetails.do?form=1&strategyId='+msg.records[i].strategyId+'&testId='+msg.records[i].id;
				}else{
					msg.records[i].url=common.sysurl()+'/web/frontend/strategydetail/firmStrategyDetails.do?form=1&strategyId='+msg.records[i].strategyId+'&testId='+msg.records[i].id;
				}
				
			}else{
				if(msg.records[i].status==1){
					msg.records[i].url=common.sysurl()+'/web/frontend/strategydetail/hisStrategyDetails.do?form=2&strategyId='+msg.records[i].strategyId+'&testId='+msg.records[i].id+'&groupId='+msg.records[i].paramGroupId;
				}else{
					msg.records[i].url=common.sysurl()+'/detail/historyTestParamGroup.do?type=2&strategyId='+msg.records[i].strategyId+'&testId='+msg.records[i].id;
				}
				
				
			}
			
			if(msg.records[i].accumulatedReturn>=0){
				msg.records[i].color='#ff5256';
			}else{
				msg.records[i].color='#5dcd0b';
			}
			msg.records[i].accumulatedReturn=simu.numbersFmt('1','2',msg.records[i].accumulatedReturn);
			msg.records[i].iniFunding=simu.numbersFmt('2','2',msg.records[i].iniFunding);
		}
		if(simu.type==1){
			$("#list").html(template("simulation_script", msg));
		}else{
			$("#list").html(template("simulation2_script", msg));
		}
		$('.data-page').show();
	}else{
		$("#list").find('tr').remove();
  		var html="<tr><td colspan='8' class='nodata'> 暂无数据</td></tr>";
  		$("#list").append(html);
  		$('.data-page').hide();
	}
	common.resetHeight();
	$("#count").text(msg.totalRows);
}
/**
 *desc:数据格式化
 *@param type 1表示加%,0表示不加%。
 *@param num 保留多少位。
 *@param number 需要被格式化的数据。
 *@return void;
 */
Simulation.prototype.numbersFmt=function(type,num,number){
	 
	  if(number==0){
		  return '0.00%';
	  }else if(number==''||number==null){
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
}
 
/*
*保留小数点前四位后四位小数
*/
Simulation.prototype.numDeal2=function(num){
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
Simulation.prototype.Init=function(){
	var type=common.GetQueryString('type');//历史回测还是实盘模拟
	var id=common.GetQueryString('strategyId');
	simu.type=type;
	if(type=='1'){
		$('#shipin').addClass('on');
		$('#his').removeClass('on');
		$('.one').show();
		$('.two').hide();
	}else{
		$('#shipin').removeClass('on');
		$('#his').addClass('on');
		$('.one').hide();
		$('.two').show();
	}
	simu.strategyId=id;
	
}
/*
 * 删除选择操作
 * */
Simulation.prototype.checkOp=function(type){
	if(type==1){
		$('.btone').hide();
		$('.bttwo').show();
		$('.table  input').show();
	}else if(type==3){
		$('.btone').show();
		$('.bttwo').hide();
		$('.table  input').hide();
	}
}
/**
 * 删除数据库交互
 * */
Simulation.prototype.deleteOp=function(){

	var str='';
	$('.table td input:checkbox:checked').each(function(i){ 
		if(i==0){
			str=$(this).val();
		}else{
			str+=','+$(this).val();
		}
	});
	if(str.length==0){
		parent.layer.alert('请选择要删除的记录', {
 	         icon: 7,
 	       },function(index){
 	    	   parent.layer.close(index);
 	       });
	}else{
		var link=common.sysurl()+'/firmoffer/deleteTestRecord.m';
		var dataLink = 'type=' + simu.type+'&ids='+str;
		 ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
		        if(rs.code==0){
		        	parent.layer.alert('删除成功', {
			  	         icon: 7,
			  	       },function(index){
			  	    	 
			  	    	 location.href=common.sysurl()+'/frontend/simulation.do?type='+simu.type+'&testId='+simu.testId+'&strategyId='+simu.strategyId; 
			  	    	 parent.layer.close(index);
			  	    });
		        }else if(rs.code==411){
		        	 layer.alert(rs.message, {
		     	         icon: 7,
		     	       },function(index){
		     	    	  location.href=common.sysurl()+'/frontend/login.do';
		     	    	   layer.close(index);
		     	       });
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
	
}
/**
 *desc:停止实盘模拟
 *@param 1表示停止实盘模拟，0表示停止历史回测
 *@return void;
 */
Simulation.prototype.stops=function(type,num){
	if(num=='1'){
	 var title="停止实盘模拟";
	 var hint="停止此次实盘模拟后，不再进行实盘模拟";	
	}else{
	  var title="停止历史回测";
	  var hint="停止此次历史回测后，不再进行历史回测";	
	};
	parent.layer.confirm(hint, {
		 title:title
    }, function(index){
    	stopTest(type,num);
	});
};
//实例化FundManagejs类
var simu= new Simulation();
$(function(){
	simu.Init();
	simu.getlist();
});
/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

//分页函数
function pageFun(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
};

//分页函数
function setPageNum(thiz, pageType) {
	
	  simu.checkOp(3);
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  if(str.isblank(pno)){
		  $("#curPage").val(1);
	  }else{
		  $("#curPage").val(pno);
	  }
	  simu.getlist();
	 
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
      	         icon:1,
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
        }else{
           parent.layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+"/frontend/login.do";
  	    	 parent.layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
