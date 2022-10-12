FROM openjdk:17-jdk-slim
ADD target/csv-db-import-*.jar csv-db-import.jar
ENTRYPOINT ["java", "-jar","csv-db-import.jar"]
EXPOSE 8080