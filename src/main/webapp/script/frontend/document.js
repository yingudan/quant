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

/**
 *desc:初始化加载
 *@param void;
 *@return void;
 */
$(function(){
   web.docheight();
   web.resizeDo();
   web.text();
});

/**
 *desc:构造函数 
 *@param void;
 *@return void;
 */
function Web(){};

 /**
 *desc：屏幕变化执行
 *@param void;
 *@return void;
 */
Web.prototype.resizeDo=function(){
	$(window).resize(function(){
       web.docheight();
	});
};



/**
 *desc：根据值查找定位
 *@param void;
 *@return void;
 */
Web.prototype.urlQtoUrl=function(){
	var q=common.GetQueryString("q");
	if(!str.isblank(q)){
		 $href=$(".docLeft a[data-txt='"+q+"']").attr("href");
		 if(!str.isblank($href)){
			 location.href=common.sysurl()+"/web/frontend/document.do"+$href; 
		 } 
	}
};
/**
 *desc：模拟滚动
 *@param void;
 *@return void;
 */
Web.prototype.anchor=function(){

};



 /**
 *desc：布局高度高度自适应
 *@param void;
 *@return void;
 */
Web.prototype.docheight=function(){
  $height=$(window).height();
  $(".docLeft").css('height',$height-60);
  $(".docRight").css('height',$height-60);
};


Web.prototype.text=function(){
	var html=$(".dataquyu").html();  
	var html2=html.replace(/{1{/gi, "<label class='anchor grandpa'><em class='mdlj'></em>")
    var html3=html2.replace(/{2{/gi, "<label class='anchor father'><em class='mdlj'></em>")
    var html4=html3.replace(/{3{/gi, "<label class='anchor son'><em class='mdlj'></em>")
    var html5=html4.replace(/}1}/gi, "</label>")
    var html6=html5.replace(/}2}/gi, "</label>")
    var html7=html6.replace(/}3}/gi, "</label>")
    $(".content").html(html7);
	var html8="";
	var eq=0;
	$(".content .anchor").each(function(){
		eq=eq+1;
		$(this).find("em").attr("id","idanchor"+eq);
	    $txt=$(this).text();
	    if($(this).hasClass("grandpa")){
	    	html8=html8+"<a data-txt='"+encodeURIComponent($txt)+"' href='#idanchor"+eq+"' data-href='idanchor"+eq+"'class='grandpa' onclick='web.anchor(this)'>"+$txt+"</a>";
	    }else if($(this).hasClass("father")){
	    	html8=html8+"<a data-txt='"+encodeURIComponent($txt)+"' href='#idanchor"+eq+"' data-href='idanchor"+eq+"'class='father' onclick='web.anchor(this)'><i class='i'></i>"+$txt+"</a>";
	    }else if($(this).hasClass("son")){
	    	html8=html8+"<a data-txt='"+encodeURIComponent($txt)+"' href='#idanchor"+eq+"' data-href='idanchor"+eq+"'class='son' onclick='web.anchor(this)'>"+$txt+"</a>";
	    };
	});
	$(".docLeft .ul").html(html8); 
	web.urlQtoUrl();
};





/**
 *desc:构造函数实例化
 */
var web = new Web();




