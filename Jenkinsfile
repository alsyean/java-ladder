  
pipeline {
  agent any

    environment {
      AWS_ACCESS_KEY_ID = credentials('awsAccessKey')
      AWS_SECRET_ACCESS_KEY = credentials('awsScretAccessKey')
      AWS_DEFAULT_REGION = 'ap-northeast-2'
      HOME = '.' // Avoid npm root owned
    }


  stages {
    
     stage('Start') {
            agent any
            steps {
                slackSend (channel: '#jenkins', color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            }
      }
    
    stage('git clone'){

            agent any

            steps{
                echo 'git clone'

                git credentialsId: 'testJenkins',
                    branch: 'step3',
                    url: 'https://github.com/alsyean/java-ladder'
            }

            post {
                success {
                  echo 'successfully clone'
                  
                   slackSend (channel: '#jenkins', color: '#00FF00', message: "SUCCESSFUL : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                  
                }

                always {
                    echo 'i tired ...'
                }

                cleanup {
                    echo 'after all other post'
                }

                failure {
                    echo 'fail clone'
                    
                  slackSend (channel: '#jenkins', color: '#00FF00', message: "FAILED Git Clone")
                  
                    mail  to: 'doc_test@tmpbox.net',
                          subject: "Deploy Frontend Success",
                          body: "Successfully deployed frontend!"

                }
            }

    }   
    
    stage('SonarQube'){
      
      environment {
        scannerHome = tool 'SonarQubeScanner'
      }
      
      steps {
        
        withSonarQubeEnv('SonarQubeServer') { // Will pick the global server connection you have configured
          
           sh "${scannerHome}/bin/sonar-scanner -X"
          
        }
      }
      
      post {
        success {
          echo 'Successfully SonarQube'
          slackSend (channel: '#jenkins', color: '#00FF00',  message: "SonarQube 성공 : SonarQube Success")
        }
        
        failure {
          echo 'Fail SonarQube'
          slackSend (channel: '#jenkins', color: '#00FF00', message: "SonarQube 실패 : SonarQube Fail")
        }
      }
      
    }
    
    
    stage("Quality Gate") {
      steps {
        timeout(time: 1, unit: 'HOURS') {
        // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
        // true = set pipeline to UNSTABLE, false = don't
          waitForQualityGate abortPipeline: true
        }
      }
    }
    
    
  } 
}
