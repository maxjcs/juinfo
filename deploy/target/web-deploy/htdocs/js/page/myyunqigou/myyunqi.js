$(document).ready(function(){
	
	$("#addCoinBtn").click(function(){
		var jine = $("#c_jine").val(); 
		var bank_type_value = $("#bank_type_value").val();
		if(jine =="" || jine <=0){
			$("#jine_error").html("��ֵ�������������������룡");
			return;
		}
		if(bank_type_value == "" || bank_type_value=="0"){
			$("#jine_error").html("��ѡ��֧����ʽ��");
			return;
		}
		
		this.form.submit();
	});
	
	/** �һ����� **/
	$("#fens2coin").click(function(){
		if(confirm("�ò��������棬ȷ�϶һ��������ң�")){
			$("#action_method").attr("name","event_submit_do_fens_2_coin");
			$("#profileform").submit();
		}
	});
	
	/** �޸ĵ�ַ **/
	$("#modifyAddr").click(function(){
		$("#setAddr").show();
	});
	
	/** �޸ĵ�ַ **/
	$("#btnmail").click(function(){
		$("#action_method").attr("name","event_submit_do_modify_addr");
		$("#profileform").submit();
	});
});