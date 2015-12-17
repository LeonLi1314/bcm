function createDpie(data,firstDimensionArr,elementId )
{
	elementId=elementId || '#graph';
    var categories = firstDimensionArr,
        name = ' ',
        data = data;
    var browserData = [];
    var versionsData = [];
    for (var i = 0; i < data.length; i++) {
        browserData.push({
            name: categories[i],
            y: data[i].y,
            color: data[i].color
        });
        for (var j = 0; j < data[i].drilldown.data.length; j++) {
            var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
            versionsData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
                color: Highcharts.Color(data[i].color).brighten(brightness).get()
            });
        }
    }
    $(elementId).highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: ' '
        },
        yAxis: {
            title: {
                text: ' '
            }
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        series: [{
            name: '人数',
            data: browserData,
            size: '60%',
            dataLabels: {
                formatter: function() {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -30
            }
        }, {
            name: '人数',
            data: versionsData,
            size: '80%',
            innerSize: '60%',
            dataLabels: {
                formatter: function() {
                    return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'人'  : null;
                }
            }
        }]
    });
}