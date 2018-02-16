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
                def currentpwd = pwd();
                echo " CURRENT DIRECTORY : ${currentpwd}"
                def currentpwdaft = currentpwd.replaceAll("\\\\", "/")
                echo " CURRENT DIRECTORY AFTER : ${currentpwdaft}"
                //bat "${currentpwdaft}/gradlew clean build -x test"
                //sh "ls -ltra"
                sh "${currentpwd}\\gradlew clean build -x test"
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
        when {
            expression {
                currentBuild.result = 'SUCCESS'
            }
        }   
        println("Entering Deploy Stage")
        /*pushToCloudFoundry(
            target: 'api.system.cumuluslabs.io',
            organization: 'nsreekala-PAL-JAN8',
            cloudSpace: 'sandbox',
            credentialsId: 'nanda-pcf',
            selfSigned: true, 
            pluginTimeout: 240, 
            servicesToCreate: [
              [name: 'music-database', type: 'p-mysql', plan: '100mb', resetService: true]
            ],
            envVars: [
              [key: 'FOO', value: 'bar']
            ],
            manifestChoice: [
                manifestFile: 'manifest.yml'
            ]
        ) */
        
        //pushToCloudFoundry cloudSpace: 'sandbox', credentialsId: 'nanda-pcf', organization: 'nsreekala-PAL-JAN8', selfSigned: true, servicesToCreate: [[name: 'music-database', plan: '100mb', resetService: true, type: 'p-mysql']], target: 'api.system.cumuluslabs.io'
        pushToCloudFoundry cloudSpace: 'sandbox', credentialsId: 'pcf-credential', organization: 'sunil-khobragade', pluginTimeout: 360, selfSigned: true, servicesToCreate: [[name: 'music-database', plan: '100mb', resetService: true, type: 'p-mysql']], target: 'api.system.dev.digifabricpcf.com'
    }
    
    stage('Notify'){
        prinln(currentBuild.result)        
    }
}
