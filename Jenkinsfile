pipeline{
	environment{
		registry = 'zarrad/achat'
		registryCredential= 'dockerId'
		dockerImage = ''
	}
	
	agent any 
	stages{
		stage ('Checkout GIT'){
			steps{
				echo 'Pulling...';
					git branch: 'main',
					url : 'https://github.com/Ahmed-Zarrad/achat',
					credentialsId: '01';
			}
		}

		stage ('Verification du  version Maven...'){
			steps{
				sh "mvn -version"
			}
		}

		stage ("Suppression du dossier tareget + Copie du livrable dans le Repository local"){
			steps{
				sh "mvn clean install"
			}
			
		}
		stage("Compile"){
                    steps {
                        sh 'mvn compile -DskipTests'
                    }
                }

		stage ('Création du livrable dans target'){
			steps{
				sh "mvn package"

			}
		}

		stage ('Lancement des Tests Unitaires...'){
			steps{
				sh "mvn test"
			}
		}

		stage ('Analyse avec Sonar...'){
			steps{
			script{
			withSonarQubeEnv(installationName: 'sonar'){
				sh "mvn sonar:sonar"
				    }
				}
			}
		}

		stage ('Deploiement dans Nexux...'){
			steps{
				sh "mvn deploy"
			}
		}

		stage('Building our image...'){
			steps{ 
				script{ 
					dockerImage= docker.build registry + ":$BUILD_NUMBER" 
				}
			}
		}

		stage('Deploy our image...'){
			steps{ 
				script{
					docker.withRegistry( '', registryCredential){
						dockerImage.push()
					} 
				} 
			}
		}


			stage ('docker-compose up'){
                        steps{

                                            sh 'docker-compose up -d --build'
                        }
                    }
}

	post{
		success{
			emailext body: 'Build success', subject: 'Jenkins', to:'ahmed.zarrad@esprit.tn'
		}
		failure{
			emailext body: 'Build failure', subject: 'Jenkins', to:'ahmed.zarrad@esprit.tn'
		}
	}


}
