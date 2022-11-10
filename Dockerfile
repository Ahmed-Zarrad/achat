
FROM adoptopenjdk/openjdk11
COPY target/achat-3.1.jar achat-3.1.jar
ENTRYPOINT ["java","-jar","/achat-3.1.jar"]

