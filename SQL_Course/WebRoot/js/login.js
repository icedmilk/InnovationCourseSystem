$(function()
{

	$('#login_a').click(function()
	{
		$('#login').dialog("open");
	});

	$('#login').dialog(
	{
		autoOpen : false,
		title : "登录",
		buttons :
		{
			"Submit" : function()
			{
				$('#login').submit();
			},

			"Cancel" : function()
			{
				$(this).dialog('close');
			}
		},
		// show : "clip",
		hide : true,
		modal : true,
		width : "320px",
	// height : "1000px"
	}).buttonset().validate(
	{
		submitHandler : function(form)
		{
			$(form).ajaxSubmit(
					{
						url : 'login.do',
						type : 'POST',
						beforeSubmit : function()
						{
							$('#login').dialog('widget').find('button')
									.eq(1).button('disable');
							// alert("b");
							$('#loading').dialog('open');
						},
						success : function(responseText)
						{

							if (responseText)
							{

								$('#login').dialog('widget').find(
										'button').eq(1)
										.button('enable');
								$('#loading').addClass('success').html(
										'Success');

								setTimeout(function()
								{
									$('#loading').dialog('close');
									$('#login').dialog('close');
									$('#login').resetForm();
									$('#login span.star').html('*')
											.removeClass('succ');

									// $.cookie('user', responseText);
									$.cookie('user', $('#sno_login')
											.val());
									$('#exit_a, #name_a').show();
									$('#name_a').find('a').html(
											$.cookie('user'));
									$('#login_a, #reg_a').hide();
									
									//CourseInfo();
									/*lucheng empty
									 * 
									 * $("header").empty();
									$("#supersized").remove();
									$.ajax(
									{
										url : "CourseInfo",
										type : "GET",
										success : function(resp)
										{
											$('header').append(resp);
											$('header').css(
													"background",
													"rgba(0,0,0,0)");
											$('.slide').remove();
											$('table').dataTable();

										}
									});
									 * */
									
								}, 1000);
							}
						}

					});
		},
		// debug: true,
		errorLabelContainer : 'ol.login_error',
		wrapper : 'li',

		rules :
		{
			sno_login :
			{
				required : true,
				minlength : 5
			},
			password_login :
			{
				required : true,
				minlength : 5,
				remote :
				{
					url : 'login.do',
					type : 'POST',
					data :
					{
						sno_login : $('#sno_login').val()
					}
				/*
				 * , success: function(){ alert("succ"); }
				 */
				}
			}
		},

		messages :
		{
			password_login :
			{
				remote : '学号/密码不正确'
			}
		},

		highlight : function(element, errorClass)
		{
			$(element).css("border", "2px solid #ff2400");
			$(element).parent().find('span').removeClass('succ');
		},

		unhighlight : function(element, errorClass)
		{
			$(element).css("border", "1px solid #ccc");
			//$(element).parent().find('span').html('√').css('color', 'green');
			$(element).parent().find('span').addClass('succ').html("");
		}

	});
});