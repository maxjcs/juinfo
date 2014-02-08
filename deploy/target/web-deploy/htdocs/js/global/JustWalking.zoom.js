// JavaScript Document
JSWK.zoom=function(obj,arguments){/*arguments json数据*/
	var initValues={
		zoomWidth:400,/*放大区域宽*/
		zoomHeight:400,/*放大区域高*/
		zoomOffset:20,/*放大区域与原图距离*/
		zoomSide:"right"/*放大区域是在原图的左边还是右边*/
	};
	if(arguments){
		initValues=arguments;
		//JSWK.jsonReset(initValues,arguments);/*将arguments json数据值赋给initValues*/
	}
	var mouseOver=false;/*判断鼠标是否在小图所在的div区域内*/
	obj.onmouseover=function(e){
		var Img=this.getElementsByTagName("img")[0];/*获取小图对象*/
		var ImgLeft=this.offsetLeft;/*获得小图区域与左边界之间的距离*/
		var ImgTop=this.offsetTop;/*获得小图区域与顶边界之间的距离*/
		var ImgWidth=this.clientWidth;/*获得小图区域有效宽*/
		var ImgHeight=this.clientHeight;/*获得小图区域有效高*/
		var ZoomImg=Img.getAttribute("zoomimg");/*获得大图地址路径*/
		if(!Jid("JSWK_ZoomDIV")){
			var curObj=JSWK.nodeProtoType(this);
			curObj.appAfter("<div id='JSWK_ZoomDIV' class='JSWK_ZoomDIV'><img id='zoomImgId' src='"+ZoomImg+"'/></div>");/*增加大图显示节点*/
			curObj.appChild("<div id='JSWK_ZoomPuP' class='JSWK_ZoomPuP'>&nbsp;</div>");/*增加小图游标节点*/
			//JSWK.nodeAfter("<div id='JSWK_ZoomDIV' class='JSWK_ZoomDIV'><img id='zoomImgId' src='"+ZoomImg+"'/></div>",this);
			//JSWK.appendChild("<div id='JSWK_ZoomPuP' class='JSWK_ZoomPuP'>&nbsp;</div>",this);
		}
		var leftpos=0;/*初始化大图显示区域的x方向位置*/
		if(initValues.zoomSide == "right"){
			if(ImgLeft + ImgWidth + initValues.zoomOffset + initValues.zoomWidth > screen.width){
				leftpos = ImgLeft  - initValues.zoomOffset - initValues.zoomWidth;
			}
			else{
				leftpos = ImgLeft + ImgWidth + initValues.zoomOffset;
			}
		}
		else{
			leftpos = ImgLeft - initValues.zoomWidth - initValues.zoomOffset;
			if(leftpos < 0){
				leftpos = ImgLeft + ImgWidth  + initValues.zoomOffset;
			}
		}
		Jid("JSWK_ZoomDIV").style.top=ImgTop+"px";
		Jid("JSWK_ZoomDIV").style.left=leftpos+"px";
		Jid("JSWK_ZoomDIV").style.width=initValues.zoomWidth+"px";
		Jid("JSWK_ZoomDIV").style.height=initValues.zoomHeight+"px";
		Jid("JSWK_ZoomDIV").style.display="block";/*显示大图区域并定位*/
		
		document.onmousemove=function(e){
			var evt=e||window.event;/*获取event事件对象*/
			var elm=evt.srcElement?evt.srcElement:evt.target;/*获取event对象所指向的元素*/
			if(elm==Img){mouseOver=true;}else{mouseOver=false;}/*如果event指向的元素是当前小图像，则表示则仍在当前图像区域内，否则在之外*/
			var mouse=new mouseXY(evt);/*获取鼠标指针坐标*/
			var bigwidth = Jid("zoomImgId").offsetWidth;/*获取大图显示区域可见宽*/
			var bigheight = Jid("zoomImgId").offsetHeight;/*获取大图显示区域可见高*/
			var scalex= 'x';
			var scaley ='y';
			if(isNaN(scalex)||isNaN(scaley)){
				scalex = (bigwidth/ImgWidth);/*获取大图显示区域与小图区域的宽度比例*/
				scaley = (bigheight/ImgHeight);/*获取大图显示区域与小图区域的高度比例*/
				Jid("JSWK_ZoomPuP").style.width=70+"px";/*根据比例，得到小图游标区域的宽度*/
		    	Jid("JSWK_ZoomPuP").style.height=70+"px";/*根据比例，得到小图游标区域的高度*/
				Jid("JSWK_ZoomPuP").style.display='block';
			}
			 var xpos = mouse.x - Jid("JSWK_ZoomPuP").clientWidth/2 - ImgLeft;/*获得游标区域相对于小图区域的x位置*/
             var ypos = mouse.y - Jid("JSWK_ZoomPuP").clientHeight/2 - ImgTop ; /*获得游标区域相对于小图区域的y位置*/
 
			 xpos = (mouse.x - Jid("JSWK_ZoomPuP").clientWidth/2 < ImgLeft ) ? 0 : (mouse.x + Jid("JSWK_ZoomPuP").clientWidth/2 > ImgWidth + ImgLeft ) ?  (ImgWidth -Jid("JSWK_ZoomPuP").clientWidth -2)  : xpos;/*当超出小图区域范围时的处理*/
			 
			 ypos = (mouse.y - Jid("JSWK_ZoomPuP").clientHeight/2 < ImgTop ) ? 0 : (mouse.y + Jid("JSWK_ZoomPuP").clientHeight/2  > ImgHeight + ImgTop ) ?  (ImgHeight - Jid("JSWK_ZoomPuP").clientHeight -2 ) : ypos;/*当超出小图区域范围时的处理*/

 			 Jid("JSWK_ZoomPuP").style.top=ypos+"px";
			 Jid("JSWK_ZoomPuP").style.left=xpos+"px";
			 var scrolly = ypos;
			 var scrollx = xpos;
			 Jid("JSWK_ZoomDIV").scrollTop = scrolly * scaley;/*根据比例得到大图显示区域应该滚动到什么位置*/
			 Jid("JSWK_ZoomDIV").scrollLeft = scrollx * scalex ;
		};
		
	};
	obj.onmouseout=function(){
		if(!mouseOver){
			document.onmousemove=null;
			if(Jid("JSWK_ZoomPuP") && Jid("JSWK_ZoomPuP").parentNode==this){
				this.removeChild(Jid("JSWK_ZoomPuP"));
			}
			if(Jid("JSWK_ZoomDIV") && Jid("JSWK_ZoomDIV").parentNode==this.parentNode){
				this.parentNode.removeChild(Jid("JSWK_ZoomDIV"));
			}
		}
	};
};



