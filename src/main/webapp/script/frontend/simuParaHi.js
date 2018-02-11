// +----------------------------------------------------------------------
// | des:mystr.js
// +----------------------------------------------------------------------
// | 2017-03-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//构造类
function SimuPara() {
	this.strategyId=5;
	this.testId=-1;
}
//初始化执行
$(function(){
	simuPara.Init();
	simuPara.getPara();	
	
	laydate(start);
	laydate(end); 
	 $(".inputcheckall").keyup(function(){      
         $(this).val($(this).val().replace(/[^0-9.]/g,''));      
     }).bind("paste",function(){  //CTR+V事件处理      
         $(this).val($(this).val().replace(/[^0-9.]/g,''));       
     }).css("ime-mode", "disabled"); //CSS设置输入法不可用   
	 /*清空时间的输入框*/
	$('#starttime').val('');
	$('#endtime').val('');
});

/**
 * 左侧参数加载完成后绑定所有事件 只能输入 数字小数点
 * */

SimuPara.prototype.bindKeyup=function(obg){
	$(".inputcheck").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9.-]/g,''));      
    }).bind("paste",function(){  //CTR+V事件处理      
        $(this).val($(this).val().replace(/[^0-9.-]/g,''));       
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用   
}
/**
 * 左侧参数加载完成后绑定所有事件 只能输入 数字小数点
 * */

SimuPara.prototype.bindKeyuparray=function(obg){
	$(".inputcheckarray").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9.,-]/g,''));      
    }).bind("paste",function(){  //CTR+V事件处理      
        $(this).val($(this).val().replace(/[^0-9.,-]/g,''));       
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用   
}
/*初始化函数变量*/
SimuPara.prototype.Init=function(){
	var id=common.GetQueryString('strategyId');
	simuPara.strategyId=id;
	var testId=common.GetQueryString('testId');
	simuPara.testId=testId;

}
/**策略启动失败原因弹出框优化
 * 
 * */
SimuPara.prototype.errAlert=function(){
	layer.open({
		  type: 1,
		  title:'启动失败',
		  area: ['400px', '235px'],
		  shadeClose: true, //点击遮罩关闭
		  content:$("#errortips")
	});
};


/*
 * 数据验证-固定值
 * */
SimuPara.prototype.checkNum=function(me){
	var value=$(me).val();
	if(value!==''){
		var alt=simuPara.checkGu(value.toString());
		if(alt=='--'){
			layer.alert($(this).find('.ileft').text()+'范围为-999,999,999.9999到999,999,999.9999，请重新输入', {
		         icon: 7,
		       },function(index){
		    	   $(me).val('');
		    	   layer.close(index);
		     });
		}else{
			$(me).val(alt);
		}
	}
	
}
/**
 * 处理需要验证的数据 固定值验证
 * 只能输入数字小数点 - 英文逗号
 * */
SimuPara.prototype.checkGu=function(data){

	var pos=data.indexOf(',');
	var errornum=0;
	if(pos>-1){//字符串是参数组
		var array = data.split (",");
		var str=''
		for(var i=0;i<array.length;i++){
			if(simuPara.checkGurlt(array[i])!=='--'){
				if(i<array.length-1){
					str+=Number(array[i])+',';
				}else{
					str+=Number(array[i]);
				}
			}else{
				errornum++;
			}
		}
		if(errornum>0){
			return '--';
		}else{
			return str;
		}
	}else{
		if(simuPara.checkGurlt(data)!=='--'){
			return Number(data);
		}else{
			return '--';
		}
	}
}
/**
 * 验证  固定值验证
 * 只能输入数字小数点 - 英文逗号
 * */
SimuPara.prototype.checkGurlt=function(data){

	if(data<=999999999.9999&&data>=-999999999.9999){
		//验证通过不需要操作
		
		return Number(data);
	}else{
		
		return '--';
	}
}
/**
 * DES:执行提交
 * @param void;
 * @return void;
 */
SimuPara.prototype.submit=function(){
	 var list=new Array();
	 var error=0;
	 $(".paraitem").each(function(){
		 var obj=$(this).find('.on');
		 var type=$(obj).attr('data');
		 var listOne=new Object(); 
    	 listOne["id"]=$(this).find('.oneinput').attr('data-id');
    	 listOne["strategyId"]=simuPara.strategyId;
    	 listOne["fieldName"]=$(this).find('.oneinput').attr('fieldName');
    	 listOne["paramName"]=$(this).find('.oneinput').attr('paramName');
    	 if(type=='1'){
    		 var fieldValue=$(this).find('.oneinput').val();
    		 if(fieldValue==''){
    			 fieldValue=$(this).find('.oneinput').attr('data');
    		 }else{
    			 if(simuPara.checkGu(fieldValue)=='--'){//验证不通过
        			 layer.alert($(this).find('.ileft').text()+'范围为-999,999,999.9999到999,999,999.9999，请输入', {
        	 	         icon: 7,
        	 	       },function(index){
        	 	    	   layer.close(index);
        	 	     });
        		 return;
    			 }else{
    				 fieldValue=simuPara.checkGu(fieldValue);
    			 }
    		 }
    		
    		 listOne["fieldValue"]=fieldValue;
    		 listOne["type"]=0;
    	 }else{
    		 var fieldValue=$(this).find('.value').val();
    		 if(fieldValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的初始值不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else{
    			 listOne["fieldValue"]=Number(fieldValue);
    		 }
    		 var stepValue=$(this).find('.stepValue').val();
    		 if(stepValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的步长不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else if(stepValue==0){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'步长不能小于0，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    		 }else{
    			 
    				 listOne["stepValue"]=Number(stepValue);	 
    		 }
    		 var maxValue=$(this).find('.maxValue').val();
    		 if(maxValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的截止值不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else{
    			 if(maxValue<fieldValue){
    				 error++;
        			 layer.alert('截止值不能小于起始值，请输入', {
        	 	         icon: 7,
        	 	       },function(index){
        	 	    	   layer.close(index);
        	 	     });
        			 return;
    			 }else{
    				 listOne["maxValue"]=Number(maxValue);	
    			 }
    		 }
    		 listOne["type"]=1;
    	 } 
    	 list.push(listOne);
	 });
	 var data=new Object();
	 data.list=list;
	 data.iniFunding=$('#total').val()==''?$('#total').attr('data'):$('#total').val();
	 data.type=0;
	 data.strategyId=simuPara.strategyId;
	 data.frequency=$('#dateType').attr('data-type');
	 if($('#starttime').attr('data')!==''){
		 data.startTime=common.transdate($('#starttime').attr('data'));
	 }else{
		 layer.alert('请选择开始时间', {
 	         icon: 7,
 	       },function(index){
 	    	   layer.close(index);
 	     });
		 return;
	 }
	 if($('#endtime').attr('data')!==''){
		 data.endTime=common.transdate($('#endtime').attr('data'));
	 }else{
		 layer.alert('请选择结束时间', {
 	         icon: 7,
 	       },function(index){
 	    	   layer.close(index);
 	     });
		 return;
	 }
	//接口
	var link=common.sysurl()+'/firmoffer/startStrategy.m';
	
    //ajax
	if(error==0){
		ajax.tranData(link,JSON.stringify(data), true, "post", function(rs) {
	    	
	        if(rs.code==0){
	        	
	        	layer.alert('启动成功', {
	        		icon:1,
	        		cancel:function(){
	        			simuPara.getGroupid(rs.data);
	        			
	        			
	        		}
		       	},function(index){
		       		simuPara.getGroupid(rs.data);
		       		
		       		
		   	    	layer.close(index);
		   	    });
	        }else if(rs.code==411){
		       	 layer.alert(rs.message, {
	   	         icon: 7,
	   	       },function(index){
	   	    	location.href=common.sysurl()+'/frontend/login.do';
	   	    	   layer.close(index);
	   	       });
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
    
}
/*启动成功后获取组id在进行跳转*/
SimuPara.prototype.getGroupid=function(data){
	var index = layer.load(1, {
		  shade: [0.1,'#000'] //0.1透明度的白色背景
	});
	
	var groupId=0;
	//接口
	var link=common.sysurl()+'/firmoffer/queryGroupId.m';
	var dataLink='testId='+data;
	ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	if(rs.data>0){
				window.location=common.sysurl()+'/web/frontend/strategydetail/hisStrategyDetails.do?form=2&testId='+data+'&strategyId='+simuPara.strategyId+'&groupId='+rs.data;
			}
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
   	         icon: 7,
   	       },function(index){
   	    	location.href=common.sysurl()+'/frontend/login.do';
   	    	   layer.close(index);
   	       });
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
	return groupId;
}
/**
 * 关闭启动失败的页面
 * */
SimuPara.prototype.closeError=function(){
	layer.closeAll(); //疯狂模式，关闭所有层
}
/*
 * 重新获取验证码
 * */
SimuPara.prototype.getPara=function(){
	//接口
	var link=$('#geturl').val();
	//需要传递的参数
    var dataLink = 'strategyId='+simuPara.strategyId;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {

        if(rs.code==0){
        	simuPara.setlistfun(rs);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
   	         icon: 7,
   	       },function(index){
   	    	location.href=common.sysurl()+'/frontend/login.do';
   	    	   layer.close(index);
   	       });
        }else{
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
/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
SimuPara.prototype.radios=function(obj,type){
	 $(obj).parent().siblings().find("i").removeClass("on");
	 $(obj).addClass("on");
	 if(type==1){
		$(obj).parents('.iright').find('.oneinput').show();
		$(obj).parents('.iright').find('.threeinput').hide();
	 }else{
		 $(obj).parents('.iright').find('.oneinput').hide();
		 $(obj).parents('.iright').find('.threeinput').show();
	 }
}

/*列表数据*/
SimuPara.prototype.setlistfun=function(msg){
	if(msg.data.length>0){
		$('.titles').text(msg.data[0].strategyName);
		for(var i=0;i<msg.data.length;i++){

		}
		$("#list").append(template("list_script", msg));
	}else{
		$("#list").find('tr').remove();
  		var html="<div class='nodata'> 暂无数据</div>";
  		$("#list").append(html);
	}
	$("#count").text(msg.totalRows);
	common.resetHeight();//数据加载后重置页脚
	simuPara.bindKeyup();
	simuPara.bindKeyuparray();
}

/*
 * 数据验证
 * */
SimuPara.prototype.checkAll=function(me){
	var value=$(me).val();
	var defaultvalue=$(me).attr('data');
	if(value!==''){
		if(value<100000){
			layer.alert('初始资金最小100,000.00元，请重新输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  $(me).val('');
	 	    	 layer.close(index);
	 	     });
		}else if(value>9999999999.99){
			layer.alert('初始资金最大9,999,999,999.99元，请重新输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  $(me).val('');
	 	    	 layer.close(index);
	 	     });
		}
		if(value.indexOf(".")>0){
			
			var str = value.substring(0,value.indexOf(".") + 3);
			$(me).val(str);
			
		}
	}
}
/**
 * 固定值 循环值的验证
 * */
SimuPara.prototype.checkOne=function(me,name){
	var value=$(me).val();
	var defaultvalue=$(me).attr('data');
	if(value!==''){
		if(value<-999999999.9999){
			layer.alert(name+'最小值为-999,999,999.9999，请重新输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  $(me).val('');
	 	    	 layer.close(index);
	 	     });
		}else if(value>999999999.9999){
			layer.alert(name+'最大值为999,999,999.9999，请重新输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  $(me).val('');
	 	    	 layer.close(index);
	 	     });
		}
		
		if(value.indexOf(".")>0){
			var str = value.substring(0,value.indexOf(".") + 5);
			$(me).val(str);
		}
		if(name=='步长'){
			var stepValue=$(me).val();
			if(Number(stepValue)>0&&Number(stepValue)<=999999999.9999){
				
			}else{
				layer.alert(name+'范围为大于0,小于等于999,999,999.9999，请重新输入', {
		 	         icon: 7,
		 	       },function(index){
		 	    	  $(me).val('');
		 	    	 layer.close(index);
		 	     });
			}
		}
		if(name=='截止值'){
			var maxValue=$(me).val();
			var value=$(me).parents('.threeinput').find('.value').val();
			if(maxValue-value<0){
				layer.alert(name+'不能小于起始值，请重新输入', {
		 	         icon: 7,
		 	       },function(index){
		 	    	  $(me).val('');
		 	    	 layer.close(index);
		 	     });
			}
		}
		if(Number(value)==0){
			$(me).val(Number(value));
		}
	}else{
		layer.alert(name+'不能为空，请输入', {
	         icon: 7,
	       },function(index){
	    	  $(me).val('');
	    	 layer.close(index);
	     });
	}
}
/*
 * 关闭弹出框
 * */
SimuPara.prototype.closePop=function(type){
	$('.popcode').hide();
	$('.popbox').hide();
}
/*
 * 关闭弹出框
 * */
SimuPara.prototype.openPop=function(type){
	$('.popcode').show();
	$('.popbox').show();
}

/**
 * DES:确认继续
 * @param void;
 * @return void;
 */
SimuPara.prototype.sure=function(){
	 var list=new Array();
	 var error=0;
	 $(".paraitem").each(function(){
		 var obj=$(this).find('.on');
		 var type=$(obj).attr('data');
		 var listOne=new Object(); 
    	 listOne["id"]=$(this).find('.oneinput').attr('data-id');
    	 listOne["strategyId"]=simuPara.strategyId;
    	 listOne["fieldName"]=$(this).find('.oneinput').attr('fieldName');
    	 listOne["paramName"]=$(this).find('.oneinput').attr('paramName');
    	 if(type=='1'){
    		 listOne["fieldValue"]=$(this).find('.oneinput').val()==''?$(this).find('.oneinput').attr('data'):$(this).find('.oneinput').val();
    		 listOne["type"]=0;
    	 }else{
    		 var fieldValue=$(this).find('.value').val();
    		 if(fieldValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的初始值不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else{
    			 listOne["fieldValue"]=fieldValue;
    		 }
    		 var stepValue=$(this).find('.stepValue').val();
    		 if(stepValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的步长不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else{
    			 listOne["stepValue"]=stepValue;
    		 }
    		 var maxValue=$(this).find('.maxValue').val();
    		 if(maxValue==''){
    			 error++;
    			 layer.alert($(this).find('.ileft').text()+'的截止值不能为空，请输入', {
    	 	         icon: 7,
    	 	       },function(index){
    	 	    	   layer.close(index);
    	 	     });
    			 return;
    		 }else{
    			 listOne["maxValue"]=maxValue;
    		 }
    		 listOne["type"]=1;
    	 } 
    	 list.push(listOne);
	 });
	 var data=new Object();
	 data.list=list;
	 data.frequency=$('#dateType').attr('data-type');
	 if($('#starttime').attr('data')!==''){
		 data.startTime=common.transdate($('#starttime').attr('data'));
	 }else{
		 layer.alert('请选择开始时间', {
 	         icon: 7,
 	       },function(index){
 	    	   layer.close(index);
 	     });
		 return;
	 }
	 if($('#endtime').attr('data')!==''){
		 data.endTime=common.transdate($('#endtime').attr('data'));
	 }else{
		 layer.alert('请选择结束时间', {
 	         icon: 7,
 	       },function(index){
 	    	   layer.close(index);
 	     });
		 return;
	 }
		//接口
		var link=common.sysurl()+'/firmoffer/getHisBacktoTake.m';

	    //ajax
		if(error==0){
			error=0;
			 ajax.tranData(link,JSON.stringify(data), true, "post", function(rs) {

			        if(rs.code==0){
			          var time=rs.data.time;
			          if(time>=864000){
			        	  layer.alert('此次历史回测，共'+rs.data.groupNum+'组参数，预计耗时超过240小时，不能进行历史回测', {
				    	         icon: 7,
				        	},function(index){
				        		 $('.popbox img').attr('src',$('#codeurl').val()+'?type=2');
				    	    	   layer.close(index);
				    	     });
			          }else{
			        	  var text=formatSeconds(rs.data.time)+'，是否继续？';
				          $('.popbox>div p').text(text);
				          simuPara.openPop();
				          
			          }
			         
			     }else if(rs.code==411){
				       	 layer.alert(rs.message, {
			    	         icon: 7,
			    	       },function(index){
			    	    	   location.href=common.sysurl()+'/frontend/login.do';
			    	    	   layer.close(index);
			    	       });
			       }else if(rs.code==100){
			        	layer.alert(rs.message, {
			    	         icon: 7,
			        	},function(index){
			        		
			    	    	   layer.close(index);
			    	     });
			        };
			    }, function(rs) {
			        ajax.sessionLost(rs.responseText);
			    });	  
		}else{
			
		}
}
/**
 * DES:回车键执行
 * @param void;
 * @return void;
 */
$(document).keypress(function(e) {
	// 回车键事件
	if (e.which == 13) {
		simuPara.sure();
	}
});

//实例化FundManagejs类
var simuPara= new SimuPara();

function formatSeconds(value) { 
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
	} 

var datenow=common.getLocalTime(Date.parse(new Date()),'s');
var yesterdsay =common.getLocalTime(new Date(new Date().getTime() - 86400000),'s');
var start = {
		  elem: '#starttime',
		  format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		  min: '2010-01-01 00:00:00', //最小日期
		  max: yesterdsay, //最大日期
		  istoday: false, //是否显示今天
		  festival: false, //显示节日
		  choose: function(datas){
			 $("#starttime").attr('data',datas);
		     end.min = datas; //开始日选好后，重置结束日的最小日期
		     end.start = datas //将结束日的初始值设定为开始日
		  }
		};
var end = {
		 elem: '#endtime',
		  format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
		  max: yesterdsay, //最大日期
		  istoday: false, //是否显示今天
		  festival: false, //显示节日
		  choose: function(datas){
		    start.max = datas; //结束日选好后，重置开始日的最大日期
		    $("#endtime").attr('data',datas);
		  }
		};
		
