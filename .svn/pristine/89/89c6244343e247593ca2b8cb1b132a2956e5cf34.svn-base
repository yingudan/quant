// +----------------------------------------------------------------------
// | 公共的js样式
// +----------------------------------------------------------------------
// | 2017-03-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
function Common() {}

//初始化执行
$(function(){
  common.autologin();
  common.clear($(".isclear"));
  common.showUser();
  var type=$('.header').attr('data');
  if(type!=='none'){
	  common.resetHeight();//数据加载后重置页脚
  }
  
  $('.menudown').mouseenter(function(){
	  common.menudown(this);
  }).mouseleave(function(){
	  common.menuup(this);
  });
  $(window).scroll(function(){
	  var data=$('.header').attr('data');
	  if(data=='scroll'){
		  $('.header').addClass('scroll');
	  }else if(data=='none'){
		  //不做任何操作
	  }else{
		  var scrollLengh=$(window).scrollTop();
			if(scrollLengh>60){
				$('.header').addClass('scroll');
				$('.header').addClass('animated');
				$('.header').addClass('fadeInDown');
				$('.header').removeClass('fadeInOut');
			}else{
				$('.header').removeClass('scroll');
				//$('.header').removeClass('animated');
				$('.header').removeClass('fadeInDown');
				$('.header').addClass('fadeInOut');
				
			}
	  }
	
  });
});

