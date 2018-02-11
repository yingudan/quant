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
	reg.setpage();
	$(".loginform input").focus(function(){
		$(".error").text("");
	});
	/*页面变化*/
	$(window).resize(function(){
		reg.setpage();
	});
	reg.getImgCode();
});
//构造类
function Reg() {
	this.isget=0;//是否能获取验证码为0是可以获取 大于0小于60不可以;
}
/**
 * 设置页面的高度
 */
Reg.prototype.setpage=function(){
	if($(window).height()>950){
		$('body').height($(window).height());
		$('body').css('overflow-y','hidden');
		$('.footer').css('position','fixed');
		$('.footer').css('margin-top','0');
		$('.formbox').removeClass('formboxc');
	}else{
		$('body').height('950px');
		$('body').css('overflow-y','auto');
		$('.footer').css('position','static');
		$('.footer').css('margin-top','100px');
		$('.formbox').addClass('formboxc');
	}
	
}
/**
 *获取系统url
 */
Reg.prototype.sysurl=function(){ 
 return  $("#sysurl").text();
},
/**
 * 设置页面的高度
 */
Reg.prototype.boxcheck=function(me){
	if($(me).hasClass('on')){
		$(me).removeClass('on');
	}else{
		$(me).addClass('on');
	}
	
}
/**
 * DES:执行下一步
 * @param void;
 * @return void;
 */
