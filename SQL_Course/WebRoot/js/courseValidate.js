$(function()
{

	$("#cid").bind("blur", "课程编号", func);
	$("#cname").bind("blur", "课程名", func);
	$("#ctype").bind("blur", "课程类型", func);
	$("#lim").bind("blur", "限制人数", func);
	
	function funcCid(e)//正则+验证是否存在
	{
		var id = $("#" + e.data + "validate");

		if ($(this).val() == "")
		{
			if (id.size() == 0)
				$(this).after(
						"<span id='" + e.data + "validate' style='color:red'>"
								+ e.data + "不可以为空" + "</span>");
			else
				id.html(e.data + "不可以为空").css("color", "red");
		} else
		{
			if (id.size() == 0)
				$(this).after(
						"<span id='" + e.data
								+ "validate' style='color:green'>" + "√"
								+ "</span>");
			else
				id.html("√").css("color", "green");
		}
	}
	
	function func(e)
	{

		var id = $("#" + e.data + "validate");

		if ($(this).val() == "")
		{
			if (id.size() == 0)
				$(this).after(
						"<span id='" + e.data + "validate' style='color:red'>"
								+ e.data + "不可以为空" + "</span>");
			else
				id.html(e.data + "不可以为空").css("color", "red");
		} else
		{
			if (id.size() == 0)
				$(this).after(
						"<span id='" + e.data
								+ "validate' style='color:green'>" + "√"
								+ "</span>");
			else
				id.html("√").css("color", "green");
		}
	}

	$("form").submit(
			function(e)
			{
				$("#cid").blur();
				$("#cname").blur();
				$("#ctype").blur();
				$("#lim").blur();

				if ($("#cid").val() == "" || $("#cname").val() == ""
						|| $("#ctype").val() == "" || $("#lim").val() == "")
				{
					e.preventDefault();
				} else
				{
					$("img").show();
					$("button").remove();
				}
			});
});