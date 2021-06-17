pipeline {
    agent none
    environment {
        registry = "jxwnhj0717/hello-jenkins"
        registryCredential = 'dockerhub'
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
                sh 'ls build/test-results/test'
                stash includes: 'build/libs/*.jar', name: 'app'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('构建并注册镜像') {
            agent any
            steps {
                unstash 'app'
                script {
                    docker.withRegistry('', registryCredential) {
                      docker.build(registry, "-f jenkins/Dockerfile build").push('latest')
                    }
                }
                sh 'docker images'
            }
        }
    }
}