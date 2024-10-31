# Use an official Node.js runtime as the base image
FROM node:14

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if available) and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application files
COPY . .

# Expose port 4000 for the GraphQL server
EXPOSE 4000

# Command to start the Node.js server
CMD ["node", "index.js"]
