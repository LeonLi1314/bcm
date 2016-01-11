function column(arrName,arrData,sum)
{
	 var  categories = arrName,
	        name = ' ',
	        data = arrData;
	    function setChart(name, categories, data, color) {
		chart.xAxis[0].setCategories(categories, false);
		chart.series[0].remove(false);
		chart.addSeries({
			name: name,
			data: data,
			color: color || 'white'
		}, false);
		chart.redraw();
	    }

	    var chart = $('#graph').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: ' '
	        },
	        subtitle: {
	            text: ' '
	        },
	        xAxis: {
	            categories: categories
	        },
	        yAxis: {
	        	labels: {
	                format: '{value}%',
	                style: {
	                    color: '#89A54E'
	                }
	            },
	            title: {
	                text: ' '
	            }
	        },
	        plotOptions: {
	            column: {
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    style: {
	                        fontWeight: 'bold'
	                    },
	                    formatter: function() {
	                        return this.y +'%';
	                    }
	                }
	            }
	        },
	        tooltip: {
	            formatter: function() {
	                var point = this.point,
	                    s = this.x +':<b>'+ Math.round(this.y *sum/100)+'人';
	                return s;
	            }
	        },
	        series: [{
	            name: name,
	            data: data,
	            color: 'white'
	        }],
	        exporting: {
	            enabled: false
	        }
	    })
}