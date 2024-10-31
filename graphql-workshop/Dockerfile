FROM openjdk:17-jdk-slim

# Copy your application jar and the wait-for-it script
COPY target/app.jar /app/app.jar
COPY wait-for-it.sh /app/wait-for-it.sh

# Run your application with the wait-for-it script
ENTRYPOINT ["./wait-for-it.sh", "cassandra", "9042", "--", "java", "-jar", "app.jar"]

# Set the working directory
WORKDIR /app

# Copy the .jar file into the container
COPY target/graphql-workshop-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (optional, but typically 8080 for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "graphql-workshop-0.0.1-SNAPSHOT.jar"]
