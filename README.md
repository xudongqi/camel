### slb - soft load balance

## 七层软负载一体解决方案以界面和api的方式对nginx集群进行管理
在点评内部目前每天执行数千次部署任务

## 环境要求：
slb-admin模块：jdk6
slb-agent模块：jdk7
maven 3.2以上
MongoDb
MySQL

## 安装步骤：
slb由两部分组成：slb管理端、nginx负载端。其中nginx负载端需要安装Dengine及slb-agent两个模块

# slb管理端：
1. 安装MongoDb, Maven, MySQL
2. 初始化MySQL数据库，使用slb-admin/src/main/resources/init-data/create_table.sql
3. 配置数据库连接信息：
sample：src/main/resources/init-data/jdbc-mysql.properties及slb.properties
配置到：/data/appdatas/slb/jdbc-mysql.properties及slb.properties
4. 启动：mvn jetty:run -Djetty.port=8080
5. 登陆网站配置按照页面提示配置初始数据

# nginx负载端：
slb-agent采用spring-boot，需要在jdk1.7及以上的环境运行
拷贝slb-agent/src/main/resources/script/nginx至/etc/init.d/nginx
以sudo身份运行，请确保sudoer中配置为NOPASSWORD：
mvn spring-boot:run
或者打jar包，java -jar slb-agent-0.1.0.jar

Dengine：
安装 sudo ./install_dengine
更改nginx_status.conf，将管理端ip地址配置到所有的allow字段

# 常见问题：
maven compile时找不到unidal或者xwork的依赖？
slb使用了unidal codegen插件。该插件已部署在maven中心仓库中，但不一定部署在其它maven仓库里(如开源中国OSChina)。为此，slb内置了依赖仓库和插件仓库，在项目内部寻找依赖。
如果你在maven配置settings.xml中设置了全局镜像<mirrorOf>*</mirrorOf>，则内置仓库会失效。此时请把镜像的匹配范围更改至某个仓库。