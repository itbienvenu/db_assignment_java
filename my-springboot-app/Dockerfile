# Use Eclipse Temurin 17 Alpine as base (replaces openjdk:17-jdk-slim which is unavailable)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory to /app
WORKDIR /app

# Copy the built JAR to app.jar
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
