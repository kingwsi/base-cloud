pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '''--name maven -v /var/jenkins_home/maven/.m2:/var/jenkins_home/maven/.m2
'''
    }

  }
  stages {
    stage('maven package') {
      steps {
        sh 'mvn -B -Dmaven.test.skip=true -Dmaven.repo.local=/var/jenkins_home/maven/.m2/repository -pl base-admin clean package -am -amd'
      }
    }

    stage('Restart Application') {
      steps {
        sh '''if [[ ! -d ./apps ]]; then
mkdir ./apps
else
echo "apps exist"
fi'''
        sh '''sp_pid=`ps -ef | grep admin-server | grep -v grep | awk \'{print $2}\'`
if [ -z "$sp_pid" ];
then
  echo "[ not find admin-server pid ]"
else
  echo "find result: $sp_pid "
  kill -9 $sp_pid
fi'''
        sh 'cp base-admin/target/base-admin-1.0-SNAPSHOT.jar ./apps/admin-server.jar'
        dir(path: 'apps') {
          sh 'java -jar -Dname=admin-server -Duser.timezone=Asia/Shanghai -Xms128M -Xmx256M -XX:MaxNewSize=128M admin-server.jar --spring.profiles.active=test > admin.log 2>&1 &'
        }

      }
    }

  }
}