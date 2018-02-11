// +----------------------------------------------------------------------
// | des:index.js
// +----------------------------------------------------------------------
// | 2017-03-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//初始化执行
$(function(){
	index.layoutsize();
	index.windowchanage();
	index.makemenu();
});
//构造类
function Index() {}

/**
 * DES:页面变化执行
 * @param void;
 * @return void;
 */
Index.prototype.windowchanage=function(){
  $(".popup .content").css("max-height",$(window).height()-150);
  $(window).resize(function(){
	  index.layoutsize();  
	  $(".popup .content").css("max-height",$(window).height()-150);
  });    
}

/**
 * DES:布局的自适应
 * @param void;
 * @return void;
 */
Index.prototype.layoutsize=function(){
   $height=$(window).height(); 
   $width=$(window).width(); 
   if($height<600){
	   $height=600;
   };
   if($width<1400){
	   $width=1400;
   };
   $leftwidth=$("#left").width();
   $headheight=$("#header").height();
   //left自适应
   $("#left").css("height",$height-$headheight+1);
   //right自适应
   $("#right").css("height",$height-$headheight);
   $("#right").css("width",$width-$leftwidth);
   $("#right").css("left",$leftwidth);
   //iframecontainer
   $("#iframecontainer").css("height",$height-$headheight-26);
}



/**
 * DES:配置侧栏导航栏目
 * @param void;
 * @return void;
 */
Index.prototype.makemenu=function(){
	$json=JSON.parse($("#menudata").text());
    for(var i=0;i<$json.length;i++){
    	if($json[i]['child'].length<1){
		   $json[i]['url']="/error.do";
		}else{
		   $json[i]['url']=$json[i]['child']['0']['url'];
		};
    }
	fatherListToHtml($json);
	sonListToHtml($json);
}


/**
 * DES:左侧点击右侧改变
 * @param url;
 * @param eq;
 * @param obj;
 * @param type 1表示点击主栏目，2点击子菜单;
 * @return void;
 */
Index.prototype.method=function(url,eq,obj,type){
	$("#iframepage").attr("src",url);
	if(type==1){
		$(".subitem .select").removeClass("select");
		$("#father-content").find(".selectmenu").removeClass("selectmenu");
		$(obj).addClass("selectmenu");
		$("#son .subitembox").hide();
		$("#son .subitembox:eq("+eq+")").show();
		$("#son .subitembox:eq("+eq+") li:eq(0)").find("a").addClass("select");
	}else{
		$("#son").find(".select").removeClass("select");
		$(obj).find("a").addClass("select");
	};
}





/**
 * DES:侧栏的展开与收起
 * @param type 1表示主栏目的操作，其他表示对子栏目的操作;
 * @return void;
 */
Index.prototype.menutoggle=function(type){
   if(type==1){
     $("#left").toggleClass("fatherhide");
	 if($("#left").hasClass("fatherhide")&&$("#left").hasClass("sonhide")){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("onew"); 
	 }else if($("#left").hasClass("fatherhide")&&(!$("#left").hasClass("sonhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("threew"); 
	 }else if($("#left").hasClass("sonhide")&&(!$("#left").hasClass("fatherhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("twow");
	 }else if((!$("#left").hasClass("sonhide"))&&(!$("#left").hasClass("fatherhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew"); 
	 };
   }else{
     $("#left").toggleClass("sonhide");
	 if($("#left").hasClass("fatherhide")&&$("#left").hasClass("sonhide")){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("onew"); 
	 }else if($("#left").hasClass("fatherhide")&&(!$("#left").hasClass("sonhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("threew"); 
	 }else if($("#left").hasClass("sonhide")&&(!$("#left").hasClass("fatherhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew");
		 $("#left").addClass("twow");
	 }else if((!$("#left").hasClass("sonhide"))&&(!$("#left").hasClass("fatherhide"))){
		 $("#left").removeClass("onew").removeClass("twow").removeClass("threew"); 
	 };
   };
   index.layoutsize();
}



/*++++++++++++++++++++++++++++++++++++++++++++++写入html++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//实例化Index类
var index= new Index();

//菜单写入html
function fatherListToHtml(obj) {
	var arr= new Array();
	arr["data"]=obj;
	$("#father-content").html(template("father-content-script",arr));
	$("#father-content li:eq(0)").find("a").addClass("selectmenu");
}

function sonListToHtml(obj) {
	var arr= new Array();
	arr["data"]=obj;
	$("#son").html(template("son-content-script",arr));
	$("#son .subitembox:eq(0)").show();
	$("#son .subitembox:eq(0) li:eq(0)").find("a").addClass("select");
	$("#iframepage").attr("src",$("#son .subitembox:eq(0) li:eq(0)").find("a").attr("url"));
}



