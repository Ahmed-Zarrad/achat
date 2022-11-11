
FROM adoptopenjdk/openjdk11
COPY target/achat-3.9-SNAPSHOT.jar achat-3.9-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/achat-3.9-SNAPSHOT.jar"]

