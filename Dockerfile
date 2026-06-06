# Multi-stage Dockerfile for diet-analysis-service (Java 11)
# Build Stage
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
# Ensure Java source files do not contain BOMs that break the compiler
RUN apt-get update && apt-get install -y dos2unix \
	&& find src -type f -name '*.java' -exec dos2unix {} \; \
	&& mvn package -DskipTests -B

# Run Stage
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/diet-analysis-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082

# JVM tuning options for cloud-native containers
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:+UseContainerSupport", "-jar", "app.jar"]
