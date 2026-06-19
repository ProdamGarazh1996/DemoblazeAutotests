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
        activeChoice(
            name: 'BRANCH_NAME',
            description: 'Выбери ветку для запуска тестов',
            choiceType: 'PT_SINGLE_SELECT',
            script: [
                $class: 'GroovyScript',
                script: [
                    $class: 'SecureGroovyScript',
                    sandbox: false,
                    script: '''
                    def branches = []
                    try {
                      def cmd = "git ls-remote --heads git@github.com:ProdamGarazh1996/DemoblazeAutotests.git"
                      def proc = cmd.execute()
                      proc.text.eachLine { line ->
                      def match = line =~ /refs\\/heads\\/(.+)/
                      if (match) branches << match[0][1]
                      }
                    } catch (Exception e) {
                      branches = ['main']
                    }
                    return branches ?: ['main']
                    '''
                ]
            ]
        )
    }

    environment {
        MAVEN_OPTS = '-Dfile.encoding=UTF-8'
    }

    stages {
        stage('CHECKOUT') {
            steps {
                git branch: "${params.BRANCH_NAME}",
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