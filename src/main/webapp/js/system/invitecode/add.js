//单独验证某一个input  class="checkpass"
jQuery.validator.addMethod("checkinv", function(value, element) {
	return (value.length != 0);
}, "邀请码不能为空");
jQuery.validator.addMethod("checkgroup", function(value, element) {
	return (value.length != 0);
}, "组织名称不能为空");
$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		/*rules : {
			"aboutUsFormMap.title" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : 'isExist.shtml',
					data : {
						name : function() {
							return $("#title").val();
						}
					}
				}
			}
		},*/
		/*messages : {
			"userFormMap.accountName" : {
				required : "请输入标题",
				remote : "该标题已经存在"
			}
		},*/
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			// element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});
