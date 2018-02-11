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
	this.strategyId=15;
	this.testId=-1;
}
//初始化执行
$(function(){
	simuPara.Init();
	simuPara.getPara();	
	/*JQuery 限制文本框只能输入数字和小数点*/    
    
});
/**
 * 左侧参数加载完成后绑定所有事件 只能输入 数字小数点
 * */

SimuPara.prototype.bindKeyup=function(obg){
	$(".inputcheck").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9.]/g,''));      
    }).bind("paste",function(){  //CTR+V事件处理      
        $(this).val($(this).val().replace(/[^0-9.]/g,''));       
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用   
}
/**
 * 左侧参数加载完成后绑定所有事件 只能输入 数字小数点 
 * */

SimuPara.prototype.bindKeyuparr=function(obg){
	$(".inputcheckarray").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9.-]/g,''));      
    }).bind("paste",function(){  //CTR+V事件处理      
        $(this).val($(this).val().replace(/[^0-9.-]/g,''));       
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用   
}


/*
 * 数据验证
 * */
SimuPara.prototype.checkone=function(me){
	var value=$(me).val();
	var defaultvalue=$(me).attr('data');
	if(value!==''){
		var tip=$(me).parent().prev().text();
		if(value<-999999999.9999){
			layer.alert(tip+' 最小值为-999,999,999.9999，请重新输入', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  $(me).val('');
	 	    	 layer.close(index);
	 	     });
		}else if(value>999999999.9999){
			layer.alert(tip+' 最大值为999,999,999.9999，请重新输入', {
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
	}
}

/*初始化函数变量*/
SimuPara.prototype.Init=function(){
	var id=common.GetQueryString('strategyId');
	simuPara.strategyId=id;
	var testId=common.GetQueryString('testId');
	simuPara.testId=testId;
}
/**
 * DES:执行提交
 * @param void;
 * @return void;
 */
SimuPara.prototype.submit=function(){
	 var list=new Array();
	 $("#list input[type='text']").each(function(){
		
		 var listOne=new Object(); 
    	 listOne["id"]=$(this).attr('data-id');
    	 listOne["strategyId"]=simuPara.strategyId;
    	 listOne["paramName"]=$(this).attr('paramName');
    	 listOne["fieldName"]=$(this).attr('fieldName');
    	 listOne["fieldValue"]=$(this).val()==''?$(this).attr('data'):$(this).val();
    	 listOne["type"]=0;
    	 list.push(listOne);
	 });
	 var data=new Object();
	 data.list=list;
	 data.iniFunding=$('#total').val()==''?$('#total').attr('data'):$('#total').val();
	 data.type=1;
	 data.strategyId=simuPara.strategyId;
	 data.startTime="";
	 data.endTime="";
	 data.frequency=0;
	
	//接口
	var link=common.sysurl()+'/firmoffer/startStrategy.m';
	

    //ajax
    ajax.tranData(link,JSON.stringify(data), true, "post", function(rs) {
    	
        if(rs.code==0){
        	layer.alert(rs.message, {
	 	         icon:1,
	 	       },function(index){
	 	    	   window.location=common.sysurl()+'/web/frontend/strategydetail/firmStrategyDetails.do?form=1&testId='+rs.data+'&strategyId='+simuPara.strategyId;
	 	    	   layer.close(index);
	 	       });
	       }else if(rs.code==411){
	       	 layer.alert(rs.message, {
	    	         icon: 7,
	    	       },function(index){
	    	    	   location.href=common.sysurl()+'/frontend/login.do';
	    	    	   layer.close(index);
	    	       });
	       }else if(rs.code==100){	
	        	simuPara.errAlert();
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
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
/**
 * 关闭启动失败的页面
 * */
SimuPara.prototype.closeError=function(){
	layer.closeAll(); //疯狂模式，关闭所有层
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
	simuPara.bindKeyuparr();
}


;

//实例化FundManagejs类
var simuPara= new SimuPara();




