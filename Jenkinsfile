pipeline {
  agent none
  stages {
    stage('Build') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '--rm -v /var/jenkins_home/maven/.m2:/var/jenkins_home/maven/.m2'
        }
      }
      steps {
        checkout scm
        sh 'mvn -B -Dmaven.test.skip=true -Dmaven.repo.local=/var/jenkins_home/maven/.m2/repository clean package'
        echo 'Maven Build Success!'
      }
    }

    stage('Deliver') {
      agent { node { label 'master' } }
      steps {
        sh 'sh ./deliver.sh base-admin'
      }
    }
  }
  options {
    skipDefaultCheckout(true)
  }
}