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

                    sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
             }
        }
        stage('deploy') {
                    steps {
                            echo "Run the jar"
                            sh "java -jar ./target/java8-projects-1.0-SNAPSHOT.jar"
                     }
                }

  }
}