Common.prototype={
		/**/
	autologin:function(){
		var _id=$('.hiddenid').attr('id');
		var _name=$('.hiddenid').attr('uname');
		
		if(_id!==''){
			$.cookie('errnum',0,{path: '/' });
        	$.cookie('username',_name,{path: '/' });
        	$.cookie('uid',_id,{path: '/' });
        	common.showUser();
		}else{
			
		}
	},
	menudown:function(me){
		$(me).find('.submenu').show();
		$(me).addClass('a_select');
	},
	menuup:function(me){
		$(me).find('.submenu').hide();
		$(me).removeClass('a_select');
	},
		/**
		 * 重置页脚高度
		 * */
		resetHeight:function(){
			var windowh=$(window).height();
			var mainh=$('.main').height();
			var headh=$('.tools').height();
			var footerh=483;
			if(windowh>(mainh+headh+footerh)){
				$('.footer').css('position','fixed');
			}else{
				$('.footer').css('position','static');
			}
		},
/**
 * DES:根据屏幕宽度自动调节table-td（th）的宽度
 * @param void
 * @return void
 */
tableAdaptation: function () {
    $width=$(".mainbox").width()-220;
    $tdwidth=$width/$("th.comm").length;
    $(".comm").css("width",$tdwidth).css("max-width",$tdwidth);
    $(window).resize(function(){
       $width=$(".mainbox").width()-220;
       $tdwidth=$width/$("th.comm").length;
       $(".comm").css("width",$tdwidth).css("max-width",$tdwidth);  
    })
},
    /**
     * 是否显示用户登录的信息
     * */
showUser:function(){
	if($.cookie('uid')!=='0'&&$.cookie('username')){
		$(".loginbox").show();
		$('.unloginbox').hide();
		var uname=$.cookie('username');
		var txt=uname.substr(0, 3) + '****' + uname.substr(7);  
		$('#username').text(txt);
		$('.usercon').show();
		$('#loginc').hide();
	}else{
		$(".loginbox").hide();
		$('.unloginbox').show();
		$('.usercon').hide();
		$('#loginc').show();
	}
},
/**
 * 显示登录等息息
 * */
showUl:function(me){
	
	if($(me).attr('data')=='1'){
		$(me).next().show();
		$(me).attr('data','2');
		
	}else{
		$(me).next().hide();
		$(me).attr('data','1');
		
	}
},


logout:function(){
	parent.layer.confirm("退出登录后，将不能查看策略", {
		 title:"提示"
	}, function(index){
		common.doLogout();
	});	
},

/**
 * 退出登录
 * */
doLogout:function(){
	var link=common.sysurl()+'/member/logout.m';
	 $.ajax({
         type: "POST",
         url: link,
         data: {uname:$.cookie('username')},
         dataType: "json",
         success: function(data){
        	 if(data.code===0){
             	$.cookie('uid','0',{path: '/' });
        		layer.alert(data.message, {
		  	         icon:1,
		  	         cancel: function(){
		  	        	location.href=common.sysurl()+'/frontend/login.do';
		  	    	  }
		  	       },function(index){
		  	    	 location.href=common.sysurl()+'/frontend/login.do'; 
		  	    	 parent.layer.close(index);
		  	     });
        	 }else{
        		 layer.alert(data.message, {
		  	         icon:7,
		  	       },function(index){
		  	    	   parent.layer.close(index);
		  	      });
        	 }
         }         
     });
},
/**
 * 关闭弹出框
 * */
closeXpop:function(){
	$('.xpopcode').hide();
	$('.xpopbox').hide();
},
/**
 * 复制密钥到剪切板
 * */
copy:function(){
	
	var clipboard = new Clipboard('.copy');
	clipboard.on('success',function(e){
		 layer.alert('复制成功', {
  	         icon:1,
  	   },function(index){
  		  common.closeXpop();
  		  $('.usercon ul').hide();
  	      parent.layer.close(index);
  	   });
	    e.clearSelection();
	});
},
/**
 * 获取密钥
 * */
showP:function(){
	var link=common.sysurl()+'/member/getSecretKey.m';
	 $.ajax({
        type: "POST",
        url: link,
        data: {uname:$.cookie('uid')},
        dataType: "json",
        success: function(data){
        	
       	 if(data.code===0){
       		$('#key').text(data.data);
       		$('.copy').attr('data-clipboard-text',data.data);
       		$('.xpopcode').show();
       		$('.xpopbox').show();
       		 
       	 }else{
       		 layer.alert(data.message, {
		  	         icon:7,
		  	   },function(index){
		  	      parent.layer.close(index);
		  	   });
       	 }
        }         
    });
	
},

/**
 * DES:根据屏幕高度自动调弹出层
 * @param void
 * @return void
 */
layerAdaptation:function(){
  $height=$(window).height()-200;
  $(".popup .content").css("max-height",$height);
  $(".contentpop").css("max-height",$height);
  $(window).resize(function(){
     $height=$(window).height()-200;
     $(".popup .content").css("max-height",$height);
     $(".contentpop").css("max-height",$height);
  });
},
/**
 * 策略是否有权限进入 
 * 
 * */
islogin:function(){
	window.location.href =common.sysurl()+"/frontend/myStrategy.do";
},
/**
 * DES:选择父级框架集元素节点
 * @param DOM 您要选择的DOM
 * @return object 选中后的DOM
 */
parent:function(dom){
  return $(dom,parent.document)
},



/**
 * DES:选择子级框架集元素节点
 * @param DOM 您要选择的DOM
 * @return object 选中后的DOM
 */
son:function(dom){
  return $(window.frames["iframepage"].document).find(dom);
},


/**
 * DES:跳转
 * @param 跳转url;
 * @return void;
 */
method:function(url){
	$("#iframepage").attr("src",url);
},

/**
 * DES:调用子级框架的js方法
 * @param void 
 * @return object 子元素对象
 */
sonjs:function(){
 return $("#iframepage")[0].contentWindow; 
},


/**
 * DES:模拟radio
 * @param object 您选择的DOM
 * @return void
 */
radios:function(obj){
   $(obj).parent().parent().find("i.i").removeClass("on");
   $(obj).addClass("on");
},


/**
 * DES:模拟check
 * @param object 您选择的DOM
 * @return void
 */
check:function(obj){
   $(obj).toggleClass("on");
},

/**
 * DES:模拟select
 * @param object 您选择的DOM
 * @return void
 */
select:function(obj){
   $(obj).parent().find("ul").show();
   $(obj).parent().find("ul a").click(function(){
      $(obj).val($(this).text());
      $(obj).attr("data-type",$(this).attr("rel"));
      $(obj).parent().find("ul").hide();
   });
   $(document).click(function(){
      $(obj).parent().find("ul").hide();
       })
       $(obj).click(function(event){
            event.stopPropagation();
    })
    $(obj).parent().find(".tishi").text("");
},


/**
 * DES:关闭所有的弹出层
 */
popAllClose:function(){
	  $(".isclear").val("");
	  $(".reasonlength").text("0");
	  $(".shade").css("display","none");
	  $(".tishi").text("");
	  layer.closeAll();
},


/**
 * DES:表单的正则验证
 * @param regs 验证正则规则
 * @param str 验证的字符串
 * @param object 您选择的DOM
 * @return false 验证通过
 * @return true 验证不通过
 */
valid:function(regs,str,obj){
    var reg=regs;
    var re = new RegExp(reg);
    $strs=$.trim(str);
    if(re.test($strs)) {
      $(obj).parent().find(".tishi").text("");
      return false;
    }else{
      $(obj).parent().find(".tishi").text($(obj).attr("placeholder"));
      return true;
    }
},
  
  
/**
 * DES:动态输入时候验证
 * @param object 您选择的DOM
 * @return void
 */
writingValidate:function(obj){
  $str=$.trim($(obj).val());
  $reg=eval($(obj).attr("validRule"));
  common.valid($reg,$str,obj)
},

/**
 * DES:提交的时候验证
 * @param object 您选择的DOM
 * @return false 验证通过
 * @return true 验证不通过
 */
submitValid:function(obj){
    var allValid=1;
    $(obj).find(".isv").each(function(){
          $str=$(this).val();
          $reg=eval($(this).attr("validRule"));
          if(common.valid($reg,$str,$(this))){
            allValid=0;
          }
    });
    if(allValid==1){
      return false;
    }else{
      return true; 
    }
},

/**
 * DES:深度清理表单
 * @param object 您选择的DOM
 * @return false 验证通过
 * @return true 验证不通过
 */
clear:function(obj){
   $(obj).val("");
},
  

/**
 * DES:时间转换时间戳
 * @param dateStr 时间格式2015-12-02 12:00
 * @return void 
 * @return str 时间戳
 */
transdate:function(dateStr){
    if(str.isblank(dateStr)){
        return "";	
    }else{
    	var newstr = dateStr.replace(/-/g,'/'); 
	    var date =  new Date(newstr); 
	    var time_str = date.getTime().toString();
	    if(time_str.substr(0, 13)=="NaN"){
	    	return "";
	    }else{
	    	return time_str.substr(0, 13)	
	    }  
    }	    
},
    
/**
 *时间格式化
 *@param int time时间戳
 *@param str d表示截止天,s表示截止秒,t表示只需要时间部分
 *@return void 
 *@return str 时间戳
 */
 getLocalTime:function(time,type,c) {
	 if(str.isblank(time)){
		   if(c){
			   return "--";  
		   }else{
			   return "";  
		   }
	 }else{
		    var ts = arguments[0] || 0;  
		    var t,y,m,d,h,i,s;  
		    if(ts < 10000*1000*1000){
		    	ts = ts*1000;
		    }
		    t = ts ? new Date(ts) : new Date();  
		    y = t.getFullYear();  
		    m = t.getMonth()+1;  
		    d = t.getDate();  
		    h = t.getHours();  
		    i = t.getMinutes();  
		    s = t.getSeconds();  
		    // 可根据需要在这里定义时间格式  
		    // return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d)+' '+(h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s); 
		    
		    if(type=="ms"){
		    	return y+'-'+(m<10?'0'+m:m); 
		    }else if(type=="d"){
		    	return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d); 
		    }else if(type=="s"){
		    	return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d)+' '+(h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s); 
		    }else if(type=="t"){
		    	return (h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s); 
		    }else if(type==='hms'){
		    	return (h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s); 
		    }else{
		    	return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d)+' '+(h<10?'0'+h:h)+':'+(i<10?'0'+i:i); 
		    }
	 }	
},

