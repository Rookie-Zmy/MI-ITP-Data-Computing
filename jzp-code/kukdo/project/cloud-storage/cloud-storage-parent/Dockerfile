FROM openjdk:17-jdk-slim

WORKDIR /app

COPY cloud-storage-web/target/cloud-storage-web-0.3-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]