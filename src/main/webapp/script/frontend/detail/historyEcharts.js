function Echartjs() {}
Echartjs.prototype.setEhartjs=function(obj,uew){
	shouyiEchart(obj,uew);
	kuisunEchart(obj);
	maimaiEchart(obj);
}



//数字格式化
Echartjs.prototype.numberFmt=function(num){
  var numbers=Math.abs(Number(num));
  if(numbers<1000){
    var numfmt= numbers;
  }else if(numbers<10000){
	var numfmt= Math.round((numbers/1000)*100)/100+"k";
  }else if(numbers<1000000){
	var numfmt= Math.round((numbers/10000)*100)/100+"W";
  }else if(numbers<10000000){
	var numfmt= Math.round((numbers/1000000)*100)/100+"百万";
  }else{
	var numfmt= Math.round((numbers/10000000)*100)/100+"千万";
  }
  if(num<0){
	  numfmt="-"+numfmt;
  };
  return numfmt;
};

var echartjs= new Echartjs();
/*+++++++++++++++++++++++++++++++++++++++++++++图表++++++++++++++++++++++++++++++++++++++++++++++++++++*/
//收益图表
function shouyiEchart(obj,uew){
	//收益的动态展示
	/*累计收益*/
	 if(uew==1){
	  var earnings=[
	      {
	          name: '我的累计收益',
	          type: 'line',
	          itemStyle:{
	             normal:{
	                 lineStyle:{
	                  color:"#872B30",
	               }
	             }
	          },
	          xAxisIndex: 0,
	          yAxisIndex: 0,
	          smooth:true,
	          smoothMonotone:'x',
	          showSymbol: false,
	          animation:false,
	          hoverAnimation: false,
	          data:obj['accumulated']
	      },
	      {
	          name: '基准累计收益',
	          type: 'line',
	          itemStyle:{
	             normal:{
	                 lineStyle:{
	                  color:"#4163A2",
	               }
	             }
	          },
	          areaStyle: {
	              normal: {
	                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                      offset: 0,
	                      color: '#E2E9F1'
	                  }, {
	                      offset: 1,
	                      color: '#E2E9F1'
	                  }])
	              }
	          },
	          xAxisIndex: 0,
	          yAxisIndex: 0,
	          smooth:true,
	          showSymbol: false,
	          animation:false,
	          hoverAnimation: false,
	          data: obj['benchmark']
	      }
	     ];
	    var xtime=obj['times'];
	    var hint="累计收益";
	 }else if(uew==2){
		 var earnings=[
	       {
	           name: '相对收益',
	           type: 'line',
	           itemStyle:{
	              normal:{
	                  lineStyle:{
	                   color:"#872B30",
	                }
	              }
	           },
	           xAxisIndex: 0,
	           yAxisIndex: 0,
	           smooth:true,
	           smoothMonotone:'x',
	           showSymbol: false,
	           animation:false,
	           hoverAnimation: false,
	           data:obj['relative']
	       }
	      ]; 
		 var xtime=obj['times'];
		 var hint="相对收益";
	 }else if(uew==3){
		 var earnings=[
		       {
		           name: '对数收益',
		           type: 'line',
		           itemStyle:{
		              normal:{
		                  lineStyle:{
		                   color:"#872B30",
		                }
		              }
		           },
		           xAxisIndex: 0,
		           yAxisIndex: 0,
		           smooth:true,
		           smoothMonotone:'x',
		           showSymbol: false,
		           animation:false,
		           hoverAnimation: false,
		           data:obj['logarithmic']
		       }
		      ]; 
		 var xtime=obj['times'];
		 var hint="对数收益";
	}else{
		alert("累计查询类型错误！");
	}	
	 
	 // 基于准备好的dom，初始化echarts实例
	 var myChart = echarts.init(document.getElementById('main'));
	 // 指定图表的配置项和数据
	  var option = {
	    title: {
	        text: hint,
	        x: 'center',
	        textStyle:{
	           color:"#666",
	           fontWeight:"200",
	           fontSize:"14"
	        }
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            animation: false,
	            label: {
	                backgroundColor: '#ccc',
	                borderColor: '#aaa',
	                borderWidth: 1,
	                shadowBlur: 0,
	                shadowOffsetX: 0,
	                shadowOffsetY: 0,
	                textStyle: {
	                    color: '#222'
	                }
	            }
	        },
	        textStyle:{
	          color:"#444",
	        },
	        backgroundColor:'#FFF',
	        borderColor:"#888",
	        borderWidth:'1',
	        formatter: function (params) {
	        	if(params.length>1){
	        		var d1=params[0].data[1];
	            	var d2=params[1].data[1];
	            	if(str.isblank(d1)){
	            		d1='0';
	            	};
	            	if(str.isblank(d2)){
	            		d2='0';
	            	};
	        	}else{
	        		var d1=params[0].data[1];
	            	if(str.isblank(d1)){
	            		d1='0';
	            	};
	        	}
	            var html=params[0].axisValue+"<br/>";
	            if(params.length>1){
	            	html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+d1+"%<br/>"
	                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+d2+"%<br/>"
	        	}else{
	        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+d1+"%<br/>"
	        	}
	                
	            return html;
	        }

	    },
	    grid: [
	        {left:"10px",top:"30px",right:'50px',bottom:"30px"}
	    ],
	    xAxis: [
	        {
	            axisLabel: {
	              rotate: -1,
	            },
	            gridIndex: 0,
	            type:"category",
	            axisLine:{
	                lineStyle:{
	                    color:"#000"
	                }
	            },
	            data:xtime
	        }
	    ],
	    yAxis: [
	        {
	            gridIndex: 0, 
	            boundaryGap:true,
	            axisLabel: {
	              formatter:'{value}%', 
	            },
	            position:"right",
	        },
	    ],
	    series:earnings
	  };
	  // 使用刚指定的配置项和数据显示图表。
	  myChart.setOption(option);
}



