function deleteConfirm(e)
{
	//if(event.srcElement.outerText == "取消课程")
	event.returnValue = confirm("确定要取消该课程吗？");
}
