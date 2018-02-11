function Ehartjs() {}
Ehartjs.prototype.setEhartjs=function(obj,uew){
	if(uew==1){//累计收益
		var accumulated=obj["accumulated"];//我的累计收益
		var benchmark=obj["benchmark"];//沪深300
		var benchmarkSz50=obj["benchmarkSz50"];//上证50
		var benchmarkZz500=obj["benchmarkZz500"];//中证500
	}else if(uew==2){//相对收益
		var relativereturn=obj["relativereturn"];//沪深300
		var relativeReturnSz50=obj["relativeReturnSz50"];//上证50
		var relativeReturnZz500=obj["relativeReturnZz500"];//中证500
	}else if(uew==3){//对数收益
		var logarithmicreturn=obj["logarithmicreturn"];//对数收益
	}else{
	    alert("错误：004");
	};
	var dailyAssetsMap=obj["dailyAssetsMap"];//每日盈亏
	var mairu=obj["mairu"];//买入
	var maichu=obj["maichu"];//卖出
	
	//数字格式化
	function numberFmt(num){
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
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data: accumulated['values']
      },
      {
          name: '沪深300',
          type: 'line',
          itemStyle:{
             normal:{
                 lineStyle:{
                  color:"#4163A2",
               }
             }
          },
          xAxisIndex: 0,
          yAxisIndex: 0,
          smooth:true,
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data: benchmark['values']
      },
      {
          name: '上证50',
          type: 'line',
          itemStyle:{
             normal:{
                 lineStyle:{
                  color:"#00B259",
               }
             }
          },
          xAxisIndex: 0,
          yAxisIndex: 0,
          smooth:true,
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data: benchmarkSz50['values']
      },
      {
          name: '中证500',
          type: 'line',
          itemStyle:{
             normal:{
                 lineStyle:{
                  color:"#FFBF00",
               }
             }
          },
          xAxisIndex: 0,
          yAxisIndex: 0,
          smooth:true,
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data: benchmarkZz500['values']
      },
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
          xAxisIndex: 1,
          yAxisIndex: 1,
          opacity:'1',
          barGap:"0",
          smooth:true,
          smoothMonotone:'x',
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data:dailyAssetsMap["values"]
      },
      {
          name: '买入',
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
          xAxisIndex: 2,
          yAxisIndex: 2,
          opacity:'1',
          barGap:"0",
          smooth:true,
          smoothMonotone:'x',
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data:mairu["values"]
      },
      {
          name: '卖出',
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
          xAxisIndex: 3,
          yAxisIndex: 3,
          opacity:'1',
          barGap:"0",
          smooth:true,
          smoothMonotone:'x',
          animation:false,
          showSymbol: false,
          hoverAnimation: false,
          data:maichu["values"]
      }
     ];
    var xtime=mairu['times'];
    var hint="累计收益";
    if(str.isblank($.cookie("echatscookie1"))){
    	var se={"上证50":false,"中证500":false};	
    }else{
    	var se=eval('(' + $.cookie("echatscookie1") + ')');
    };
    var t2=['沪深300','上证50','中证500'];
    
 }else if(uew==2){
 var earnings=[
       {
           name: '沪深300',
           type: 'line',
           itemStyle:{
              normal:{
                  lineStyle:{
                   color:"#4163A2",
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
           data:relativereturn['values']
       },
       {
           name: '上证50',
           type: 'line',
           itemStyle:{
              normal:{
                  lineStyle:{
                   color:"#00B259",
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
           data:relativeReturnSz50['values']
       },
       {
           name: '中证500',
           type: 'line',
           itemStyle:{
              normal:{
                  lineStyle:{
                   color:"#FFBF00",
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
           data:relativeReturnZz500['values']
       },
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
           xAxisIndex: 1,
           yAxisIndex: 1,
           opacity:'1',
           barGap:"0",
           smooth:true,
           smoothMonotone:'x',
           animation:false,
           showSymbol: false,
           hoverAnimation: false,
           data:dailyAssetsMap["values"]
       },
       {
           name: '买入',
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
           xAxisIndex: 2,
           yAxisIndex: 2,
           opacity:'1',
           barGap:"0",
           smooth:true,
           smoothMonotone:'x',
           animation:false,
           showSymbol: false,
           hoverAnimation: false,
           data:mairu["values"]
       },
       {
           name: '卖出',
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
           xAxisIndex: 3,
           yAxisIndex: 3,
           opacity:'1',
           barGap:"0",
           smooth:true,
           smoothMonotone:'x',
           animation:false,
           showSymbol: false,
           hoverAnimation: false,
           data:maichu["values"]
       }
      ]; 
	 var xtime=relativereturn['times'];
	 var hint="相对收益";
	 if(str.isblank($.cookie("echatscookie2"))){
		 var se={"上证50":false,"中证500":false};	
	 }else{
		 var se=eval('(' + $.cookie("echatscookie2") + ')');
	 }; 
	 var t2=['沪深300','上证50','中证500'];

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
	           data:logarithmicreturn['values']
	       },
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
	           xAxisIndex: 1,
	           yAxisIndex: 1,
	           opacity:'1',
	           barGap:"0",
	           smooth:true,
	           smoothMonotone:'x',
	           animation:false,
	           showSymbol: false,
	           hoverAnimation: false,
	           data:dailyAssetsMap["values"]
	       },
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
	           xAxisIndex: 1,
	           yAxisIndex: 1,
	           opacity:'1',
	           barGap:"0",
	           smooth:true,
	           smoothMonotone:'x',
	           animation:false,
	           showSymbol: false,
	           hoverAnimation: false,
	           data:dailyAssetsMap["values"]
	       },
	       {
	           name: '买入',
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
	           xAxisIndex: 3,
	           yAxisIndex: 3,
	           opacity:'1',
	           barGap:"0",
	           smooth:true,
	           smoothMonotone:'x',
	           animation:false,
	           showSymbol: false,
	           hoverAnimation: false,
	           data:mairu["values"]
	       },
	       {
	           name: '卖出',
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
	           xAxisIndex: 2,
	           yAxisIndex: 2,
	           opacity:'1',
	           barGap:"0",
	           smooth:true,
	           smoothMonotone:'x',
	           animation:false,
	           showSymbol: false,
	           hoverAnimation: false,
	           data:maichu["values"]
	       }
	      ]; 
	 var xtime=logarithmicreturn['times'];
	 var hint="对数收益";
	 var t2=[];
}else{
   alert("累计查询类型错误！");
}	


//+++++++++++++++++++++++++++++++++++++++++++++++++++++地图1+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//基于准备好的dom，初始化echarts实例
 var myChart = echarts.init(document.getElementById('main'));
 // 指定图表的配置项和数据
  var option = {
    title: {
        text: hint,
        x: 'center',
        textStyle:{
           color:"#666",
           fontWeight:"700",
           fontSize:"16"
        }
    },
    legend: {
        left: 'right',
        data: t2,
        selected:se
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
        	function numf(num){
        
        		if(num=="买入"||num=="卖出"||num=="当日盈亏"){
        			return "";
        		}else{
        			return "%";
        		};
        	};
        	function jueduizhi(num){
        		return num;
        	};
            var html=params[0].axisValue+"<br/>";
        	if(params.length==0){
 
        	}else if(params.length==1){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
        	}else if(params.length==2){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
        	}else if(params.length==3){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+jueduizhi(params[2].data[1])+numf(params[2].seriesName)+"<br/>"
            	
        	}else if(params.length==4){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+jueduizhi(params[2].data[1])+numf(params[2].seriesName)+"<br/>"
                html+="<font style='color:"+params[3].color+"'>●&nbsp&nbsp</font>"+params[3].seriesName+"："+jueduizhi(params[3].data[1])+numf(params[3].seriesName)+"<br/>"
        	}else if(params.length==5){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+jueduizhi(params[2].data[1])+numf(params[2].seriesName)+"<br/>"
                html+="<font style='color:"+params[3].color+"'>●&nbsp&nbsp</font>"+params[3].seriesName+"："+jueduizhi(params[3].data[1])+numf(params[3].seriesName)+"<br/>"
                html+="<font style='color:"+params[4].color+"'>●&nbsp&nbsp</font>"+params[4].seriesName+"："+jueduizhi(params[4].data[1])+numf(params[4].seriesName)+"<br/>"
        	}else if(params.length==6){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+jueduizhi(params[2].data[1])+numf(params[2].seriesName)+"<br/>"
                html+="<font style='color:"+params[3].color+"'>●&nbsp&nbsp</font>"+params[3].seriesName+"："+jueduizhi(params[3].data[1])+numf(params[3].seriesName)+"<br/>"
                html+="<font style='color:"+params[4].color+"'>●&nbsp&nbsp</font>"+params[4].seriesName+"："+jueduizhi(params[4].data[1])+numf(params[4].seriesName)+"<br/>"
                html+="<font style='color:"+params[5].color+"'>●&nbsp&nbsp</font>"+params[5].seriesName+"："+jueduizhi(params[5].data[1])+numf(params[5].seriesName)+"<br/>"
        	}else if(params.length==7){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+jueduizhi(params[0].data[1])+numf(params[0].seriesName)+"<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+jueduizhi(params[1].data[1])+numf(params[1].seriesName)+"<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+jueduizhi(params[2].data[1])+numf(params[2].seriesName)+"<br/>"
                html+="<font style='color:"+params[3].color+"'>●&nbsp&nbsp</font>"+params[3].seriesName+"："+jueduizhi(params[3].data[1])+numf(params[3].seriesName)+"<br/>"
                html+="<font style='color:"+params[4].color+"'>●&nbsp&nbsp</font>"+params[4].seriesName+"："+jueduizhi(params[4].data[1])+numf(params[4].seriesIndex)+"<br/>"
                html+="<font style='color:"+params[5].color+"'>●&nbsp&nbsp</font>"+params[5].seriesName+"："+jueduizhi(params[5].data[1])+numf(params[5].seriesName)+"<br/>"
                html+="<font style='color:"+params[6].color+"'>●&nbsp&nbsp</font>"+params[6].seriesName+"："+jueduizhi(params[6].data[1])+numf(params[6].seriesName)+"<br/>"
        	}
                
            return html;
        }

    },
    axisPointer: {
        link: {xAxisIndex: 'all'}
    },
    grid: [{
    	left: 50,
        right: 0,
        top: '60px',
        height: '200px'
    },{
    	left: 50,
        right: 0,
        top: '320px',
        height: '200px'
    },{
    	left: 50,
        right: 0,
        top: '580',
        height: '200px'
    },{
    	left: 50,
        right: 0,
        top: '780px',
        height: '200px'
    }],
    
    xAxis: [
        {
            gridIndex: 0,
            type:"category",
            axisLine:{
                lineStyle:{
                    color:"#000"
                }
            },
            data:xtime
        },
        {
            gridIndex: 1,
            type:"category",
            axisLine:{
                lineStyle:{
                    color:"#000"
                }
            },
            data:xtime
        },
        {
          show:false, 
          gridIndex: 2,
          type:"category",
          axisLine:{
              lineStyle:{
                  color:"#000"
              }
          },
          data:xtime
          },
          {
              gridIndex: 3,
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
            position:"left",
        },
        {
            gridIndex: 1, 
            boundaryGap:true,
            axisLabel: {
              formatter:'{value}', 
            },
            position:"left",
        },
        {
            gridIndex: 2, 
            boundaryGap:true,
            axisLabel: {
              formatter:'{value}', 
            },
            position:"left",
        },
        {
            gridIndex: 3, 
            boundaryGap:true,
            axisLabel: {
              formatter:'{value}', 
            },
            position:"left",
        }
    ],
    series:earnings
  };



 // 使用刚指定的配置项和数据显示图表。
 myChart.setOption(option);
 myChart.on("legendselectchanged",function(params){
	 var type=$(".uew-radio em.on").attr("data");
	 var obj=JSON.stringify(params.selected);
	 if(type==1){
		 $.cookie("echatscookie1",obj);
	 }else{
		 $.cookie("echatscookie2",obj);
	 }
 });

 window.onresize = myChart.resize;
}


var ehartjs= new Ehartjs();
/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/
