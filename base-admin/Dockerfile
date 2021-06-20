# 该镜像需要依赖的基础镜像
FROM openjdk:8-jre-alpine
# 将targer目录下的jar包复制到docker容器/home/springboot目录下面目录下面
ADD ./target/base-admin-1.0-SNAPSHOT.jar /home/springboot/base-admin-1.0-SNAPSHOT.jar
EXPOSE 8094
# 执行命令
ENTRYPOINT ["java","-jar","-Dname=admin-server","-Duser.timezone=Asia/Shanghai","-Xms128M","-Xmx256M","-XX:MaxNewSize=128M","/home/springboot/base-admin-1.0-SNAPSHOT.jar","--spring.profiles.active=test"]
# 指定维护者名称
MAINTAINER kingwsi@icloud.com
