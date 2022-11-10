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

		stage ("Clean..."){
			steps{
				sh "mvn clean"
			}
			
		}
		stage("Compile"){
                    steps {
                        sh 'mvn compile -DskipTests'
                    }
                }

		stage ('Creation du livrable...'){
			steps{
				sh "mvn package -Dmaven.test.skip=true"
				sh 'mvn install'
			}
		}

		stage ('Lancement des Tests Unitaires...'){
			steps{
				sh "mvn test"
			}
		}

		stage ('Analyse avec Sonar...'){
			steps{
			withSonarQubeEnv(installationName: 'sonar'){
				sh "mvn sonar:sonar"
				}
			}
		}

		stage ('Deploiement dans Nexux...'){
			steps{
				sh "mvn -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true deploy:deploy-file -DgroupId=tn.esprit.rh -DartifactId=achat -Dversion=3.1 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.1.18:8081/repository/maven-releases/ -Dfile=target/achat-3.1.jar"
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

		stage('Cleaning up...'){
			steps{
				sh "docker rmi $registry:$BUILD_NUMBER"
			}
		}
			stage ('run container'){
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
