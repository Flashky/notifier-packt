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
				// TODO figure out how to call readMavenPom() without needing to copy to parent directory.
				sh 'cp notifier-packt/pom.xml .'
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
				//pom = readMavenPom file: 'notifier-packt/pom.xml'
				//IMAGE = ${pom.artifactId}
				//VERSION = ${pom.version}
				
				// Use Pipeline Utility Steps
				// Also, approve the scripts: 
				// Manage Jenkins > In-process Script Approval
				IMAGE = readMavenPom().getArtifactId()    //Use Pipeline Utility Steps
				VERSION = readMavenPom().getVersion()
			}
			steps {
				echo "${IMAGE}"
				echo "${VERSION}"
                docker build -t "${IMAGE}":"${VERSION}" .
			}
		}
	}
}