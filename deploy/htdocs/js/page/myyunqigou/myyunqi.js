$(document).ready(function(){
	
	$("#addCoinBtn").click(function(){
		var jine = $("#c_jine").val(); 
		var bank_type_value = $("#bank_type_value").val();
		if(jine =="" || jine <=0){
			$("#jine_error").html("充值金额输入错误，请重新输入！");
			return;
		}
		if(bank_type_value == "" || bank_type_value=="0"){
			$("#jine_error").html("请选择支付方式！");
			return;
		}
		
		this.form.submit();
	});
	
	/** 兑换积分 **/
	$("#fens2coin").click(function(){
		if(confirm("该操作不可逆，确认兑换成运气币？")){
			$("#action_method").attr("name","event_submit_do_fens_2_coin");
			$("#profileform").submit();
		}
	});
	
	/** 修改地址 **/
	$("#modifyAddr").click(function(){
		$("#setAddr").show();
	});
	
	/** 修改地址 **/
	$("#btnmail").click(function(){
		$("#action_method").attr("name","event_submit_do_modify_addr");
		$("#profileform").submit();
	});
});