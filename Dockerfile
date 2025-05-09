FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package spring-boot:repackage -DskipTests


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SECURITY_JWT_SECRET="lo2JquwqHcflyBYlCr1y34kySZEYnQ4p9++U2uNYxkk="
ENV SECURITY_JWT_EXPIRATION_MS=3600000

ENTRYPOINT ["java", "-jar", "app.jar"]
