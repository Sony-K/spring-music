node('master') {
    stage('Code Checkout') {
        println("Entering Code checkout Stage")
        checkout scm
     }
    stage('Build') {
        println("Entering Build Stage")
         if (isUnix()) {
                sh './gradlew clean build'
            } else {
                bat 'gradlew.bat clean build'
            }
    }
    stage('Test') {
        println("Entering Test Stage")
    }
    stage('Deploy') {
        println("Entering Deploy Stage")
    }
}