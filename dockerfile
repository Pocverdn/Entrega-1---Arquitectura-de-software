FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/trabajo-0.0.1-SNAPSHOT.jar /app/trabajo.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/trabajo.jar"]