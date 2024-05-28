# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the build jar file from the host to the container
COPY build/libs/*.jar app.jar

# Set the startup command to run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expose port 8080 to the outside world
EXPOSE 8080
