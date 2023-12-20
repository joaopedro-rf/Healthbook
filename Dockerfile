FROM openjdk:20-jdk AS build
WORKDIR /app

COPY . .

FROM openjdk:20-jdk
WORKDIR /app

COPY --from=build /app/target/Healthbook-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

COPY . .

ENTRYPOINT ["java", "-jar", "demo.jar"]

