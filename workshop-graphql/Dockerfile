# Use a Maven image with OpenJDK 17 for building
FROM maven:3.8-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml to download dependencies
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the project files
COPY . .

# Package the application
RUN ./mvnw clean package

# Use an OpenJDK image for running the application
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
