FROM openjdk:8-alpine3.9
ARG JAR_FILE=target/Ticket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]