<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据统计</title>
    <script type="text/javascript" src="/Highcharts-6.0.4/code/highcharts.js"></script>
    <script type="text/javascript" src="/Highcharts-6.0.4/code/highcharts-3d.src.js"></script>
    <script type="text/javascript" src="/Highcharts-6.0.4/code/modules/exporting.js"></script>

    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/js/china.js"></script>
</head>

<body onload="startime()" background="/images/datebook.jpg">
    <div align="center" style="height:30px;border: 0px;">
        <div id="div" style="border: 0px; display:inline; color:#112200;"></div>
    </div>
    <script>
            function startime(){
                var t=new Date();//创建一个date的对象，才可以引用相关的date
                var year=t.getFullYear();
                var mon=t.getMonth()+1;
                var day=t.getDate();
                var h=t.getHours();
                var m=t.getMinutes();
                var s=t.getSeconds();
                var ms=t.getMilliseconds();
                mon=checktime(mon);
                day=checktime(day);
                m=checktime(m);
                s=checktime(s);
                document.getElementById("div").innerHTML='公元 '+year+'-'+mon+'-'+day+'  '+h+':'+m+':'+s;
                t=setTimeout("startime()",100 );//注意一下函数的的参数的使用，第一个参数是要加上引号的，这里呢重新调用了原来的函数，实现了刷新的功能
                //第二个参数是表示刷新的时间，500表示的是0.5秒
            }
            function checktime(i) {//这里只是为了美观一点  ，在个位数的面前，将数字的显示+一个0
                if(i<10)
                {
                    i="0"+i;
                }
                return i;
            }
    </script>
    <div align="center">
        <div id="china" style="position: relative;margin-bottom:10px;width: 90%;height:500px;display: inline-block;" ></div>
        <div id="sliders" align="right" style="position: absolute;z-index:99;top:40px;left:110px;">
            <table>
                <tr>
                    <td><font color="#8ECCA1">α 角（内旋转角）</font></td>
                    <td><input id="alpha" type="range" min="0" max="45" value="15"/> <span id="alpha-value" class="value"></span></td>
                </tr>
                <tr>
                    <td><font color="#8ECCA1">β 角（外旋转角）</font></td>
                    <td><input id="beta" type="range" min="-45" max="45" value="15"/> <span id="beta-value" class="value"></span></td>
                </tr>
                <tr>
                    <td><font color="#8ECCA1">深度</font></td>
                    <td><input id="depth" type="range" min="20" max="100" value="50"/> <span id="depth-value" class="value"></span></td>
                </tr>
            </table>
        </div>
    </div>
    <div align="center">
        <div align="left">
            <div id="line" style="margin-bottom:10px;width: 700px;height:400px;display: inline-block;" ></div>
        </div>
        <div align="right">
            <div id="pie" style="width: 600px;height:400px;display: inline-block;"></div>
        </div>
    </div>

    <script type="text/javascript">
        $(function(){
            var xtext = [];//X轴TEXT
            var nums = [];//y轴数量
            $.ajax({
                url:'/userLog/loginCnt',//请求数据的地址
                type : "post",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                data : {},
                dataType : "json",        //返回数据形式为json
                success:function(result){
                    for(var i=0;i<result.length;i++){
                        xtext.push(result[i].userId);
                        nums.push(result[i].countNum);
                    }
                    chart.series[0].setData(nums);
                },
                error:function(e){
                }
            });
            var chart = new Highcharts.Chart({
                chart:{
                    renderTo:'china',//div的id值
                    type:'column', //显示类型 柱形
                    margin: 75,
                    options3d: {
                        enabled: true,
                        alpha: 10,
                        beta: 25,
                        depth: 70,
                        viewDistance: 100,      // 视图距离，它对于计算角度影响在柱图和散列图非常重要。此值不能用于3D的饼图
                        frame: {                // Frame框架，3D图包含柱的面板，我们以X ,Y，Z的坐标系来理解，X轴与 Z轴所形成
                            // 的面为bottom，Y轴与Z轴所形成的面为side，X轴与Y轴所形成的面为back，bottom、
                            // side、back的属性一样，其中size为感官理解的厚度，color为面板颜色
                            bottom: {
                                size: 10,
                                color: '#8ECCA1'
                            },
                            side: {
                                size: 1,
                                color: 'transparent'
                            },
                            back: {
                                size: 1,
                                color: 'transparent'
                            }
                        }
                    },
                },
                title:{
                    text:'今日登录用户次数' //图表的标题
                },
                plotOptions: {
                    column: {
                        depth: 25
                    }
                },
                xAxis:{
                    categories:xtext
                },
                yAxis:{
                    title:{
                        text:'数量' //Y轴的名称
                    },
                },
                plotOptions: {
                    column: {
                        dataLabels: {
                            enabled: true          // 开启数据标签
                        },
                        enableMouseTracking: true // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    }
                },
                series:[{
                    name:"数量"
                }],
                credits: {//去掉版权
                    enabled: false
                }
            });
            function showValues() {
                $('#alpha-value').html(chart.options.chart.options3d.alpha);
                $('#beta-value').html(chart.options.chart.options3d.beta);
                $('#depth-value').html(chart.options.chart.options3d.depth);
            }
            // Activate the sliders
            $('#sliders input').on('input change', function () {
                chart.options.chart.options3d[this.id] = this.value;
                showValues();
                chart.redraw(false);
            });
            showValues();
        });
    </script>

    <script type="text/javascript">
        $(function(){
            var xtext = [];//X轴TEXT
            var nums = [];//y轴数量
            $.ajax({
                url:'/userLog/detail',//请求数据的地址
                type : "post",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                data : {},
                dataType : "json",        //返回数据形式为json
                success:function(result){
                    for(var i=0;i<result.length;i++){
                        xtext.push(result[i].userId);
                        nums.push(result[i].operation);
                    }
                    chart.series[0].setData(nums);
                },
                error:function(e){
                }
            });
            var chart = new Highcharts.Chart({
                chart:{
                    renderTo:'line',//div的id值
                    type:'line' //显示类型 柱形
                },
                title:{
                    text:'用户操作记录' //图表的标题
                },
                xAxis:{
                    categories:xtext
                },
                yAxis:{
                    title:{
                        text:'操作' //Y轴的名称
                    },
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true          // 开启数据标签
                        },
                        enableMouseTracking: true // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    }
                },
                series:[{
                    name:"操作"
                }],
                credits: {//去掉版权
                    enabled: false
                }
            });
        });
    </script>
</body>
</html>