FROM java:openjdk-8-alpine
MAINTAINER ligongzhaoyang@163.com
ARG JAR_FILE
WORKDIR /home
ADD  ${JAR_FILE} apps/app.jar
EXPOSE 8080
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
ENV SPRING_OPTS=""
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ  /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar apps/app.jar $SPRING_OPTS