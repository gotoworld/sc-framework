#servers
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
    hsd-servers                                                         |   
        |--hsd-account                                                  |   
        |-   |--hsd-account-actor                                       |   
        |-        |--hsd-account-actor-api                              |   
        |-        |--hsd-account-actor-services                         |   
        |-             |--hsd-account-actor-biz                         |   
        |-             |--hsd-account-actor-dao                         |   
        |-             |--hsd-account-actor-server                      |   
        |-        |--hsd-account-actor-web-boss                         |   
        |-        |--hsd-account-actor-web-portal                       |   
        |-   |--hsd-account-channel                                     |   
        |-        |--hsd-account-channel-api                            |   
        |-        |--hsd-account-channel-services                       |   
        |-             |--hsd-account-channel-biz                       |   
        |-             |--hsd-account-channel-dao                       |   
        |-             |--hsd-account-channel-server                    |   
        |-        |--hsd-account-channel-web-boss                       |   
        |-        |--hsd-account-channel-web-portal                     |   
        |-   |--hsd-account-platform                                    |   
        |-        |--hsd-account-platform-api                           |   
        |-        |--hsd-account-platform-services                      |   
        |-             |--hsd-account-platform-biz                      |   
        |-             |--hsd-account-platform-dao                      |   
        |-             |--hsd-account-platform-server                   |   
        |-        |--hsd-account-platform-web-boss                      |   
        |-        |--hsd-account-platform-web-portal                    |   
        |-   |--hsd-account-staff                                       |   
        |-        |--hsd-account-staff-api                              |   
        |-        |--hsd-account-staff-services                         |   
        |-             |--hsd-account-staff-biz                         |   
        |-             |--hsd-account-staff-dao                         |   
        |-             |--hsd-account-staff-server                      |   
        |-        |--hsd-account-staff-web-boss                         |   
        |--hsd-common                                                   |   
        |-   |--hsd-common-util-excel                                   |   
        |-   |--hsd-common-util-sms                                     |   
        |--hsd-framework-starter                                        |   
        |-   |--hsd-framework-amqp                                      |   
        |-   |--hsd-framework-api                                       |   
        |-   |--hsd-framework-cache                                     |   
        |-   |--hsd-framework-core                                      |   
        |-   |--hsd-framework-rule                                      |   
        |-   |--hsd-framework-server                                    |   
        |-   |--hsd-framework-service                                   |   
        |-   |--hsd-framework-web                                       |   
        |-   |--hsd-framework-web-restful                               |   
        |--hsd-util                                                     |   
        |-   |--hsd-util-sms                                            |   
        |-        |--hsd-util-sms-services                              |   
        |-             |--hsd-util-sms-biz                              |   
        |-             |--hsd-util-sms-dao                              |   
        |-             |--hsd-util-sms-server                           |   
        |-        |--hsd-util-sms-web-boss                              |   
        |-        |--hsd-util-sms-web-portal                            |   
        |--hsd-servers                                                  |   
        |-   |--hsd-eycode-server                                       |   
        |-   |--hsd-app-starter                                         |   
        |-        |--hsd-service-server                                 |   
        |-        |--hsd-web-boss-server                                |   
        |-        |--hsd-web-portal-server                              |   
        |-   |--hsd-server-starter                                      |   
        |-        |--hsd-config-server                                  |   
        |-        |--hsd-eureka-server                                  |   
        |-        |--hsd-file-server                                    |   
        |-        |--hsd-gateway-server                                 |   
        |-        |--hsd-health-server                                  |   