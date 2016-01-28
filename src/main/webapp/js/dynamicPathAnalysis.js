window.timer = null;
var rate = 1.6;
//var mapw=0;
//var maph=0;
var bFlag=true;
function createPointMove(arr, buildingNo) {
	$('#svg-map').html('');
	if(arr == null || arr.length == 0)
		return;
	
	var n = 0;
	timer = setInterval(function() {
		bFlag=false;
		var oC = document.createElementNS('http://www.w3.org/2000/svg',
				'circle');
		var coordinate = convertPoint(buildingNo, arr[n][0], arr[n][1]);
		var color;
		if (arr[n][2] == 1) {
			color = 'green';
		} else {
			color = 'yellow';
		}

		$(oC).attr({
			'cx' : coordinate.x,
			'cy' : coordinate.y,
			'r' : 3,
			'fill' : color,
			'id' : 'oC' + n
		});
		$(oC).appendTo($('#svg-map'));
		var arr1 = caculatePosition(coordinate.x,coordinate.y);

		reposition(arr1[0], arr1[1]);
		n++;
		var count = 5;
		if (n >= count) {
			var id = '#oC' + (n - count);
			$(id).remove();
		}
		if (n >= arr.length) {
			var m = 0;
			var timer2 = setInterval(function() {
				$('#svg-map circle').eq(0).remove();
				m++;
				if (m == count - 2) {
					$('#svg-map circle').eq(0).delay(2000).attr({
						'r' : 6,
						'fill' : 'red'
					});
					clearInterval(timer2);
				}
			}, 200);
			bFlag=true;
			clearInterval(timer);
			// bFlag = true;
		}
	}, 200)
}
// 传入新计算出的点x/y，计算新的svg-map位置;
function caculatePosition(x, y) {
	var a = $('#map').width();
	var b = $('#map').height();
	var w = $('#svg-map').width();
	var h = $('#svg-map').height();
	var maxLeft = w - a;
	var maxTop = h - b;

	var newLeft = x - a / 2;
	var newTop = y - b / 2;

	if (newLeft > maxLeft)
		newLeft = maxLeft;
	else if (newLeft < 0)
		newLeft = 0;

	if (newTop > maxTop)
		newTop = maxTop;
	else if (newTop < 0)
		newTop = 0;

	return [ newLeft, newTop ];
}

function reposition(newLeft, newTop) {
	var left = Math.abs($('#svg-map').position().left);
	var top = Math.abs($('#svg-map').position().top);
	// 对比newLeft与当前的left;对比newTop与当前的top
	if (newLeft != left || newTop != top) {
		$('#svg-map').css({
			'left' : -newLeft,
			'top' : -newTop
		});
	}
}
function plus() {
	if(!bFlag)
		return;
	
		svgMapW = rate * svgMapW;
		svgMapH = rate * svgMapH;
		var left = $('#svg-map').position().left * rate;
		var top = $('#svg-map').position().top * rate;
		if (left > 0) {
			left = 0;
		} else if (left < svgMapInitW - svgMapW) {
			left = svgMapInitW - svgMapW;
		}
		if (top > 0)
			{top = 0;}
		else if (top < svgMapInitH - svgMapH)
			top = svgMapInitH - svgMapH;
		if (svgMapW >= svgMapMaxW) {
			svgMapW = svgMapMaxW;
			left = left / rate;
		}

		if (svgMapH >= svgMapMaxH) {
			svgMapH = svgMapMaxH;
			top = top / rate;
		}

		$('#svg-map').css({
			'width' : svgMapW,
			'height' : svgMapH,
			'left' : left,
			'top' : top
		});
	
}
function minus() {
	//console.log('minus');
	if(bFlag)
	{
		svgMapW = svgMapW / rate;
		svgMapH = svgMapH / rate;
		//console.log('wwww=='+svgMapW+'hhhhh=='+svgMapH);
		var left = $('#svg-map').position().left;
		var top = $('#svg-map').position().top;
		//console.log(svgMapW);
		if (left > 0) {
			left = 0;
		} else if (left < svgMapInitW - svgMapW) {
			left = svgMapInitW - svgMapW;
		}
		if (top > 0)
			{top = 0;}
		else if (top < svgMapInitH - svgMapH)
			top = svgMapInitH - svgMapH;
		if (svgMapW >= svgMapMaxW) {
			svgMapW = svgMapMaxW;
			left = left / rate;
		}
		//console.log('svgMapW=='+Math.floor(svgMapW)+'svgMapInitW=='+Math.floor(svgMapInitW));
		if (Math.floor(svgMapW) <= Math.floor(svgMapInitW)) {
			//console.log('小于');
			svgMapW = svgMapInitW;
			left=0;
		}else{
			//console.log('大于');
			left = left / rate;
		}

		if (Math.floor(svgMapH) <= Math.floor(svgMapInitH)) {
			svgMapH = svgMapInitH;
			top=0;
		}else{
			top = top /rate;
		}

		$('#svg-map').css({
			'width' : svgMapW,
			'height' : svgMapH,
			'left' : left,
			'top' : top
		});
	//console.log('left=='+left+'top=='+top);
	}
}

