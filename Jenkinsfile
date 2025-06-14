pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'  // Asegúrate de tener Maven configurado en Jenkins
    }

    stages {
        stage('Compilar') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Desplegar en pruebas') {
            steps {
                sh 'cp target/*.war /opt/tomcat-test/webapps/app.war'
            }
        }
    }
}
