
FROM adoptopenjdk/openjdk11
COPY target/achat-3.7.jar achat-3.7.jar
ENTRYPOINT ["java","-jar","/achat-3.7.jar"]

