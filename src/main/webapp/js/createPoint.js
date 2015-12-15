function createPoint(arr) {
	$('#svg-map').html('');

	setTimeout(function() {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i][0] == 0 && arr[i][1] == 0)
				continue;

			var oC = document.createElementNS('http://www.w3.org/2000/svg',
					'circle');
			var color;
			if (i == 0) {
				color = 'green';
			} else {
				color = 'red';
			}

			$(oC).attr(
					{
						'cx' : Math.floor((arr[i][0] + 14492) / 1211
								* $('#map').width() / 945),
						'cy' : Math.floor((Math.abs(arr[i][1]) - 74620) / 1220
								* $('#map').height() / 630),
						'r' : 5,
						'fill' : color
					});
			$(oC).appendTo($('#svg-map'));
		}
	}, 200)
}