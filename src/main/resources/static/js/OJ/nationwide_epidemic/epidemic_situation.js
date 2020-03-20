$(function () {
    showecharts();
});

function showecharts() {
    $.get("http://api.tianapi.com/txapi/ncovcity/index?key=5d4fb67d8bd16c1fd371311e334a4bd0",
        function (data, status) {
            var arr = [];
            // 处理数据，成地图需要的数据结构
            data.newslist.forEach(item => {
                // 循环的过程中，向空数组中加入所需的内容
                arr.push({
                    name: item.provinceShortName, // name固定
                    value: item.confirmedCount, // value固定
                    zuixin: item.currentConfirmedCount, // 当前确诊
                    zhiyu: item.curedCount,
                    siwang: item.deadCount
                });
            });
            arr.push({
                name: '南海诸岛',
                value: '未统计',
                zuixin: '未统计',
                zhiyu: '未统计',
                siwang: '未统计'
            });
            var mapChart = echarts.init(document.getElementById("echarts-map-chart"));
            var mapoption = {
                title : {
                    text: '全国疫情统计',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: function (params) {
                        return `${params.data.name}<br/>
                               累计确诊：${params.data.value}<br/>
                               当前确诊：${params.data.zuixin}<br />
                               治愈人数：${params.data.zhiyu}<br />
                               死亡人数：${params.data.siwang}`;
                    }
                },
                dataRange: {
                    min: 0,
                    max: 2500,
                    x: 'left',
                    y: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                roamController: {
                    show: true,
                    x: 'right',
                    mapTypeControl: {
                        'china': true
                    }
                },
                visualMap: [{
                    type: 'piecewise',
                    pieces: [
                        { gt: 10000 },
                        { gt: 5000, lte: 10000 }, // 不指定 max，表示 max 为无限大（Infinity）。
                        { gt: 1000, lte: 5000 },
                        { gt: 500, lte: 1000 },
                        { gt: 100, lte: 500 },
                        { gt: 10, lte: 100 },
                        { gt: 0, lte: 10 }     // 不指定 min，表示 min 为无限大（-Infinity）。
                    ],

                    inRange: {
                        color: ['#ffcfc3', '#eda595', '#d27b64', '#d5462a', '#a8352e', '#6a211d', '#562a2a']
                    },
                    showLabel: {
                        show: true
                    }
                }],
                series : [
                    {
                        name: '疫情统计',
                        type: 'map',
                        map: 'china',
                        itemStyle:{
                            normal: {label:{show:true}},
                            emphasis: {label:{show:true}}
                        },
                        data:arr
                    },
                ]
            };
            mapChart.setOption(mapoption);
            $(window).resize(mapChart.resize);
        })
}