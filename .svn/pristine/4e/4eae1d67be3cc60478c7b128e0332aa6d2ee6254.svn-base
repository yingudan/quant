function Ehartjs() {}
Ehartjs.prototype.setEhartjs=function(obj){
 var list=obj["list"];
 //动态展示
  var earnings=[
  {
      name: '策略评分',
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
      data: list['values']
  }
 ];
var xtime=list['times'];
 
//基于准备好的dom，初始化echarts实例
 var myChart = echarts.init(document.getElementById('main'));
 // 指定图表的配置项和数据
  var option = {
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
        	var html=params[0].axisValue+"<br/>";
        	    var shuzi=params[0].data[1];
        	    
        	    if(str.isblank(shuzi)){
        	    	shuzi="0";
        	    }
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+shuzi+"<br/>" 
            return html;
        }

    },
    grid: [
        {left:"40px",top:"10px",right:'0',bottom:"30px"}
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
              formatter:'{value}', 
            },
            position:"left",
        },
    ],
    series:earnings
  };
 // 使用刚指定的配置项和数据显示图表。
 myChart.setOption(option);
 window.onresize = myChart.resize;
};

Ehartjs.prototype.setEhartjsRadar=function(obj){
	$leftv=JSON.parse(JSON.stringify(obj['leftv']));
	$leftdb=JSON.parse(JSON.stringify(obj['leftdb']));

	
	$rightv=JSON.parse(JSON.stringify(obj['rightv']));
	$rightdb=JSON.parse(JSON.stringify(obj['rightdb']));

	
	//基于准备好的dom，初始化echarts实例
	 var radarleft = echarts.init(document.getElementById('radarleft'));
	 var radarright = echarts.init(document.getElementById('radarright'));
	 // 指定图表的配置项和数据
	 optionleft = {
	    title: {
	        text: '风格因子月度收益雷达图',
	        x: 'center',
	        textStyle:{
	           color:"#999",
	           fontWeight:"700",
	           fontSize:"15"
	        }
	    },
	    
	    tooltip : {
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
	    },
	    radar: {
	        indicator:$leftdb
	    },
	    series: [{
	        name: '风格因子月度收益雷达图',
	        type: 'radar',
	        itemStyle:{
	              normal:{
	                  lineStyle:{
	                   color:"#81B3EE",
	                }
	              }
	           },
	        data : [
	            {
	                value :$leftv,
	                name : '风格因子月度收益雷达图'
	            }
	        ]
	    }]
	};
	 
	 optionright = {
			    title: {
			        text: '行业因子月度收益雷达图',
			        x: 'center',
			        textStyle:{
			           color:"#999",
			           fontWeight:"700",
			           fontSize:"15"
			        }
			    },
			    
			    tooltip : {
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
			    },
			    radar: {
			        // shape: 'circle',
			        indicator:$rightdb
			    },
			    position: [10, 10],
			    series: [{
			        name: '风格因子月度收益雷达图',
			        type: 'radar',
			        itemStyle:{
			              normal:{
			                  lineStyle:{
			                   color:"#81B3EE",
			                }
			              }
			           },
			        data : [
			            {
			                value : $rightv,
			                name : '行业因子月度收益雷达图'
			            }
			        ]
			    }]
			};
	 // 使用刚指定的配置项和数据显示图表。
	 radarleft.setOption(optionleft);
	 radarright.setOption(optionright);
}








var ehartjs= new Ehartjs();
/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

