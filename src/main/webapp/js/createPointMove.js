	function createPoint(arr)
	{
		$('#svg-map').html('');
		var n=0;
		var timer=null;
		timer=setInterval(function(){
			var oC=document.createElementNS('http://www.w3.org/2000/svg','circle');
			$(oC).attr({
				'cx':Math.floor((arr[n][0]+14492)/1211*$('#map').width()/945),
				'cy':Math.floor((arr[n][1]-74620)/1220*$('#map').height()/630),
				'r':2,
				'fill':'green',
				'id':'oC'+n
			});
			$(oC).appendTo($('#svg-map'));
			n++;
			
			var count = 5;
			if(n>=count)
			{
				var id='#oC'+(n-count);
				$(id).remove();
			}
			if(n==arr.length)
			{
				clearInterval(timer);
			}
		},200)
	}