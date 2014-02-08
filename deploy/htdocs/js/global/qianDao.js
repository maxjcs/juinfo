	$(function(){
	//关闭签到提示
	$(".ts2 .gb").click(function(){
		$(".ts2").hide();
		//$(".tan").hide();
	})
	
	//弹出签到
	$(".qd").click(function(){
		//$.ajax({
//                url: " ",//这里是提交处理页面
//                data: {
//                    a: $("#b").val()//这里传进页面的值
//                },
//                dataType: 'html',
//                type: "POST",
//                success: function (data) {
//					//data表示返回的值
//                    if (data != "1") {//可判断返回值是否正确
//						
//                    }
//                    else {
                       center2($(".ts2"));
					   $(".ts2").show();
//                    }
//                },
//                error: function (XMLHttpRequest, textStatus, errorThrown) {
//                    alert("出错了！");
//                }
//            });
		
		
	})
	
	// 居中2
    function center2(obj) {
        var screenWidth = $(window).width(), screenHeight = $(window).height();  //当前浏览器窗口的 宽高
        var scrolltop = $(document).scrollTop(); //获取当前窗口距离页面顶部高度
        var objLeft = (screenWidth - obj.width()) / 2;
        var objTop = (screenHeight - obj.height()) / 2 + scrolltop;
        obj.css({ left: objLeft + 'px', top: objTop + 'px' });
        //浏览器窗口大小改变时
        $(window).resize(function () {
            screenWidth = $(window).width();
            screenHeight = $(window).height();
            scrolltop = $(document).scrollTop();
            objLeft = (screenWidth - obj.width()) / 2;
            objTop = (screenHeight - obj.height()) / 2 + scrolltop;
            obj.css({ left: objLeft + 'px', top: objTop + 'px' });

        });
		//浏览器有滚动条时的操作、
		$(window).scroll(function () {
			screenWidth = $(window).width();
			screenHeight = $(window).height();
			scrolltop = $(document).scrollTop();
			objLeft = (screenWidth - obj.width()) / 2;
			objTop = (screenHeight - obj.height()) / 2 + scrolltop;
			obj.css({ left: objLeft + 'px', top: objTop + 'px' });
		});
    }
})