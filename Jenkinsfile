#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('build') {
            steps {
                    //echo "Running java ${env.JAVA_HOME} on path ${env.PATH}"
                    def mvnHome = tool name: 'Maven3', type: 'maven'
                    sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
             }
        }
  }
}

