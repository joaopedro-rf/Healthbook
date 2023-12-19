FROM openjdk:20-jdk AS build
WORKDIR /app


COPY . .
RUN ./mvnw clean install

FROM openjdk:20-jdk
WORKDIR /app

COPY --from=build /app/target/Healthbook-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080

RUN curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose \
    && chmod +x /usr/local/bin/docker-compose

COPY . .

CMD ["docker-compose", "up"]

ENTRYPOINT ["java", "-jar", "demo.jar"]

