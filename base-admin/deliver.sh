pwd
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
docker run -d --rm --name base-admin-server -p 8094:8094  base-admin-server:latest
# 打印当前容器
echo "current container"
docker ps -a | grep base-admin-server
echo "star service success!"
