# Use a minimal JDK image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR from your local machine
COPY build/libs/*.jar app.jar

# Copy resources if needed at runtime
COPY src/main/resources /app/resources

ENTRYPOINT ["java", "-jar", "app.jar"]