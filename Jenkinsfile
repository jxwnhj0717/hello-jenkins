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
            publishHTML (target : [allowMissing: false,
             alwaysLinkToLastBuild: true,
             keepAll: true,
             reportDir: 'reports',
             reportFiles: 'myreport.html',
             reportName: 'My Reports',
             reportTitles: 'The Report'])
        }
    }
}