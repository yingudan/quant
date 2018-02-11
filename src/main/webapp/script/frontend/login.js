// +----------------------------------------------------------------------
// | des:login.js
// +----------------------------------------------------------------------
// | 2017-03-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//初始化执行
$(function(){
	login.setpage();
	$(".loginform input").focus(function(){
		$(".error").text("");
	});
	if($.cookie('errnum')){
		
	}else{
		$.cookie('errnum',0,{path: '/' });
	}
	/*最后一次登录错误时间*/
	if($.cookie('ltime')){
		
	}else{
		$.cookie('ltime',0,{path: '/' });
	}
	
	if($.cookie('rem')&&$.cookie('rem')==1){
		$('#uname').val($.cookie('username'));
	}else{
		$('#uname').val('');
	}
	var now= Date.parse(new Date());
	//*错误才输大于3并且30分钟之内 显示验证码*/
	if($.cookie('errnum')>=3&&(now-$.cookie('ltime'))<300000){
		$('.opbox .code').show(); 
  		var link=$('#codeurl').val();
		$('.opbox .code img').attr('src',link+'?type=1');
		$('.loginbox ').addClass('codebox');
	}
	
	$('#imgcode').val('');
	if($('.loginform .code').css('display')=='block'){
		$(".opbox").addClass('opboxcode');
	}
	
	/*页面变化*/
	$(window).resize(function(){
		login.setpage();
	});
	 $('.menudown').mouseenter(function(){
		  login.menudown(this);
	  }).mouseleave(function(){
		  login.menuup(this);
	  });
});

//构造类
function Login() {
	this.rember=1;//1未选中 2选中
}
Login.prototype.menudown=function(me){
	$(me).find('.submenu').show();
	$(me).addClass('a_select');
},
Login.prototype.menuup=function(me){
	$(me).find('.submenu').hide();
	$(me).removeClass('a_select');
}
/**
 * 设置页面的高度
 */
Login.prototype.setpage=function(){
	if($(window).height()>800){
		$('body').height($(window).height());
		$('body').css('overflow-y','hidden');
		$('.footer').css('position','fixed');
		$('.footer').css('margin-top','0');
		$('.opbox').removeClass('opboxstatic');
	}else{
		$('body').height('800px');
		$('body').css('overflow-y','auto');
		$('.footer').css('position','static');
		$('.footer').css('margin-top','150px');
		$('.opbox').addClass('opboxstatic');
	}
	
}
/**
 * DES:执行登录
 * @param void;
 * @return void;
 */
