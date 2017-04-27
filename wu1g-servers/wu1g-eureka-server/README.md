#服务发现
eureka是一个高可用的组件，它没有后端缓存，
每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成），
在默认情况下erureka server也是一个eureka client ,
必须要指定一个 server。eureka server的配置文件appication.yml：

server:
  port: 8761
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
通过eureka.client.registerWithEureka：false和fetchRegistry：false来表明自己是一个eureka server.

eureka server 是有界面的，启动工程,打开浏览器访问： 
http://localhost:8761

#创建一个服务提供者 (eureka client)
当client向server注册时，它会提供一些元数据，例如主机和端口，URL，主页等。Eureka server 从每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除。
通过注解@EnableEurekaClient 表明自己是一个eurekaclient. 且在配置文件中注明自己的服务注册中心的地址