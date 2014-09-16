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

/*mv to reg.js*/	

/*mv to login.js*/
	
	$('#messagebox').click(function(){
		MessageBox();
		$('body').click();
	});
	
	$('#chosen').click(function(){
		ChosenCourse();
		$('body').click();
	});
	
	$('#courseinfo').click(function(){
		CourseInfo();

		$('body').click();
	});
	
	function MessageBox(){
		$("header").empty();
		$("#supersized").remove();
		
		$.ajax(
				{
					url : "MessageBox.show",
					type : "GET",
					data :
					{
						user: $.cookie('user')
					}, 
					success : function(resp)
					{
						$('header').append(resp);
						$('header').css(
								"background",
								"rgba(0,0,0,0)").css("color", "#000");
						$('.slide').remove();

					}
				});
	}
	
	function ChosenCourse(){
		$("header").empty();
		$("#supersized").remove();
		
		$.ajax(
				{
					url : "ChosenCourse.show",
					type : "GET",
					data :
					{
						user: $.cookie('user')
					}, 
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
	}
	
	function CourseInfo(){
		$("header").empty();
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
	}
	
	

});