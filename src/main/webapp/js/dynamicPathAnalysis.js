function createPointMove(arr) {
	$('#svg-map').html('');
	var n = 0;
	timer = setInterval(function() {
		var oC = document.createElementNS('http://www.w3.org/2000/svg',
				'circle');
		var x1 = Math.floor((arr[n][0] + 14492) / 1211 * $('#svg-map').width()
				/ 945);
		var y1 = Math.floor((arr[n][1] - 74620) / 1220 * $('#svg-map').height()
				/ 630);
		$(oC).attr({
			'cx' : x1,
			'cy' : y1,
			'r' : 3,
			'fill' : 'green',
			'id' : 'oC' + n
		});
		$(oC).appendTo($('#svg-map'));
		var arr1 = caculatePosition(x1, y1);
		reposition(arr1[0], arr1[1]);
		n++;
		var count = 5;
		if (n >= count) {
			var id = '#oC' + (n - count);
			$(id).remove();
		}
		if (n == arr.length) {
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
			}, 200)
			clearInterval(timer);
			bFlag = true;
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
	svgMapW = 1.2 * svgMapW;
	svgMapH = 1.2 * svgMapH;
	if (svgMapW < svgMapInitW)
		svgMapW = svgMapInitW;
	else if (svgMapW > 9000)
		svgMapW = 9000;
	if (svgMapH < svgMapInitH)
		svgMapH = svgMapInitH;
	else if (svgMapH > 6000)
		svgMapH = 6000;
	$('#svg-map').css({
		'width' : svgMapW,
		'height' : svgMapH,
		'left' : Math.floor($('#svg-map').position().left * 1.2),
		'top' : Math.floor($('#svg-map').position().top * 1.2)
	});
}
function minus() {
	svgMapW = svgMapW / 1.2;
	svgMapH = svgMapH / 1.2;
	if (svgMapW < svgMapInitW)
		svgMapW = svgMapInitW;
	else if (svgMapW > 9000)
		svgMapW = 9000;
	if (svgMapH < svgMapInitH)
		svgMapH = svgMapInitH;
	else if (svgMapH > 6000)
		svgMapH = 6000;
	$('#svg-map').css({
		'width' : svgMapW,
		'height' : svgMapH,
		'left' : Math.floor($('#svg-map').position().left / 1.2),
		'top' : Math.floor($('#svg-map').position().top / 1.2)
	});
}

$.fn.drag = function() {
	this.each(function(){
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
		})
	});
}

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
