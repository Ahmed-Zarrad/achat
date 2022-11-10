
FROM adoptopenjdk/openjdk11
COPY target/achat-3.4.jar achat-3.4.jar
ENTRYPOINT ["java","-jar","/achat-3.4.jar"]

