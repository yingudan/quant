/**
 * page_ele:分页div
 * page_script：动态渲染script
 */
function setPageData(page_ele,page_script,total_ele,total,pno){
//	alert("total=="+total);
//	alert("加载分页...");
	var pageData = new Object();
	var pageLi = new Array();
	var pageLi2 = new Array();
			//var totalRows = data.totalRows;
	if(pno == null || pno == '' || pno.length == 0 || pno < 0){
		pno = 1;
	}
	pno = parseInt(pno);
	$('#'+total_ele).val(total);
	var pData ;
	var maxPage = total;
	if(maxPage > 13){
		maxPage = 13;
	}
	if(pno>total){
		pno=total;
	}
	
	if(pno <= 7){
		for(var i=1;i<=maxPage;i++){
			pData = new Object();
			pData.num = i;
			if(pno == i){
				pData.current = "1";
			}else{
				pData.current = "-1";
			}
			pageLi.push(pData);
		}
	}else{
		if(total-pno < 7){
			var cur = total-12;
			for(var i=total;i>total-13;i--){
				pData = new Object();
				pData.num = cur;
				if(pno == cur){
					pData.current = "1";
				}else{
					pData.current = "-1";
				}
				cur++;
				pageLi.push(pData);
			}
		}else{
			for(var i=pno-6;i<=pno+6;i++){
				pData = new Object();
				pData.num = i;
				if(pno == i){
					pData.current = "1";
				}else{
					pData.current = "-1";
				}
				pageLi.push(pData);
			}
		}
	}
	
//	alert(JSON.stringify(pageLi));
for(var i=0;i<pageLi.length;i++){
	if(pageLi[i]["num"]>0){
	  pData2 = new Object();
	  pData2.num=pageLi[i].num;
	  pData2.current=pageLi[i].current;
	  pageLi2.push(pData2);	
	}
};

	pageData.pageLi = pageLi2;
	
	var html = template(page_script,pageData);
	$("#"+page_ele).html(html);
	
	$("#"+page_ele).siblings("label[name='pagelabel']").html(pno+"/"+total);
}		

//@param pageType:1表示加下一页，-1表示上一页；0表示直接点击页码,8:跳转，//-2表示首页,2表示尾页
function setPageNumfun(thiz,pageType,page_ele,total_ele,callback){
	//alert(pageType);
//	var eveg=window.event||arguments.callee.caller.arguments[0];
//	var thiz=eveg.srcElement||eveg.target;
	var maxPageNum = parseInt($('#'+total_ele).val());
	if(pageType == 0){//点击页码
		var curT = $(thiz).parent().find(".current");
		if($(curT).text() == $(thiz).text()){
			return;
		}
		var thizText = $(thiz).text();
		if($(thiz).next().length==0 && maxPageNum>thizText){//当前已经到倒数第二个页码
			$(thiz).text(parseInt(thizText)+1);
			$(thiz).prev().addClass("current").siblings().removeClass("current");
			while($(thiz).prev().length != 0){
				$(thiz).prev().text(parseInt($(thiz).prev().text())+1);
				thiz = $(thiz).prev();
			}
		}else if($(thiz).prev().length==0 && thizText>1){
			$(thiz).text(parseInt(thizText)-1);
			$(thiz).next().addClass("current").siblings().removeClass("current");
			while($(thiz).next().length != 0){
				$(thiz).next().text(parseInt($(thiz).next().text())-1);
				thiz = $(thiz).next();
			}
		}else{
			$(thiz).addClass("current").siblings().removeClass("current");
		}	
	}else{
		thiz = $(thiz).parent().find(".current");
		var thizText = $(thiz).text();
		if(pageType == 1){//下一页
			if($(thiz).next().next().length==0 && maxPageNum-thizText>=2){//当前已经到倒数第二个页码
					thiz = $(thiz).next();
					$(thiz).text(parseInt($(thiz).text())+1);
					
					while($(thiz).prev().length != 0){
						$(thiz).prev().text(parseInt($(thiz).prev().text())+1);
						thiz = $(thiz).prev();
					}
			}else{
				if(maxPageNum-thizText==0){
					return;
				}
				$(thiz).next().addClass("current").siblings().removeClass("current");
			}
		}else if(pageType == -1){//上一页
			if($(thiz).prev().prev().length==0 && thizText>=3){//当前已经到倒数第二个页码
				thiz = $(thiz).prev();
				$(thiz).text(parseInt($(thiz).text())-1);
					
				while($(thiz).next().length != 0){
					$(thiz).next().text(parseInt($(thiz).next().text())-1);
					thiz = $(thiz).next();
				}
			}else{
				if(thizText-1==0){
					return;
				}
				$(thiz).prev().addClass("current").siblings().removeClass("current");
			}
			
		}else if(pageType == -2){//首页
			if($(thiz).parent().find(".current").text()==1){
				return;
			}
			thiz = $('#'+page_ele).find("a").first();
			$(thiz).addClass("current").siblings().removeClass("current");
			
			if($(thiz).text()>1){
				var i=1;
				$(thiz).text(i);
				
				while($(thiz).next().length != 0){
					$(thiz).next().text(++i);
					thiz = $(thiz).next();
				}
			}
			
		}else if(pageType == 2){//尾页
			if($(thiz).parent().find(".current").text()==maxPageNum){
				return;
			}
			thiz = $('#'+page_ele).find("a").last();//$('#divOne > p:last-child');
			$(thiz).addClass("current").siblings().removeClass("current");
			//alert($(thiz).text()+",,,"+maxPageNum);
			if(parseInt($(thiz).text())<maxPageNum){
			//alert(1333);
				$(thiz).text(maxPageNum);
				while($(thiz).prev().length != 0){
					$(thiz).prev().text(--maxPageNum);
					thiz = $(thiz).prev();
				}
			}
		}else if(pageType == 8){//跳转
			var jpage_ele = $(thiz).parent().siblings(".jump_k");
			var jpage = parseInt($(jpage_ele).val());
			if(jpage>0){
				if(jpage>maxPageNum){
					jpage=maxPageNum;
					$(jpage_ele).val(jpage);
				}
				
				if(thizText==jpage){
					return;
				}
				
					var alist = $('#'+page_ele).find("a");
					if(jpage>7){
						var j=0;
						if(maxPageNum-jpage>=6){
							maxPageNum = parseInt(jpage)+7;
							
							for(var i=alist.length-1;i>=0;i--){
								++j;
								$(alist[i]).text(maxPageNum-j);
								if(maxPageNum-j==jpage){
									$(alist[i]).addClass("current").siblings().removeClass("current");
								}
							}
						}else{
							for(var i=alist.length-1;i>=0;i--){
								$(alist[i]).text(maxPageNum-j);
								if(maxPageNum-j==jpage){
									$(alist[i]).addClass("current").siblings().removeClass("current");
								}
								j++;
							}
						}
					}else{
						for(var i=0;i<alist.length;i++){
							if(jpage==i+1){
								$(alist[i]).addClass("current").siblings().removeClass("current");
							}
							$(alist[i]).text(i+1);
						}
					}
				
			}else return;
		
	} 
}

var pno = $(thiz).parent().find(".current").text();

$("#"+page_ele).siblings("label[name='pagelabel']").html(pno+"/"+$('#'+total_ele).val());
//alert(pno);
callback(pno);
 }