/**
 * maxjcs
 */
$(document).ready(function(){
    	setContent();
    	});


   var ue=new UE.ui.Editor();
    ue.render('editor');    
    
    
    function submitIt(){
    	getContent();
        document.getElementById("form1").submit();
    }
    
    function getContent(){
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(ue.getContent());
        alert(arr.join("\n"));
        $("#detail").value=ue.getContent();
        document.getElementById("$!group.detail.key").value=ue.getContent();
    }
    
    function setContent(){
        ue.setContent($("#detail").value);
    }
    
    

