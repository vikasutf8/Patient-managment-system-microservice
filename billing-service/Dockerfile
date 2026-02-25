# ---------- Build Stage ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/billing-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082
EXPOSE 9092

ENTRYPOINT ["java", "-jar", "app.jar"]