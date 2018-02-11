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
	forget.setpage();
	$(".loginform input").focus(function(){
		$(".error").text("");
	});
	/*页面变化*/
	$(window).resize(function(){
		forget.setpage();
	});
});
//构造类
function Forget() {
	this.isget=0;//是否能获取验证码为0是可以获取 大于0小于60不可以;
}
/**
 * 设置页面的高度
 */
Forget.prototype.setpage=function(){
	if($(window).height()>800){
		$('body').height($(window).height());
		$('body').css('overflow-y','hidden');
		$('.footer').css('position','fixed');
		$('.footer').css('margin-top','0');
		$('.formbox').removeClass('formboxc');
	}else{
		$('body').height('800px');
		$('body').css('overflow-y','auto');
		$('.footer').css('position','static');
		$('.footer').css('margin-top','150px');
		$('.formbox').addClass('formboxc');
	}
	
}
/**
 * DES:执行下一步
 * @param void;
 * @return void;
 */
Forget.prototype.next=function(){
	var phoneNum = $("#phonenum").val();
	var rlt=forget.isPhone(phoneNum);
	if (!rlt) {
		layer.alert('请输入正确的手机号码', {
 	         icon: 7,
 	       },function(index){
 	    	  
 	    	   layer.close(index);
 	       });
		return;
	}
	
	var code = $(".step1 .code").val();
	
	if (str.isblank(str.trim(code))) {
		$(".step1 .code").next().text('验证码不正确，请重新输入');
	}
	//接口
	var link=$('#step1url').val();
	//需要传递的参数
	var dataLink="phoneNum="+phoneNum+"&code="+code+"&type=3";
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
Forget.prototype.submit=function(){
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
	var dataLink="phoneNum="+$("#phonenum").val()+"&upass="+hex_md5(value)+"&type=2";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	layer.alert('修改成功', {
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
Forget.prototype.isPhone=function(phone){
	// 验证手机号
	 var pattern = /^1[34578]\d{9}$/; 
	 return pattern.test(phone); 

}
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Forget.prototype.checkPhone=function(me){
	// 验证手机号
	 var value=$(me).val();
	 var rlt=forget.isPhone(value);
	 if(!rlt){
		 $(me).next().text('请输入正确的手机号码');
	 }else{
		 $(me).next().text('');
		 //forget.isForget(value);
	 }
}
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Forget.prototype.checkCode=function(me){
	// 验证手机号
	 var value=$(me).val();
	 if (str.isblank(str.trim(value))) {
		 $(me).parent().next().text('请输入验证码');
	}else{
		$(me).parent().next().text('');
	}
}	
/**
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Forget.prototype.checkPass=function(me){
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
 * DES:手机号码输入框blur事件
 * @param void;
 * @return void;
 */
Forget.prototype.checkRePass=function(me){
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
Forget.prototype.isForget=function(phone){
	//接口
	var link=$('#checkurl').val();
	//需要传递的参数
	var dataLink="phoneNum="+phone;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.data==true){
        	
        }else{
        	$('#phonenum').next().text('该账号不存在，请核对后输入');
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}
/*
 * 重新获取验证码
 * */
Forget.prototype.regetImgCode=function(link){
	var timestamp = Date.parse(new Date())
		$('.popbox img').attr('src',link+'?type=3&t='+timestamp);

	
};
/*
 * 关闭弹出框
 * */
Forget.prototype.closePop=function(type){
	$('.popcode').hide();
	$('.popbox').hide();
	$('.popbox .code').val('');
}
/**
 * DES:获取验证码
 * @param void;
 * @return void;
 */
Forget.prototype.getImgCode=function(type,link2){
	
		var phonenum = $("#phonenum").val();
		var rlt=forget.isPhone(phonenum);
		if (!rlt) {
			layer.alert('请输入正确的手机号码', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
			return;
		}
		if(forget.isget!==0){
			layer.msg(forge.isget+'后可重新发送');
		}else{
			//接口
			var link=$('#checkurl').val();
			//需要传递的参数
			var dataLink="phoneNum="+phonenum;
		    //ajax
		    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
		    	
		        if(rs.data==true){
		        	$('.popcode').show();
		    		$('.popbox').show();
		    		var timestamp = Date.parse(new Date());
		    		$('.popbox img').attr('src',link2+'?type='+type+'&t='+timestamp);
		        }else{
		        	$('#phonenum').next().text('该账号不存在，请核对后输入');
		        };
		    }, function(rs) {
		        ajax.sessionLost(rs.responseText);
		    });	 
	    } 
	}
/*
 * 重新获取验证码
 * */
Forget.prototype.forgetetImgCode=function(){
		var link=$('#codeurl').val();
		$('.popbox img').attr('src',link+'?type=2');
};
/**
 * DES:请求发送短信验证码接口
 * @param void;
 * @return void;
 */
Forget.prototype.sure=function(imglink){
	if (forget.isget == 0) { 
		var imageCode = $(".popbox #imgcode").val();
		
		if (str.isblank(str.trim(imageCode))) {
			layer.alert('请输入验证码', {
	 	         icon: 7,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
			return;
		}
		var phoneNum = $("#phonenum").val();
		var rlt=forget.isPhone(phoneNum);
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
		var dataLink="imageCode="+imageCode+"&phoneNum="+phoneNum+"&type=3"
	    //ajax
	    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
	        if(rs.code==0){
	        	layer.msg(rs.message);
	        	forget.closePop();
	        	
	        	forget.isget=60;
	    		time();
	        }else if(rs.code==100){
	        	layer.alert('验证码有误', {
	    	         icon: 7,
	        	},function(index){
	        		var timestamp = Date.parse(new Date());
	        		 $('.popbox img').attr('src',imglink+'?type=3&t='+timestamp);
	    	    	   layer.close(index);
	    	     });
	        };
	    }, function(rs) {
	        ajax.sessionLost(rs.responseText);
	    });	  
		
    } else { // 
    	layer.msg(forget.isget+'后可重新发送');
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
		forget.logining();
	}
});

//实例化FundManagejs类
var forget= new Forget();
/*
 * 定时间
 * */
function time(){
    if (forget.isget == 0) { 
    	$('.getcode').text('获取验证码');
    } else { 
    	$('.getcode').text(forget.isget+'s');
        forget.isget--;
        setTimeout(function() {
            time()
        },
        1000)
    }
}
