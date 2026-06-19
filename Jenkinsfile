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
        choice(
            name: 'BRANCH_NAME',
            choices: ['main', 'develop', 'feature/my-feature'],  // перечисли свои ветки
            description: 'Выбери ветку для запуска тестов'
        )
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
    }

    stages {
        stage('CHECKOUT') {
            steps {
                git branch: "${params.BRANCH_NAME}",   // <-- было '${BRANCH_NAME}' (не работало в обычном Pipeline)
                    credentialsId: 'b7091f56-1b3d-4643-ae6b-c9f616ede5e6',
                    url: 'git@github.com:ProdamGarazh1996/DemoblazeAutotests.git'
            }
        }
        stage('CLEAN') {
            steps {
                bat "mvn clean"
            }
        }
        stage('TEST') {
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