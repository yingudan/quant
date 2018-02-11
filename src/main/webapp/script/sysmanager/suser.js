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
	suser.isPageType();
});
//构造类
function Suser() {}

/**
 * DES:判断是列表页面还是编辑页面
 * @param status 1表示列表，其他表示编辑
 * @param name 员工名称
 * @return void
 */
Suser.prototype.isPageType=function(id,status,name){
	$pagetype=common.GetQueryString("pagetype");
	if($pagetype>0){
//	   common.parent("#webmap").html("<a href='javascript:void(0);'>员工管理</a>");
	   suser.doList(null);
	   $("#isSearch").val("");
	   suser.keySearch();
	   common.tableAdaptation();
    }else{
       suser.isTool();
 	   getAllRoles();
    }
}

/**
 * DES:修改员工的状态
 * @param status 1表示暂停，0表示正常
 * @param name 员工名称
 * @return void
 */
Suser.prototype.setUserStatus=function(id,status,name){
	 if(status==0){
		 var statusmessage="暂停员工“"+name+"”的账号后,该账号不可登录";
		 var statusmessagetitle="暂停";
		 var newstatus=1;
	 }else{
		 var statusmessage="确认恢复员工“"+name+"”的账号？";
		 var statusmessagetitle="恢复";
		 var newstatus=0;
	 }
	 parent.layer.confirm(statusmessage,{
		 title:statusmessagetitle+'员工账号'
     }, function(index){
    	 doSetUserStatus(id,newstatus);
    	 parent.layer.close(index);
	 });
}


/**
 * DES:删除一个员工
 * @param status 1表示暂停，0表示正常
 * @param name 员工名称
 * @return void
 */
Suser.prototype.deleteUser=function(id,name){
	 parent.layer.confirm('删除员工“'+name+'”后，数据将无法恢复', {
		 title:'删除员工'
     }, function(index){
    	 deleteSuser(id);
    	 parent.layer.close(index);
	 });
}

/**
 * DES:判断是修改还是添加
 * @param void
 * @return void
 */
Suser.prototype.isTool=function(){
    $id=common.GetQueryString("id");
    if($id>0){
       $maptxt="<a href='sys/suser/index.do?pagetype=1' target='iframepage'>员工管理</a><em>››</em><a href='javascript:void(0);'>修改员工</a>";
 	   $(".addbtnbox .toolBtnyes").text("确认修改");
 	   $(".addbtnbox .toolBtnno").text("放弃修改"); 
 	   $("#password").removeClass("isv");
    }else{
       $maptxt="<a href='sys/suser/index.do?pagetype=1' target='iframepage'>员工管理</a><em>››</em><a href='javascript:void(0);'>添加员工</a>";
       $(".addbtnbox .toolBtnyes").text("确认添加");
  	   $(".addbtnbox .toolBtnno").text("放弃添加"); 
 	   $("#password").addClass("isv");
 	   $("#isSystem i[data-id=0]").addClass("on");
    }
//    common.parent("#webmap").html($maptxt);
}


/**
 * DES:保存数据的时候获取数据
 * @param void
 * @return void
 */
Suser.prototype.getEditData=function(){
    $name=common.outBadData($.trim($("#name").val()));
    $duties=common.outBadData($.trim($("#duties").val()));
    $username=common.outBadData($.trim($("#username").val()));
    $id=common.GetQueryString("id");
    $isSystem=$("#isSystem .on").attr("data-id");
    $password=$.trim($("#password").val());
    $obj=new Object();
    $obj["name"]=$name;
    $obj["duties"]=$duties;
    $obj["username"]=$username;
    $obj["isSystem"]=$isSystem;
    if($password.length>0){
    	$obj["password"]=hex_md5($password);
    }else{
    	$obj["password"]="-9009";
    }
    if($id>0){
       $obj["id"]=$id; 
    }else{
       $obj["id"]="-1"; 
    };
    //获取权限
    var roleStr='';
    $("#roleStr").find(".on:visible").each(function(){
    	$id=$(this).attr("data-id");
    	roleStr=roleStr+$id+",";
    });
    $obj["roleStr"]=(roleStr.substring(roleStr.length-1)==',')?roleStr.substring(0,roleStr.length-1):roleStr;
    return $obj;
}

/**
 * DES:密码的输入时的验证
 * @param void
 * @return void
 */
Suser.prototype.passvalid=function(obj){
	$id=common.GetQueryString("id");
	$password=$.trim($("#password").val());
	if($id>0){
	  if($password.length>0){
		 common.writingValidate(obj);
	 	 $("#password").addClass("isv");
	  }else{
		 $("#password").parent().find(".tishi").text("");
	 	 $("#password").removeClass("isv");
	  }
	}else{
		$("#password").addClass("isv");
		common.writingValidate(obj);
	};
}



