# Use a Debian-based Maven image with apt-get
FROM maven:3.8-openjdk-17-slim

# Set the working directory inside the container
WORKDIR /workspace

# Install additional tools (curl and git)
RUN apt-get update && apt-get install -y curl git && rm -rf /var/lib/apt/lists/*

# Expose the application port (adjust as needed)
EXPOSE 8080
