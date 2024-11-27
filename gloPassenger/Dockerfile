# Use an official OpenJDK base image
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
# If your Spring Boot application is packaged as a JAR, adjust the path to your JAR file
COPY target/Passenger-0.0.1-SNAPSHOT.jar passenger-service.jar

# Expose the port the application will run on
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/passenger-service.jar"]
