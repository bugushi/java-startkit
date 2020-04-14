# 目录结构
model层
- `dao` : 操作数据库的接口
- `entity`: 表对象，dao返回

服务层
- `service`: 服务层
- `dto` 服务层接收对象
- `bo` 服务层返回对象

控制层
- `controller`：控制层
- `query` 查询对象，controller从前端接收
- `vo`: 视图对象，controller返回

# 本地环境
### Idea中spring-boot-devtools开启live reload
当我们修改了Java类后，IDEA默认是不自动编译的，而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用，所以需要设置IDEA的自动编译：
- Settings -> Compiler -> Build Project automatically
- 按 `command + option + shift + /` -> Registry -> 勾选`compiler.automake.allow.when.app.running`

### swagger
用于生成API文档，涉及代码中所有`@Api`开头的注解

本地文档地址：http://localhost:8080/doc.html

### mysql 
- 安装：`brew install mysql`
- 启动：`mysql.server start`
- 进入：`mysql -u root -p`
- 查看版本：`select version();`

### redis

- 安装：`brew install redis`
- 启动：`/usr/local/Cellar/redis/{VERSION}/bin/redis-server`

# 约定
### Restful
方法
- GET: 获取某个资源，GET操作应该是幂等（idempotence）的，且无副作用。
- POST: 创建一个新的资源。
- PUT: 修改某个已有的资源。PUT操作虽然有副作用，但其应该是幂等的。
- DELETE：删除某个资源。DELETE操作有副作用，但也是幂等的。

状态码






