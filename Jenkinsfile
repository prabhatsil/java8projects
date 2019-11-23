#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('build') {
            steps {
                echo "Running java ${env.JAVA_HOME} on ${env.JENKINS_URL}"

                sh 'mvn -B -DskipTests clean package'
             }
        }
  }
}