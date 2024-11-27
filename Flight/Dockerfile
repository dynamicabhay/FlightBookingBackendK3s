
FROM deadshot007/flight-server:v0.0.1

WORKDIR /app

COPY target/Flight-0.0.1-SNAPSHOT.jar flight-server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/flight-server.jar"]
