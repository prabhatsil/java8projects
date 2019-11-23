#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('build') {
            steps {
                echo "Running java ${env.JAVA_HOME} on path ${env.PATH}"

                sh 'mvn -B -DskipTests clean package'
             }
        }
  }
}