function AjaxUtils(){}
var stru = new StringUtils();
/**
 * 扩展jquery ajax 获取数据的方式
 * @param url 服务的url
 * @param json json数据
 * @param async true：异步，false:同步
 * @param mtd 传输type,get/post,默认get
 * @param sucess 成功的回调
 * @param faile 失败的回调
 */
AjaxUtils.prototype.tranData=function(url,json,async,mtd,sucess,faile){
	if(stru.isblank(url)){
		layer.alert('路径不能为空', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(stru.isblank(json)){
		layer.alert('json不能为空', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(async==null){
		async = true;
	}
	
	if(stru.isblank(mtd)){
		mtd = 'get';
	}
	
	if(stru.isblank(sucess)){
		layer.alert('必须要有成功的回调', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(typeof sucess != "function"){
		layer.alert('设置的成功回调有误', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(stru.isNotblank(faile)){
		if(typeof faile != "function"){
			layer.alert('设置的错误回调有误', {
			    icon: 0,
			    skin: 'layer-ext-moon',
			});
			return;
		}
	}
	
	$.ajax({
        url:url,
        cache:false,
        type:mtd,//可选get
        contentType:"application/json;charset=utf-8",
        data:json,
        timeout:15*10000,// 15ss
        dataType : 'json',
        async:async,
        success:function(msg){
        	(eval(sucess))(msg);
        },
       /* complete:function(XMLHttpRequest,status){ //请求完成后最终执行参数
            if(status=='timeout'){
            	
            }
        },*/
        error:function(m,m1){//ajax提交失败的处理函数！
        	if(m1=='timeout'){
        		layer.alert('网络有点不给力', {
    			    icon: 0,
    			    skin: 'layer-ext-moon',
    			});
        	}else{
        		if(m.responseText){
        			if(faile){
                        (eval(faile))(m);
                    }else{
                    	layer.alert(m.responseText, {
            			    icon: 0,
            			    skin: 'layer-ext-moon',
            			});
                    }
        		}
        		
        	}
           
        }
    });
	
}


AjaxUtils.prototype.tranDataParams=function(url,datas,async,mtd,sucess,faile){
//	datas=encodeURI(encodeURI(datas));
	if(stru.isblank(url)){
		layer.alert('路径不能为空', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	/*if(stru.isblank(datas)){
		alert("datas不能为空");
		return;
	}*/
	
	if(async==null){
		async = true;
	}
	
	if(stru.isblank(mtd)){
		mtd = 'get';
	}
	
	if(stru.isblank(sucess)){
		layer.alert('必须要有成功的回调', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(typeof sucess != "function"){
		layer.alert('设置的成功回调有误', {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	
	if(stru.isNotblank(faile)){
		if(typeof faile != "function"){
			layer.alert('设置的错误回调有误', {
			    icon: 0,
			    skin: 'layer-ext-moon',
			});
			return;
		}
	}
	
	$.ajax({
        url:url,
        cache:false,
        type:mtd,//可选get
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        data:datas,
        timeout:15*10000,// 15ss
        dataType : 'json',
        async:async,
        success:function(msg){
        	(eval(sucess))(msg);
        },
       /* complete:function(XMLHttpRequest,status){ //请求完成后最终执行参数
            if(status=='timeout'){
            	
            }
        },*/
        error:function(m,m1){//ajax提交失败的处理函数！
        	if(m.status==200 && m.readyState==4){ //这里如果是“？”就会调到错误中来
        		(eval(sucess))(JSON.parse(m.responseText));
        	}else{
        		if(m1=='timeout'){
            		layer.alert('网络有点不给力', {
        			    icon: 0,
        			    skin: 'layer-ext-moon',
        			});
            	}else{
            		if(m.responseText){
            			if(faile){
                            (eval(faile))(m);
                        }else{
                        	layer.alert(m.responseText, {
                			    icon: 0,
                			    skin: 'layer-ext-moon',
                			});
                        }
            		}
            		
            	}
        	}
        }
    });
	
}

AjaxUtils.prototype.sessionLost=function(text){
	if(stru.isblank(text)){
		return;
	}
	if(text.indexOf("<!DOCTYPE") == -1){
		layer.alert(text, {
		    icon: 0,
		    skin: 'layer-ext-moon',
		});
		return;
	}
	var curWwwPath=window.document.location.href;  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    var localhostPaht=curWwwPath.substring(0,pos)+"/investor/backend/index"; 
    
    layer.alert('您的会话已超时,请重新登录', {
	    icon: 0,
	    skin: 'layer-ext-moon',
	},function(index){
		layer.close(index);
		window.location.href=localhostPaht; 
	});
    
}


function tranFile(){
	
}