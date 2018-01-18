#servers
=====
    | 代码地址           | http://192.168.108.16/file/hsd-servers |          
    ---------------------------------------------------------------------------------------------------------
    |                    | http://192.168.108.100:8761/                 | admin/admin
    | 注册中心           | http://192.168.108.101:8761/                 | admin/admin
    |                    | http://192.168.108.102:8761/                 | admin/admin          
    ---------------------------------------------------------------------------------------------------------
    |                    | http://192.168.108.100:6379/                 | 
    | redis              | http://192.168.108.101:6379/                 | 
    |                    | http://192.168.108.102:6379/                 | 
    ---------------------------------------------------------------------------------------------------------
    |                    | http://192.168.108.100:15672/                | admin/admin-123
    | rabbitMQ           | http://192.168.108.101:15672/                | admin/admin-123
    |                    | http://192.168.108.102:15672/                | admin/admin-123
    ---------------------------------------------------------------------------------------------------------
    | crapAPI            | http://192.168.108.100:10086/api             | 姓名pinyin/123456
    ---------------------------------------------------------------------------------------------------------
    | druid监控(服务层)  | http://ip:port/hsd/druidAdmin/               | 
    ---------------------------------------------------------------------------------------------------------
    | swagger(web层)     | http://ip:port/swagger-ui.html               |                                    
    ---------------------------------------------------------------------------------------------------------
    | 应用监控中心       | http://192.168.108.100:8889/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
    | docker容器管理中心 | http://192.168.108.150:8890/                 | admin/admin-123
    ---------------------------------------------------------------------------------------------------------
    | docker私有仓库     | http://192.168.108.150/                      | admin/admin-123
    ---------------------------------------------------------------------------------------------------------
    | jekins             | http://192.168.108.150:8080/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
    | API网关            | http://192.168.108.100:28890/                | 
    ---------------------------------------------------------------------------------------------------------
    | 服务链路追踪       | http://192.168.108.101:9411/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
      
    
