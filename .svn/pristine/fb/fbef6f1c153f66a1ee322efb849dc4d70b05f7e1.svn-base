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

function numf(num){
	if(str.isblank(num)){
		var db1=0;
	}else{
		var db1=num;
	}
	return db1;
}	
	
	
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
      }
     ];
    var xtime=accumulated['times'];
    var hint="累计收益";
    var t2=['沪深300','上证50','中证500'];
    if(str.isblank($.cookie("echatscookie1"))){
		 var se={"上证50":false,"中证500":false};	
	 }else{
		 var se=eval('(' + $.cookie("echatscookie1") + ')');
	 }; 
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
       }
      ]; 
	 var xtime=relativereturn['times'];
	 var hint="相对收益";
	 var t2=['沪深300','上证50','中证500'];
	 if(str.isblank($.cookie("echatscookie2"))){
		 var se={"上证50":false,"中证500":false};	
	 }else{
		 var se=eval('(' + $.cookie("echatscookie2") + ')');
	 }; 
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
            var html=params[0].axisValue+"<br/>";
        	if(params.length==0){
 
        	}else if(params.length==1){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+numf(params[0].data[1])+"%<br/>"
        	}else if(params.length==2){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+numf(params[0].data[1])+"%<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+numf(params[1].data[1])+"%<br/>"
        	}else if(params.length==3){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+numf(params[0].data[1])+"%<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+numf(params[1].data[1])+"%<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+numf(params[2].data[1])+"%<br/>"
            	
        	}else if(params.length==4){
        		html+="<font style='color:"+params[0].color+"'>●&nbsp&nbsp</font>"+params[0].seriesName+"："+numf(params[0].data[1])+"%<br/>"
                html+="<font style='color:"+params[1].color+"'>●&nbsp&nbsp</font>"+params[1].seriesName+"："+numf(params[1].data[1])+"%<br/>"
                html+="<font style='color:"+params[2].color+"'>●&nbsp&nbsp</font>"+params[2].seriesName+"："+numf(params[2].data[1])+"%<br/>"
                html+="<font style='color:"+params[3].color+"'>●&nbsp&nbsp</font>"+params[3].seriesName+"："+numf(params[3].data[1])+"%<br/>"
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















