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
                    sh 'gradlew clean build -x test'
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
    stage('Teardown'){
        println("Teardown PCF apps and services")
        try{
            withCredentials([usernamePassword(credentialsId: 'pcf-credential', passwordVariable: 'pass', usernameVariable: 'user')]) {
                sh '''
                    cf login -u $user -p $pass -a https://api.system.dev.digifabricpcf.com -o sunil-khobragade -s sandbox
                '''
                sh '''
                    cf delete spring-music -f
                '''
                sh '''
                    cf delete-service music-database -f
                '''
                echo "TEAR DOWN COMPLETE"
            }
            //bat 'call cf delete spring-music -f'
            //bat 'call cf delete-service music-database -f'
        } catch(err){
            currentBuild.result = 'FAILURE'
            echo "CF delete app/service failed"
            //println(e.getMessage())
            echo "Caught exception: ${err}"
            //throw err
            //step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'nandagopan.gs@cognizant.com 673326@cognizant.com', sendToIndividuals: false])
            //mail bcc: '', body: 'test jenkins ', cc: '', from: '', replyTo: '', subject: 'test', to: 'nandagopan.gs@cognizant.com'
        }
    }
    stage('Deploy') {
        println("Entering Deploy Stage")
         try{
               sh '''
                 cf create-service p-msql 100mb music-database
               '''
               sh '''
                  cf push
               '''
            } catch(err){
                echo "CF Deploy failed"
                echo "Caught exception: ${err}"
            }
        //pushToCloudFoundry cloudSpace: 'sandbox', credentialsId: 'pcf-credential', organization: 'sunil-khobragade', pluginTimeout: 360, selfSigned: true, servicesToCreate: [[name: 'music-database', plan: '100mb', resetService: true, type: 'p-mysql']], target: 'api.system.dev.digifabricpcf.com'
    }
}
