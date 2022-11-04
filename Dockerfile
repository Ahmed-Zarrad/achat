
FROM adoptopenjdk/openjdk11
COPY target/achat.jar achat.jar
ENTRYPOINT ["java","-jar","/achat.jar"]
