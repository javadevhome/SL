/**
 * 登录也js
 */
$(".loginBtn").click(function() {
	var user = new Object();
	user.loginCode = $.trim($("#loginCode").val());
	user.password = $.trim($("#password").val());
	user.isStart = 1;
	if (user.loginCode == "" || user.loginCode == null) {
		$("#loginCode").focus();
		$("#tips").css("color", "red").html("用户名不能为空！");
	} else if (user.password == "" || user.password == null) {
		$("#password").focus();
		$("#tips").css("color", "red").html("密码不能为空！");
	} else {
		$("#tips").html("");
		$.ajax({
			url : "/dologin.html",
			data : {
				user : JSON.stringify(user)
			},
			dataType : "text",
			type : "POST",
			timeout : 5000,
			success : function(result) {
				if (result == "success") {
					window.location.href = "/main.html"
				} else if (result == "faile") {
					$("#tips").css("color", "red").html("登录失败，请重试！");
					$("#loginCode").val("");
					$("#password").val("");
				} else if (result == "nologinCode") {
					$("#tips").css("color", "red").html("此用户名不存在！");
					$("#loginCode").val("");
					$("#password").val("");
				} else if (result == "pwerror") {
					$("#tips").css("color", "red").html("密码错误！");
					$("#loginCode").val("");
					$("#password").val("");
				} else if (result == "nodata") {
					$("#tips").css("color", "red").html("没有数据返回！");
					$("#loginCode").val("");
					$("#password").val("");
				}
			},
			error : function() {
				$("#tips").css("color", "red").html("登录失败，请稍后再试！");
			}
		})





	}
})