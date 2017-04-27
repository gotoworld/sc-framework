#配置分发

http://localhost:8888/gateway/default/master
http://localhost:8888/${appname}/${profile}/${label}

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties