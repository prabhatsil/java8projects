#!/usr/bin/env
// Declarative //
pipeline {
  agent any

    stages {

        stage('build') {
            environment {
                            mvnHome = tool name: 'Maven3', type: 'maven'
                        }
            steps {
                    //echo "Running java ${env.JAVA_HOME} on path ${env.PATH}"
                    sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
             }
        }
  }
}

