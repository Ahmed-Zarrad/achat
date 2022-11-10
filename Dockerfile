
FROM adoptopenjdk/openjdk11
COPY target/achat-3.0.jar achat-3.0.jar
ENTRYPOINT ["java","-jar","/achat-3.0.jar"]

