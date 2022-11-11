
FROM adoptopenjdk/openjdk11
COPY target/achat-3.8.jar achat-3.8.jar
ENTRYPOINT ["java","-jar","/achat-3.8.jar"]

