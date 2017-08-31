# 基于哪个镜像
FROM java:8
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 拷贝文件到容器
ADD hsd-account-actor-web-portal-1.0.jar /app.jar
RUN bash -c 'touch /app.jar'
# 开放端口
EXPOSE 6066
# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]