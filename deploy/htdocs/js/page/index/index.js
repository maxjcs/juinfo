$(document).ready(function(){
	

	
	// 焦点图片淡隐淡现
	$("#slider3").Xslider({
		affect:'fade',
		ctag: 'div'
	});
	
	
	
     center($(".tt"));
	 $(".tt").show();

// 居中
    function center(obj) {

        var screenWidth = $(window).width(), screenHeight = $(window).height();  //当前浏览器窗口的 宽高
        var scrolltop = $(document).scrollTop(); //获取当前窗口距离页面顶部高度

        var objLeft = (screenWidth - obj.width()) / 1-50;
        var objTop = (screenHeight - obj.height()) / 2 + scrolltop;
        //var objTop = (screenHeight - obj.height()) - 280 + scrolltop;

        obj.css({ left: objLeft + 'px', top: objTop + 'px' });
        //浏览器窗口大小改变时
        $(window).resize(function () {
            screenWidth = $(window).width();
            screenHeight = $(window).height();
            scrolltop = $(document).scrollTop();

            objLeft = (screenWidth - obj.width()) / 1-50;
            objTop = (screenHeight - obj.height()) / 2 + scrolltop;
            //objTop = (screenHeight - obj.height()) - 280 + scrolltop;

            obj.css({ left: objLeft + 'px', top: objTop + 'px' });

        });
		//浏览器有滚动条时的操作、
		$(window).scroll(function () {
			screenWidth = $(window).width();
			screenHeight = $(window).height();
			scrolltop = $(document).scrollTop();

			objLeft = (screenWidth - obj.width()) / 1-50;
			objTop = (screenHeight - obj.height()) / 2 + scrolltop;

			obj.css({ left: objLeft + 'px', top: objTop + 'px' });
		});

    }
	
	
	
	function animate(){
	var max="barblue";
	var middle="barblue";
	var min="barblue";	
	
	var maxValue=0;
	var minValue=0;
	
	var maxIndex=0;
	var minIndex=0;
	
	$(".charts").each(function(i,item){
		var a=parseInt($(item).attr("w"));
	
		if(i==0){
			minValue=a;
			minIndex=i;
		}
	
		if(a>maxValue){
			maxValue=a;
			maxIndex=i;
		}else if(a<minValue){
			minValue=a;
			minIndex=i;
		}
	
	});
	
	$(".charts").each(function(i,item){

		var addStyle="";
		var divindex=parseInt($(item).attr("divindex"));

		addStyle=max;

		$(item).addClass(addStyle);
		var a=$(item).attr("w");
		$(item).animate({
			width: a+"%"
		},4000);
	});
	
}
animate();







	
});

$(function(){
      lxfEndtime();
   });
 function lxfEndtime(){
          $(".djs").each(function(){
                //var lxfday=$(this).attr("lxfday");//用来判断是否显示天数的变量
                var endtime = new Date($(this).attr("endtime")).getTime();//取结束日期(毫秒值)
                var nowtime = new Date().getTime();        //今天的日期(毫秒值)
                var youtime = endtime-nowtime;//还有多久(毫秒值)
                var seconds = youtime/1000;
                var minutes = Math.floor(seconds/60);
                var hours = Math.floor(minutes/60);
                var days = Math.floor(hours/24);
                var CDay= days ;
                var CHour= hours % 24;
                var CMinute= minutes % 60;
                var CSecond= Math.floor(seconds%60);//"%"是取余运算，可以理解为60进一后取余数，然后只要余数。
                if(endtime<=nowtime){
                   $(this).html("已过期");//如果结束日期小于当前日期就提示过期啦
                }else{
                   $(this).html(days+"天&nbsp;&nbsp;&nbsp;&nbsp;"+CHour+"时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+CMinute+"分");   
                }
          });
   setTimeout("lxfEndtime()",1000);
  };

