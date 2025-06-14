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
                bat 'mvn clean package'
            }
        }

        stage('Análisis SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {  // usa el mismo nombre del servidor configurado
                    bat "${SONAR_SCANNER_HOME}\\bin\\sonar-scanner.bat"
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
                bat 'copy target\\*.war C:\\Users\\luisk\\tomcat-pruebas\\webapps\\app.war'

            }
        }

        stage('Desplegar en producción') {
            steps {
                bat 'copy target\\*.war C:\\Users\\luisk\\tomcat-produccion\\webapps\\app.war'

            }
        }

        stage('Notificar al programador') {
            steps {
                bat 'echo Notificación completada'
            }
        }
    }
}

