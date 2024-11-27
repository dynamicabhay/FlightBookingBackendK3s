
FROM deadshot007/flightbooking-service:v0.0.1

WORKDIR /app

COPY target/FlightBooking-0.0.1-SNAPSHOT.jar flightbooking-server.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/flightbooking-server.jar"]
