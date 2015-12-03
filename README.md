## slb-open-source

七层软负一体解决方案
手工部署
api部署(大部分是更改upstream)：增删改查
每天数千次

安装步骤：
admin：
配置文件
sample：src/main/resources/init-data/ 下 jdbc-mysql.properties  slb.properties
配置到：/data/appdatas/slb/
log：/data/applogs/slb/
mvn jetty:run -Djetty.port=8080

agent：

nginx：
安装 sudo ./install_dengine
更改nginx_status.conf


常见问题：
maven compile时找不到unidal或者xwork的依赖？
slb使用了unidal codegen插件。该插件已部署在maven中心仓库中，但不一定部署在其它maven仓库里(如开源中国OSChina)。为此，slb内置了依赖仓库和插件仓库，在项目内部寻找依赖。
如果你在maven配置settings.xml中设置了全局镜像<mirrorOf>*</mirrorOf>，则内置仓库会失效。此时请把镜像的匹配范围更改至某个仓库。