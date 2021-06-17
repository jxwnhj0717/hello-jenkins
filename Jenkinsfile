pipeline {
    agent any
    environment {
        PROJECT_NAME = 'hello-jenkins'
    }
    stages {
        stage('构建') {
            agent {
                docker {
                    image 'gradle:7.0.2-jdk8-hotspot'
                    args '-v gradle_home:/home/gradle/.gradle -v /tmp/jenkins/hello-jenkins:/tmp/jenkins/hello-jenkins'
                }
            }
            steps {
                sh 'gradle build -i'
                sh 'cp build/libs/**/*.jar /tmp/jenkins/hello-jenkins'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                    junit 'build/test-results/**/*.xml'
                }
            }
        }
        stage('构建镜像') {
            steps {
                sh 'ls /tmp/jenkins/hello-jenkins'
            }
        }
    }
}