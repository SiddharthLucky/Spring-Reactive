# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the host machine into the container
COPY target/api-caller.jar api-caller.jar

# Expose port 8081 to the outside world
EXPOSE 8081

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "api-caller.jar"]

#docker run --name mysql_local -v mysql_vol:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=mappingops -p 3306:3306 mysql:latest