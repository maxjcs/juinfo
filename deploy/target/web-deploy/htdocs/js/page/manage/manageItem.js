$(document).ready(function(){
	
	/**  ÏÂ¼ÜÉÌÆ· **/
	$(".downitem").click(function(){
		var batchId = $(this).attr("dataparamid");
		$("#batchId").attr("value",batchId);
		$("#form1").submit();
	}
	);
});