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
			environment {
				pom = readMavenPom file: 'notifier-packt/pom.xml'
				//IMAGE = ${pom.artifactId}
				//VERSION = ${pom.version}
				//IMAGE = readMavenPom().getArtifactId()    //Use Pipeline Utility Steps
				//VERSION = readMavenPom().getVersion()
			}
			steps {

				echo "${pom.version}"
				//sh 'docker build -t flashk/notify-watchers:0.0.1 .'
			}
		}
	}
}