pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('maven package') {
      steps {
        sh 'mvn -f /pom.xml -B -DskipTests -pl base-admin clean package -am -amd'
      }
    }

  }
}