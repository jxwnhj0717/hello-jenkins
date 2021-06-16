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
            echo 'container id: ${Container.id}'
            sh 'gradle build -i'
          }
        }
    }
    post {
        always {
            echo 'container id: ${Container.id}'
            junit 'build/test-reports/**/*.xml'
        }
    }
}