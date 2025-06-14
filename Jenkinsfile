pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'
    }

    environment {
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner' // nombre exacto del scanner en Jenkins
    }

    stages {
        stage('Compilar') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Análisis SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {  // usa el mismo nombre del servidor configurado
                    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner"
                }
            }
        }

        stage('Esperar calidad del código') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Desplegar en pruebas') {
            steps {
                sh 'cp target/*.war /opt/tomcat-test/webapps/app.war'
            }
        }

        stage('Desplegar en producción') {
            steps {
                sh 'cp target/*.war /opt/tomcat-prod/webapps/app.war'
            }
        }

        stage('Notificar al programador') {
            steps {
                echo '✅ El pipeline finalizó correctamente y la app fue desplegada en producción.'
            }
        }
    }
}

