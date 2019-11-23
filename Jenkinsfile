#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('build') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                sh 'mvn -B -DskipTests clean package'
             }
        }
  }
}