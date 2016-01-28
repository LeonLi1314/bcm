
function createPoint(arr, isPair, buildingNo, blink) {
	$('#svg-map').empty();
	// isPair = isPair || true;
	//console.log(isPair);

	for (var i = 0; i < arr.length; i++) {
		if (arr[i][0] == 0 && arr[i][1] == 0)
			continue;
		//console.log(1);
		var oC = document.createElementNS('http://www.w3.org/2000/svg',
				'circle');
		var color;

		if (isPair) {
			if (i == 0) {
				color = 'green';
			} else {
				color = 'red';
			}
		} else {
			color = 'green'
		}
		
		var coordinate =convertPoint(buildingNo, arr[i][0], arr[i][1]);
		console.log(coordinate);
		$(oC).attr(
				{
					'cx' : coordinate.x,
					'cy' : coordinate.y,
					'r' : 3,
					'fill' : color
				});
		$(oC).appendTo($('#svg-map'));
		
		if(blink)
		{
			$(oC).css('display', 'block');
			show($(oC));
		}
	}
}
function show(oC) {
	if (oC.css('display') == 'block') {
		oC.css('display', 'none');
	} else {
		oC.css('display', 'block');
	}
	setTimeout(function() {
		show($(oC));
	}, 300);
}

