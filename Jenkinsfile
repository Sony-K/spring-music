node('master') {
    stage('Code Checkout') {
        println("Entering Code checkout Stage")
        checkout scm
    }
    stage('Build') {
        println("Entering Build Stage")
        try{
             if (isUnix()) {
                    println("UNIX Build Stage")
                    sh './gradlew clean build'
                } else {
                    println("WIN Build Stage using cygwin")
                    sh './gradlew clean build -x test'
                }
                 currentBuild.result = 'SUCCESS'
         }catch(err){
           currentBuild.result = 'FAILURE'
            echo "Gradle Build failed"
            echo "Caught exception: ${err}"
         }
    }
    stage('Test') {
        println("Entering Test Stage")
    }

}
