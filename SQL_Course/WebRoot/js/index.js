$(function()
{
	$('#search_button').button(
	{
		icons:
		{
			primary: "ui-icon-search"
		}
	});



	//$.cookie('user', 'val1',{
	//	expires: 7,// 7 days
	//});

	// $('#bb').submit(function()
	// {
	// 	$(this).ajaxSubmit();
	// 	return false;
	// });

	$('#exit_a').click(function()
	{
		$.removeCookie('user');
		window.location.href = '/sysui';
	});

	$('#exit_a, #name_a').hide();

	if ($.cookie('user'))
	{
		$('#exit_a, #name_a').show();
		$('#name_a').find('a').html($.cookie('user'));
		$('#login_a, #reg_a').hide();
	}
	else
	{
		$('#exit_a, #name_a').hide();
		$('#login_a, #reg_a').show();
	}

	$('#loading').dialog(
	{
		autoOpen: false,
		modal: true,
		height: 50,
		width: 100
	}).parent().parent().find('.ui-widget-header').hide();

	$('#reg').dialog(
	{
		autoOpen: false,
		title: "注册",
		buttons:
		{
			"Submit": function()
			{
				//$('#expires').is(':checked')
				$('#reg').submit();
				//alert("a");
			},

			"Cancel": function()
			{
				$(this).dialog('close');
			}
		},
		//show : "clip",
		hide: true,
		modal: true,
		width: "350px",
		//height : "1000px"
	}).buttonset()
		.validate(
		{
			submitHandler: function(form)
			{
				$(this).ajaxSubmit(
				{
					type: 'POST',
					url: 'register.do',
					data: {
						sno: $('#sno').val(),
						name: $('#name').val(),
						password: $('#password').val()
					},
					beforeSubmit: function()
					{
						//alert("a");
						$('#reg').dialog('widget').find('button').eq(1).button('disable');

						$('#loading').dialog('open');
					},
					success: function(responseText)
					{
						//alert(responseText);
						
						if (responseText == 'true')
						{
							$('#reg').dialog('widget').find('button').eq(1).button('enable');
							
							$('#loading').addClass('success').html('Success');

							setTimeout(function()
							{
								$('#loading').dialog('close');
								$('#reg').dialog('close');
								$('#reg').resetForm();
								$('#reg span.star').html('*').removeClass('succ');
								//alert($('#name').val());
								$.cookie('user', $('#name').val());
								
								$('#exit_a, #name_a').show();
								$('#name_a').find('a').html($.cookie('user'));
								$('#login_a, #reg_a').hide();

							}, 1000);
						}
						else
						{
							alert('已被注册');
						}
					}

				});
			},
			//debug: true,
			errorLabelContainer: 'ol.reg_error',
			wrapper: 'li',

			rules:
			{
				sno:
				{
					required: true,
					minlength: 5,
					remote:
					{
						url: 'register.do',
						type: 'POST'
					}
				},
				name:
				{
					required: true,
					minlength: 2
				},
				password:
				{
					required: true,
					minlength: 5
				},
				confirm:
				{
					required: true,
					minlength: 5
				},
//				birth:
//				{
//					required: true,
//				},
				email:
				{
					required: true,
					email: true
				}
			},
			messages:
			{
				sno:
				{
					remote: '该学号已被注册'
				}
			},

			highlight: function(element, errorClass)
			{
				$(element).css("border", "2px solid #ff2400");
				$(element).parent().find('span').removeClass('succ');
			},

			unhighlight: function(element, errorClass)
			{
				$(element).css("border", "1px solid #ccc");
				//$(element).parent().find('span').html('√').css('color', 'green');
				$(element).parent().find('span').addClass('succ').html("");
			}

		});


	$('#reg_a').click(function()
	{
		$('#reg').dialog("open");
	});

	$('#birth').datepicker(
	{
		changeYear: true,
		changeMonth: true,
		dateFormat: "yy-mm-dd",
		showButtonPanel: true,
		maxDate: 0,

		yearRange: "1914:2014",
		hideIfNoPrevNext: true,

		defaultDate: "1990-1-1"
	}); //日历

	$('#reg input[title]').tooltip(
	{
		position:
		{
			my: "left+5px center",
			at: "right center"
		},
		show: false,
		hide: false
	}); //提示标签位置


	$('#email').autocomplete(
	{
		source: function(request, response)
		{
			var hosts = ["qq.com", "163.com", "gmail.com", "sina.com.cn", "hotmail.com"];
			var term = request.term; //用户填写的内容
			var name = term; //name部分
			var host = ""; //host部分
			var ix = term.indexOf("@"); //@的位置
			var result = [];

			result.push(term);

			if (ix > -1)
			{
				name = term.slice(0, ix);
				host = term.slice(ix + 1);
			}

			if (name)
			{
				var findedHosts = [];

				if (host)
				{
					findedHosts = $.grep(hosts, function(value, index)
					{
						return value.indexOf(host) > -1;
					});
				}
				else
				{
					findedHosts = hosts;
				}
				var findedResult = $.map(findedHosts, function(value, index)
				{
					return name + "@" + value;
				});
			}

			var finalResult = result.concat(findedResult);

			response(finalResult);
		},
		delay: 0,
		autoFocus: true,
	}); //自动补全


	$('#login_a').click(function()
	{
		$('#login').dialog("open");
	});

	$('#login').dialog(
	{
		autoOpen: false,
		title: "登录",
		buttons:
		{
			"Submit": function()
			{
				$('#login').submit();
			},

			"Cancel": function()
			{
				$(this).dialog('close');
			}
		},
		//show : "clip",
		hide: true,
		modal: true,
		width: "320px",
		//height : "1000px"
	}).buttonset()
		.validate(
		{
			submitHandler: function(form)
			{
				$(form).ajaxSubmit(
				{
					url: 'login.do',
					type: 'POST',
					beforeSubmit: function()
					{
						$('#login').dialog('widget').find('button').eq(1).button('disable');
						//alert("b");
						$('#loading').dialog('open');
					},
					success: function(responseText)
					{
						
						if (responseText)
						{

							$('#login').dialog('widget').find('button').eq(1).button('enable');
							$('#loading').addClass('success').html('Success');
							
							
							setTimeout(function()
							{
								$('#loading').dialog('close');
								$('#login').dialog('close');
								$('#login').resetForm();
								$('#login span.star').html('*').removeClass('succ');
								
								$.cookie('user', responseText);
								
								$('#exit_a, #name_a').show();
								$('#name_a').find('a').html($.cookie('user'));
								$('#login_a, #reg_a').hide();
								
								$("header").empty();
								$("#supersized").remove();
								$.ajax({
									url: "CourseInfo",
									type: "GET",
									success: function(resp){
										$('header').append(resp);
										$('header').css("background", "rgba(0,0,0,0)");
										$('.slide').remove();
										$('table').dataTable();

										
									}
								});
								
							}, 1000);
						}
					}

				});
			},
			//debug: true,
			errorLabelContainer: 'ol.login_error',
			wrapper: 'li',

			rules:
			{
				sno_login:
				{
					required: true,
					minlength: 5
				},
				password_login:
				{
					required: true,
					minlength: 5,
					remote:
					{
						url: 'login.do',
						type: 'POST',
						data: {
							sno_login: $('#sno_login').val()
						}/*,
						success: function(){
							alert("succ");
						}*/
					}
				}
			},

			messages:
			{
				password_login:
				{
					remote: '学号/密码不正确'
				}
			},

			highlight: function(element, errorClass)
			{
				$(element).css("border", "2px solid #ff2400");
				$(element).parent().find('span').removeClass('succ');
			},

			unhighlight: function(element, errorClass)
			{
				$(element).css("border", "1px solid #ccc");
				//$(element).parent().find('span').html('√').css('color', 'green');
				$(element).parent().find('span').addClass('succ').html("");
			}

		});

});