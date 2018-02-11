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
	role.isPageType();
});
//构造类
function Role() {}

/**
 * DES:判断是列表页面还是编辑页面
 * @param status 1表示列表，其他表示编辑
 * @param name 角色名称
 * @return void
 */
Role.prototype.isPageType=function(id,status,name){
	$pagetype=common.GetQueryString("pagetype");
	if($pagetype>0){
//	   common.parent("#webmap").html("<a href='javascript:void(0);'>角色管理</a>");
	   role.doList(null);
	   $("#isSearch").val("");
	   role.keySearch();
	   common.tableAdaptation();
    }else{
       role.isTool();
    }
}




/**
 * DES:删除一个角色
 * @param status 1表示暂停，0表示正常
 * @param name 角色名称
 * @return void
 */
Role.prototype.deleteUser=function(id,name){
	 parent.layer.confirm('删除角色“'+name+'”后，数据将无法恢复', {
		 title:'删除角色'
     }, function(index){
    	 deleteRole(id);
    	 parent.layer.close(index);
	 });
}


/**
 * DES:删除一个角色
 * @param status 1表示暂停，0表示正常
 * @param name 角色名称
 * @return void
 */
Role.prototype.check=function(obj){
	common.check(obj);
    if($("#roleStr dd .on").length>0){
      $(obj).parent().parent().parent().find("dt").addClass("on");	
    }else{
      $(obj).parent().parent().parent().find("dt").removeClass("on");
    };
}

/**
 * DES:判断是修改还是添加
 * @param void
 * @return void
 */
Role.prototype.isTool=function(){
    $id=common.GetQueryString("id");
    if($id>0){
       $maptxt="<a href='sys/role/index.do?pagetype=1' target='iframepage'>角色管理</a><em>››</em><a href='javascript:void(0);'>修改角色</a>";
 	   $(".addbtnbox .toolBtnyes").text("确认修改");
 	   $(".addbtnbox .toolBtnno").text("放弃修改");
 	   getOneRoles($id);
    }else{
       $maptxt="<a href='sys/role/index.do?pagetype=1' target='iframepage'>角色管理</a><em>››</em><a href='javascript:void(0);'>添加角色</a>";
       $(".addbtnbox .toolBtnyes").text("确认添加");
  	   $(".addbtnbox .toolBtnno").text("放弃添加"); 
 	   getAllRoles();
    }
//    common.parent("#webmap").html($maptxt);
}


/**
 * DES:保存数据的时候获取数据
 * @param void
 * @return void
 */
Role.prototype.getEditData=function(){
    $name=common.outBadData($.trim($("#name").val()));
    $description=common.outBadData($.trim($("#description").val()));
    $id=common.GetQueryString("id");
    $obj=new Object();
    $obj["name"]=$name;
    $obj["description"]=$description;
    if($id>0){
       $obj["id"]=$id; 
    }else{
       $obj["id"]="-1"; 
    };
    //获取权限
    var roleStr='';
    $("#roleStr").find(".on").each(function(){
    	$id=$(this).attr("data-id");
    	roleStr=roleStr+$id+",";
    });
    $obj["roleStr"]=(roleStr.substring(roleStr.length-1)==',')?roleStr.substring(0,roleStr.length-1):roleStr;
    return $obj;
}




/**
 * DES:判断有无特殊字符
 * @param void
 * @return void
 */
