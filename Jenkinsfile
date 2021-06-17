pipeline {
    agent none
    environment {
        PROJECT_NAME = 'hello-jenkins'
    }
    stages {
        stage('构建项目') {
            agent {
                docker {
                    image 'gradle:7.0.2-jdk8-hotspot'
                    args '-v gradle_home:/home/gradle/.gradle'
                }
            }
            steps {
                sh 'gradle --build-cache -i build'
                sh 'pwd'
                sh 'ls build/libs'
                sh 'ls build/test-results'
                sh 'ls build/test-results/test'
                stash includes: 'build/libs/*.jar', name: 'app'
            }
        }
        stage('构建并注册镜像') {
            agent any
            steps {
                unstash 'app'
                script {
                    docker.withRegistry('https://hub.docker.com/', 'dockerhub') {
                      docker.build("jxwnhj0717/hello-jenkins", "-f jenkins/Dockerfile build").push('latest')
                    }
                }
                sh 'docker images'
            }
        }
    }
}