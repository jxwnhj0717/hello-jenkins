pipeline {
    agent {
        docker {
          image 'gradle:7.0.2-jdk8-hotspot'
          args '-v gradle_home:/home/gradle/.gradle'
        }

    }
    stages {
        stage('build') {
          steps {
            sh 'gradle build -i'
            sh 'ls build/test-reports/test'
          }
        }
    }
    post {
        always {
            junit 'build/test-reports/**/*.xml'
        }
    }
}