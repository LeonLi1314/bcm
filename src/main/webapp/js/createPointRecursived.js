function createPoint(arr, isPair) {
	$('#svg-map').html('');

	for (var i = 0; i < arr.length; i++) {
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
		$(oC).attr(
				{
					'cx' : Math.floor((arr[i][0] + 14492) / 1211
							* $('#map').width() / 945),
					'cy' : Math.floor((Math.abs(arr[i][1] )- 74620) / 1220
							* $('#map').height() / 630),
					'r' : 5,
					'fill' : color
				});
		$(oC).css('display', 'block');
		$(oC).appendTo($('#svg-map'));
		show($(oC));
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
