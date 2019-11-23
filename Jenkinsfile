#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('build') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                sh 'mvn -B -DskipTests clean package'
             }
        }
  }
}