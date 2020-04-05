# 目录结构
- `dao` : 操作数据库的接口
- `entity`: 表对象，dao返回
- `service`: 服务层
- `dto`(可选): 中间层对象
- `controller`：控制层
- `vo`: 视图对象，controller返回
- `query` 查询对象，controller从前端接收

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



