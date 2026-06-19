pipeline {
    agent any

    tools {
        maven 'Maven_3'
        allure 'Allure'
        jdk 'Java_21'
    }
    triggers {
        cron 'H/50 * * * *'
        pollSCM 'H/50 * * * *'
    }

    parameters {
        booleanParam(defaultValue: true, description: 'clean and run tests', name: 'CleanAndRunTests')
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
    }

    stages {
        // Извлекаем проект из Git
        stage('CHECKOUT') {
            when {
                expression {
                    return params.CleanAndRunTests
                }
            }
            steps {
                git branch: '${BRANCH_NAME}', credentialsId: 'b7091f56-1b3d-4643-ae6b-c9f616ede5e6', url: 'git@github.com:ProdamGarazh1996/DemoblazeAutotests.git'
            }
        }
        stage('CLEAN') {
            when {
                expression {
                    return params.CleanAndRunTests
                }
            }
            steps {
                bat "mvn clean"
            }
        }
        stage('TEST') {
            when {
                expression {
                    return params.CleanAndRunTests
                }
            }
            steps {
                bat "mvn test"
            }
        }
    }
    post {
        always {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
    }
}