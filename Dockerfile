
FROM adoptopenjdk/openjdk11
COPY target/achat-4.5.jar achat-4.5.jar
ENTRYPOINT ["java","-jar","/achat-4.5.jar"]

