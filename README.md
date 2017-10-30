#servers
=====
    | 代码地址           | http://192.168.101.16/wuxiaogang/hsd-servers |          
    ---------------------------------------------------------------------------------------------------------
    |                    | http://192.168.101.100:8761/                 | admin/admin
    | 注册中心           | http://192.168.101.101:8761/                 | admin/admin
    |                    | http://192.168.101.102:8761/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
    | crapAPI            | http://192.168.101.100:10086/api             | 姓名pinyin/123456
    ---------------------------------------------------------------------------------------------------------
    | druid监控(服务层)  | http://ip:port/hsd/druidAdmin/               | 
    ---------------------------------------------------------------------------------------------------------
    | swagger(web层)     | http://ip:port/swagger-ui.html
    ---------------------------------------------------------------------------------------------------------
    | 应用监控中心       | http://192.168.101.100:8889/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
    | docker容器管理中心 | http://192.168.101.150:8890/                 | admin/admin-123
    ---------------------------------------------------------------------------------------------------------
    | docker私有仓库     | http://192.168.101.150/                      | admin/admin-123
    ---------------------------------------------------------------------------------------------------------
    | jekins             | http://192.168.101.150:8080/                 | admin/admin
    ---------------------------------------------------------------------------------------------------------
    | API网关            | http://192.168.101.100:28890/                | 
    ---------------------------------------------------------------------------------------------------------
    
    
#模块说明
=====
    hsd-servers                                                     | pom |   
        |--hsd-account                                              | pom |   父模块，不应直接使用
        |-   |--hsd-account-actor                                   | pom |   父模块，不应直接使用
        |-        |--hsd-account-actor-api                          | jar |   
        |-        |--hsd-account-actor-services                     | pom |   父模块，不应直接使用
        |-             |--hsd-account-actor-biz                     | jar |   
        |-             |--hsd-account-actor-dao                     | jar |   
        |-             |--hsd-account-actor-server                  | app |   
        |-        |--hsd-account-actor-web-boss                     | a+j |   
        |-        |--hsd-account-actor-web-portal                   | a+j |   
        |-   |--hsd-account-channel                                 | pom |   
        |-        |--hsd-account-channel-api                        | jar |   
        |-        |--hsd-account-channel-services                   | pom |   父模块，不应直接使用
        |-             |--hsd-account-channel-biz                   | jar |   
        |-             |--hsd-account-channel-dao                   | jar |   
        |-             |--hsd-account-channel-server                | app |   
        |-        |--hsd-account-channel-web-boss                   | a+j |   
        |-        |--hsd-account-channel-web-portal                 | a+j |   
        |-   |--hsd-account-platform                                | pom |   父模块，不应直接使用
        |-        |--hsd-account-platform-api                       | jar |   
        |-        |--hsd-account-platform-services                  | pom |   父模块，不应直接使用
        |-             |--hsd-account-platform-biz                  | jar |   
        |-             |--hsd-account-platform-dao                  | jar |   
        |-             |--hsd-account-platform-server               | app |   
        |-        |--hsd-account-platform-web-boss                  | a+j |   
        |-        |--hsd-account-platform-web-portal                | a+j |   
        |-   |--hsd-account-staff                                   | pom |   父模块，不应直接使用
        |-        |--hsd-account-staff-api                          | jar |   
        |-        |--hsd-account-staff-services                     | pom |   父模块，不应直接使用
        |-             |--hsd-account-staff-biz                     | jar |   
        |-             |--hsd-account-staff-dao                     | jar |   
        |-             |--hsd-account-staff-server                  | app |   
        |-        |--hsd-account-staff-web-boss                     | a+j |   
        |--hsd-common                                               | pom |   父模块，不应直接使用
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
        |--hsd-util                                                 | pom |   父模块，不应直接使用
        |-   |--hsd-util-sms                                        | pom |   父模块，不应直接使用
        |-        |--hsd-util-sms-services                          | pom |   父模块，不应直接使用
        |-             |--hsd-util-sms-biz                          | jar |   
        |-             |--hsd-util-sms-dao                          | jar |   
        |-             |--hsd-util-sms-server                       | app |   
        |-        |--hsd-util-sms-web-boss                          | a+j |   
        |-        |--hsd-util-sms-web-portal                        | a+j |   
        |--hsd-servers                                              | pom |   父模块，不应直接使用
        |-   |--hsd-eycode-server                                   |     |   
        |-   |--hsd-app-starter                                     | pom |   父模块，不应直接使用
        |-        |--hsd-service-server                             | app |   
        |-        |--hsd-web-boss-server                            | app |   
        |-        |--hsd-web-portal-server                          | app |   
        |-   |--hsd-server-starter                                  | pom |   父模块，不应直接使用
        |-        |--hsd-config-server                              | app |   
        |-        |--hsd-eureka-server                              | app |   
        |-        |--hsd-file-server                                | app |   
        |-        |--hsd-gateway-server                             | app |   
        |-        |--hsd-health-server                              | app |   