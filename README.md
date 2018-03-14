[wongH Blog](http://blog.csdn.net/wangh92) springboot modules
==== 
建表语句在项目中的  数据表.sql  数据自己插一点  第一个数据的id改成 123 吧
-------  
先安装好mysql和redis  并启动   redis缓存  logback日志
-------  
集成 elasticsearch搜索  自己先安装好并启动  具体数据存储的位置配置文件里自己设置
-------  
<br>elasticsearch搜索接口的命名是遵循规范的。常用命名规则如下：
<br>关键字                     方法命名
<br>**And**              `findByNameAndPwd`
<br>**Or**               `findByNameOrSex`
<br>**Is**               `findById`
<br>**Between**         `findByIdBetween`
<br>**Like**            `findByNameLike`
<br>**NotLike**         `findByNameNotLike`
<br>**OrderBy**         `findByIdOrderByXDesc`
<br>**Not**             `findByNameNot`
<br>**模糊查询**        `findByName1ContainingOrName2Containing`

<br>redis缓存使用demo ：   UserLogServiceImpl 
<br>elasticsearch搜索使用demo ：  HelloController  

使用highcharts
-------  
