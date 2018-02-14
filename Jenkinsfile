node('master') {
    stage('Code Checkout') {
        println("Entering Code checkout Stage")
        checkout scm
     }
    stage('Build') {
        println("Entering Build Stage")
         if (isUnix()) {
                println("UNIX Build Stage")
                sh './gradlew clean build'
            } else {
                println("WIN Build Stage")
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