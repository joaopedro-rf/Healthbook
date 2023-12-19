FROM maven:3.9.5 AS build
COPY Healthbook .
RUN mvn clean package -DskipTests

FROM openjdk:20-jdk
COPY --from=build /target/Healthbook-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

