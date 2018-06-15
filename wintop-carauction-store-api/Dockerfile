#
# Dockerfile for wintop-carauction-store-api
#

# 基于哪个镜像
FROM jdk:8
MAINTAINER Mike
# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器，也可以直接写成ADD  wintop-carauction-store-api-0.0.1-SNAPSHOT.jar /app.jar
COPY  wintop-carauction-store-api-0.0.1-SNAPSHOT.jar /home/ms/service.jar

RUN bash -c 'touch /home/ms/service.jar'

# 开放8088
EXPOSE 8088

# 配置容器启动后执行的命令
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/ms/service.jar",">/home/ms/service.log &"]