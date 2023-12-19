FROM maven:3.9.5 AS build
COPY . .

FROM openjdk:20-jdk
WORKDIR /app
COPY --from=build /Healthbook/target/Healthbook-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
