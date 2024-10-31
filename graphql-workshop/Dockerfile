# Use a valid Maven image with OpenJDK 17
FROM maven:3.8-openjdk-17-slim AS build

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

# Use the OpenJDK image for running the application
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
