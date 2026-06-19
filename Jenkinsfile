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
            choices: ['main'],  // перечисли свои ветки
            description: 'Выбери ветку для запуска тестов'
        )
        choice(
            name: 'TEST_GROUPS',
            choices: ['all', 'regression'],
            description: 'Выбери группу тестов для запуска (all — запустить все тесты)'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Выбери браузер для запуска тестов'
        )
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
        DEMOBLAZE_CREDS = credentials('demoblaze-user')
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
                bat """mvn test \
                    -Dbrowser=${params.BROWSER} \
                    -Dlogin=%DEMOBLAZE_CREDS_USR% \
                    -Dpass=%DEMOBLAZE_CREDS_PSW% \
                    ${params.TEST_GROUPS == 'all' ? '' : '-Dgroups=' + params.TEST_GROUPS}"""
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}