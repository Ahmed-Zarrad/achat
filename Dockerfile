
FROM adoptopenjdk/openjdk11
COPY target/achat-4.1.jar achat-4.1.jar
ENTRYPOINT ["java","-jar","/achat-4.1.jar"]

