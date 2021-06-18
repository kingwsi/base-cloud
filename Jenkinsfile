pipeline {
  agent any
  stages {
    stage('maven package') {
      steps {
        sh 'docker run -it --rm --name my-maven -v /var/jenkins_home/maven/.m2:/root/.m2  -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-alpine mvn -B -DskipTests -pl base-admin clean compile -am -amd'
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
        dir(path: '%WORKSPACE%/applications') {
          sh 'cp %WORKSPACE%/base-admin/target/base-admin-1.0-SNAPSHOT.jar ./admin-server.jar'
          sh 'java -jar -Dname=admin-server -Duser.timezone=Asia/Shanghai -Xms128M -Xmx256M -XX:MaxNewSize=128M admin-server.jar --spring.profiles.active=test > admin.log 2>&1 &'
        }

      }
    }

  }
}