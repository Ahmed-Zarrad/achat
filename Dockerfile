
FROM adoptopenjdk/openjdk11
COPY target/achat-3.6.jar achat-3.6.jar
ENTRYPOINT ["java","-jar","/achat-3.6.jar"]

