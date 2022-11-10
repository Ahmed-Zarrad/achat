
FROM adoptopenjdk/openjdk11
COPY target/achat-3.2.jar achat-3.2.jar
ENTRYPOINT ["java","-jar","/achat-3.2.jar"]

