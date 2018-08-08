/**
 * 权限管理
 */
$(".roleNameAuthority").click(function() {
	var authority = $(this);
	var roleName = authority.attr("rolename");
	var roleId = authority.attr("roleid");
	$("#selectrole").html("当前配置角色为：" + roleName);
	$("#roleidhide").val(roleId);

	$.ajax({
		url : "/backend/functions.html",
		data : {},
		dataType : "html",
		type : "POST",
		errer : function() {
			alert("对不起，获取权限列表失败，请稍后再试！");
		},
		success : function(data) {
			var json = eval(data);
			listr = "";
			for (var i = 0; i < json.length; i++) {
				listr += "<li>";
				listr += "<ul id=\"subfuncul" + json[i].mainFunction.id + "\" class=\"subfuncul\">";
				listr += "<li  class=\"functiontitle\" ><input id='functiontitle" + json[i].mainFunction.id + "' onchange='mainFunctionSelectChange(this," + json[i].mainFunction.id + ");' funcid=\"" + json[i].mainFunction.id + "\" type='checkbox' />" + json[i].mainFunction.functionName + "</li>";
				for (j = 0; j < json[i].subFunctionList.length; j++) {
					listr += "<li><input onchange='subFunctionSelectChange(this," + json[i].mainFunction.id + ");' funcid=\"" + json[i].subFunctionList[j].id + "\" type='checkbox' /> " + json[i].subFunctionList[j].functionName + "</li>";
				}
				listr += "</ul></li>";
			}
			$("#functionList").html(listr);

			//获取默认勾选状态,循环遍历check选框
			$("#functionList :checkbox").each(function() {
				var checkbox = $(this);
				$.ajax({
					url : "/backend/getAuthorityDefault.html",
					data : {
						fid : checkbox.attr("funcid"),
						rid : roleId
					},
					type : "POST",
					dataType : "html",
					success : function(data) {
						if (data == "success") {
							checkbox.attr("checked", true);
						} else {
							checkbox.attr("checked", false);
						}
					},
					timeout : 1000
				})

			})
		},
		timeout : 1000
	})

})


function subFunctionSelectChange(obj, id) {
	if (obj.checked) {
		$("#functiontitle" + id).attr("checked", true);
	}
}

function mainFunctionSelectChange(obj, id) {
	if (obj.checked) {
		$("#subfuncul" + id + " :checkbox").attr("checked", true);
	} else {
		$("#subfuncul" + id + " :checkbox").attr("checked", false);
	}

//alert($(this) +　id);
}


$("#selectAll").click(function() { //全选  
	$("#functionList :checkbox").attr("checked", true);
});

$("#unSelect").click(function() { //全不选  
	$("#functionList :checkbox").attr("checked", false);
});

$("#reverse").click(function() {
	$("#functionList :checkbox").each(function() {
		$(this).attr("checked", !$(this).attr("checked"));
	})
})

//保存赋予权限操作
$("#confirmsave").click(function() {
	if (confirm("您确定要赋予所选的授权吗？")) {
		//获取roleId,获取所有勾选状态的functionId
		var roleId = $("#roleidhide").val();
		var funIds="";
		$("#functionList :checkbox").each(function(){
				if($(this).attr('checked')=='checked'){
				funIds+=$(this).attr('funcid')+",";
			}
		})
		//alert("角色id："+roleId+",所选择的funId有："+funIds);
		$.ajax({
			url:"/backend/modifyAuthority.html",
			data:{rid:roleId,funId:funIds},
			type:"POST",
			dataType:"html",
			error:function(){
				alert("系统超时，请稍后再试！");
			},
			success:function(data){
				if(data=="success"){
					alert("恭喜你，授权成功！");
				}else{
					alert("授权失败，请稍后再试！");
				}
			}
			
		})
		
		

	}
})