function convertXPoint(buildingNo, xPoint) {
	buildingNo = buildingNo.toUpperCase();
	if (buildingNo == "T3E") {
		return Math.floor((xPoint - 14337) * $('#svg-map').width()/1061313);
	} else if (buildingNo == "T3C") {
		return Math.floor((xPoint + 14492)  * $('#svg-map').width()/ 1144395);
	}
	
	return 0;
}

function convertYPoint(buildingNo, yPoint) {
	buildingNo = buildingNo.toUpperCase();
	var y=Math.abs(yPoint);
	if (buildingNo == "T3E") {
		return Math.floor((y - 22536) * $('#svg-map').height()	/ 848000);
	} else if (buildingNo == "T3C") {
		return Math.floor((y - 74620) * $('#svg-map').height()/ 768600);
	}
	
	return 0;
}

function convertPoint(buildingNo, xPoint, yPoint) {
	var coordinate = {};
	buildingNo = buildingNo.toUpperCase();
	var y=Math.abs(yPoint);
	if (buildingNo == "T3E") {
		coordinate.x = Math.floor((xPoint - 14337) * $('#svg-map').width()/1061313);
		coordinate.y = Math.floor(( y- 22536) * $('#svg-map').height()/ 848000);
	} else if (buildingNo == "T3C") {
		coordinate.x = Math.floor((xPoint + 14492)  * $('#svg-map').width()/ 1144395);
		coordinate.y = Math.floor((y - 74620) * $('#svg-map').height() / 768600);
	}
	
	return coordinate;
}

function backgroudChage(buildingNo, type){
	buildingNo = buildingNo.toUpperCase();
	
	if("small.jpg" == type){
		if (buildingNo == "T3C") {
			changeImage('T3C-F3_small.jpg',1.5);
		} else if (buildingNo == "T3E") {
			changeImage('T3E-F2_small.jpg',1.25);
		} else {
			return;
		}
	}else if("small.png" == type){
		if (buildingNo == "T3C") {
			changeImage('T3C-F3_small.png',1.5);
		} else if (buildingNo == "T3E") {
			changeImage('T3E-F2_small.png',1.25);

		} else {
			return;
		}
	}else if("big.jpg" == type){
		if (buildingNo == "T3C") {
			changeImage('T3C-F3_big.jpg',1.5);
		} else if (buildingNo == "T3E") {
			changeImage('T3E-F2_small.jpg',1.25);
		} else {
			return;
		}
	}else{
		if (buildingNo == "T3C") {
			changeImage('T3C-F3.jpg',1.5);
		} else if (buildingNo == "T3E") {
			changeImage('T3E-F2_small.jpg',1.25);
		} else {
			return;
		}
	}
}

function changeImage(strImage,ratio)
{
	$('#svg-map').css({'background':' url(../img/'+strImage+') no-repeat','backgroundSize':'cover'});
	var svgmapH=$('#svg-map').height();
	var maph=$('#map').height();
	var mapw=maph*ratio;
	$('#map').css({'width':mapw});
	$('#svg-map').css({'width':ratio*svgmapH});
	svgMapInitW = mapw;
	svgMapMaxW = mapw * Math.pow(1.6, 5);
	svgMapInitH = maph;
	svgMapMaxH = maph * Math.pow(1.6, 5);
	svgMapW = mapw;
	svgMapH = maph;
}