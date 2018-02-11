// +----------------------------------------------------------------------
// | des:pcenter.js
// +----------------------------------------------------------------------
// | 2017-07-18
// +----------------------------------------------------------------------
// | Author: andrew
// +----------------------------------------------------------------------
var str = new StringUtils();
var ajax = new AjaxUtils();
//初始化执行
$(function(){
	pcenter.getindex('');	
	pcenter.settopMenu();
});
//构造类
function Pcenter() {
	this.countdown=10;
	this.defaultdown=10;//页面需要多少秒刷新
}
/*选第几个头部菜单显示*/
Pcenter.prototype.settopMenu=function(){
	var para=common.GetQueryString('type');
	if(para==''){
		$('.noindex').show();
		$('.noindex').siblings().hide();
	}else if(para=='1'){
		$('.oneindex').show();
		$('.oneindex').siblings().hide();
	}else if(para=='2'){
		$('.twoindex').show();
		$('.twoindex').siblings().hide();
	}
}
	
/*
 * 获取主页数据
 * */
Pcenter.prototype.getindex=function(){
	//接口
	var link=common.sysurl()+'/member/queryUserInfo.m';
	//需要传递的参数

    var dataLink = 'userId='+$.cookie('uid');//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	pcenter.setindex(rs.data);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
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
/*
 * 
 * 跳转到基本信息
 */
Pcenter.prototype.tobaseinfo=function(){
	/*$("#baseinfo").parents('.left').find('.on').removeClass('on');
	$("#baseinfo").addClass('on');
	*/
	$('#baseinfoc').click();
}

/*个人主页赋值*/
Pcenter.prototype.setindex=function(data){
	$('#cmyaccount .name').text(data.userName);
	$('#cmyaccount .phonenum').text(data.callPhone.substr(0, 3) + '****' + data.callPhone.substr(7));
	if(data.introduce!=''){
		$('#cmyaccount .scribe').text(data.introduce);
	}
	$('#cmyaccount .key').text(data.secretKey);
	$('#cmyaccount .copy').attr('data-clipboard-text',data.secretKey);
	$('#cmyaccount .count a').text(data.strategyNum==null?0:data.strategyNum);
	$('#cmyaccount .his a').text(data.hisNum);
	$('#cmyaccount .now a').text(data.nowNum);
	
}
/*获取我购买的*/
Pcenter.prototype.getBuy=function(){
		
	  //接口
		var link2=common.sysurl()+'/member/findUserInfo.m';
		//需要传递的参数

	    var dataLink2 = 'userId='+$.cookie('uid');//'+$.cookie('uid');
	    //ajax
	    ajax.tranDataParams(link2,dataLink2, true, "post", function(rs) {
	    	
	        if(rs.code==0){;
	        	$('#mybuy .key').text(rs.data.accessKey);
	        	$('#mybuy .copy').attr('data-clipboard-text',rs.data.accessKey);
	        	pcenter.setbuy(rs.data.producList);
	        }else if(rs.code==411){
		       	 layer.alert(rs.message, {
	  	         icon: 7,
	  	       },function(index){
	  	    	 location.href=common.sysurl()+'/frontend/login.do';
	  	    	   layer.close(index);
	  	       });
	       }else {
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
/*我购买的套餐*/
Pcenter.prototype.setbuy=function(data){
	if(data.length!==0){//不为空
		var html='';
		for(var i=0;i<data.length;i++){
			html+='<tr>';
			html+='<td>'+data[i].menuName+'</td>';
			if(data[i].productName=='初级版'){
				html+='<td><p class="level">初级版</p><p class="data">一个月，5万次</p></td>';	
			}else if(data[i].productName=='中级版'){
				html+='<td><p class="level">中级版</p><p class="data">一个月，50万次</p></td>';	
			}else{
				html+='<td><p class="level">高级版</p><p class="data">一个月，500次</p></td>';	
			}
			
			html+='<td>'+common.getLocalTime(data[i].buyTime,'d')+'</td>';
			html+='<td>'+common.getLocalTime(data[i].endTime,'d')+'</td>'
			html+='<td>'+data[i].amount+'</td>';
			html+='<td><a href="javascript:void(0)" onclick="pcenter.apply()">续费</a> </td>';
			html+='</tr>';
		}
		$('#tablebuy').find('tr').remove();
		$('#tablebuy').append(html);
	}
}
/**
 * 申请提示
 * */
Pcenter.prototype.apply=function(id){
	layer.alert("购买数据接口服务，请联系有据量化工作人员，联系方式：028-65732739", {
	      icon: 1,
	    },function(index){
	      layer.close(index);
	    });
}
/*
 * 获取主页数据
 * */
Pcenter.prototype.getBaseInfo=function(){
	//接口
	var link=common.sysurl()+'/member/queryUserInfo.m';
	//需要传递的参数

    var dataLink = 'userId='+$.cookie('uid');//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	console.log(rs.data);
        	$("#baseinfo .uname").val(rs.data.userName);
        	$("#baseinfo .pingjai").val(rs.data.introduce);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
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
/*保存基本信息*/
Pcenter.prototype.saveBaseInfo=function(){
	//接口
	var link=common.sysurl()+'/member/saveEdit.m';
	//需要传递的参数
	var userName=$('#baseinfo .uname').val();
	var introduce=$('#baseinfo .pingjai').val();
	//var pingjia
    var dataLink = 'userId='+$.cookie('uid')+'&introduce='+introduce+'&userName='+userName;//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	layer.alert('修改成功', {
	 	         icon: 1,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
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
/*保存基本信息*/
Pcenter.prototype.updatePass=function(){
	//接口
	var link=common.sysurl()+'/member/updatePassword.m';
	//需要传递的参数
	var bfPass=$('#mypass #pass').val();
	var nowPass=$('#mypass #repass').val();
	//var pingjia
    var dataLink = 'userId='+$.cookie('uid')+'&bfPass='+hex_md5(bfPass)+'&nowPass='+hex_md5(nowPass);//'+$.cookie('uid');

    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	layer.alert('修改成功', {
	 	         icon: 1,
	 	       },function(index){
	 	    	   layer.close(index);
	 	       });
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
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
/*
*保留小数点前四位后四位小数
*/
Pcenter.prototype.numDeal=function(num){
	var data=num.toString();
	var leftdata=data.substr(0,data.indexOf('.'));
	var left='';
	if(leftdata.length>=4){
		left=data.substr(data.indexOf(".")-4,4);
	}else{
		left=leftdata;
	}
	var right=data.substr(data.indexOf(".")+1,4);
	return left+'.'+right;
}
/*
*保留小数点前四位后四位小数
*/
Pcenter.prototype.numDeal2=function(num){
	var data=num.toString();
	var leftdata=data.substr(0,data.indexOf('.'));
	var left='';
	if(leftdata.length>=4){
		left=data.substr(data.indexOf(".")-4,4);
	}else{
		left=leftdata;
	}
	var right=data.substr(data.indexOf(".")+1,2);
	return left+'.'+right;
}
/*分页*/
Pcenter.prototype.pageFun=function(totalPage) {
	var curPage = $("#curPage").val();
	setPageData("page_div", "page_script", "total", totalPage, curPage);
}
//分页函数
Pcenter.prototype.setPageNum=function(thiz, pageType) {
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  $("#curPage").val(pno);
	  //获取搜索字段
	  var searchText=$.trim($("#searchtext").val());
	  var isSearch=$("#isSearch").val();
	  if(str.isNotblank(searchText)&&isSearch==1){
		  mystr.getlist(searchText);
	  }else{
		  mystr.getlist(searchText);
	  };
	});
};
//错侧菜单切换
Pcenter.prototype.leftmchange=function(me) {
	var id='#'+$(me).attr('data');
	$(id).show();
	$(id).siblings().hide();
	$(me).parents('.left').find('.on').removeClass('on');
	$(me).addClass('on');
	if($(me).attr('data')=='mybuy'){
		pcenter.getBuy();
	}else if($(me).attr('data')=='cmyaccount'){
		pcenter.getindex();
	}else if($(me).attr('data')=='baseinfo'){
		pcenter.getBaseInfo();
	}
};
/*消息类型的切换*/
Pcenter.prototype.tabchange=function(me,type){
	if(type==1){
		
	}
	//接口
	var link=$('#geturl').val();
	//需要传递的参数
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
    var searchText = stext;
    var dataLink = 'pageSize=' + pageSize + '&pageNum=' + pageNum +  '&searchText='+searchText+'&userId=16';//'+$.cookie('uid');
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
    	
        if(rs.code==0){
        	mystr.setlistfun(rs.data);
        	mystr.pageFun(rs.data.totalPage);
        }else if(rs.code==411){
	       	 layer.alert(rs.message, {
  	         icon: 7,
  	       },function(index){
  	    	 location.href=common.sysurl()+'/frontend/login.do';
  	    	   layer.close(index);
  	       });
       }else {
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
/**
 * DES:回车键执行
 * @param void;
 * @return void;
 */
$(document).keypress(function(e) {
	// 回车键事件
	if (e.which == 13) {
		 var searchText=$.trim($("#searchtext").val());
		 mystr.getlist(searchText);
	}
});

//实例化FundManagejs类
var pcenter= new Pcenter();

//分页函数
function setPageNum(thiz, pageType) {
	
	  setPageNumfun(thiz, pageType, "page_div", "total", function(pno) {
	  if(str.isblank(pno)){
		  $("#curPage").val(1);
	  }else{
		  $("#curPage").val(pno);
	  }
	  mystr.getlist();
	});
};