//亏损图表
function kuisunEchart(obj){
    //5、每日盈亏todayPftAndLoss
	// 基于准备好的dom，初始化echarts实例
	  var myChart2 = echarts.init(document.getElementById('main2'));
	  // 指定图表的配置项和数据
	   var option2 = {
	     title: {
	         text: '每日盈亏',
	         x: 'center',
	         textStyle:{
	            color:"#666",
	            fontWeight:"200",
	            fontSize:"14"
	         }
	     },
	     tooltip : {
	         trigger: 'axis',
	         axisPointer: {
	             type: 'cross',
	             animation: false,
	             label: {
	                 backgroundColor: '#ccc',
	                 borderColor: '#aaa',
	                 borderWidth: 1,
	                 shadowBlur: 0,
	                 shadowOffsetX: 0,
	                 shadowOffsetY: 0,
	                 textStyle: {
	                     color: '#222'
	                 }
	             }
	         },
	         textStyle:{
	           color:"#444",
	         },
	         backgroundColor:'#FFF',
	         borderColor:"#888",
	         borderWidth:'1',
	         formatter: function (params) {
	         	var d1=params[0].data[1];
	          	if(str.isblank(d1)){
	          		d1='0';
	          	}
	            var html=params[0].axisValue+"<br/>";
	              html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+common.formatNumber(d1)+"元<br/>"
	            return html;
	          }
	     },
	     grid: [
	         {left:"10px",top:"30px",right:'50px',bottom:"30px"}
	     ],
	     xAxis: [
	         {
	             axisLabel: {
	               rotate: -1,
	             },
	             gridIndex: 0,
	             type:"category",
	             axisLine:{
	                 lineStyle:{
	                     color:"#000"
	                 }
	             },
	             data: obj["times"]
	         }
	     ],
	     yAxis: [
	         {
	             gridIndex: 0, 
	             boundaryGap:true,
	             position:"right",
	             axisLabel: {
	               formatter:function(value,index){
	                 return echartjs.numberFmt(value);
	               }
	             }
	         },
	     ],
	     series: [
	         {
	             name: '当日盈亏',
	             type: 'bar',
	             itemStyle:{
	                normal:{
	                    color: function(params) {
	                     var index_color = params.value[1];
	                     if(index_color>=0){
	                         return '#fe4365';
	                     }else {
	                         return 'green';
	                     }
	                    },
	                }
	             },
	             barWidth:2,
	             shadowColor: '#fff',
	             shadowBlur: 0,
	             xAxisIndex: 0,
	             yAxisIndex: 0,
	             opacity:'1',
	             barGap:"13px",
	             smooth:true,
	             smoothMonotone:'x',
	             animation:false,
	             showSymbol: false,
	             hoverAnimation: false,
	             data:obj["todayPftAndLoss"]
	         }
	        ]
	   };
	   // 使用刚指定的配置项和数据显示图表。
	   myChart2.setOption(option2);
}


