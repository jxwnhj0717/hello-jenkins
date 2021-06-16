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
          }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/test-results/**/*.xml'
        }
    }
}