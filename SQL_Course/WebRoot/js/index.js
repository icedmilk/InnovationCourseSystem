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
		//MessageBox();
		OverallSituation();
		$.ajax({
			url: 'pivot.html',
			success: function(resp){
				$('header').append('<iframe src="pivot.html" frameborder="0" scrolling="no" width="100%" height="700"></iframe>');
			}
		});
//		$('header').append("<div id='output'></div>")

		$('body').click();
	});
	
	$('#chosen').click(function(){
		ChosenCourse();
		$('body').click();
	});
	
	$('#courseinfo').click(function(){
		CourseInfo();
		//OverallSituation();
		$('body').click();
	});

	function OverallSituation(){
		$("header").empty();
		$("#supersized").remove();
		
		$.ajax({
			url : "OverallSituation.show",
			type : "GET",
			
			success : function(resp)
			{
				$('header').append(resp);
				$('header').css(
						"background",
						"rgba(0,0,0,0)").css("color", "#000");
				$('.slide').remove();
				//$('table').dataTable();

			}
		});
	}
	
	function MessageBox(){
		$("header").empty();
		$("#supersized").remove();
		
		$.ajax({
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
		
		$.ajax({
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
						"rgba(0,0,0,0)").css("color", "#000");
				$('.slide').remove();
				$('table').dataTable();

			}
		});
	}
	
	
	
	function CourseInfo(){
		$("header").empty();
		$("#supersized").remove();
		
		$.ajax({
			url : "CourseInfo",
			type : "GET",
			data:{
				sno: $.cookie("user")
			},
			success : function(resp)
			{
				$('header').append(resp);
				$('header').css(
						"background",
						"rgba(0,0,0,0)").css("color", "#000");
				$('.slide').remove();
				//$('table').dataTable();
				
				
				var status = $('#status').text();//get status
				if(status == "课程发布阶段")
				{
					$('table button').attr('disabled',"true");
				}
				
				var $button = $('table button');
				//console($button.length);
				for(var i = 0; i < $button.length; i++)
				{
					if(status == "课程发布阶段")
						$button.eq(i).text('此阶段不能操作');
					var buttonStatus = $button.eq(i).text();
					
					if(buttonStatus == "选课")
						$button.eq(i).button(
						{
							icons:
							{
								primary: "ui-icon-heart"
							}
						});
					else
						$button.eq(i).button(
						{
							icons:
							{
								primary: "ui-icon-closethick"
							}
						});
					
				}
				

				//选课/取消
				$('table button').click(function(){
					var index = $("button").index(this);
					var courseID = $('table tbody tr').eq(index).children('td').eq(0).text();
					var operation = $('table tbody tr').eq(index).children('td').eq(5).children('button').children('span').eq(1).text();
					
					if(operation == "取消")
					{
						$.ajax({
							url: "CancelChoose.do",
							type: "POST",
							data: {
								sno: $.cookie('user'),
								cno: courseID
							},
							success: function(resp){
								var $clickButton = $('table tbody tr').eq(index).children('td').eq(5).children('button');
								$clickButton.empty().removeAttr();
								$clickButton.button({
									icons:
									{
										primary: "ui-icon-heart"
									}
								});
								$clickButton.children('span').eq(1).text('选课');
//								alert('成功');
							}
						});
					}
					else if(status == "预选阶段")//pre choose
					{
						$('#rate').dialog("open");
						$('.btn-primary').click(function(){
							var str = $('.caption span').text();
							var arr = [];
							arr = str.split(" ");
							
							$.ajax({
								url: "ChooseCourse.do",
								type: "GET",
								data: {
									sno: $.cookie('user'),
									cno: courseID,
									star: arr[0]
								},
								success: function(resp){
									var $clickButton = $('table tbody tr').eq(index).children('td').eq(5).children('button');
									$clickButton.empty().removeAttr();
									$clickButton.button({
										icons:
										{
											primary: "ui-icon-closethick"
										}
									});
									$clickButton.children('span').eq(1).text('取消');
									$('#rate').dialog("close");
									//alert('成功');
									$('.btn-primary').unbind();
									
								}
							});
						});

					}
					
					else
					{
						$.ajax({
							url: "ChooseCourse.do",
							type: "POST",
							data: {
								sno: $.cookie('user'),
								cno: courseID
							},
							success: function(resp){
								var $clickButton = $('table tbody tr').eq(index).children('td').eq(5).children('button');
								$clickButton.empty().removeAttr();
								$clickButton.button({
									icons:
									{
										primary: "ui-icon-closethick"
									}
								});
								$clickButton.children('span').eq(1).text('取消');
								
								$('#rate').dialog("close");
								//alert('成功');
							}
						});
					}//else

				});//click
				$('table').dataTable();
			}//ajax success
			
		});
	}//CourseInfo
	
	
	
	
	//$('#courseinfo').click();
	
	$('#rate').dialog({
		width : "450px",
		modal : true,
		autoOpen : false
	});
	
	
	
	$('#ChangeStatus').dialog({
		width : "370px",
		modal : true,
		autoOpen : false
	});
	
	$('#ChangeStatus').buttonset();
    var column=$('#tr1');
	
	$('label[for=yuxuan]').click(function(){
		
		column.css('display', 'none');
	});
	
	$('label[for=zhengxuan]').click(function(){
		column.css('display', 'none');
	});
	
	$('label[for=fabu]').click(function(){
		column.css('display', 'block');
	});
	
	
	$('#ChangeStatus input[type=submit]').click(function(){
		var checkStatus = $('#ChangeStatus input:radio[value="zhengxuan"]:checked').val();
		if(checkStatus != null)
		{
			return confirm("正选阶段将进行选课志愿算法的分配，不能回退，确定继续吗？");
		}
	});
	
	$('#changestat').click(function(){
		$.ajax({
			url: 'GetStatus.show',
			type: 'POST',
			success: function(resp){
				$('#statusNow').text(resp);
			}
		});
		
		$('#ChangeStatus').dialog('open');
		$('body').click();
	});
	
	
	$('#submitChange').click(function(){
		event.preventDefault();
		//console.log($('#language').val());
		var chooseStatus = $('#ChangeStatus .ui-state-active').children('span').text();
		$.ajax({
			url: "CoreAlgorithm.do",
			data: {
				status: chooseStatus,
				science: $('#science').val(),
				language: $('#language').val(),
				art: $('#art').val(),
				management: $('#management').val(),
				economy: $('#economy').val()
			},
			type: "POST",
			success: function(){
				$('#ChangeStatus').dialog('close');
//				alert('成功');
				
			}
		});
	});
});