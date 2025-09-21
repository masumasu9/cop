# Use an official Java runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the project using Maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package

# The command to run your jar
CMD ["java", "-jar", "target/place-1.0-SNAPSHOT.jar"]
