if [[ ! -d ./apps ]]; then
mkdir ./apps
else
echo "apps exist"
fi

echo 'copy to apps'
cp base-admin/target/base-admin-1.0-SNAPSHOT.jar ./apps/admin-server.jar

# stop server
sp_pid=`ps -ef | grep admin-server | grep -v grep | awk '{print $2}'`
if [ -z "$sp_pid" ];
then
  echo "[ not find admin-server pid ]"
else
  echo "find result: $sp_pid "
  kill -9 $sp_pid
fi

echo 'start application'
set -x
java -jar -Dname=admin-server -Duser.timezone=Asia/Shanghai -Xms128M -Xmx256M -XX:MaxNewSize=128M admin-server.jar --spring.profiles.active=test  > admin.log 2>&1 &
