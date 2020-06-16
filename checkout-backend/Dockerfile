FROM openjdk:8-jdk-alpine
CMD gradle build
ARG JAR_FILE=target/*.jar
COPY build/libs/checkout-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