/**
 * DES:判断有无特殊字符
 * @param void
 * @return void
 */
Suser.prototype.isbaddata=function(){
    $name=$.trim($("#name").val());
    $duties=$.trim($("#duties").val());
    $username=$.trim($("#username").val());
    $password=$.trim($("#password").val());
    $id=common.GetQueryString("id");
    //判断权限
    var roleStr='';
    $("#roleStr").find(".on:visible").each(function(){
    	$id=$(this).attr("data-id");
    	roleStr=roleStr+$id+",";
    });
    $roleStr=(roleStr.substring(roleStr.length-1)==',')?roleStr.substring(0,roleStr.length-1):roleStr;
    //密码验证
    if($id>0){
	  if($password.length>0){
	 	 $("#password").addClass("isv");
	  }else{
	 	 $("#password").removeClass("isv");
	  }
    }else{
    	$("#password").addClass("isv");
    };
    
    if(common.isBadData($name,"员工姓名")){
       return true;	
    }else if(common.isBadData($duties,"员工职务")){
       return true;	
    }else if(common.isBadData($username,"登录账号")){
       return true;	
    }else if($roleStr.length<1){
    	parent.layer.alert("请为这个员工分配角色！", {
  	      icon: 7,
  	    },function(index){
  	      parent.layer.close(index);
  	    });	
    	return true;
    }else{
       return false;
    }

}


/**
 * DES:提交数据
 * @param void
 * @return void
 */
Suser.prototype.saveData=function(){
	$mid=common.submitValid($("#suser"));
	$mid2=suser.isbaddata();
	$id=common.GetQueryString("id");
	//验证密码
	if($id>0){
	  if($password.length>0){
	  	if($password.length<6||$password.length>16){
	  		$("#password").parent().find(".tishi").text("登录账号,中英文、数字、下划线组成，6~14字");
	  	};
	  }
  	}else{
  	  if($password.length<6||$password.length>16){
	  		$("#password").parent().find(".tishi").text("登录密码,中英文、数字、下划线组成，6~14字");
	  };
  	};
  	if($mid){
  		parent.layer.alert("您输入的信息有误，请根据提示从新输入！", {
	      icon: 7,
	    },function(index){
	      parent.layer.close(index);
	    });
  		return;
  	}else if($mid2){
  		return;
  	}else{
  		saveSuser(suser.getEditData());
  	}
  	
}


/**
 * DES:读取数据列表
 * @param void
 * @return void
 */
Suser.prototype.doList=function(isSearch){
    $("#isSearch").val(isSearch);
    $("#isSearch").text("");
    getSuserList(true,isSearch);
};



/**
 * DES:点击选择系统用户和普通用户
 * @param this obj
 * @return void
 */
Suser.prototype.radios=function(obj){
    common.radios(obj);
    $type=$(obj).attr("data-id");
    if($type==1){
       $("#roleStr").find(".on").removeClass("on");
       $('.isSystem').show();
       $('.noSystem').hide();
    }else{
       $("#roleStr").find(".on").removeClass("on");
       $('.isSystem').hide();
       $('.noSystem').show();
    }
};


/**
 * DES:状态搜索
 * @param void
 * @return void
 */
Suser.prototype.displaydata=function(obj){
   $(".runtype").text($(obj).text());
   $(".runtype").attr("data-type",$(obj).attr("data-type"));
   suser.doList(null);
};


/**
 * DES:回车键搜索
 * @param void
 * @return void
 */
Suser.prototype.keySearch=function(){
  $("#searchText").keypress(function(e) {
    if (e.which == 13) {
    	suser.doList(1);
    }
  });
};

/**
 * DES:点击搜索
 * @param void
 * @return void
 */
Suser.prototype.clickSearch=function() {
	suser.doList(1);
};

/**
 * DES:按照状态搜索
 * @param void
 * @return void
 */
Suser.prototype.clickstatus=function(type,name){
	 $(".runtype").text(name); 
	 $(".runtype").attr("data-type",type); 
	 suser.doList(1);
};





//实例化FundManagejs类
var suser= new Suser();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
/**
 *desc:查询全部权限
 *@param void;
 *@return void;
 */
