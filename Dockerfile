
FROM adoptopenjdk/openjdk11
COPY target/achat-3.3.jar achat-3.3.jar
ENTRYPOINT ["java","-jar","/achat-3.3.jar"]

