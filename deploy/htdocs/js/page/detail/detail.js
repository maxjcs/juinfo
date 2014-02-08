 function regInput(obj, reg, inputStr)
 {
  var docSel = document.selection.createRange()
  if (docSel.parentElement().tagName != "INPUT") return false
  oSel = docSel.duplicate()
  oSel.text = ""
  var srcRange = obj.createTextRange()
  oSel.setEndPoint("StartToStart", srcRange)
  var str = oSel.text + inputStr + srcRange.text.substr(oSel.text.length)
  return reg.test(str)
 }

 

$(document).ready(function(){

	//清空选项
	$("#qingkong").click(function(){
		$(".zz").html("共0个号  第1/1页");
		$(".selected_zong").html("");
	})
	
	$(".p2").each(function(e){
		$(this).mouseover(function(){
			$(this).css("background","#F2F2F2");
		})
		$(this).mouseout(function(){
			$(this).css("background","#ffffff");
		})
	})
	$(".p3").mouseover(function(){
		$(this).css("background","#F2F2F2");
	})
	$(".p3").mouseout(function(){
		$(this).css("background","#ffffff");
	})
	$(".p4").mouseover(function(){
		$(this).css("background","#F2F2F2");
	})
	$(".p4").mouseout(function(){
		$(this).css("background","#ffffff");
	})
	
//	del();
//	function del(){
//	$ (".zong .cc").each(function(n){
//		$ (this).click(function(){
//			$ (".zong .dan").eq(n).remove();
//			//$ (".zong .dan").eq(n).hide();
//			//var ss=$(".ww").html();
//			//$ (".ww").html(ss);
//			//del();
//		})
//	})
//	}
	
	//选中部分的翻页
	function showpage(){
		var pageNum = $("#s_pageNum").val();
		var count = $(".selected_zong .dan").size();
		var endIndex = pageNum*20 > count ? count: pageNum*20
		var i=0;
		while(i < count){
			if( i >= (pageNum-1)*20 && i < endIndex){
				$(".selected_zong .dan").eq(i).show();
			}else{
				$(".selected_zong .dan").eq(i).hide();
			}
			i++;
		}
		
		//设置总页数
		var pageCount = parseInt((count-1)/20) +1;
		$("#s_pageCount").val(pageCount);
		$("#selected_page").html("共"+count+"个号  第"+pageNum+"/"+pageCount+"页");
	}	
	
	//删除选中的号码
	removeNumber();
	function removeNumber(){
		$ (".selected_zong").find("span[class='cc']").click(function(){
			$(this).parent().remove();
		});
		//设置翻页值
		showpage();
	}
	
	//添加选中的号码
	addNumber();
	function addNumber(){
		$ (".zong").find("span[class='add']").click(function(){
			var addnew=$(this).parent().clone();
			addnew.find("span[class='add']").remove();
			addnew.append("<span class=\"cc\" title=\"删除\"><a href='#'><img src=\"/images/cha.png\" /></a></span>");
			$ (".selected_zong").append(addnew);
			//换成勾的图片
			$(this).html("<a href=\"#\"><img src=\"/images/gou.png\" />");
			//绑定删除事件
			removeNumber();
			//设置翻页值
			$(".s_pageNum").val(1);
			showpage();
		});
	}
	
	
	//切换
	$("#xiangqing .ji").eq(0).show();
	$(".qiehuan li").each(function(s){  
		$(this).mouseover(function(){
			$(".qiehuan li").removeClass();
			$("#xiangqing .ji").hide();
			$(this).addClass("select");
			$("#xiangqing .ji").eq(s).show();
		})
	})	
	
	//确定拍下
	$(".qdpx1").click(function(){
		
		//选中的号放入数组
		var numberArray=new Array();
		$(".selected_zong .aa").each(function(){
			numberArray.push($(this).html());
		})
		if(numberArray.length == 0){
			$(".ts .bottom").html("你还未选号！");
        	center2($(".ts"));
			$(".ts").show();
			return true;
		}
		
		
		$.ajax({
                url: "http://yunqigou.com/home/makeOrder.do ",//这里是提交处理页面
                data: {
                    bid: $("#bid_hidden").val(),//这里传进页面的值
                    numbers:numberArray.join(",")
                },
                dataType: 'json',
                type: "POST",
                success: function (json) {                	
                	$(".ts .bottom").html(json.msg);
                	center2($(".ts"));
	  				$(".ts").show();
	  				return true;
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                	$(".ts .bottom").html("系统繁忙，请稍后！");
                	center2($(".ts"));
	  				$(".ts").show();
	  				return true;
                }
            });
		
		  
	})
	
	//弹出选号框
	$("input[class=btnXH]").click(function(){
		center2($(".selectNum"));
		$(".selectNum").show();
	})
	
	function rangeAjax(){
		//校验值
		if($("#range_start").val()==""){
			$("#rangeerror").html("请输入正确的开始值!");
			return;
		}
		if($("#range_end").val()==""){
			$("#rangeerror").html("请输入正确的结束值!");
			return;
		}
		$.ajax({
			url:"http://yunqigou.com/home/numRangeSelect.do",
			data:{
				range_start:$("#range_start").val(),
				range_end:$("#range_end").val(),
				type:2,
				pageNum:$("#pageNum").val(),
				bid:$("#bid_hidden").val()
			},
            type: "POST",
            'dataType': 'json',
            success: function (json) {
            	//清楚错误消息
            	$("#rangeerror").html("");
            	//设置当前页
            	$("#pageNum").val(json.pageNum);
            	$("#pageCount").val(json.pageCount);
            	$("#type").val(json.type);
            	//渲染数据
				$(".zong").html(json.data);
				resetPage(json);
				addNumber();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            	//$(".zong").html("111");
            }
				
		})
	}
	
	
	
	function huzhiAjax(){
		//校验值
		if($("#huzhi_start").val()==""){
			$("#huzhierror").html("请输入正确的沪指开始值!");
			return;
		}
		if($("#huzhi_end").val()==""){
			$("#huzhierror").html("请输入正确的沪指结束值!");
			return;
		}
		$.ajax({
			url:"http://yunqigou.com/home/numRangeSelect.do",
			data:{
				range_start:$("#huzhi_start").val(),
				range_end:$("#huzhi_end").val(),
				type:1,
				pageNum:$("#pageNum").val(),
				bid:$("#bid_hidden").val()				
			},
            type: "POST",
            'dataType': 'json',
            success: function (json) {
            	//清楚错误消息
            	$("#huzhierror").html("");
            	//设置当前页
            	$("#pageNum").val(json.pageNum);
            	$("#pageCount").val(json.pageCount);
            	$("#type").val(json.type);
            	//渲染数据
				$(".zong").html(json.data);
				resetPage(json);
				addNumber();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            	//$(".zong").html("111");
            }
		})
	}
	
	function randomAjax(){
		//校验值
		if($("#random_text").val()==""){
			$("#randomerror").html("请输入正确的值!");
			return;
		}
		//console.log($("#pageNum").val());
		$.ajax({
			url:"http://yunqigou.com/home/numRangeSelect.do",
			data:{
				range_start:$("#random_text").val(),
				type:3,
				page_num:$("#pageNum").val(),
				bid:$("#bid_hidden").val()				
			},
            type: "POST",
            'dataType': 'json',
            success: function (json) {
            	//console.log(json);
            	//清楚错误消息
            	$("#randomerror").html("");
            	//设置当前页
            	$("#pageNum").val(json.pageNum);
            	$("#pageCount").val(json.pageCount);
            	$("#type").val(json.type);
            	//渲染数据
				$(".zong").html(json.data);
				resetPage(json);
				addNumber();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            	//$(".zong").html("111");
            }
				
		})
	}
	
	
	$("#huzhiselect").click(function(){
		huzhiAjax();
	})
	$("#rangeselect").click(function(){
		rangeAjax();
	})
	$("#randomselect").click(function(){
		randomAjax();
	})

	//重置翻页值
	function resetPage(json){
		$("#s_page").html("第"+json.pageNum+"/"+json.pageCount+"页,共"+json.total+"个号");
	}
	
	//向左翻页
	$("#img_left").click(function(){
		var pageNum = $("#pageNum").val();
		var type = $("#type").val();
		//当前就是第一页
		if(pageNum == "" || pageNum == "1"){
			return;
		}else{
			$("#pageNum").val(parseInt(pageNum) - 1);
		}	
		switch (type){
		case '1':
			huzhiAjax();
		    break;
		case '2':
			rangeAjax();
		    break;
		case '3':
			randomAjax();
			break;
		default:
			//
		}		
	})
	//向右翻页
	$("#img_right").click(function(){
		
		var pageNum = $("#pageNum").val();
		var pageCount = $("#pageCount").val();
		var type = $("#type").val();
		//当前就是最后一页
		if(pageCount == "" || pageCount == "1" || pageNum == pageCount){
			return;
		}else{
			$("#pageNum").val(parseInt(pageNum) + 1);
		}
		
		switch (type){
			case '1':
				huzhiAjax();
			    break;
			case '2':
				rangeAjax();
			    break;
			case '3':
				randomAjax();
				break;
			default:
			//
		}	
		
	})
	
	//向左翻页
	$("#s_leftimg").click(function(){
		var pageNum = $("#s_pageNum").val();
		//当前就是第一页
		if(pageNum == "" || pageNum == "1"){
			return;
		}else{
			$("#s_pageNum").val(parseInt(pageNum) - 1);
		}	
		//选中的号翻页
		showpage();
	})
	
	//向右翻页
	$("#s_rightimg").click(function(){
		
		var pageNum = $("#s_pageNum").val();
		var pageCount = $("#s_pageCount").val();
		//当前就是最后一页
		if(pageCount == "" || pageCount == "1" || pageNum == pageCount){
			return;
		}else{
			$("#s_pageNum").val(parseInt(pageNum) + 1);
		}
		//选中的号翻页
		showpage();		
	})
	
	
	//关闭购物提示
	$(".ts .gb").click(function(){
		$(".ts").hide();
		$(".tan").hide();
	})
	
	$(".ts .btnClose").click(function(){
		$(".ts").hide();
		$(".tan").hide();
	})
	
	//关闭选号框
	$(".selectNum .gb").click(function(){
		$(".selectNum").hide();
		$(".tan").hide();
	})
	
	//关闭选号框
	$(".selectNum .btnClose").click(function(){
		$(".selectNum").hide();
		$(".tan").hide();
	})
	
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

	//$(".smallClass img").attr("src",$(".chang2 li img").eq(0).attr("src"));
	//$(".smallClass img").attr("zoomimg",$(".chang2 li img").eq(0).attr("zoomimg"));
	$(".chang2 li img").each(function(i){
		$(".chang2 li img").eq(i).click(function(){
			$(".smallClass img").attr("src",$(this).attr("src"));
			$(".smallClass img").attr("zoomimg",$(this).attr("zoomimg"));
			$(".chang2 li").removeClass();
			$(".chang2 li").eq(i).addClass("select");
		})
	})

	//var setValues={
//		/*以下属性名称不能更改*/
//		zoomWidth:400,/*放大区域宽*/
//		zoomHeight:400,/*放大区域高*/
//		zoomOffset:10,/*放大区域与原图距离*/
//		zoomSide:"left"/*放大区域是在原图的左边还是右边 left or right*/
//	};
//	var itImgs=Jids("ImgSmallDiv");
//	itImgs.each(function(x){
//		JSWK.zoom(itImgs[x],setValues);
//	});

	
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
	
	//关闭签到提示
	$(".ts2 .gb").click(function(){
		$(".ts2").hide();
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
	
	$(".radio").each(function(i){
		$(this).click(function(){
			$(".text").attr("disabled","disabled");
			if(i==0)
			{
				$(".text").eq(0).attr("disabled","");	
				$(".text").eq(1).attr("disabled","");	
			}
			else if(i==1)
			{
				$(".text").eq(2).attr("disabled","");
				$(".text").eq(3).attr("disabled","");
			}
			else if(i==2)
			{
				$(".text").eq(4).attr("disabled","");	
			}
			//$(".radio").parent().hide();
			//$(this).parent().show();
		})
	})
	
	$("#clearZong").click(function(){
		$(".zong").html("");
	});

	

})