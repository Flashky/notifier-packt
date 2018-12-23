pipeline {
	agent any
	tools {
		maven 'M3_Jenkins' 
	}
	stages {
		
		stage('Clone Repository') {
			steps {
				// Get some code from a GitHub repository
				git 'https://github.com/Flashky/notifier-packt.git'
			}
			post {
				success {
					environment {
						IMAGE = readMavenPom().getArtifactId()    //Use Pipeline Utility Steps
						VERSION = readMavenPom().getVersion()
					}
				}
			}

		}

		stage('Build') {
			steps {
				sh 'mvn -f notifier-packt/pom.xml install -DskipTests'
			}
		}
		
		// TODO Add Nexus deployment in a future
		// will need to add the target repository at the pom file
		//stage('Nexus deploy') {
		//	steps {
		//		sh 'mvn -f notifier-packt/pom.xml deploy'
		//	}
		//}
		
		stage('Docker image build') {
			steps {

				echo "${VERSION}"
				//sh 'docker build -t flashk/notify-watchers:0.0.1 .'
			}
		}
	}
}