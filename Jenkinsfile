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
				// Remove previous jars
				//sh 'rm -f target/notifier-packt*.jar'
				//sh 'rm -f *.jar'
				
				// Build
				sh 'mvn -f pom.xml clean install'
				
			}
		}
		
		stage('Report tests results') {
			environment {
				CODACY_PROJECT_TOKEN = "${env.CODACY_PROJECT_TOKEN_NOTIFIER_PACKT}"
			}
			steps {
				sh 'curl -Ls -o codacy-coverage-reporter-assembly.jar "https://dl.bintray.com/codacy/Binaries/6.0.0/codacy-coverage-reporter-assembly.jar"'
				sh 'java -jar codacy-coverage-reporter-assembly.jar report -l Java -r target/site/jacoco/jacoco.xml'
			}
		}
		
		stage('Docker image build') {
			environment {
				// Use Pipeline Utility Steps
				// Also, approve the scripts: 
				// Manage Jenkins > In-process Script Approval
				IMAGE = readMavenPom().getArtifactId()    //Use Pipeline Utility Steps
				VERSION = readMavenPom().getVersion()
			}
			steps {
                sh "docker build -t flashk/${IMAGE}:${VERSION} ."
			}
		}
	}
}