Login.prototype.logining=function(){
	
	 var uname = $("#uname").val();
	 var pword = $("#pword").val();
	 var imgcode = $("#imgcode").val();
	 var rlt=login.isPhone(uname);
	 if(!rlt){
		 $(".error").text('请输入正确的手机号码');
		 return;
	 }else{
		 $(".error").text('');
	 }
	
	 var pattern = /^[a-zA-Z0-9_]{6,14}$/; 
	 if(pattern.test(pword)){
		 $(".error").text('');
	 }else{
		 $(".error").text('密码由英文、数字、下划线组成，6~14位');
	 }
	 var now= Date.parse(new Date());
	 var num=$.cookie('errnum');
	if (num>=3&&str.isblank(str.trim(imgcode))&&(now-$.cookie('ltime'))<300000) {
		$(".error").text('请输入验证码');
		$('.opbox .code').show(); 
  		var link=$('#codeurl').val();
		$('.opbox .code img').attr('src',link+'?type=1');
		return;
	}
	//接口
	var link=$('#loginurl').val();
	
	//需要传递的参数
	if (num>=3){
		var dataLink="uname="+uname+"&upass="+hex_md5(pword)+"&imageCode="+imgcode;
	}else{
		var dataLink="uname="+uname+"&upass="+hex_md5(pword);
	}
	if($('#remeber').is(':checked')){
		$.cookie('rem',1,{path:'/'});
	}else{
		$.cookie('rem',2,{path:'/'});
	}
	
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	$.cookie('errnum',0,{path: '/' });
        	$.cookie('username',rs.data.userName,{path: '/' });
        	$.cookie('uid',rs.data.id,{path: '/' });
        	
        	var href=document.referrer;
        	console.log(href);
        	if(href.indexOf('login')>-1||href.indexOf('reg')>-1){
        		location.href=login.sysurl()+'/frontend/index.do';
        		
        	}else{
        		location.href=href;
        	}
        }else{
	          $('.error').text(rs.message);
  	    	 $.cookie('errnum',rs.data.errorcount,{path: '/' });
  	    	 var now1= Date.parse(new Date());
  	    	 
  	    	 if(rs.data.errorcount>2&&(now1-$.cookie('ltime'))<300000){
  	    		$('.opbox').addClass('opboxcode');
  	    		$('.opbox .code').show(); 
  	    		var timestamp = Date.parse(new Date());
  	  		    $('.opbox .code img').attr('src',login.sysurl()+'/member/getImageValidate.m?type=1&t='+timestamp);
  	    	 }else{
  	    		$('.opbox').removeClass('opboxcode');
  	    	 }
  	    	 
            if(rs.message=='验证码不正确'){
            	var timestamp = Date.parse(new Date());
	  		    $('.opbox .code img').attr('src',login.sysurl()+'/member/getImageValidate.m?type=1&t='+timestamp);
	  		 }
  	    	 $.cookie('ltime',now1,{path: '/' });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	 
}

/**
 *获取系统url
 */
Login.prototype.sysurl=function(){ 
 return  $("#sysurl").text();
},

/*
 * 重新获取验证码
 * */
Login.prototype.reCode=function(me,link){
	var timestamp = Date.parse(new Date())
	$(me).attr('src',link+'?type=1&t='+timestamp);
}

/**
 * DES:退出登录
 * @param void;
 * @return void;
 */
Login.prototype.logout=function(){
	 parent.layer.confirm('确认退出登录？', {
		 title:'退出登录'
     }, function(index){
    	  location.href="login.do";
    	 parent.layer.close(index);
	 });
}



/**
 * DES:验证手机号码
 * @param void;
 * @return void;
 */
Login.prototype.isPhone=function(phone){
	// 验证手机号
	 var pattern = /^1[34578]\d{9}$/; 
	 return pattern.test(phone); 

}
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Login.prototype.checkPhone=function(me){
	// 验证手机号
	 var value=$(me).val();
	 var rlt=login.isPhone(value);
	 if(!rlt){
		 $(me).next().next().text('请输入正确的手机号码');
	 }else{
		 $(me).next().next().text('');
	 }
}

/**
 * DES:密码输入框blur事件
 * @param void;
 * @return void;
 */
Login.prototype.checkPass=function(me){
	// 验证手机号
	 var value=$(me).val();
	 var pattern = /^[a-zA-Z0-9_]{6,14}$/; 
	 if(pattern.test(value)){
		 $(me).next().text('');
	 }else{
		 $(me).next().text('密码由英文、数字、下划线组成，6~14位');
	 }
	 
}	
/**
 * DES:获取验证码
 * @param void;
 * @return void;
 */
Login.prototype.getyzm=function(){
	//验证码更换
	$("#captcha").click(function(){
		 $("#captcha").attr("src",$("#captcha").attr("src")+"?");
	});	
	$("#captcha2").click(function(){
		 $("#captcha").attr("src",$("#captcha").attr("src")+"?");
	});	
	//延后500毫秒加载图片
	window.setTimeout(function(){
		var img=$("#captcha");
		img.attr("src",img.attr("data-echo"));
	},500);
}


/**
 * DES:回车键执行
 * @param void;
 * @return void;
 */
$(document).keypress(function(e) {
	// 回车键事件
	if (e.which == 13) {
		login.logining();
	}
});

//实例化FundManagejs类
var login= new Login();



