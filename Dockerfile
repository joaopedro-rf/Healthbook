FROM openjdk:20-jdk AS build

COPY . .

FROM openjdk:20-jdk
COPY --from=build /Healthbook/target/Healthbook-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demo.jar"]
