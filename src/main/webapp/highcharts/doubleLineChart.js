 function lineChart(arrName,arrFirstValue,arrSecondValue){
	 $('#graph').highcharts({
	    chart: {
	        zoomType: 'xy'
	    },
	    title: {
	        text: ' '
	    },
	    xAxis: [{
	        categories: arrName
	    }],
	    yAxis: [{  
	        labels: {
	            format: '{value}人',
	            style: {
	                color: '#89A54E'
	            }
	        },
	        title: {
	            text: '总人数',
	            style: {
	                color: '#89A54E'
	            }
	        }
	    }, {  
	        title: {
	            text: '路程',
	            style: {
	                color: '#4572A7'
	            }
	        },
	        labels: {
	            format: '{value} m',
	            style: {
	                color: '#4572A7'
	            }
	        },
	        opposite: true
	    }],
	    tooltip: {
	        shared: true
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'left',
	        x: 120,
	        verticalAlign: 'top',
	        y: 100,
	        floating: true,
	        backgroundColor: '#FFFFFF'
	    },
	    series: [{
	        name: '路程',
	        color: '#4572A7',
	        type: 'spline',
	        yAxis: 1,
	        data:arrSecondValue,
	        tooltip: {
	            valueSuffix: 'm'
	        }

	    }, {
	        name: '人数',
	        color: '#89A54E',
	        type: 'spline',
	        data: arrFirstValue,
	        tooltip: {
	            valueSuffix: '人'
	        }
	    }]
	});
}