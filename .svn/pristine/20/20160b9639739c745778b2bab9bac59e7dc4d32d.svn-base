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
	
});

 
/**
 * DES:验证手机号是否已经注册
 * @param void;
 * @return void;
 */
Doc.prototype.getlist=function(phone){
	//接口
	var link=$('#checkurl').val();
	//需要传递的参数
	var dataLink="phoneNum="+phone;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	console.log(rs);
        if(rs.data==true){
        	
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




//实例化FundManagejs类
var doc= new Doc();