/**
 *获取url参数的值
 *@param name 获取字段名
 *@return str 
 */
GetQueryString:function (name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
    var context = ""; 
    if (r != null) 
         context = r[2]; 
    reg = null; 
    r = null; 
    return context == null || context == "" || context == "undefined" ? "" : context; 
},


/**
 *为空和不存在和null都转化为''
 *@param string 要转化的字段
 *@return str 
 */
 convert:function(string){
  if(string==0){
	  return string;
  }
  if(str.isblank(string)){
	  return "";
  }else{
	  return string;
  }
},


/**
 *整数分割,12456789->124,567,789
 *@param string 要转化的字段
 *@return str 
 */
formatNumber:function(s1,n) {
	    var s=Math.abs(s1);
	    if(s1<0){
	    	var as="-";
	    }else{
	    	var as="";
	    }
	    
	    
	    if(str.isblank(s)){
	    	return 0;
	    };
		if(n==0){
			
		}else{
			n = n > 0 && n <= 20 ? n : 2;  
		}
	    
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
	    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
	    t = "";  
	    for (i = 0; i < l.length; i++) {  
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
	    }  
	    if(n==0){
	    	return as+t.split("").reverse().join("")+'';  
	    }else{
	    	return as+t.split("").reverse().join("") + "." + r;  
	    }
	  
},


