function createPoint(arr)
{
	$('#svg-map').html('');
	
	setTimeout(function(){
		for(var i=0; i<arr.length; i++)
		{
			var oC=document.createElementNS('http://www.w3.org/2000/svg','circle');
			$(oC).attr({
				'cx':Math.floor((arr[i][0]+14492)/1211*$('#map').width()/945),
				'cy':Math.floor((Math.abs(arr[i][1])-74620)/1220*$('#map').height()/630),
				'r':5,
				'fill':'red'
			});
			$(oC).appendTo($('#svg-map'));
		}
	},200)
}