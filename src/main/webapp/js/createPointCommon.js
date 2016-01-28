function convertXPoint(buildingNo, xPoint) {
	buildingNo = buildingNo.toUpperCase();
	if (buildingNo == "T3E") {
		return Math.floor((xPoint - 15225) * $('#svg-map').width()/1058400);
	} else if (buildingNo == "T3C") {
		return Math.floor((xPoint + 14492)  * $('#svg-map').width()/ 1144395);
	}
	
	return 0;
}

function convertXPoint(buildingNo, yPoint) {
	buildingNo = buildingNo.toUpperCase();
	if (buildingNo == "T3E") {
		return Math.floor((yPoint + 24468) * $('#svg-map').height()	/ 880800);
	} else if (buildingNo == "T3C") {
		return Math.floor((yPoint - 74620) * $('#svg-map').height()/ 768600);
	}
	
	return 0;
}

function convertPoint(buildingNo, xPoint, yPoint) {
	var coordinate = {};
	buildingNo = buildingNo.toUpperCase();
	if (buildingNo == "T3E") {
		coordinate.x = Math.floor((xPoint - 15225) * $('#svg-map').width()/1058400);
		coordinate.y = Math.floor((Math.abs(yPoint) + 24468) * $('#svg-map').height()	/ 880800);
	} else if (buildingNo == "T3C") {
		coordinate.x = Math.floor((xPoint + 14492)  * $('#svg-map').width()/ 1144395);
		coordinate.y = Math.floor((Math.abs(yPoint) - 74620) * $('#svg-map').height()/ 768600);
	}
	
	return coordinate;
}

function backgroudChage(buildingNo, type){
	buildingNo = buildingNo.toUpperCase();
	
	if("small.jpg" == type){
		if (buildingNo == "T3C") {
			$('#svg-map').css({'background':' url(../img/T3C-F3_small.jpg) no-repeat','backgroundSize':'cover'});
		} else if (buildingNo == "T3E") {
			$('#svg-map').css({'background':'url(../img/T3E-F2_small.jpg) no-repeat','backgroundSize':'cover'});
		} else {
			return;
		}
	}else if("small.png" == type){
		if (buildingNo == "T3C") {
			$('#svg-map').css({'background':' url(../img/T3C-F3_small.png) no-repeat','backgroundSize':'cover'});
		} else if (buildingNo == "T3E") {
			$('#svg-map').css({'background':' url(../img/T3E-F2_small.png) no-repeat','backgroundSize':'cover'});
		} else {
			return;
		}
	}else if("big.jpg" == type){
		if (buildingNo == "T3C") {
			$('#svg-map').css({'background:':'url(../img/T3C-F3_big.jpg) no-repeat','backgroundSize':'cover'});
		} else if (buildingNo == "T3E") {
			$('#svg-map').css({'background':'url(../img/T3E-F2_big.jpg) no-repeat','backgroundSize':'cover'});
		} else {
			return;
		}
	}else{
		if (buildingNo == "T3C") {
			$('#svg-map').css({'background':' url(../img/T3C-F3.jpg) no-repeat','backgroundSize':'cover'});
		} else if (buildingNo == "T3E") {
			$('#svg-map').css({'background':'url(../img/T3E-F2.jpg) no-repeat','backgroundSize':'cover'});
		} else {
			return;
		}
	}
}