/**
 *清理特殊符号
 *@param ss 要清理的字段
 *@return str 
 */
outBadData:function(ss){
	var ss=$.trim(ss);
	var pattern = new RegExp("[`&*<>/|']")
    var rs = "";
	for (var i = 0; i < ss.length; i++) {
	    rs = rs + ss.substr(i, 1).replace(pattern, '');
	}
    return rs;
 },

/**
 *为测试人员制定的验证
 *@param ss 要验证的字段
 *@return str 
 */
isBadData:function(ss,message){
	 if((ss.length>common.outBadData(ss).length) || ss=="null" || ss=="undefined"){
	      parent.layer.alert(message+"不能有特殊字符：&*=|''<>，并且不能为null，undefined!", {
	         icon: 7,
	       },function(index){
		       parent.layer.close(index);
	    	   return true;
	       });
	     return true;
	 }else{
		 return false; 
	 } 
},

 
 
/**
 *去除所有空格
 *@param ss 要清理的字段
 *@return str 
 */
noSpace:function(text1){ 
	text2=text1.replace(/[ ]/g,"");
    text3=text2.replace(/[\r\n]/g,"");
	return text3;
},


/**
 *获取系统url
 */
sysurl:function(){ 
 return  $("#sysurl").text();
},


/**
 *阿拉伯数字转换成中文
 */
noToChinese:function(num) {
	var c="";
    if(num==1){c="一";}
    if(num==2){c="二";}
    if(num==3){c="三";}
    if(num==4){c="四";}
    if(num==5){c="五";}
    if(num==6){c="六";}
    if(num==7){c="七";}
    if(num==8){c="八";}
    if(num==9){c="九";}
    if(num==10){c="十";}
    if(num==11){c="十一";}
    if(num==12){c="十二";}
    if(num==13){c="十三";}
    if(num==14){c="十四";}
    if(num==15){c="十五";}
    return c;
},


/**
 *返回到上一页
 */
goBack:function(text1){ 
	window.history.go(-1);	
},

/**
 * DES:面包屑导航
 * @param strs当前页名称
 * @return void
 */
map:function(){
  var a=common.parent(".selectmenu .inner-text").text();
  var url=common.parent(".subitem .select").attr("url");
  var b=common.parent(".subitem .select").text();
  var html="<a href='"+url+"' target='iframepage'>"+a+"</a>";
      html+="<em>>></em>";
      html+="<a target='iframepage' href='"+url+"'>"+b+"</a>";
  common.parent("#webmap").html(html) 
},

/**
 *点击查看大图
 */
bigimg:function(url){
	common.parent("#bigimgbox").css("display","table");
	common.parent("#bigimg").attr("src",url);
},

/**
 *desc:跳转到当前模拟状态
 *@param num type 2历史 1实盘。
 *@return void;
 */
toTesting:function(type){
	//接口
	var link=common.sysurl()+"/firmoffer/findRunTestId.m";
	//需要传递的参数
	var strategyId=common.GetQueryString("strategyid");//策略id
	var dataLink="strategyId="+strategyId+"&type="+type;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	if(rs.code==411){
    		detailcomm.failToLogin(rs.message);
    		return;
    	};
        if(rs.code==0){
 	       if(rs.data>0){
 	    	  if(type==1){
        		location.href=common.sysurl()+"/web/frontend/strategydetail/firmStrategyDetails.do?form=1&testId="+rs.data+"&strategyid="+strategyId;
        	  }else{
        		location.href=common.sysurl()+"/web/frontend/strategydetail/hisStrategyDetails.do?form=2&testId="+rs.data+"&strategyId="+strategyId;
        	  };
 	       }else{
 	    	  if(type==1){
        		location.href=common.sysurl()+"/frontend/simulation.do?type=1&strategyId="+strategyId;
        	  }else{
        		location.href=common.sysurl()+"/frontend/simulation.do?type=2&strategyId="+strategyId;
        	  };
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
},





/**
 *desc:数据格式化
 *@param type 1表示加%,0表示不加%。
 *@param num 保留多少位。
 *@param number 需要被格式化的数据。
 *@return void;
 */
numbersFmt:function(type,num,number){
	    if(number==null){
	    	return "--";
	    };
		number=number.toString();
	  if(number==''||number==null){
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
 
 
/*未完待续...*/ 
    
}




//实例化Commonjs类
var common= new Common();