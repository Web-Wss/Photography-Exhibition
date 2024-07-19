# 摄影作品展示平台

### 文件说明

- photography：项目前端文件目录
- photography_api：项目后端文件目录
- photography.sql：数据库文件

### 项目简介

摄影作品展示平台是一个基于Vue3+Pinia+SpringBoot+MySQL开发的作品展示系统

用户可以浏览平台作品

管理员可以管理相关信息

### 技术栈

- 前端：Vue3+TS+Pinia
- 后端：SpringBoot+MyBatis+MyBatisPlus+MySQL

### 如何运行

- 克隆该仓库到本地
- 使用IDE打开项目
- 后端配置QiniuUtilsHC文件

```java
    //访问授权码
    public  static String accessKey = "";
    //秘密钥匙
    public  static String secretKey = "";
    //空间名称
    public  static String bucket = "";
    //外链域名
    public static String domain = "";
```

- 后端配置数据库连接：创建application.yml文件，配置信息

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/photography
    username: username
    password: password
  servlet:
    multipart:
      enabled: true
      max-file-size: -1
      max-request-size: -1
mybatis:
  type-aliases-package: com.example.photography_api.model
mybatis-plus:
  type-aliases-package: com.example.photography_api.model
  mapper-locations: classpath:Mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true
```

- 运行后端项目：端口：8080
- 运行前端项目：端口：5173

访问应用程序：浏览器中输入：[http://localhost:5173访问](http://localhost:5173访问/)

**后台：**

管理员登录：http://localhost:5173/login

### 贡献者

- [爽爽同学](https://github.com/Web-Wss) — 主要开发者

### 联系方式

如有任何疑问或建议，欢迎联系项目主要开发者：

- 电子邮箱：[1306822660@qq.com](mailto:1306822660@qq.com)

# 平台部分截图

![image-20240719130455452](/img/image-20240719130455452.png)

![image-20240719130522571](/img/image-20240719130522571.png)

![image-20240719130545132](/img/image-20240719130545132.png)

![image-20240719130601368](/img/image-20240719130601368.png)

![image-20240719130611643](/img/image-20240719130611643.png)