function Ehartjs() {}
Ehartjs.prototype.setEhartjs=function(obj,uew){
//	1、我的累计收益accumulated
	var accumulated=obj["accumulated"];
//	2、基准累计收益benchmark
	var benchmark=obj["benchmark"];
//  3、相对收益
	var relative=obj["relativereturn"];
//  4、对数收益	
	var logarithmic=obj["logarithmicreturn"];
	
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
          name: '基准累计收益',
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
      }
     ];
    var xtime=accumulated['times'];
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
           data:relative['values']
       }
      ]; 
	 var xtime=relative['times'];
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
	           data:logarithmic['values']
	       }
	      ]; 
	 var xtime=logarithmic['times'];
	 var hint="对数收益";
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
        {left:"20px",top:"30px",right:'50px',bottom:"30px"}
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

var ehartjs= new Ehartjs();
/*++++++++++++++++++++++++++++++++++++++++++++++与服务器交互数据++++++++++++++++++++++++++++++++++++++++++++++++++++*/