Reg.prototype.next=function(){
	var phoneNum = $("#phonenum").val();
	var rlt=reg.isPhone(phoneNum);
	if (!rlt) {
		layer.alert('请输入正确的手机号码', {
 	         icon: 7,
 	       },function(index){
 	    	  
 	    	   layer.close(index);
 	       });
		return;
	}
	
	var code = $(".step1 .codex .code").val();
	
	if (str.isblank(str.trim(code))) {
		$(".step1 .code").next().text('验证码不正确，请重新输入');
	}
	 var nickName=$('#username').val();
	 if(nickName.legnth=0||nickName.length>20){
		 layer.alert('用户昵称的长度为1-20字', {
 	         icon: 7,
 	       },function(index){
 	    	  
 	    	   layer.close(index);
 	       });
		return;
	 }
	
	//接口
	var link=$('#step1url').val();
	//需要传递的参数
	var dataLink="phoneNum="+phoneNum+"&code="+code+"&type=2&nickName="+nickName;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
    	   $('.step1').hide();
    	   $('.step2').show();
    	   $('.progtop .two').addClass('on');
    	   $('.progtop .two').siblings().removeClass('on');
    	   $('.progbottom .two').show();
    	   $('.progbottom .one').hide();
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

/**
 * DES:执行设置密码
 * @param void;
 * @return void;
 */
Reg.prototype.submit=function(){
	var pass = $("#password1").val();
	var pattern = /^[a-zA-Z0-9_]{6,14}$/; 
	if(!pattern.test(pass)){
		 $('.step2 .error').text('密码由英文、数字、下划线组成，6~14位');
		 return;
	}else{
		 $('.step2 .error').text('');
	}
	
	 var value=$('#password2').val();
	 var value2=$('#password1').val();
	 if(value!==value2){
		 $('.step2 .error').text('两次密码输入不一样，请确认');
		 return;
	 }else{
		 $('.step2 .error').text('');
	 }
	
	 
	 
	//接口
	var link=$('#step2url').val();
	//需要传递的参数
	var dataLink="phoneNum="+$("#phonenum").val()+"&upass="+hex_md5(value)+"&type=1";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	layer.alert('注册成功', {
     	         icon: 1,
     	       },function(index){
     	    	   layer.close(index);
     	    	   window.location=$('#login').val();
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

/**
 * DES:验证手机号码
 * @param void;
 * @return void;
 */
Reg.prototype.isPhone=function(phone){
	// 验证手机号
	 var pattern = /^1[34578]\d{9}$/; 
	 return pattern.test(phone); 

}
/**
 * DES:验证用户名
 * @param void;
 * @return void;
 */
Reg.prototype.checkName=function(me){
	var text=$('#username').val();
	 if (str.isblank(str.trim(text))) {
		 $(me).parent().find('.error').text('请输入用户昵称');
	}else if(str.length>20){
		$(me).parent().find('.error').text('用户昵称长度为1-20字');
	}else{
		$(me).parent().find('.error');
	}
}
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Reg.prototype.checkPhone=function(me){
	// 验证手机号
	 var value=$(me).val();
	 var rlt=reg.isPhone(value);
	 if(!rlt){
		 $(me).next().text('请输入正确的手机号码');
	 }else{
		 $(me).next().text('');
		 reg.isReg(value);
	 }
}
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Reg.prototype.checkCode=function(me){
	// 验证手机号
	 var value=$(me).val();
	 if (str.isblank(str.trim(value))) {
		 $(me).parent().next().text('请输入验证码');
	}else{
		$(me).parent().next().text('');
	}
}	
/**
 * DES:密码输入框blur事件
 * @param void;
 * @return void;
 */
Reg.prototype.checkPass=function(me){
	// 验证手机号
	 var value=$(me).val();
	 
	 var pattern = /^[a-zA-Z0-9_]{6,14}$/; 
	 if(pattern.test(value)){
		 $('.step2 .error').text('');
	 }else{
		 $('.step2 .error').text('密码由英文、数字、下划线组成，6~14位');
	 }
	 
}	
/**
 * DES:密码输入框blur事件
 * @param void;
 * @return void;
 */
Reg.prototype.checkRePass=function(me){
	// 验证手机号
	 var value=$(me).val();
	 var value2=$('#password1').val();
	 if(value!==value2){
		 $('.step2 .error').text('两次密码输入不一样，请确认');
	 }else{
		 $('.step2 .error').text('');
	 }
}	
/**
 * DES:验证手机号是否已经注册
 * @param void;
 * @return void;
 */
Reg.prototype.isReg=function(phone){
	//接口
	var link=$('#checkurl').val();
	//需要传递的参数
	var dataLink="phoneNum="+phone;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.data==false){
    	   
        }else{
           $('#phonenum').next().text('该手机号码已注册');
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}

/*
 * 关闭弹出框
 * */
Reg.prototype.closePop=function(type){
	$('.popcode').hide();
	$('.popbox').hide();
	$('.popbox .code').val('');
}
/**
 * DES:获取验证码
 * @param void;
 * @return void;
 */
Reg.prototype.getImgCode=function(type,link){
				var link=$('#codeurl').val();
				var timestamp = Date.parse(new Date());
				$('#imgcode').next().attr('src',link+'?type=2&t='+timestamp);
	
}
/*
 * 重新获取验证码
 * */
Reg.prototype.regetImgCode=function(link){
		var timestamp = Date.parse(new Date());
		$('#imgcode').next().attr('src',link+'?type=2&t='+timestamp);
};
/**
 * DES:请求发送短信验证码接口
 * @param void;
 * @return void;
 */
Reg.prototype.sure=function(imglink){
	if (reg.isget == 0) { 
		var imageCode = $(" #imgcode").val();
		
		if (str.isblank(str.trim(imageCode))) {
			layer.alert('请输入验证码', {
	 	         icon: 7,
	 	       },function(index){
	 	    	    reg.getImgCode();
	 	    	   layer.close(index);
	 	       });
			return;
		}
		var phoneNum = $("#phonenum").val();
		var rlt=reg.isPhone(phoneNum);
		if (!rlt) {
			layer.alert('请输入正确的手机号码', {
	 	         icon: 7,
	 	       },function(index){
	 	    	  
	 	    	   layer.close(index);
	 	       });
			return;
		}
		//接口
		var link=$('#msgcodeurl').val();
		//需要传递的参数
		var dataLink="imageCode="+imageCode+"&phoneNum="+phoneNum+"&type=2"
	    //ajax
	    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
	        if(rs.code==0){
	        	layer.msg(rs.message);
	        	reg.closePop();
	        	
	        	reg.isget=60;
	    		time();
	        }else if(rs.code==100){
	        	layer.alert('验证码有误', {
	    	         icon: 7,
	        	},function(index){
	        		reg.getImgCode();
	    	    	   layer.close(index);
	    	     });
	        };
	    }, function(rs) {
	        ajax.sessionLost(rs.responseText);
	    });	  
		
    } else { // 
    	layer.msg(reg.isget+'后可重新发送');
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
		reg.logining();
	}
});

//实例化FundManagejs类
var reg= new Reg();
/*
 * 定时间
 * */
function time(){
    if (reg.isget == 0) { 
    	$('.getcode').text('获取验证码');
    } else { 
    	$('.getcode').text(reg.isget+'s');
        reg.isget--;
        setTimeout(function() {
            time();
        },
        1000)
    }
}
