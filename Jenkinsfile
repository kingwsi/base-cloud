pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '--name maven -v /var/jenkins_home/maven/.m2:/var/jenkins_home/maven/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -Dmaven.test.skip=true -Dmaven.repo.local=/var/jenkins_home/maven/.m2/repository -pl base-admin clean package -am -amd'
      }
    }

    stage('Deliver') {
      steps {
        sh './jenkins/deliver.sh'
      }
    }

  }
}