function drag(obj)
{
	var objW=obj.width();
	var objH=obj.height();
	if(objW>svgMapInitW && objH>svgMapInitH){
		obj.mousedown(function(ev) {
		var disX = ev.clientX - obj.position().left;
		var disY = ev.clientY - obj.position().top;
		function move(ev) {
			var left = ev.clientX - disX;
			var top = ev.clientY - disY;
			//console.log(ev.clientX);
			if (left > 0) {
				left = 0;
			} else if (left < svgMapInitW - objW) {
				left = svgMapInitW - objW;
			}
			if (top > 0)
				{top = 0;}
			else if (top < svgMapInitH - objH)
				top = svgMapInitH - objH;
			//console.log('left==='+left+'top==='+top);
			//console.log('left=='+left+'top=='+top);
			obj.css({
				left : left + 'px',
				top : top + 'px'
			});
			//console.log('left=='+left+'%%%top=='+top);
		}

		function up(ev) {
			$(document).unbind('mousemove', move);
			$(document).unbind('mouseup', up);
		}
		$(document).bind('mousemove', move);
		$(document).bind('mouseup', up);
		// return false;
		ev.preventDefault();
	})}
}
/*$.fn.drag = function() {
	this.each(function() {
		var _this = $(this);
		$(this).mousedown(function(ev) {
			var disX = ev.clientX - _this.position().left;
			var disY = ev.clientY - _this.position().top;
			function move(ev) {
				var left = ev.clientX - disX;
				var top = ev.clientY - disY;
				if (left > 0) {
					left = 0;
				} else if (left < 945 - _this.width()) {
					left = 945 - _this.width();
				}
				if (top > 0)
					top = 0;
				else if (top < 630 - _this.height())
					top = 630 - _this.height();
				//console.log('left==='+left+'top==='+top);
				_this.css({
					left : left + 'px',
					top : top + 'px'
				});
			}

			function up(ev) {
				$(document).unbind('mousemove', move);
				$(document).unbind('mouseup', up);
			}
			$(document).bind('mousemove', move);
			$(document).bind('mouseup', up);
			// return false;
			ev.preventDefault();
		})
	});
}*/

$.fn.wheelEvent = function(fn) {
	this.each(function() {
		_wheel(this);
	});
	function _wheel(obj) {
		if (window.navigator.userAgent.toLowerCase().indexOf('firefox') != -1) {
			obj.addEventListener('DOMMouseScroll', function(ev) {
				var down = ev.detail > 0 ? true : false;
				fn.call(this, down);
			}, false);
		} else {
			obj.onmousewheel = function(ev) {
				var oEvent = ev || event;
				var down = oEvent.wheelDelta > 0 ? false : true;
				fn.call(this, down);
			};
		}
	}
};
