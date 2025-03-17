@Library('Ali-shared-library')_

pipeline {
    agent any

    environment {
        REPO_URL = "https://github.com/aliSharb/App3.git"
        BRANCH = "main"  // Adjust if needed
        APP_DIR = "App3"  // Directory where `App.java` exists
        TEST_DIR = "${APP_DIR}/src/test/java/com/example"
        DOCKER_IMAGE = "alisharb/my-app:latest"
        DOCKER_CONTEXT = "${APP_DIR}/"
        DOCKER_CREDENTIALS_ID = "docker-hub-credentials"
        KUBE_CONFIG_PATH = "~/.kube/config"
        KUBE_NAMESPACE = "default"
        KUBE_DEPLOYMENT_FILE = "${APP_DIR}/deployment.yaml"
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    sh "rm -rf ${APP_DIR}" // Ensure clean workspace
                    sh "git clone -b ${BRANCH} ${REPO_URL} ${APP_DIR}"
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                script {
                    RunUnitTest(TEST_DIR)
                }
            }
        }

        stage('Build Application') {
            steps {
                script {
                    buildApp(APP_DIR)
                }
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    buildPushDocker(DOCKER_IMAGE, DOCKER_CONTEXT, DOCKER_CREDENTIALS_ID)
                }
            }
        }

        stage('Deploy on Kubernetes') {
            steps {
                script {
                      deployOnK8s(KUBE_CONFIG_PATH, KUBE_NAMESPACE, KUBE_DEPLOYMENT_FILE, BUILD_TAG)
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'  // For Maven tests
            junit '**/build/test-results/test/*.xml'  // For Gradle tests
        }
    }
}
