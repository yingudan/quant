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
	$login_error=common.GetQueryString("login_error");
	if($login_error>0){
		window.parent.location="login.do";
		return;
	}
	login.resize();
	login.getyzm();
	$(window).resize(function() {
	  login.resize();
	});
	$(".formlogin input").focus(function(){
		$(".tishi").text("");
	});
});
//构造类
function Login() {}

/**
 * DES:执行登录
 * @param void;
 * @return void;
 */
Login.prototype.logining=function(){
	var j_username = $("#username").val();
	var j_password = $("#pwd").val();
	var j_verifycode = $("#code").val();
	if (str.isblank(str.trim(j_username))) {
		$(".tishi").text('请输入用户名');
		return;
	}
	if (str.isblank(str.trim(j_password))) {
		$(".tishi").text('请输入密码');
		return;
	}
	if (str.isblank(str.trim(j_verifycode))) {
		$(".tishi").text('请输入验证码');
		return;
	}
	//接口
	var link="securityLogin";
	//需要传递的参数
	var dataLink="j_username="+j_username+"&j_password="+hex_md5(j_password)+"&j_verifycode="+j_verifycode;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   location.href=rs.data; 
        }else{
           $("#captcha").attr("src",$("#captcha").attr("src")+"?");
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
 * DES:根据屏幕的高度页面自适应
 * @param void;
 * @return void;
 */
Login.prototype.resize=function(){
	$(".wrap").height($(window).height());
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




