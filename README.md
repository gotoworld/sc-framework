#servers

spring boot + redis 实现session共享

@EnableRedisHttpSession  

<dependency>  
        <groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-redis</artifactId>  
</dependency>  
<dependency>  
        <groupId>org.springframework.session</groupId>  
        <artifactId>spring-session-data-redis</artifactId>  
</dependency> 