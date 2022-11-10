
FROM adoptopenjdk/openjdk11
COPY target/achat-3.5.jar achat-3.5.jar
ENTRYPOINT ["java","-jar","/achat-3.5.jar"]

