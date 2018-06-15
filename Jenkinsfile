pipeline {
  agent none
  stages {
    stage('clone') {
      steps {
        git(url: 'https://github.com/fuchenlin/wintop-ms', branch: 'wintop-ms-20180326', changelog: true, credentialsId: '463967e4-0dec-4ee3-be08-a3ed3b43cf67')
      }
    }
  }
}
