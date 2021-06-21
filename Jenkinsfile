pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '--rm -v /var/jenkins_home/maven/.m2:/var/jenkins_home/maven/.m2'
        }

      }
      steps {
        dir(path: '${params.workspace}/base-cloud_main') {
          sh 'mvn -B -Dmaven.test.skip=true -Dmaven.repo.local=/var/jenkins_home/maven/.m2/repository -pl base-admin clean package -am -amd'
          echo 'Maven Build Success!'
        }

      }
    }

    stage('Deliver') {
      steps {
        sh 'sh ./base-admin/deliver.sh'
      }
    }

  }
}