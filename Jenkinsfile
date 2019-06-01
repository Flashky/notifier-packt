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
				sh 'rm -f target/notifier-packt*.jar'
				sh 'rm -f *.jar'
				
				// Build
				sh 'mvn -f pom.xml install'
				
			}
		}
		
		stage('Report tests results') {
			steps {
				echo env.CODACY_PROJECT_TOKEN_NOTIFIER_PACKT
				sh 'CODACY_PROJECT_TOKEN=env.CODACY_PROJECT_TOKEN_NOTIFIER_PACKT'
				sh 'Token: ${CODACY_PROJECT_TOKEN}'
				sh 'curl -Ls -o codacy-coverage-reporter-assembly.jar "https://dl.bintray.com/codacy/Binaries/6.0.0/codacy-coverage-reporter-assembly.jar"'
				sh 'java -jar codacy-coverage-reporter-assembly.jar report -l Java -r build/reports/jacoco/test/jacocoTestReport.xml'
			}
		}
		
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
                sh "docker build -t flashk/${IMAGE}:${VERSION} ."
			}
		}
	}
}