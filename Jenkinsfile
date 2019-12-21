pipeline {
    agent any
    stages {
        stage('Build Image') {
            steps {
                sh 'docker build -t="gokul/rest-api-test" .'
            }
        }
        stage('Push Image') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]){
                sh "docker login --username=${user} --password=${pass}"
                sh "docker push gokul/rest-api-test:latest"
            }
          }
        }
        stage('Run tests') {
            steps {
                sh 'docker-compose up'
            }
        }
        stage('Shut down image') {
            steps {
                sh 'docker-compose down'
            }
        }
    }
}