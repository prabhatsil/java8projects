#!/usr/bin/env
// Declarative //
pipeline {
  agent any
    stages {
        stage('init'){
            steps {
                def mvnHome = tool name: 'Maven3', type: 'maven'
            }
        }
        stage('build') {
            steps {
                    //echo "Running java ${env.JAVA_HOME} on path ${env.PATH}"
                    sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
             }
        }
  }
}

