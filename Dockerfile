#
# Mvn Build
#
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /home/app

# Copy the Maven project file and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

#
# Jar Package
#
FROM eclipse-temurin:21-jre

COPY --from=build /home/app/target/moviemania-*.jar /usr/local/lib/moviemania.jar

# Expose the port your application runs on (e.g., 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/usr/local/lib/moviemania.jar"]