//买卖图表
function maimaiEchart(obj){
	// 基于准备好的dom，初始化echarts实例
	  var myChart3 = echarts.init(document.getElementById('main3'));
	  // 指定图表的配置项和数据
	   var option3 = {
	     title: {
	         text: '每日买卖',
	         x: 'center',
	         textStyle:{
	            color:"#666",
	            fontWeight:"200",
	            fontSize:"14"
	         }
	     },
	     tooltip : {
	         trigger: 'axis',
	         axisPointer: {
	             type: 'cross',
	             animation: false,
	             label: {
	                 backgroundColor: '#ccc',
	                 borderColor: '#aaa',
	                 borderWidth: 1,
	                 shadowBlur: 0,
	                 shadowOffsetX: 0,
	                 shadowOffsetY: 0,
	                 textStyle: {
	                     color: '#222'
	                 }
	             }
	         },
	         textStyle:{
	           color:"#444",
	         },
	         backgroundColor:'#FFF',
	         borderColor:"#888",
	         borderWidth:'1',
	         formatter: function (params) {
	        	var d1=params[0].data[1];
	         	var d2=params[1].data[1];
	         	if(str.isblank(d1)){
	         		d1='0';
	         	}
	         	if(str.isblank(d2)){
	         		d2='0';
	         	}
	             var html=params[0].axisValue+"<br/>";
	                 html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+common.formatNumber(d1)+"元<br/>"
	                 html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+common.formatNumber(d2)+"元<br/>"
	             return html;
	         }
	     },
	     grid: [
	         {left:"10px",top:"30px",right:'50px',bottom:"30px"}
	     ],
	     xAxis: [
	         {
	             axisLabel: {
	               rotate: -1,
	             },
	             gridIndex: 0,
	             type:"category",
	             axisLine:{
	                 lineStyle:{
	                     color:"#000"
	                 }
	             },
	             data:obj['times']
	         }
	     ],
	     yAxis: [
	         {
	             gridIndex: 0, 
	             boundaryGap:true,
	             axisLabel: {
	              formatter:function(value,index){
	                 return echartjs.numberFmt(value);
	               }
	             },
	             position:"right"
	         },
	     ],
	     series: [
	         {
	             name: '当日买',
	             type: 'bar',
	             itemStyle:{
	                normal:{
	                    color: function(params) {
	                     var index_color = params.value[1];
	                     if(index_color>=0){
	                         return '#395169';
	                     }else {
	                         return '#3394DB';
	                     }
	                    },
	                }
	             },
	             barWidth:2,
	             shadowColor: '#fff',
	             shadowBlur: 0,
	             xAxisIndex: 0,
	             yAxisIndex: 0,
	             opacity:'1',
	             barGap:"13px",
	             smooth:true,
	             smoothMonotone:'x',
	             animation:false,
	             showSymbol: false,
	             hoverAnimation: false,
	             data:obj['todayBuying']
	         },
	         {
	             name: '当日卖',
	             type: 'bar',
	             itemStyle:{
	                normal:{
	                    color: function(params) {
	                     var index_color = params.value[1];
	                     if(index_color>=0){
	                         return '#395169';
	                     }else {
	                         return '#3394DB';
	                     }
	                    },
	                }
	             },
	             barWidth:2,
	             shadowColor: '#fff',
	             shadowBlur: 0,
	             xAxisIndex: 0,
	             yAxisIndex: 0,
	             opacity:'1',
	             barGap:"0",
	             animation:false,
	             smooth:true,
	             smoothMonotone:'x',
	             showSymbol: false,
	             hoverAnimation: false,
	             data:obj['todaySale']
	         }
	        ]
	   };
	   // 使用刚指定的配置项和数据显示图表。
	   myChart3.setOption(option3);
}
