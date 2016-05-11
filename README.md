Camel - a soft load balance midware
================

> [github.com/leonindy/camel](https://github.com/leonindy/camel)

![logo](https://raw.githubusercontent.com/leonindy/camel/master/camel-admin/src/main/webapp/assets/images/camel_logo_blue.png)

[Camel](https://github.com/leonindy/camel) 是大众点评开发的软负载一体解决方案，承担了F5硬负载层后的软负载工作。Camel已成为大众点评网络流量中必不可缺的一层。

![logo](https://raw.githubusercontent.com/leonindy/camel/master/camel-admin/src/main/webapp/assets/images/whole_picture.png)

Camel在大众点评的应用规模如下：

1. nginx服务器：

	15个nginx集群，共63台nginx服务器

2. 站点及业务集群：

	356个域名站点，857个业务服务器集群

3. qps：

	每天响应约1.5万次接口调用，其中约3000次部署请求


Camel项目由camel-admin, Dengine(基于Tengine开发的Web服务器), camel-agent三个模块组成。