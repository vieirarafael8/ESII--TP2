pipeline {
	agent any 
		stages {
			stage('Build') {
				steps {
					checkout scm
						sh './EXP/gradlew build'
					}
			}
		}
}