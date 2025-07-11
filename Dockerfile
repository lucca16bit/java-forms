# Etapa de build
FROM gradle:8.7.0-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
