$(function(){
    var derivers = $.pivotUtilities.derivers;

    function html2json(tableID)
    {
        //get head name
        var headData = $('#' + tableID + ' thead tr th');
        var i = 0;
        var headName = [];
        $(headData).each(function()
        {
            headName.push(headData.eq(i++).text());
        }); 

        //get json data
        var dataByColumn = $('#' + tableID + ' tbody tr');
        var rowLen = dataByColumn.eq(0).children('td').length;

        var jsonData = [];
        for (var j = 0; j < dataByColumn.length; j++)
        {
            var str = '{';
            for (var k = 0; k < rowLen; k++)
            {
                str += '"' + headName[k] + '":"' + dataByColumn.eq(j).children('td').eq(k).text() + '"';
                if (k != rowLen - 1)
                    str += ',';
            }
            str += '}';
            eval('var json = ' + str);
            jsonData.push(json);

        }
        return jsonData;
        //console.log(jsonData);
    }
	$.ajax({
		url : "OverallSituation.show",
		type : "GET",
		
		success : function(resp)
		{
			$('body').append(resp);
			//$('table').dataTable();
			var jsonObj = html2json('overallsituation');
			var key = [];
			for (var j in jsonObj[0])
			{
				key.push(j);
				
			}
		    $("#output").pivotUI(jsonObj, {
		        renderers: $.extend(
		            $.pivotUtilities.renderers, 
		            $.pivotUtilities.gchart_renderers, 
		            $.pivotUtilities.d3_renderers
		            ),
		        cols: [key[0], key[1]], rows: [key[2], key[3], key[4]],
		        //cols: ["Count"], rows: ["System"],
		        rendererName: "Bar Chart"
		    });

		}
	});

});
