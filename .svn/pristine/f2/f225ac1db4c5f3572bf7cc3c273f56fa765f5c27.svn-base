function StringUtils(){
	
}

StringUtils.prototype.isblank=function(chr){
	if(chr == null || chr == '' || chr == undefined || chr == 'undefined' || chr == 'null' || chr.length == 0){
		return true;
	}return false;
},

StringUtils.prototype.isNotblank=function(chr){
//	if(chr != null && chr != '' && chr != undefined && chr != 'undefined' && chr != 'null' && chr.length > 0){
//		return true;
//	}return false;
	
	if(chr != null && chr != '' && chr != undefined && chr.length > 0){
		return true;
	}return false;
},

StringUtils.prototype.trim=function(chr){
	if(chr == null){
		return "";
	}
    return chr.replace(/(^\s*)|(\s*$)/g, "");
},
StringUtils.prototype.ltrim=function(chr){
	if(chr == null){
		return "";
	}
    return chr.replace(/(^\s*)/g,"");
},
StringUtils.prototype.rtrim=function(chr){
	if(chr == null){
		return "";
	}
    return chr.replace(/(\s*$)/g,"");
};

StringUtils.prototype.getlength = function(chr) {  
	  var len = 0;  
	  for (var i=0; i<chr.length; i++) {  
	    if (chr.charCodeAt(i)>127 || chr.charCodeAt(i)==94) {  
	       len += 2;  
	     } else {  
	       len ++;  
	     }  
	   }  
	  return len;  
	}

//去除字符串所有的空格
StringUtils.prototype.allTrim=function(str) {
	if(str == null){
		return "";
	}
	  return str.replace(/\s+/g, ""); 
}




















