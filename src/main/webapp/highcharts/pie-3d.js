function createPie(newArr,elementId )
{
	elementId=elementId || '#graph';
	$(elementId).highcharts({                  
	    chart: {
	        type: 'pie',                        
	         options3d: {
	            enabled: true,
	            alpha: 50,
	            sbeta: 0
	        }
	    },
	    title: {
	        text: ''
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	     plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            depth: 50,
	            dataLabels: {
	                enabled: true,
	                format: '{point.name}'
	            }
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: '比例',
	        data: newArr}]
  });
}