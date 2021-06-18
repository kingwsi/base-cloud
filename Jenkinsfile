pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v ~/maven/.m2:/root/.m2'
    }

  }
  stages {
    stage('maven package') {
      steps {
        sh 'mvn -B -DskipTests -pl base-admin clean compile -am -amd'
      }
    }

    stage('Restart Application') {
      steps {
        sh '''sp_pid=`ps -ef | grep admin-server | grep -v grep | awk \'{print $2}\'`
if [ -z "$sp_pid" ];
then
  echo "[ not find sp-tomcat pid ]"
else
  echo "find result: $sp_pid "
  kill -9 $sp_pid
fi'''
        dir(path: '~/base-admin-web_cloud/applications') {
          sh 'cp ~/base-admin-web_cloud/base-admin/target/base-admin-1.0-SNAPSHOT.jar ./admin-server.jar'
          sh 'java -jar -Dname=admin-server -Duser.timezone=Asia/Shanghai -Xms128M -Xmx256M -XX:MaxNewSize=128M admin-server.jar --spring.profiles.active=test > admin.log 2>&1 &'
        }

      }
    }

  }
}