#模块说明
=====
      servers                                                       | pom |   
        |--hsd-account                                              | pom |   #用户域#父模块，不应直接使用
        |-   |--hsd-account-actor                                   | pom |   #客户管理# 父模块，不应直接使用
        |-        |--hsd-account-actor-api                          | jar |      服务api接口,兼FeignClient客户端
        |-        |--hsd-account-actor-services                     | pom |      业务层 父模块，不应直接使用
        |-             |--hsd-account-actor-biz                     | jar |         业务处理实现类
        |-             |--hsd-account-actor-dao                     | jar |         数据持久层
        |-             |--hsd-account-actor-server                  | app |         基础服务->启动类
        |-        |--hsd-account-actor-web-boss                     | a+j |       boss系统rest接口  控制层
        |-        |--hsd-account-actor-web-portal                   | a+j |       portal系统rest接口 控制层
        |-   |--hsd-account-channel                                 | pom |   #渠道商管理#
        |-        |--hsd-account-channel-api                        | jar |   
        |-        |--hsd-account-channel-services                   | pom |   父模块，不应直接使用
        |-             |--hsd-account-channel-biz                   | jar |   
        |-             |--hsd-account-channel-dao                   | jar |   
        |-             |--hsd-account-channel-server                | app |   
        |-        |--hsd-account-channel-web-boss                   | a+j |   
        |-        |--hsd-account-channel-web-portal                 | a+j |   
        |-   |--hsd-account-finance                                 | pom |   父模块，不应直接使用
        |-        |--hsd-account-finance-api                        | jar |   
        |-        |--hsd-account-finance-services                   | pom |   父模块，不应直接使用
        |-             |--hsd-account-finance-biz                   | jar |   
        |-             |--hsd-account-finance-dao                   | jar |   
        |-             |--hsd-account-finance-server                | app |   
        |-        |--hsd-account-finance-web-boss                   | a+j |   
        |-        |--hsd-account-finance-web-portal                 | a+j |   
        |-   |--hsd-account-staff                                   | pom |   #员工管理# 父模块，不应直接使用
        |-        |--hsd-account-staff-api                          | jar |   
        |-        |--hsd-account-staff-services                     | pom |   父模块，不应直接使用
        |-             |--hsd-account-staff-biz                     | jar |   
        |-             |--hsd-account-staff-dao                     | jar |   
        |-             |--hsd-account-staff-server                  | app |   
        |-        |--hsd-account-staff-web-boss                     | a+j |   
        |--hsd-common                                               | pom |   #通用服务# 父模块，不应直接使用
        |-   |--hsd-common-util-excel                               | jar |   
        |-   |--hsd-common-util-sms                                 | jar |   
        |--hsd-framework-starter                                    | pom |   架构父模块，不应直接使用
        |-   |--hsd-framework-amqp                                  | jar |   消息队列依赖与工具配置,可直接使用
        |-   |--hsd-framework-api                                   | jar |   服务api依赖与工具配置,可直接使用
        |-   |--hsd-framework-cache                                 | jar |   缓存依赖与配置,session,分布式id工具类,,可直接使用
        |-   |--hsd-framework-core                                  | jar |   架构核心,直接使用
        |-   |--hsd-framework-rule                                  | jar |   规则引擎依赖与工具配置,可直接使用
        |-   |--hsd-framework-server                                | jar |   分布式服务依赖与配置,可直接使用
        |-   |--hsd-framework-service                               | jar |   数据层等依赖与配置,可直接使用
        |-   |--hsd-framework-web                                   | jar |   后端模板控制层,可直接使用
        |-   |--hsd-framework-web-restful                           | pom |   前后端分离restful控制层,可直接使用
        |--hsd-msg                                                  | pom |   #工具服务#父模块，不应直接使用
        |-   |--hsd-msg-sms                                         | pom |   #短信服务#父模块，不应直接使用
        |-        |--hsd-msg-sms-api                                | pom |   
        |-        |--hsd-msg-sms-services                           | pom |   父模块，不应直接使用
        |-             |--hsd-msg-sms-biz                           | jar |   
        |-             |--hsd-msg-sms-dao                           | jar |   
        |-             |--hsd-msg-sms-server                        | app |   
        |-        |--hsd-msg-sms-web-boss                           | a+j |   
        |-        |--hsd-msg-sms-web-portal                         | a+j |   
        |--hsd-servers                                              | pom |   父模块，不应直接使用
        |-   |--hsd-eycode-server                                   | app |   #简单代码生成器#
        |-   |--hsd-app-starter                                     | pom |   #业务app集中部署#父模块，不应直接使用
        |-        |--hsd-service-server                             | app |   #服务集中部署#
        |-        |--hsd-web-boss-server                            | app |   #boss接口集中部署#
        |-        |--hsd-web-portal-server                          | app |   #portal接口集中部署#
        |-   |--hsd-server-starter                                  | pom |   #功能服务器端app#父模块，不应直接使用
        |-        |--hsd-config-server                              | app |   #config分布式配置分发#
        |-        |--hsd-eureka-server                              | app |   #eureka注册中心#
        |-        |--hsd-file-server                                | app |   #简单文件上传服务器#
        |-        |--hsd-gateway-server                             | app |   #接口网关#
        |-        |--hsd-health-server                              | app |   #应用监控中心#
        
        
#代码包说明
=====
    -----------------------------------------------------------------
    |  ==java==                                         |
    -----------------------------------------------------------------
    |- com.hsd                                          |   
    -----------------------------------------------------------------
           |- framework                                 |   架构层
               |- annotation                            |   自定义注解
               |- aspect                                |   自定义注解实现
                    |- auth                             |   权限
               |- config                                |   配置
               |- exception                             |   异常
               |- lock                                  |   分布式锁
               |- net                                   |   http连接工具
               |- security                              |   数据加密/解密
               |- util                                  |   工具类
                    |- resource                         |   
               |- dto                                   |   
               |- service                               |   业务实现类封装
               |- cache                                 |   
                    |- config                           |   
                    |- redis                            |   
                    |- util                             |   
               |- entity                                |   
               |- filter                                |   过滤器
               |- handler                               |   
               |- interceptor                           |   拦截器
               |- listener                              |   监听器
               |- thread                                |   线程池
               |- web                                   |   
                    |- servlet                          |   
                    |- tag                              |   自定义后台模板标签
                    |- controller                       |   
    ------------------------------------------------------------------               
           |- common                                    |   通用功能
                |- util                                 |   工具类
                    |- excel                            |
    ------------------------------------------------------------------      
           |- account                                   |   领域包
                |- staff                                |   功能包
                    |- api                              |   业务接口/feign服务调用接口
                    |- dto                              |   数据传输对象
                    |- aspect                           |   自定义注解切面实现
                    |- service                          |   业务处理实现
                    |- task                             |   定时任务
                    |- dao                              |   数据库接口
                    |- entity                           |   数据库实体
                    |- web                              |   
                        |- controller                   |   
    ------------------------------------------------------------------      
    | ==resources==                                     |
    ------------------------------------------------------------------      
    |- mybatis                                          |   mybatis mapper.xml
        |- account                                      |
            |- staff                                    |
    ------------------------------------------------------------------                  