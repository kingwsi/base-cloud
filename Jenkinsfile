pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '--rm -v /var/jenkins_home/maven/.m2:/var/jenkins_home/maven/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -Dmaven.test.skip=true -Dmaven.repo.local=/var/jenkins_home/maven/.m2/repository -pl base-admin clean package -am -amd'
      }
    }

    stage('Deliver') {
      agent {
        node {
          label 'docker build'
        }

      }
      steps {
        sh 'sh ./base-admin/deliver.sh'
      }
    }

  }
}