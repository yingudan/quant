// +----------------------------------------------------------------------
// | des:这是一个js文件模板,有着严格的书写格式，为了统一全站代码风格和提高代码质量，请严格遵守。
// +----------------------------------------------------------------------
// | 2017-03-20
// +----------------------------------------------------------------------
// | Author: liangsir
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//初始化执行
$(function(){

});
//构造类
function Xxx() {}

/**
 * DES:函数功能描述
 * @param xxx xxx的解释
 * @return xxx xxx的解释
 */
FundManage.prototype.xxx=function(){
  
}

//实例化FundManagejs类
var xxx= new Xxx();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
/**
 *desc:描述--json入参
 *@param void;
 *@return void;
 */
function getXxxxxList(){
	//接口
	var link="";
	//需要传递的参数
    var $json="";
    //ajax
    ajax.tranData(link,$json, true, "post", function(rs) {
        if(rs.code==0){
    	   parent.layer.alert("数据保存成功！", {
 	         icon: 1,
 	       },function(index){
 	        //coding 您的逻辑
 	        parent.layer.close(index);
 	       });
        }else{
           parent.layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       parent.layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}


/**
 *desc:描述
 *@param void;
 *@return void;
 */
function getXxxxxList(){
	//接口
	var link="";
	//需要传递的参数
	var dataLink="";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   parent.layer.alert("数据保存成功！", {
 	         icon: 1,
 	       },function(index){
 	        //coding 您的逻辑
 	        parent.layer.close(index);
 	       });
        }else{
           parent.layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	       parent.layer.close(index);
  	       });
        };
    }, function(rs) {
        ajax.sessionLost(rs.responseText);
    });	  
}

/*++++++++++++++++++++++++++++++++++++++++++++++写入html++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//分页函数
function pageFun(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
};

//分页函数
function setPageNum(thiz, pageType) {
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  $("#curPage").val(pno);
	  //获取搜索字段
	  var searchText=$.trim($("#searchText").val());
	  var isSearch=$("#isSearch").val();
	  if(str.isNotblank(searchText)&&isSearch==1){
		 
	  }else{
		  
	  };
	});
};

//基金数据数据写入模板
function fundListToHtml(obj) {
	if(str.isblank(obj.list)){
	  $("#data-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	  common.tableAdaptation();
	}else{
	  $("#data-content").html(template("data-content-script",obj));
	  common.tableAdaptation();
	}
}

