# ===== Build stage =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build and create an executable Spring Boot fat jar
RUN mvn -q -DskipTests clean package spring-boot:repackage \
 && sh -c 'JAR=$(ls target/*-SNAPSHOT.jar | grep -v "\.original$" | head -n1); echo "Using JAR: $JAR"; cp "$JAR" /app/app.jar'

# ===== Runtime stage =====
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/app.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
