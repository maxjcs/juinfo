$(function(){
	//右边滚动
	center($(".tt"));
	$(".tt").show();

	// 居中1
    function center(obj) {
        var screenWidth = $(window).width(), screenHeight = $(window).height();  //当前浏览器窗口的 宽高
        var scrolltop = $(document).scrollTop(); //获取当前窗口距离页面顶部高度
        var objLeft = (screenWidth - obj.width()) / 1-50;
        var objTop = (screenHeight - obj.height()) / 2 + scrolltop;
		obj.css({ left: objLeft + 'px', top: objTop + 'px' });
        //浏览器窗口大小改变时
        $(window).resize(function () {
            screenWidth = $(window).width();
            screenHeight = $(window).height();
            scrolltop = $(document).scrollTop();
            objLeft = (screenWidth - obj.width()) / 1-50;
            objTop = (screenHeight - obj.height()) / 2 + scrolltop;
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
	
	
	
	$(".shousuo span").each(function(i){
		$(this).click(function(){
			$(".shousuo .su").eq(i).slideToggle(500);
		})
	})
	
	
	
})
