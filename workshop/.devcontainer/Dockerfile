# Use the official Microsoft Java 17 image as the base image
FROM mcr.microsoft.com/vscode/devcontainers/java:17

# Install Maven (if not already included in the base image)
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

ENV JDK_HOME=/usr/lib/jvm/java-17-openjdk-amd64


# Set the default user and work directory
USER vscode
WORKDIR /workspace