function getAllRoles(){
	//接口
	var link="allRoles.m";
	//需要传递的参数
	var dataLink="";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
          allRolesToHtml(rs);
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
 *desc:查询一条员工数据
 *@param void;
 *@return void;
 */
function getOneSuser(id){
	//接口
	var link="detail.m";
	//需要传递的参数
	var dataLink="id="+id;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
        	oneSuserToHtml(rs.data);
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
 *desc:修改员工状态
 *@param void;
 *@return void;
 */
function doSetUserStatus(id,disabled){
	//接口
	var link="editStatus.m";
	//需要传递的参数
	var dataLink="id="+id+"&disabled="+disabled;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
    	 parent.layer.alert("数据操作成功！", {
 	         icon: 1,
 	     },function(index){
 	       suser.doList(null);
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
 *desc:删除员工
 *@param void;
 *@return void;
 */
function deleteSuser(id){
	//接口
	var link="delete.m";
	//需要传递的参数
	var dataLink="id="+id;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
    	 parent.layer.alert("员工删除成功！", {
 	         icon: 1,
 	     },function(index){
 	       suser.doList(null);
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
 *desc:获取员工列表
 *@param void;
 *@return void;
 */
function getSuserList(loadPage,isSearch){
    if(loadPage){
	  $("#curPage").val(1);
	}
	$type=$(".tabtnbox .on").attr("data-type");
	/*需要的参数*/
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	var disabled=$(".runtype").attr("data-type");
	var searchText=$.trim($("#searchText").val());
    if((isSearch==1)&&(str.isNotblank(searchText))){
      var searchText=$.trim($("#searchText").val());
    }else{
      var searchText="";
    }
	//需要传递的参数
    if(disabled==2){
      var dataLink ='pageSize=' + pageSize + '&pageNum=' + pageNum+'&searchText=' + searchText;//查询数据参数【搜索】
    }else{
      var dataLink ='pageSize=' + pageSize + '&pageNum=' + pageNum+'&searchText=' + searchText+"&disabled="+disabled;//查询数据参数  【不搜索】
    }
	//接口
	var link="getList.m";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   $("#count").text(rs.data.totalRecords);
    	   rs.data.rowNum = (pageNum - 1) * pageSize;
           pageFun(Math.ceil(rs.data.totalRecords/rs.data.pageSize));
           suserListToHtml(rs.data);
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
 *desc:保存员工
 *@param obj 需要保存的数据;
 *@return void;
 */
function saveSuser(obj){
	//接口
	var link="saveEdit.m";
	//需要传递的参数
	$id=obj["id"];
	$name=obj["name"];
	$duties=obj["duties"];
	$username=obj["username"];
	$password=obj["password"];
	$roleStr=obj["roleStr"];
	$isSystem=obj["isSystem"];
	if($id=="-1"){
	     var dataLink="name="+$name+"&duties="+$duties+"&username="+$username+"&password="+$password+"&roleStr="+$roleStr+"&isSystem="+$isSystem;	
	}else{
	  if($password=="-9009"){
		 var dataLink="id="+$id+"&name="+$name+"&duties="+$duties+"&username="+$username+"&roleStr="+$roleStr+"&isSystem="+$isSystem;  
	  }else{
		 var dataLink="id="+$id+"&name="+$name+"&duties="+$duties+"&username="+$username+"&password="+$password+"&roleStr="+$roleStr+"&isSystem="+$isSystem;  
	  }
	};
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   parent.layer.alert("数据保存成功！", {
 	         icon: 1,
 	       },function(index){
 	       location.href="sys/suser/index.do?pagetype=1";
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
	  if(str.isNotblank(searchText)){
		  getSuserList(false,1);
	  }else{
		  getSuserList(false,null); 
	  };
	});
};


//权限写html入模板
function allRolesToHtml(obj) {
	if(str.isblank(obj.data)){
	  
	}else{
	  $("#roleStr").html(template("allRoles-script",obj));
	  $id=common.GetQueryString("id");
	  if($id>0){
	 	 getOneSuser($id);
	  }else{
		  $("#roleStr .noSystem").show();
	  }
	}
}


//权限写html入模板
function oneSuserToHtml(obj) {
	$("#name").val(obj["name"]);
	$("#duties").val(obj["duties"]);
	$("#username").val(obj["username"]);
	//选中权限
	var obj2=obj["roles"];
	for(var i= 0;i<obj2.length;i++) {
       $id=obj2[i]["id"];
       $("#roleStr i[data-id="+$id+"]").addClass("on");
	}
	//员工类型
	$("#isSystem i").removeClass("on");
	$("#isSystem i[data-id="+obj["isSystem"]+"]").addClass("on");
	if(obj["isSystem"]==1){
	   $(".isSystem").show();
	   $(".noSystem").hide();
	}else{
	   $(".noSystem").show();
	   $(".isSystem").hide();
	}
}


//员工列表数据数据写入模板
function suserListToHtml(obj) {
	if(str.isblank(obj.records)){
	  $("#data-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	  common.tableAdaptation();
	}else{
	  $("#data-content").html(template("data-content-script",obj));
	  common.tableAdaptation();
	}
}
