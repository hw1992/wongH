<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>一个网页</title>
</head>
<body onload="startime()" background="/images/back.jpg">
    <center>
    <div id="div" style="border: 0px; display:inline; color:lawngreen;"></div>
    <script>
        function startime(){
            var t=new Date();//创建一个date的对象，才可以引用相关的date
            var year=t.getFullYear();
            var mon=t.getMonth();
            var day=t.getDate();
            var h=t.getHours();
            var m=t.getMinutes();
            var s=t.getSeconds();
            var ms=t.getMilliseconds();
            m=checktime(m);
            s=checktime(s);
            document.getElementById("div").innerHTML='公元 '+year+'-'+mon+'-'+day+'  '+h+':'+m+':'+s;
            t=setTimeout("startime()",1 );//注意一下函数的的参数的使用，第一个参数是要加上引号的，这里呢重新调用了原来的函数，实现了刷新的功能
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
        <p style="font-size:18px;color:white;">
            welcome dear user ${userId} to freemarker!
        </p>

        <p style="font-size:18px;color:white;">
            您的log操作是：
            <#if (operation = 15)>估计是登录
            <#elseif (operation < 15)> 我也不知道是啥
            <#else> 我真不知道
            </#if>
            ，结果
            <#if (result = 1)>估计成功了？
            <#else> 可能失败了
            </#if>
        </p>
    </center>
</body>
</html>