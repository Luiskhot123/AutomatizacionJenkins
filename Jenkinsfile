pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'
    }

    environment {
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner'
    }

    stages {
        stage('Compilar') {
            steps {
                bat 'mvn clean package'
                currentBuild.result = 'UNSTABLE'
            }
        }

        stage('Análisis SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat "${SONAR_SCANNER_HOME}\\bin\\sonar-scanner.bat"
                }
            }
        }

        

        stage('Desplegar según resultado') {
            steps {
                script {
                    if (currentBuild.result == 'UNSTABLE') {
                        echo '⚠️ Calidad de código NO aprobada. Desplegando en entorno de pruebas...'
                        bat 'copy target\\*.war C:\\Users\\luisk\\tomcat-pruebas\\webapps\\app.war'
                        bat 'echo Notificación: El código tiene errores. Desplegado en pruebas.'
                    } else {
                        echo '✅ Calidad de código aprobada. Desplegando en producción...'
                        bat 'copy target\\*.war C:\\Users\\luisk\\tomcat-produccion\\webapps\\app.war'
                        bat 'echo Notificación: Código limpio. Desplegado en producción.'
                    }
                }
            }
        }
    }
}


