pipeline {
    agent any

    tools {
        maven 'Maven_3'
        allure 'Allure'
        jdk 'Java_21'
    }

    triggers {
        parameterizedCron('''
            H/15 * * * * % MODULE=ui-tests; BROWSER=firefox;   BRANCH_NAME=main
            H/20 * * * * % MODULE=api-tests; BROWSER=firefox;   BRANCH_NAME=main
        ''')
    }

    parameters {
        string(
            name: 'BRANCH_NAME',
            defaultValue: 'main',
            description: 'Введи ветку для запуска тестов'
        )
        string(
            name: 'TEST_GROUPS',
            defaultValue: 'all',
            description: 'Введи группу тестов (all, regression, smoke)'
        )
        choice(
            name: 'MODULE',
            choices: ['all', 'ui-tests', 'api-tests'],
            description: 'Выбери модуль для запуска'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Выбери браузер (только для ui-tests)'
        )
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
    }

    stages {
        stage('CHECKOUT') {
            steps {
        script {
            def branch = params.BRANCH_NAME.trim() == ''
                ? 'main'
                : params.BRANCH_NAME.trim()

            git branch: "${branch}",
                credentialsId: 'b7091f56-1b3d-4643-ae6b-c9f616ede5e6',
                url: 'git@github.com:ProdamGarazh1996/DemoblazeAutotests.git'
        }
            }
        }
        stage('CLEAN') {
            steps {
                bat "mvn clean"
            }
        }
        stage('TEST') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'demoblaze-user',
                    usernameVariable: 'USR',
                    passwordVariable: 'PSW'
                )]) {
                    script {
                        def mavenModule = params.MODULE == 'all'
                            ? '-pl ui-tests,api-tests -am'
                            : "-pl ${params.MODULE} -am"

                        def groups = (params.TEST_GROUPS == 'all' || params.TEST_GROUPS == '')
                            ? ''
                            : "-Dgroups=${params.TEST_GROUPS}"

                        def browser = params.MODULE == 'api-tests'
                            ? ''
                            : "-Dbrowser=${params.BROWSER}"

                        bat """mvn test ${mavenModule} \
                            ${browser} \
                            "-Dlogin=%USR%" \
                            "-Dpass=%PSW%" \
                            ${groups}"""
                    }
                }
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [
                [path: 'ui-tests/target/allure-results'],
                [path: 'api-tests/target/allure-results']
            ]
        }
    }
}