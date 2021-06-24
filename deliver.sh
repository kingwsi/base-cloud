#!/bin/bash
MODULE_NAME=$1
declare -A MODULE_PORT_MAP=(["base-admin"]="8094" ["base-rest"]="8093" ["gateway"]="8088")
echo "module name: $MODULE_NAME"

# 模块端口
PORT=${MODULE_PORT_MAP[$MODULE_NAME]}
if [ -n "${PORT}" ]; then
  echo "错误的module name, 请检查参数"
  exit 8
fi
echo "检查自定义jre-alpine"
IID=$(docker images | grep new/openjdk:8-jre-alpine | awk '{print $3}')
if [ -n "$IID" ]; then
        echo "镜像new/openjdk:8-jre-alpine 已存在"
else
        echo "FROM openjdk:8-jdk-alpine" >> Dockerfile
        echo "RUN apk add --update --no-cache ttf-dejavu fontconfig && rm -rf /var/cache/apk/*" >> Dockerfile
        docker build -t new/openjdk:8-jre-alpine .
        echo "new/openjdk:8-jre-alpine构建完成"
        rm Dockerfile
fi

echo "准备$MODULE_NAME Dockerfile"
rm Dockerfile
{
echo "FROM new/openjdk:8-jre-alpine"
echo "ADD ./$MODULE_NAME/target/$MODULE_NAME-1.0-SNAPSHOT.jar /home/springboot/$MODULE_NAME-server.jar"
echo "EXPOSE ${PORT}"
echo "ENTRYPOINT [\"java\",\"-jar\",\"-Dname=$MODULE_NAME-server\",\"-Duser.timezone=Asia/Shanghai\",\"-Xms128M\",\"-Xmx256M\",\"-XX:MaxNewSize=128M\",\"/home/springboot/$MODULE_NAME-1.0-SNAPSHOT.jar\",\"--spring.profiles.active=test\"]"
echo "MAINTAINER kingwsi@icloud.com"
} >> Dockerfile
echo "Dockerfile准备完成"
cat Dockerfile

# 构建镜像
docker build -t "$MODULE_NAME-server":latest .
echo "停止旧服务"
docker ps -a | grep "$MODULE_NAME-server" | awk '{print $1}'| xargs docker stop
echo "清理无效镜像 <none>"
docker image prune -f
echo "启动容器..."
docker run -d --rm --name "$MODULE_NAME-server" -p "$PORT":"$PORT" -v "$HOME/logs":/logs "$MODULE_NAME-server":latest
# 是否启动成功
RES=$(docker ps | grep "$MODULE_NAME-server" | awk '{print $3}')
if [ -n "$RES" ]; then
    echo "$MODULE_NAME 已启动..."
else
    echo "$MODULE_NAME 启动失败！"
fi
echo "---------End---------"
