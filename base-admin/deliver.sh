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
# 构建镜像
docker build -t base-admin-server:latest ./base-admin/.
# 先删除之前的容器
echo "remove old container"
docker ps -a | grep base-admin-server | awk '{print $1}'| xargs docker stop
# 清理无效镜像<none>
docker image prune -f
# 打印当前镜像
echo "current docker images"
docker images | grep base-admin-server
# 启动容器
echo "start container"
docker run -d --rm --name base-admin-server -p 8094:8094 -v "$HOME/logs":/logs base-admin-server:latest
# 打印当前容器
echo "current container"
docker ps -a | grep base-admin-server
echo "star service success!"