Role.prototype.isbaddata=function(){
    $name=$.trim($("#name").val());
    $description=$.trim($("#description").val());
    $id=common.GetQueryString("id");
    //判断权限
    var roleStr='';
    $("#roleStr").find(".on").each(function(){
    	$id=$(this).attr("data-id");
    	roleStr=roleStr+$id+",";
    });
    $roleStr=(roleStr.substring(roleStr.length-1)==',')?roleStr.substring(0,roleStr.length-1):roleStr;
    if(common.isBadData($name,"角色名称")){
       return true;	
    }else if(common.isBadData($description,"角色描述")){
       return true;	
    }else if($roleStr.length<1){
    	parent.layer.alert("请为这个角色分配权限！", {
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
Role.prototype.saveData=function(){
	$mid=common.submitValid($("#role"));
	$mid2=role.isbaddata();
	$id=common.GetQueryString("id");
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
  		saveRole(role.getEditData());
  	}
  	
}


/**
 * DES:读取数据列表
 * @param void
 * @return void
 */
Role.prototype.doList=function(isSearch){
    $("#isSearch").val(isSearch);
    $("#isSearch").text("");
    getRoleList(true,isSearch);
};


/**
 * DES:回车键搜索
 * @param void
 * @return void
 */
Role.prototype.keySearch=function(){
  $("#searchText").keypress(function(e) {
    if (e.which == 13) {
    	role.doList(1);
    }
  });
};

/**
 * DES:点击搜索
 * @param void
 * @return void
 */
Role.prototype.clickSearch=function() {
	role.doList(1);
};

/**
 * DES:按照状态搜索
 * @param void
 * @return void
 */
Role.prototype.clickstatus=function(type,name){
	 $(".runtype").text(name); 
	 $(".runtype").attr("data-type",type); 
	 role.doList(1);
};





//实例化Role类
var role= new Role();

/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
/**
 *desc:查询全部权限
 *@param void;
 *@return void;
 */
function getAllRoles(){
	//接口
	var link="getAllModule.m";
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
 *desc:查询一条角色数据
 *@param void;
 *@return void;
 */
function getOneRoles(id){
	//接口
	var link="detail.m";
	//需要传递的参数
	var dataLink="id="+id;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
        	oneRoleToHtml(rs.data);
        	var obj=new Object();
        	obj.data=rs.data.modules;
        	allRolesToHtml(obj);
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
 *desc:修改角色状态
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
 	       role.doList(null);
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
 *desc:删除角色
 *@param void;
 *@return void;
 */
function deleteRole(id){
	//接口
	var link="delete.m";
	//需要传递的参数
	var dataLink="id="+id;
    //ajax
    ajax.tranDataParams(link,dataLink, true, "get", function(rs) {
        if(rs.code==0){
    	 parent.layer.alert("角色删除成功！", {
 	         icon: 1,
 	     },function(index){
 	       role.doList(null);
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
 *desc:获取角色列表
 *@param void;
 *@return void;
 */
function getRoleList(loadPage,isSearch){
    if(loadPage){
	  $("#curPage").val(1);
	}
	$type=$(".tabtnbox .on").attr("data-type");
	/*需要的参数*/
	var pageNum = $("#curPage").val();
    var pageSize = $("#pageSize").val();
	var searchText=$.trim($("#searchText").val());
    if((isSearch==1)&&(str.isNotblank(searchText))){
      var searchText=$.trim($("#searchText").val());
    }else{
      var searchText="";
    }
	//需要传递的参数
    var dataLink ='pageSize=' + pageSize + '&pageNum=' + pageNum+'&searchText=' + searchText;//查询数据参数【搜索】
	//接口
	var link="getList.m";
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   $("#count").text(rs.data.totalRecords);
    	   rs.data.rowNum = (pageNum - 1) * pageSize;
    	   pageFun(Math.ceil(rs.data.totalRecords/rs.data.pageSize));
           roleListToHtml(rs.data);
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
 *desc:保存角色
 *@param obj 需要保存的数据;
 *@return void;
 */
function saveRole(obj){
	//接口
	var link="saveEdit.m";
	//需要传递的参数
	$id=obj["id"];
	$name=obj["name"];
	$description=obj["description"];
	$modules=obj["roleStr"];
	if($id=="-1"){
	     var dataLink="name="+$name+"&description="+$description+"&modules="+$modules;	
	}else{
		 var dataLink="id="+$id+"&name="+$name+"&description="+$description+"&modules="+$modules;
	};
    //ajax
    ajax.tranDataParams(link,dataLink, true, "post", function(rs) {
        if(rs.code==0){
    	   parent.layer.alert("数据保存成功！", {
 	         icon: 1,
 	       },function(index){
 	         location.href="sys/role/index.do?pagetype=1";
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
		  getRoleList(false,1);
	  }else{
		  getRoleList(false,null); 
	  };
	});
};


//权限写html入模板
function allRolesToHtml(obj) {
	if(str.isblank(obj.data)){
	  
	}else{
	  $("#roleStr").html(template("allRoles-script",obj));
	}
}


//权限写html入模板
function oneRoleToHtml(obj) {
	$("#name").val(obj["name"]);
	$("#description").val(obj["description"]);
	//选中权限
	var obj2=obj["modules"];
	for(var i= 0;i<obj2.length;i++) {
       $id=obj2[i]["id"];
       $("#roleStr i[data-id="+$id+"]").addClass("on");
	}
}


//角色列表数据数据写入模板
function roleListToHtml(obj) {
	if(str.isblank(obj.records)){
	  $("#data-content").html("<tr><td style='text-align: center;' colspan='100'>暂无数据!</td></tr>");
	  common.tableAdaptation();
	}else{
	  $("#data-content").html(template("data-content-script",obj));
	  common.tableAdaptation();
	}
}
