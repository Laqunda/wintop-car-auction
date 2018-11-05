#
# Dockerfile for ${project.artifactId}
#

#step1
#基础镜像运行环境使用jre暂用空间小
#FROM java:openjdk-8-alpine
FROM java:openjdk-8-jre-alpine

#step2维护者信息
MAINTAINER Mike

#step3镜像的操作指令
ARG JAR_FILE
ARG LOG_FILE

#设置docker操作的当前目录
WORKDIR /home/msapps

# 将本地文件夹挂载到当前容器
#VOLUME /home/data/msappslogs

# 拷贝文件到容器,并创建微服务的work目录
ADD  ${JAR_FILE} apps/app.jar
#ADD  ""  work/${LOG_FILE}

# 开放端口
EXPOSE 8082

#指定环境变量

#设置jvm默认参数,可通过docker run运行容器是指定-e JAVA_OPTS覆盖
ENV LOG_FILE=${LOG_FILE}
ENV JAVA_OPTS="\
-server \
-Xmx512m \
-Xms256m \
-Xmn256m \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=50m \
-XX:MaxMetaspaceSize=100m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:work/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"
#ENV JAVA_OPTS=""
ENV SPRING_OPTS=""

#设置操作系统编码
ENV LANG C.UTF-8

#设置时区
#ENV M2_HOME="/home/data/maven"
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#step4配置容器启动后执行的命令
#每个容器只能执行一条CMD命令，多个CMD命令时，只最后一条被执行
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar apps/app.jar $SPRING_OPTS > work/$LOG_FILE

#手动构建镜像
#docker build --build-arg JAR_FILE='wintop-ms-eureka.jar' --build-arg LOG_FILE='wintop-ms-eureka.log' -t wintop-ms-eureka:1.0.0 .
#手动运行镜像
#linux平台 docker run -dti  -e JAVA_OPTS="-Xms128m  -Xmx512m -server" -e SPRING_OPTS="--spring.profiles.active=uat" -v /home/data/msappslogs:/home/msapps/work -p 11103:1000 --name 'wdp'   wintop-ms-eureka:1.0.0
#win平台 docker run -dti  -e JAVA_OPTS="-Xms128m  -Xmx512m -server" -e SPRING_OPTS="--spring.profiles.active=uat" -v C:\wintop\msappslogs:/home/msapps/work -p 11103:1000 --name 'wdp'   wintop-ms-eureka:1.0.0
