node('master') {
    stage('Code Checkout') {
        println("Entering Code checkout Stage")
        checkout scm
     }

    stage('Deploy') {
        println("Entering Deploy Stage")
        pushToCloudFoundry(
            target: 'api.system.dev.digifabricpcf.com',
            organization: 'sunil-khobragade',
            cloudSpace: 'sandbox',
            credentialsId: 'pcf-credential',
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
        )
    }
}