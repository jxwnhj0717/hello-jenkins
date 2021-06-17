pipeline {
    docker {
        image 'gradle:7.0.2-jdk8-hotspot'
        args '-v gradle_home:/home/gradle/.gradle'
    }
    stages {
        stage('构建') {
            steps {
                sh 'docker version'
                sh 'gradle build -i'
                stash includes: 'build/libs/**/*.jar', name: 'app'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                    junit 'build/test-results/**/*.xml'
                }
            }
        }
    }
}